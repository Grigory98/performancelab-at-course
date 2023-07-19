import config.ApplicationConfig;
import pages.LoginPage;
import pages.UserPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortedTests
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

    private LoginPage logIn()
    {
        driver.get(config.baseUrl);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.fillLoginInput(config.username);
        loginPage.fillPasswordInput(config.userPassword);
        loginPage.submitForm();
        loginPage.dismissAlert();
        return new LoginPage(driver, wait);
    }

    @Test
    public void SortedTest()
    {
        var loginPage = this.logIn();
        loginPage.openOptionFromRibbon("Users", "Read all");
        var userPage = new UserPage(driver, wait);
        userPage.setSortingByID(true);
        Assertions.assertTrue(userPage.checkSortedGrid(true), "Sorting should be by ascending.");
        userPage.cancelSorting("ID");
        userPage.setSortingByID(false);
        Assertions.assertTrue(userPage.checkSortedGrid(false), "Sorting should be by descending");
    }
}
