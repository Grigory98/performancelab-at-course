package steps;

import Core.DriversManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginPageSteps
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
        loginPage.dismissAlert();
    }
}
