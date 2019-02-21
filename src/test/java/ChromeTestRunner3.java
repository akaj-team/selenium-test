import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import vn.asiantech.base.CucumberRunnerBase;

/**
 * ChromeTestRunner2
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        tags = {"not @Ignore"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/ChromeTestReport3.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
@Test
public class ChromeTestRunner3 extends CucumberRunnerBase {

}
