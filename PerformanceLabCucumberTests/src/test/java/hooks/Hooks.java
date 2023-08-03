package hooks;

import core.DriversManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @After(order = 0)
    public void tearDown(Scenario scenario)
    {
        if(!DriversManager.isDriverExist()) return;
        if(scenario.isFailed()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) DriversManager.current();
                byte[] src = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(src, "image/png", "screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DriversManager.closeBrowser();
    }
}
