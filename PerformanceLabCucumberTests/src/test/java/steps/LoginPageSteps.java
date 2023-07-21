package steps;

import Core.DriversManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import java.util.HashMap;

public class LoginPageSteps extends Steps
{
    @Given("Открыть браузер")
    public void openBrowser()
    {
        DriversManager.getCurrent().get("http://77.50.236.203:4881/");
    }

    @And("Войти в систему под пользователем user@pflb.ru. Пароль: user")
    public void logIn()
    {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginInput("user@pflb.ru");
        loginPage.fillPasswordInput("user");
        loginPage.submitForm();
        //loginPage.dismissAlert();
        scContext.put(LoginPageSteps.Context.Page, loginPage);
    }

    @Then("Получить сообщение об успешной авторизации")
    public void getSuccessfulAuthMessage()
    {
        var loginPage = (LoginPage) scContext.get(LoginPageSteps.Context.Page);
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
    }

    public static class Context
    {
        public static final String Page = "Page";
    }

    public LoginPageSteps(HashMap<String, Object> scContext) { super(scContext); }
}
