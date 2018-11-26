package stepdefs.wiki;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.wiki.NewPagePage;

/**
 * @author at-anh.quach
 * NewPageDefinition
 */

public class NewPageDefinition extends DriverBase implements En {
    private WebDriver driver;
    private NewPagePage newPagePage;

    public NewPageDefinition() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        newPagePage = initPage(driver, NewPagePage.class);

        And("^Child Page page displayed$", () -> {
            driver.get(Constant.CHILD_PAGE_URL);
            waitForPageDisplayed(driver, Constant.CHILD_PAGE_URL, By.id("wiki-form-wrapper"));
        });

        When("^I enter title$", () -> newPagePage.enterTitle());

        And("^I enter content$", () -> newPagePage.enterContent());

        Then("^Submit button is enabled$", () -> Assert.assertTrue(newPagePage.isSubmitEnable()));

        Given("^Enter title and content$", () -> {
            newPagePage.enterTitle();
            newPagePage.enterContent();
        });

        When("^Click submit child page button$", () -> newPagePage.clickSubmit());

        Then("^Submit button is disabled$", () -> Assert.assertFalse(newPagePage.isSubmitEnable()));

        Then("^Show message \"([^\"]*)\"$", (String text) -> Assert.assertTrue(newPagePage.isShowMessageError(text)));

        And("^I clear title$", () -> newPagePage.clearTitle());

        When("^I clear content$", () -> newPagePage.clearContent());
    }
}
