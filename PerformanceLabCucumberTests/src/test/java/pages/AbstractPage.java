package pages;

import Core.DriversManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage
{
    protected WebDriver driver = DriversManager.getCurrent();
    protected WebDriverWait wait = DriversManager.waitFor();
}
