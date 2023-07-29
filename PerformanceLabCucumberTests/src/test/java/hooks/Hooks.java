package hooks;

import core.DriversManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;

public class Hooks {

    @After(order = 0)
    public void tearDown(Scenario scenario) throws InterruptedException, IOException, IllegalMonitorStateException
    {
        if(scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) DriversManager.getCurrent();
            byte[] src = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }

        DriversManager.closeBrowser();
    }
}
