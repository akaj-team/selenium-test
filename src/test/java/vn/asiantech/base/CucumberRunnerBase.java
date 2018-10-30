package vn.asiantech.base;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class CucumberRunnerBase extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters("browserType")
    public void beforeSuite(@Optional("browserType") String browserType) {
        System.out.println("instantiateDriverObject");
        DriverBase.instantiateDriverObject(browserType);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("closeDriverObjects");
        DriverBase.closeDriverObjects();
    }

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass() throws Exception {
        super.setUpClass();
        System.out.println("setUpClass");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    @Override
    public void tearDownClass() throws Exception {
        System.out.println("tearDownClass");
        super.tearDownClass();
    }
}
