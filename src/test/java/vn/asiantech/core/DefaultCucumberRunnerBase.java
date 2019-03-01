package vn.asiantech.core;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners(ScreenShotListener.class)
public class DefaultCucumberRunnerBase extends AbstractTestNGCucumberTests {

    @DataProvider
    public Object[][] features() {
        return super.scenarios();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(final ITestContext context) throws Exception {
        super.setUpClass();
        DriverFactory.instance.startDriver(context);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        super.tearDownClass();
        System.out.println("tearDownClass");
        DriverFactory.instance.quitDriver();
    }
}
