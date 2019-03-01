package vn.asiantech.core;

import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomAbstractTestNGCucumberTests {
    private static int browserCount;
    private TestNGCucumberRunner testNGCucumberRunner;
    private int threadCount;

    public CustomAbstractTestNGCucumberTests() {
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) throws Exception {
        threadCount = context.getCurrentXmlTest().getSuite().getTests().get(0).getThreadCount();
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
        if (scenarios.length != 0 && scenarios.length > threadCount) {
            int number = scenarios.length / threadCount;
            if (browserCount != (threadCount - 1)) {
                for (int i = browserCount * number; i < number * (browserCount + 1); i++) {
                    runScenarios.add(scenarios[i]);
                }
                System.out.println("Thread " + browserCount + " run " + runScenarios.size() + " scenarios");

            } else {
                int startIndex = number * (threadCount - 1);
                for (int i = startIndex; i < scenarios.length; i++) {
                    runScenarios.add(scenarios[i]);
                }
                System.out.println("Thread " + browserCount + " run " + runScenarios.size() + " scenarios");
            }
            browserCount++;
            return this.testNGCucumberRunner == null ? new Object[0][0] : runScenarios.toArray(new Object[0][]);
        } else {
            return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
        }
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
