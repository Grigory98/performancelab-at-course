package steps;

import api.Api;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ApiSteps extends Steps {

    @Given("Api. Получить токен")
    public void GetToken()
    {
        var api = new Api();
        var token = api.getToken();
        scContext.put(Context.token, token);
        scContext.put(Context.api, api);
    }

    @When("^Api. Проверить сортировку списка в порядке (.+)")
    public void sortUsers(String order)
    {
        boolean check;
        var api = (Api) scContext.get(Context.api);
        if(order.equals("возрастания"))
            check = api.checkUsersSortAsc();
        else
            check = api.checkUsersSortDesc();
        Assertions.assertTrue(check, "Проверить сортировку списка в порядке " + order);
    }

    public static class Context
    {
        public static final String api = "api";
        public static final String token = "token";
    }

    //region Конструктор

    public ApiSteps(HashMap scContext) { super(scContext); }

    //endregion
}
