import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import vn.asiantech.base.CucumberRunnerBase;
import vn.asiantech.base.TestNG;

/**
 * ChromeTestRunner
 */
@CucumberOptions(
        features = {"src/test/resources/features/login"},
        glue = {"stepdefs"})
@Test
public class ChromeTestRunner extends CucumberRunnerBase {
    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass(final ITestContext context) throws Exception {
        super.setUpClass(context);
    }
}
