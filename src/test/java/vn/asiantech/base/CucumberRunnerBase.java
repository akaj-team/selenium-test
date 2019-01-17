package vn.asiantech.base;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class CucumberRunnerBase extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(final ITestContext context) {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        DriverFactory.instance.startDriver(context.getCurrentXmlTest());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("tearDownClass");
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
        DriverFactory.instance.quitDriver();
    }
}
