package pages;

import core.DriversManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage() {
        this.driver = DriversManager.getCurrent();
        this.wait = DriversManager.waitFor();
        PageFactory.initElements(driver, this);
    }

}
