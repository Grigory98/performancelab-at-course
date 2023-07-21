package hooks;

import Core.DriversManager;
import config.ApplicationConfig;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Steps;

import java.util.HashMap;

public class Hooks extends Steps {

    public Hooks(HashMap<String, Object> scContext) {
        super(scContext);
    }

    private WebDriver driver = DriversManager.getCurrent();
    private ApplicationConfig config;
    private WebDriverWait wait;

    /*@BeforeAll
    public void configInit() {
        config = new ApplicationConfig();
    }

    @BeforeEach
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }*/

    @After
    public void afterScenario()
    {
        System.out.println("TEST");
        driver.quit();
    }
}
