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
    @Given("DriverManager. Открыть браузер")
    public void openBrowser()
    {
        DriversManager.getCurrent().get("http://77.50.236.203:4881/");
    }

    @And("^LoginPage. Войти в систему под пользователем (.+). Пароль: (.+)")
    public void logIn(String login, String password)
    {
        LoginPage loginPage = new LoginPage();
        loginPage.fillLoginInput(login);
        loginPage.fillPasswordInput(password);
        loginPage.submitForm();
        scContext.put(LoginPageSteps.Context.Page, loginPage);
    }

    @Then("LoginPage. Проверить, что авторизация произошла успешно")
    public void getSuccessfulAuthMessage()
    {
        var loginPage = (LoginPage) scContext.get(LoginPageSteps.Context.Page);
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
        loginPage.dismissAlert();
    }

    public static class Context
    {
        public static final String Page = "Page";
    }

    public LoginPageSteps(HashMap<String, Object> scContext) { super(scContext); }
}
