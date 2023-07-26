package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm" },
        features = { "src/test/resources/features" },
        glue = {"steps", "hooks"}
        )
public class RunCucumberTest {
}
