import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import vn.asiantech.core.CucumberRunnerBase;

/**
 * FirefoxTestRunner
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        tags = {"not @Ignore"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/FirefoxCucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
@Test
public class FirefoxTestRunner extends CucumberRunnerBase {

}
