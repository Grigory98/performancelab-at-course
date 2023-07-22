package steps;

import Core.DriversManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.UserPage;
import java.util.HashMap;

public class LoginPageSteps extends Steps
{
    @Given("DriverManager. Открыть браузер")
    public void openBrowser()
    {
        DriversManager.getCurrent().get("http://77.50.236.203:4881/");
        LoginPage loginPage = new LoginPage();
        scContext.put(LoginPageSteps.Context.Page, loginPage);
    }

    @Given("^LoginPage. Ввести логин пользователя (.+)")
    public void fillLogin(String login)
    {
        LoginPage loginPage = (LoginPage) scContext.get(Context.Page);
        loginPage.fillLoginInput(login);
    }

    @Given("^LoginPage. Ввести пароль пользователя (.+)")
    public  void  fillPassword(String password)
    {
        LoginPage loginPage = (LoginPage) scContext.get(Context.Page);
        loginPage.fillPasswordInput(password);
    }

    @When("^LoginPage. Войти в систему")
    public  void pressGoBtn()
    {
        LoginPage loginPage = (LoginPage) scContext.get(Context.Page);
        loginPage.submitForm();
    }

    @And("^LoginPage. Войти в систему под пользователем (.+). Пароль: (.+)")
    public void logIn(String login, String password)
    {
        var loginPage = (LoginPage) scContext.get(Context.Page);
        loginPage.fillLoginInput(login);
        loginPage.fillPasswordInput(password);
        loginPage.submitForm();
        loginPage.dismissAlert();
    }

    @Then("LoginPage. Проверить, что авторизация произошла успешно")
    public void getSuccessfulAuthMessage()
    {
        var loginPage = (LoginPage) scContext.get(LoginPageSteps.Context.Page);
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auths");
        loginPage.dismissAlert();
    }

    @And("LoginPage. Открыть список всех пользователей с ленты меню")
    public void openAllUsersGrid()
    {
        var loginPage = (LoginPage) scContext.get(LoginPageSteps.Context.Page);
        loginPage.openOptionFromRibbon("Users", "Read all");
        scContext.put(LoginPageSteps.Context.Page, new UserPage());
    }

    public static class Context
    {
        public static final String Page = "Page";
    }

    public LoginPageSteps(HashMap<String, Object> scContext) { super(scContext); }
}
