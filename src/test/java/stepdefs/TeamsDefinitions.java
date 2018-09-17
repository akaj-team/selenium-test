package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import vn.asiantech.base.DriverBase;

public class TeamsDefinitions extends DriverBase implements En {
    private WebDriver driver;

    public TeamsDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
