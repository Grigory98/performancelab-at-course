package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DriversManager {

    private static ChromeDriver _current;

    private static WebDriverWait _wait;

    public static WebDriver getCurrent() {
        if (_current == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            _current = new ChromeDriver(chromeOptions);
        }
        return _current;
    }

    public static WebDriverWait waitFor() {
        if(_wait == null) {
            _wait = new WebDriverWait(DriversManager.getCurrent(), Duration.ofSeconds(20));
        }
        return _wait;
    }
}
