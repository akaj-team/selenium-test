package stepdefs.wiki;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.wiki.UpdateWikiPage;

/**
 * @author at-anh.quach
 * UpdateWikiDefinition
 */
public class UpdateWikiDefinition extends DriverBase implements En {
    private WebDriver driver;
    private UpdateWikiPage updateWikiPage;

    public UpdateWikiDefinition() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateWikiPage = initPage(driver, UpdateWikiPage.class);

        And("^Update Page page displayed$", () -> {
            driver.get(Constant.UPDATE_PAGE_URL);
            waitForPageDisplayed(driver, Constant.UPDATE_PAGE_URL, By.id("wiki-form-wrapper"));
        });

        When("^I click on menu parent$", () -> updateWikiPage.clickInputParent());

        Then("^Menu is drop down$", () -> Assert.assertTrue(updateWikiPage.isDropDownMenu()));

        When("^I click on item parent$", () -> updateWikiPage.clickItemParent());

        Then("^Change parent$", () -> Assert.assertTrue(updateWikiPage.isChangeParent()));
    }
}
