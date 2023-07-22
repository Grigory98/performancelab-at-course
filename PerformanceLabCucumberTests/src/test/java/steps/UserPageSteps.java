package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.UserPage;
import java.util.HashMap;

public class UserPageSteps extends Steps
{
    @And("^UserPage. Отсортировать пользователей по ID в порядке (.+)")
    public void setSorting(String orderType)
    {
        var userPage = (UserPage) scContext.get(PageSteps.Context.Page);
        if(orderType == "возрастания") userPage.setSortingByID(true);
        else userPage.setSortingByID(false);
    }

    @And("UserPage. Сбросить сортировку")
    public  void resetSorting()
    {
        var userPage = (UserPage) scContext.get(PageSteps.Context.Page);
        userPage.cancelSorting("ID");
    }

    @Then("^UserPage. Проверить, что пользователи отсортированы в порядке (.+)")
    public void checkSorting(String orderType)
    {
        var userPage = (UserPage) scContext.get(PageSteps.Context.Page);
        if(orderType == "возрастания")
            Assertions.assertTrue(userPage.checkSortedGrid(true), "Sorting should be by ascending.");
        else
            Assertions.assertTrue(userPage.checkSortedGrid(false), "Sorting should be by descending");
    }

    public UserPageSteps(HashMap<String, Object> scContext) {
        super(scContext);
    }
}
