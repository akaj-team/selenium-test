package stepdefs.wiki;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.wiki.NewWikiPage;

/**
 * @author at-anh.quach
 * NewWikiDefinition
 */
public class NewWikiDefinition extends DriverBase implements En {
    private WebDriver driver;
    private NewWikiPage newWikiPage;

    public NewWikiDefinition() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        newWikiPage = initPage(driver, NewWikiPage.class);

        And("^Child Page page displayed$", () -> {
            driver.get(Constant.CHILD_PAGE_URL);
            waitForPageDisplayed(driver, Constant.CHILD_PAGE_URL, By.id("wiki-form-wrapper"));
        });

        When("^I enter title$", () -> newWikiPage.enterTitle());

        And("^I enter content$", () -> newWikiPage.enterContent());

        Then("^Submit button is enabled$", () -> Assert.assertTrue(newWikiPage.isSubmitEnable()));

        Given("^Enter title and content$", () -> newWikiPage.enterTitle().enterContent());

        When("^Click submit child page or update page button$", () -> newWikiPage.clickSubmit());

        Then("^Submit button is disabled$", () -> Assert.assertTrue(newWikiPage.isSubmitDisable()));

        Then("^Show message \"([^\"]*)\"$", (String text) -> Assert.assertTrue(newWikiPage.isShowMessageError(text)));

        And("^I clear title$", () -> newWikiPage.clearTitle());
    }
}
