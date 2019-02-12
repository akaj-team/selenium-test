import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import vn.asiantech.base.CucumberRunnerBase;
import vn.asiantech.base.DriverFactory;
import vn.asiantech.base.TestNG;

/**
 * ChromeTestRunner
 */
@CucumberOptions(
        features = "src/test/resources/features/login",
        glue = {"stepdefs"})
@Test
public class ChromeTestRunner extends AbstractTestNGCucumberTests {
    @DataProvider
    public Object[][] features() {
        return super.scenarios();
    }

    @BeforeClass(alwaysRun = true)
    public synchronized void setUpClass(final ITestContext context) throws Exception {
        System.setProperty("cucumber.options", "--plugin json:target/" + System.currentTimeMillis() + ".json --plugin json:target/" + System.currentTimeMillis() + ".json");
        super.setUpClass();
        System.out.println("setUpClass");
        DriverFactory.instance.startDriver(context.getCurrentXmlTest());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        super.tearDownClass();
        System.out.println("tearDownClass");
        DriverFactory.instance.quitDriver();
    }
}
