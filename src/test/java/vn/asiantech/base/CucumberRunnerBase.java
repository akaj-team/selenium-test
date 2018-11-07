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
        DriverBase.instantiateDriverObject(context.getCurrentXmlTest());
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("tearDownClass");
        DriverBase.closeDriverObjects();
    }
}
