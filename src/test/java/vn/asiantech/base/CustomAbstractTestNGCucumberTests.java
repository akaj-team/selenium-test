package vn.asiantech.base;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomAbstractTestNGCucumberTests {
    private static final int BROWSER_INSTANCE_SUM = 5;
    private static int browserCount;
    private TestNGCucumberRunner testNGCucumberRunner;

    public CustomAbstractTestNGCucumberTests() {
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Test(groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        Object[][] scenarios = this.testNGCucumberRunner.provideScenarios();
        List<Object[]> runScenarios = new ArrayList();
        int number = scenarios.length / BROWSER_INSTANCE_SUM;
        if (browserCount != (BROWSER_INSTANCE_SUM - 1)) {
            for (int i = browserCount * number; i < number * (browserCount + 1); i++) {
                runScenarios.add(scenarios[i]);
            }
            System.out.println("Thread " + browserCount + " run " + runScenarios.size() + " scenarios");

        } else {
            int startIndex = number * (BROWSER_INSTANCE_SUM - 1);
            for (int i = startIndex; i < scenarios.length; i++) {
                runScenarios.add(scenarios[i]);
            }
            System.out.println("Thread " + browserCount + " run " + runScenarios.size() + " scenarios");
        }
        browserCount++;
        return this.testNGCucumberRunner == null ? new Object[0][0] : runScenarios.toArray(new Object[0][]);
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass() throws Exception {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }
}
