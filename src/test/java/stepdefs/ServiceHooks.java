package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import vn.asiantech.core.DriverFactory;

public class ServiceHooks {
    private static boolean isFirstStep = true;

    @Before
    public void initializeTest() {
        if (isFirstStep) {
            Runtime.getRuntime().addShutdownHook(new Thread(this::afterAll));
            isFirstStep = false;
            beforeAll();
        }
    }

    @After
    public void embedScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            //Do some stuff
        }
    }

    private void afterAll() {
        DriverFactory.instance.quitDriver();
    }

    private void beforeAll() {
        //Do some stuff
    }
}