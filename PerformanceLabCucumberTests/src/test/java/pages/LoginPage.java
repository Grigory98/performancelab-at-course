package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class LoginPage extends AbstractPage
{
    //region Методы

    public void openOptionFromRibbon(String columnName, String optionContextName)
    {
        var navBar = driver.findElements(By.id("basic-nav-dropdown"));
        var option = navBar.stream().filter(opt -> opt.getText().equals(columnName)).findFirst().orElse(null);
        option.click();
        var contextMenu = driver.findElement(By.cssSelector("[class*=\"dropdown-menu\"]"));
        if(contextMenu.equals(null)) throw new RuntimeException("Контекстное меню не открылось.");

        var ctxMenuOptions = contextMenu.findElements(By.tagName("a"));
        var ctxMenuOption = ctxMenuOptions.stream().filter(opt -> opt.getText().equals(optionContextName)).findFirst().orElse(null);
        ctxMenuOption.click();
    }

    private Alert findAlert()
    {
        return wait.until(drv -> drv.switchTo().alert());
    }

    public void fillLoginInput(String text)
    {
        var loginInput = driver.findElement(By.cssSelector("input[name=email]"));
        loginInput.clear();
        loginInput.sendKeys(text);
    }

    public void fillPasswordInput(String text)
    {
        var passwordInput = driver.findElement(By.cssSelector("input[name=password]"));
        passwordInput.clear();
        passwordInput.sendKeys(text);
    }

    public void submitForm()
    {
        var submitBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        submitBtn.click();
    }

    public void dismissAlert()
    {
        findAlert().dismiss();
    }

    public String getAlertText()
    {
        return findAlert().getText();
    }

    //endregion
}

