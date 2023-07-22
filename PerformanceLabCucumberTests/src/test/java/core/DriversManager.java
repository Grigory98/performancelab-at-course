package core;

import config.ApplicationConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DriversManager {

    private static ChromeDriver _current;

    private static WebDriverWait _wait;

    private static ApplicationConfig _config;

    public static ApplicationConfig config()
    {
        if(_config == null) {
            _config = new ApplicationConfig();
        }
        return _config;
    }

    public static WebDriver getCurrent()
    {
        if (_current == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            _current = new ChromeDriver(chromeOptions);
        }
        return _current;
    }

    public static void closeBrowser()
    {
        _wait = null;
        _current.quit();
        _current = null;
    }

    public static WebDriverWait waitFor()
    {
        if(_wait == null) {
            _wait = new WebDriverWait(DriversManager.getCurrent(), Duration.ofSeconds(20));
        }
        return _wait;
    }
}
