package generate;
import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import vn.asiantech.core.ParallelCucumberRunnerBase;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs"},
        tags = {"@Only"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/ChromeCucumberTestReport1.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
@Test
class ParallelChromeTestRunnerGenerate1 extends ParallelCucumberRunnerBase {
}
