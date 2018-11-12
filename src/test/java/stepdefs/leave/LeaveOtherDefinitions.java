package stepdefs.leave;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.leave.LeaveOtherPage;

/**
 * @author at-hanhnguyen
 */
public class LeaveOtherDefinitions extends DriverBase implements En {
    private LeaveOtherPage leaveOtherPage;
    private WebDriver webDriver;

    private static final int EMPLOYER_ID_POS = 0;
    private static final int EMPLOYER_NAME_POS = 1;

    private String pathOrganisationEmployer;
    private String pathEmployerName;
    private int countItem;

    public LeaveOtherDefinitions() {
        try {
            webDriver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        leaveOtherPage = initPage(webDriver, LeaveOtherPage.class);

        And("^Open leave of other page$", () -> {
            webDriver.get(Constant.LEAVE_TRACKING_PAGE_URL);
            waitForPageDisplayed(webDriver, Constant.LEAVE_TRACKING_PAGE_URL, By.id("page-wrapper"));
        });
        And("^Wait for load data$", () -> leaveOtherPage.waitForLoadData());

        When("^I click status item$", () -> leaveOtherPage.clickStatusItem());
        Then("^Should show status dropdown$", () -> Assert.assertTrue(leaveOtherPage.checkShowStatusDropdown()));
        When("^I click group by item$", () -> leaveOtherPage.clickGroupByItem());
        Then("^Should show group by dropdown$", () -> Assert.assertTrue(leaveOtherPage.checkShowGroupByItem()));
        When("^I click choose start date$", () -> leaveOtherPage.clickItemStartDate());
        Then("^Should show start date dialog$", () -> Assert.assertTrue(leaveOtherPage.checkShowStartDateDialog()));
        When("^I click end date$", () -> leaveOtherPage.clickItemEndDate());
        Then("^Should show end day dialog$", () -> Assert.assertTrue(leaveOtherPage.checkShowEndDateDialog()));

        Then("^I choose a start date$", () -> leaveOtherPage.chooseStartDate());
        Then("^I choose a end date$", () -> leaveOtherPage.chooseEndDate());
        When("^End date less than start date$", () -> Assert.assertTrue(leaveOtherPage.compareTwoDate()));
        Then("^show error message with text is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(leaveOtherPage.checkShowErrorMessage(message)));

        Given("^Leave of other have data$", () -> {
            Assert.assertFalse(leaveOtherPage.checkDataEmpty());
            pathOrganisationEmployer = leaveOtherPage.getPathPageRedirect(EMPLOYER_ID_POS);
            pathEmployerName = leaveOtherPage.getPathPageRedirect(EMPLOYER_NAME_POS);
            countItem = leaveOtherPage.countDataItem();
        });
        When("^I click in employer id$", () -> leaveOtherPage.clickEmployerId());
        Then("^Should redirect to organisation employees$", () -> redirectPage(pathOrganisationEmployer));

        When("^I click in employees name$", () -> leaveOtherPage.clickEmployerName());
        Then("^Should redirect to profile detail$", () -> redirectPage(pathEmployerName));

        When("^I click in icon search$", () -> leaveOtherPage.clickSearchIcon());

        When("^Button next_to_end_page is shown$", () -> Assert.assertTrue(leaveOtherPage.checkButtonNextEndPageShown(countItem)));
        And("^I click button next_to_end_page$", () -> leaveOtherPage.clickButtonLast());
        Then("^Should show end page$", () -> Assert.assertEquals(webDriver.getCurrentUrl(), leaveOtherPage.getPathLastPage(countItem)));
    }

    private void redirectPage(final String path) {
        new WebDriverWait(webDriver, Constant.MAXIMUM_TIME_OUT).until(
                driver -> driver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
