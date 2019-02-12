package vn.asiantech.base;

import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class CucumberRunnerBase extends TestNG {

    @DataProvider
    public Object[][] features() {
        return super.scenarios();
    }

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass(final ITestContext context) throws Exception {
        super.setUpClass(context);
        System.out.println("setUpClass");
        DriverFactory.instance.startDriver(context.getCurrentXmlTest());
    }

    @AfterClass(alwaysRun = true)
    @Override
    public void tearDownClass() throws Exception {
        super.tearDownClass();
        System.out.println("tearDownClass");
        DriverFactory.instance.quitDriver();
    }
}

