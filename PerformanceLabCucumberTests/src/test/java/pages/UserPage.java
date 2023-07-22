package pages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends AbstractPage
{
    //region Поля и свойства

    @FindBy(css = "table tbody tr")
    private List<WebElement> userRows;

    private  static final int ID_COL = 0;

    //endregion

    //region Методы

    public void setSortingByID(boolean isAsc)
    {
        var idColumn = getSortButton("ID");
        try { Thread.sleep(5000); }
        catch(InterruptedException ie) {}
        if(isAsc)
            idColumn.click();
        else
        {
            idColumn.click();
            idColumn.click();
        }
    }

    public boolean checkSortedGrid(boolean isAsc)
    {
        boolean check;
        var numbers = this.getListUsersId();
        var minId =  Arrays.stream(numbers).min().getAsLong();
        var maxId = Arrays.stream(numbers).max().getAsLong();

        var userFirst = getUserIdByRowNum(0);
        var userLast = getUserIdByRowNum(userRows.size() - 1);

        if(isAsc)
            check = userFirst < userLast && userFirst == minId && userLast == maxId;
        else
            check = userFirst > userLast && userFirst != minId && userLast != maxId;

        return check;
    }

    public void cancelSorting(String buttonName)
    {
        while(true)
        {
            var btn = this.getSortButton(buttonName);
            var text = btn.getText().trim();
            if(text.length() != buttonName.length())
                btn.click();
            else
                return;
        }
    }

    private WebElement getSortButton(String buttonName)
    {
        var buttons = driver.findElements(By.tagName("button"));
        var button = buttons.stream().filter((opt) -> opt.getText().contains(buttonName)).findFirst().orElse(null);
        return button;
    }

    private List<WebElement> getUserRowCells(int num)
    {
        WebElement tableRow = userRows.get(num);
        return tableRow.findElements(By.cssSelector("td"));
    }

    //Это лучше положить в отдельный класс, содержащий API методы, но для простоты оставил тут.
    public long[] getListUsersId()
    {
        long[] usersIds = new long[userRows.size()];
        String query = "http://77.50.236.203:4879/users"; //запрос на получение users
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);
            connection.connect();

            StringBuilder sb = new StringBuilder();
            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));
                String line;
                while((line = in.readLine()) != null)
                    sb.append(line);

                Object obj = new JSONParser().parse(sb.toString());
                JSONArray jsonArray = (JSONArray) obj;

                for(int i = 0; i < jsonArray.size(); i++)
                {
                    var jsonObj = (JSONObject) jsonArray.get(i);
                    usersIds[i]= (long) jsonObj.get("id");
                }

            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch(Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return usersIds;
    }

    public int getUserIdByRowNum(int num)
    {
        List<WebElement> tds = getUserRowCells(num);
        return Integer.parseInt(tds.get(ID_COL).getText());
    }

    //endregion
}
