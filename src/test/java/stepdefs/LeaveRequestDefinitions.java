package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveRequestPage;

/**
 * @author at-anh-quach
 */
public class LeaveRequestDefinitions extends DriverBase implements En {
    private static final int TIME_OUT_IN_SECONDS = 10;

    private WebDriver driver;
    private LeaveRequestPage leaveRequestPage;

    public LeaveRequestDefinitions() {
        leaveRequestPage = initPage(getDriver(), LeaveRequestPage.class);

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Display leave request page$", () -> {
            driver.get("http://portal-stg.asiantech.vn/leave/request");
            new WebDriverWait(driver, TIME_OUT_IN_SECONDS).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/request", driver.getCurrentUrl());
        });

        And("^Marriage Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextMarriageLeave(number)));

        And("^Overtime Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextOvertimeLeave(number)));

        And("^Paternal Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextPaternalLeave(number)));

        When("^I enter the message$", () -> leaveRequestPage.withMessage());

        And("^Annual Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextAnnualLeave(number)));

        And("^I choose type leave is \"([^\"]*)\"$", (String status) -> leaveRequestPage.clickItemMenuType(status));

        Then("^Show message is \"([^\"]*)\"$", (String mess) -> Assert.assertTrue(leaveRequestPage.isShowMessage(mess)));

        When("^I click on timeFrom box leave time$", () -> leaveRequestPage.clickTimeFromBox());

        And("^I click on timeTo box leave time$", () -> leaveRequestPage.clickTimeToBox());

        And("^Show date request$", () -> Assert.assertTrue(leaveRequestPage.isDateRequestShow()));

        When("^I choose afternoon on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseAfternoon()));

        When("^I choose morning on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseMorning()));

        Then("^Submit button is enabled$", () -> Assert.assertTrue(leaveRequestPage.isEnableSubmitButton()));

        Then("^Submit button still disabled$", () -> Assert.assertFalse(leaveRequestPage.isEnableSubmitButton()));

        And("^I click menu type of leave$", () -> leaveRequestPage.clickMenuTypeOfLeave());

        Then("^Menu type of leave drop down$", () -> Assert.assertTrue(leaveRequestPage.isMenuDropDown()));

        Then("^Calendar timeFrom display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow("timeFrom")));

        Then("^Calendar timeTo display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow("timeTo")));

        And("^I choose all day on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseAllday()));

        And("^Check date is \"([^\"]*)\"$", (String date) -> Assert.assertTrue(leaveRequestPage.checkDateInDateRequest(0, date)));

        When("^I click remove button$", () -> leaveRequestPage.clickRemoveButton(0));

        Then("^Date request is removed$", () -> Assert.assertFalse(leaveRequestPage.isRemoveDateRequest()));

        Given("^I chose type leave is \"([^\"]*)\"$", (String status) -> leaveRequestPage.setNonePaidInTypeOfLeave(status));

        Given("^Enter full information$", () -> leaveRequestPage.enterFullInfor());

        When("^I click submit$", () -> leaveRequestPage.clickSubmit());

        Then("^Show dialog confirmation$", () -> Assert.assertTrue(leaveRequestPage.isShowDialog()));

        Given("^Dialog was showed$", () -> leaveRequestPage.showDialog());

        When("^I click submit on dialog$", () -> leaveRequestPage.clickSubmitButtonInDialog());

        Then("^Redirect to leave detail$", () -> leaveRequestPage.redirectLeaveDetail());

        When("^I click cancel on dialog$", () -> leaveRequestPage.clickCancelButtonInDialog());

        Then("^Dialog disappeared$", () -> Assert.assertTrue(leaveRequestPage.isDialogDisappeared()));

        And("^I choose today in timeForm calendar$", () -> leaveRequestPage.chooseTime("timeFrom"));

        And("^I choose today in timeTo calendar$", () -> leaveRequestPage.chooseTime("timeTo"));
    }
}