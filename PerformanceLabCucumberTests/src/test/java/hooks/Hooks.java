package hooks;

import core.DriversManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import steps.Steps;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

public class Hooks extends Steps {

    //region Конструктор
    public Hooks(HashMap<String, Object> scContext) {
        super(scContext);
    }
    //endregion

    @After(order = 1)
    public void takeScraenshotOnFailure(Scenario scenario)
    {
        TakesScreenshot ts = (TakesScreenshot) DriversManager.getCurrent();
        byte[] src = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(src, "image/png", "screenshot");
    }

    @After(order = 0)
    public void tearDown()
    {
        DriversManager.closeBrowser();
    }
}
