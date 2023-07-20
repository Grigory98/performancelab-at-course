package Core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriversManager {

    private static ChromeDriver _current;

    public static WebDriver getCurrent() {
        if (_current == null) {
            _current = new ChromeDriver();
        }
        return _current;
    }
}
