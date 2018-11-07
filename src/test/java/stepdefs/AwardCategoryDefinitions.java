package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.AwardCategoryPage;

/**
 * @author at-anh.quach
 * AwardCategoryDefinitions
 */

public class AwardCategoryDefinitions extends DriverBase implements En {
    private AwardCategoryPage awardCategoryPage;
    private WebDriver driver;

    public AwardCategoryDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        awardCategoryPage = initPage(driver, AwardCategoryPage.class);

        And("^Award Category page displayed$", this::displayPage);

        When("^I click new award button$", () -> awardCategoryPage.clickNewAward());

        Then("^Show dialog new award$", () -> Assert.assertTrue(awardCategoryPage.isDisplayDialog()));

        When("^I click edit button$", () -> awardCategoryPage.clickEditButton());

        Then("^Show dialog edit$", () -> Assert.assertTrue(awardCategoryPage.isDisplayDialog()));

        Given("^New dialog is showed$", () -> awardCategoryPage.clickNewAward());

        When("^I input name on Name box$", () -> awardCategoryPage.enterName("Best project" + System.currentTimeMillis()));

        Then("^Submit change from disable to enable$", () -> Assert.assertTrue(awardCategoryPage.isEnableSubmitButton()));

        Given("^New dialog is showed and entered name$", () -> {
            awardCategoryPage.clickNewAward();
            awardCategoryPage.enterName("Best project" + System.currentTimeMillis());
        });

        When("^I click submit button$", () -> awardCategoryPage.clickSubmit());

        When("^I enter description$", () -> awardCategoryPage.enterDescription());

        Given("^Dialog edit is showed$", () -> Assert.assertTrue(awardCategoryPage.isDisplayDialog()));

        When("^I clear name box$", () -> awardCategoryPage.clearText());

        Then("^Display message \"([^\"]*)\"$", (String message) -> Assert.assertTrue(awardCategoryPage.isShowError()));

        And("^Disable submit button$", () -> Assert.assertFalse(awardCategoryPage.isEnableSubmitButton()));

        But("^I click close button$", () -> awardCategoryPage.clickCloseDialog());

        And("^Disappear dialog$", () -> Assert.assertFalse(awardCategoryPage.isDisplayDialog()));

        When("^I click cancel button$", () -> awardCategoryPage.clickCancelButton());

        Then("^Alert category has been created show$", () -> Assert.assertTrue(awardCategoryPage.isDisplayAlertSuccess()));

        When("^I input name was exist on Name box$", () -> awardCategoryPage.enterName("Best project"));

        Then("^Alert danger show$", () -> Assert.assertTrue(awardCategoryPage.isDisplayAlertDanger()));

        When("^I click Delete button$", () -> awardCategoryPage.clickDeleteButton());

        Then("^I click delete confirm button$", () -> awardCategoryPage.clickDeleteConfirm());

        Then("^Alert delete success is showed$", () -> Assert.assertTrue(awardCategoryPage.isDisplayAlertDeleteSuccess()));
    }

    private void displayPage() {
        driver.get(Constant.AWARD_CATEGORY_PAGE_URL);
        waitForPageDisplayed(driver, Constant.AWARD_CATEGORY_PAGE_URL, By.id("page-wrapper"));
    }
}
