package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveOtherPage;

/**
 * @author at-hanhnguyen
 */
public class LeaveOtherDefinitions extends DriverBase implements En {
    private LeaveOtherPage leaveOtherPage;
    private WebDriver webDriver;

    private static final int TIME_OUT = 30;
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

        And("^Open leave of other page$", () -> {
            webDriver.get(Constant.LEAVE_TRACKING_PAGE_URL);
            leaveOtherPage = initPage(getDriver(), LeaveOtherPage.class);
            new WebDriverWait(webDriver, TIME_OUT).until(
                    driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(Constant.LEAVE_TRACKING_PAGE_URL, webDriver.getCurrentUrl());
        });
        And("^Wait for load data$", () -> leaveOtherPage.waitForLoadData(webDriver));

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
        When("^end date less than start date$", () -> Assert.assertTrue(leaveOtherPage.compareTwoDate()));
        Then("^show error message with text is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(leaveOtherPage.checkShowErrorMessage(message, getDriver())));

        Given("^Leave of other have data$", () -> {
            Assert.assertFalse(leaveOtherPage.checkDataEmpty());
            pathOrganisationEmployer = leaveOtherPage.getPathPageRedirect(EMPLOYER_ID_POS);
            pathEmployerName = leaveOtherPage.getPathPageRedirect(EMPLOYER_NAME_POS);
            countItem = leaveOtherPage.countDataItem(webDriver);
        });
        When("^I click in employer id$", () -> leaveOtherPage.clickEmployerId());
        Then("^Should redirect to organisation employees$", () -> redirectPage(pathOrganisationEmployer));

        When("^I click in employees name$", () -> leaveOtherPage.clickEmployerName());
        Then("^Should redirect to profile detail$", () -> redirectPage(pathEmployerName));

        When("^I click in icon search$", () -> leaveOtherPage.clickSearchIcon());

        When("^Button next_to_end_page is shown$", () -> Assert.assertTrue(leaveOtherPage.checkButtonNextEndPageShown(countItem)));
        And("^I click button next_to_end_page$", () -> leaveOtherPage.clickButtonLast());
        Then("^Should show end page$", () -> Assert.assertEquals(getDriver().getCurrentUrl(), leaveOtherPage.getPathLastPage(countItem)));
    }

    private void redirectPage(final String path) {
        new WebDriverWait(getDriver(), TIME_OUT).until(
                driver -> driver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
