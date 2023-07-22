package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class LoginPage extends AbstractPage
{

    //region Поля и свойства

    @FindBy(id = "basic-nav-dropdown")
    private List<WebElement> navBar;

    @FindBy(css = "[class*=\"dropdown-menu\"]")
    private WebElement contextMenu;

    @FindBy(css = "input[name=email]")
    private WebElement loginInput;

    @FindBy(css = "input[name=password]")
    private WebElement passwordInput;

    @FindBy(css = "button[type=submit]")
    private WebElement submitBtn;

    //endregion

    //region Методы

    public void openOptionFromRibbon(String columnName, String optionContextName)
    {
        var option = navBar.stream().filter(opt -> opt.getText().equals(columnName)).findFirst().orElse(null);
        option.click();
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
        loginInput.clear();
        loginInput.sendKeys(text);
    }

    public void fillPasswordInput(String text)
    {
        passwordInput.clear();
        passwordInput.sendKeys(text);
    }

    public void submitForm()
    {
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

