package pages;

import core.DriversManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage
{
    protected WebDriver driver = DriversManager.getCurrent();
    protected WebDriverWait wait = DriversManager.waitFor();
}
