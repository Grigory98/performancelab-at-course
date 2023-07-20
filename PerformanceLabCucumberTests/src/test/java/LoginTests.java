import config.ApplicationConfig;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests
{
    private RemoteWebDriver driver;
    private ApplicationConfig config;
    private WebDriverWait wait;

    @BeforeAll
    public void configInit()
    {
        config = new ApplicationConfig();
    }

    @BeforeEach
    public void init()
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void loginTest()
    {
        driver.get(config.baseUrl);
        WebElement loginInput = driver.findElement(By.cssSelector("input[name=email]"));
        WebElement passwordInput = driver.findElement(By.cssSelector("input[name=password]"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type=submit]"));

        loginInput.sendKeys(config.username);
        passwordInput.sendKeys(config.userPassword);
        submitBtn.click();

        String alertText = wait.until(driver -> {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.dismiss();
            return text;
        });

        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
    }

    /*@Test
    public void loginTestUsingPo()
    {
        driver.get(config.baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.fillLoginInput(config.username);
        loginPage.fillPasswordInput(config.userPassword);
        loginPage.submitForm();
        String alertText = loginPage.getAlertText();
        Assertions.assertTrue(alertText.contains("Successful"), "Alert text doesn't contains info about successful auth");
        loginPage.dismissAlert();
    }*/
}
