import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import vn.asiantech.base.CucumberRunnerBase;

@CucumberOptions(
        features = "src/test/resources/features/wiki",
        glue = {"stepdefs"},
        tags = {"not @Ignore"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReportChrome.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
@Test
public class TestRunnerChrome extends CucumberRunnerBase {

}