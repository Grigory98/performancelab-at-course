package steps;

import api.Api;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;

public class ApiSteps extends Steps {

    @Given("Api. Получить токен")
    public void GetToken()
    {
        var token = Api.getToken();
        scContext.put(Context.token, token);
    }

    @When("^Api. Проверить сортировку списка в порядке (.+)")
    public void sortUsers(String order)
    {
        boolean check;
        if(order.equals("возрастания"))
            check = Api.checkUsersSortAsc();
        else
            check = Api.checkUsersSortDesc();
        Assertions.assertTrue(check, "Проверить сортировку списка в порядке " + order);
    }

    public static class Context
    {
        public static final String token = "token";
    }

    //region Конструктор

    public ApiSteps(HashMap scContext) { super(scContext); }

    //endregion
}
