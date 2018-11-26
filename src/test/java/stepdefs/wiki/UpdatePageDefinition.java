package stepdefs.wiki;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

        When("^I click on menu parent$", () -> updatePagePage.clickInputParent());

        Then("^Menu is drop down$", () -> Assert.assertTrue(updatePagePage.isDropDownMenu()));

        When("^I click on item parent$", () -> updatePagePage.clickItemParent());

        Then("^Change parent$", () -> Assert.assertTrue(updatePagePage.isChangeParent()));
    }
}
