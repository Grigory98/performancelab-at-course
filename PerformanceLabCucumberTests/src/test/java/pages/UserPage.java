package pages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import api.Api;
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
        var api = new Api();
        api.getToken();
        var users = api.getUsers();
        var minId = Arrays.stream(users).min(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();
        var maxId = Arrays.stream(users).max(Comparator.comparingInt(x -> x.id)).map(x -> x.id).orElseThrow();

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

    public int getUserIdByRowNum(int num)
    {
        List<WebElement> tds = getUserRowCells(num);
        return Integer.parseInt(tds.get(ID_COL).getText());
    }

    //endregion
}
