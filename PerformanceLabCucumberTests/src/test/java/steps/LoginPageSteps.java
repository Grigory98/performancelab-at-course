package steps;

import core.DriversManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.UserPage;
import java.util.HashMap;

public class LoginPageSteps extends Steps
{
    @Given("DriverManager. Открыть браузер")
    public void openBrowser()
    {
        var config = DriversManager.config();
        DriversManager.current().get(config.baseUrl);
        LoginPage loginPage = new LoginPage();
        scContext.put(PageSteps.Context.Page, loginPage);

        //Скорость загрузки страницы
        var loadTime = DriversManager.getLoadPageTime();
        Allure.parameter("Page load speed", loadTime + "ms");
    }

    @Given("^LoginPage. Ввести логин пользователя (.+)")
    public void fillLogin(String login)
    {
        LoginPage loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        loginPage.fillLoginInput(login);
    }

    @Given("^LoginPage. Ввести пароль пользователя (.+)")
    public  void  fillPassword(String password)
    {
        LoginPage loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        loginPage.fillPasswordInput(password);
    }

    @When("^LoginPage. Войти в систему")
    public  void pressGoBtn()
    {
        LoginPage loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        loginPage.submitForm();
    }

    @And("^LoginPage. Войти в систему под пользователем (.+). Пароль: (.+)")
    public void logIn(String login, String password)
    {
        var loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        loginPage.fillLoginInput(login);
        loginPage.fillPasswordInput(password);
        loginPage.submitForm();
        String alertText = loginPage.getAlertText();
        if(!alertText.contains("Successful"))
            throw new RuntimeException("Unsecessful authorization");
        loginPage.dismissAlert();
    }

    @Then("LoginPage. Проверить, что авторизация произошла успешно")
    public void getSuccessfulAuthMessage()
    {
        var loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auths");
        loginPage.dismissAlert();
    }

    @And("LoginPage. Открыть список всех пользователей с ленты меню")
    public void openAllUsersGrid()
    {
        var loginPage = (LoginPage) scContext.get(PageSteps.Context.Page);
        loginPage.openOptionFromRibbon("Users", "Read all");
        scContext.put(PageSteps.Context.Page, new UserPage());
    }

    public LoginPageSteps(HashMap<String, Object> scContext) { super(scContext); }
}
