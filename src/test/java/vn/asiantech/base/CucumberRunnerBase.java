package vn.asiantech.base;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class CucumberRunnerBase extends AbstractTestNGCucumberTests {

    @DataProvider
    public Object[][] features() {
        return super.scenarios();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(final ITestContext context) throws Exception {
        super.setUpClass();
        DriverFactory.instance.startDriver(context.getCurrentXmlTest());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        super.tearDownClass();
        System.out.println("tearDownClass");
        DriverFactory.instance.quitDriver();
    }
}
