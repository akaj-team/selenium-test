package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveOtherPage;

public class LeaveOtherDefinitions extends DriverBase implements En {
    private LeaveOtherPage leaveOtherPage;
    private WebDriver webDriver;
    private static final int TIME_OUT = 30;

    public LeaveOtherDefinitions() {
        try {
            webDriver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Open leave of other page$", () -> {
            webDriver.get("http://portal-stg.asiantech.vn/leave/tracking");
            leaveOtherPage = initPage(getDriver(), LeaveOtherPage.class);
            new WebDriverWait(webDriver, TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/tracking", webDriver.getCurrentUrl());
        });

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
        Then("^show error message with text is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(leaveOtherPage.checkShowErrorMessage(message)));
    }
}
