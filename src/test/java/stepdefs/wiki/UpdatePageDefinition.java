package stepdefs.wiki;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.wiki.UpdatePagePage;

public class UpdatePageDefinition extends DriverBase implements En {
    private WebDriver driver;
    private UpdatePagePage updatePagePage;

    public UpdatePageDefinition() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        updatePagePage = initPage(driver, UpdatePagePage.class);

        And("^Update Page page displayed$", () -> {
            driver.get(Constant.UPDATE_PAGE_URL);
            waitForPageDisplayed(driver, Constant.UPDATE_PAGE_URL, By.id("wiki-form-wrapper"));
        });
    }
}
