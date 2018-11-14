package stepdefs.leave;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.leave.LeavePlannerPage;

import static vn.asiantech.base.Constant.LEAVE_PLANNER_PAGE_URL;

/**
 * @author at-hangtran
 */
public class LeavePlannerDefinitions extends DriverBase implements En {

    private LeavePlannerPage leavePlanerPage;
    private WebDriver driver;
    private String profileLink;

    public LeavePlannerDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        leavePlanerPage = initPage(driver, LeavePlannerPage.class);

        Given("^Display leave planner page$", () -> {
            driver.get(LEAVE_PLANNER_PAGE_URL);
            waitForPageDisplayed(getDriver(), LEAVE_PLANNER_PAGE_URL, By.id("page-wrapper"));
        });

        Then("^Can not click this week button$", () -> Assert.assertFalse(leavePlanerPage.getClickable()));

        When("^Click on back button$", () -> leavePlanerPage.clickBackButton());

        Then("^Can click this week button$", () -> Assert.assertTrue(leavePlanerPage.getClickable()));

        When("^Click on next button$", () -> leavePlanerPage.clickNextButton());

        When("^Click username$", () -> profileLink = leavePlanerPage.clickUserName());

        Then("^Open successfully profile page of that user$", () -> waitForPageDisplayed(getDriver(), profileLink, By.className("section-top")));

        When("^Move to avatar user$", () -> leavePlanerPage.moveToAvatar());

        Then("^Display leave message$", () -> Assert.assertTrue(true, leavePlanerPage.isShowLeaveMessage().toString()));

        Then("^Display full seven columns$", () -> Assert.assertTrue(true, leavePlanerPage.isDisplayFullColumns().toString()));

        Then("^Display time correctly$", () -> Assert.assertEquals(leavePlanerPage.getTableTime(), leavePlanerPage.getHeaderTime()));
    }
}
