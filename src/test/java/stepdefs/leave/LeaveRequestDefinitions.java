package stepdefs.leave;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.leave.LeaveRequestPage;

/**
 * @author at-anh-quach
 */
public class LeaveRequestDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private LeaveRequestPage leaveRequestPage;

    public LeaveRequestDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        leaveRequestPage = initPage(driver, LeaveRequestPage.class);

        And("^Display leave request page$", () -> {
            driver.get(Constant.LEAVE_REQUEST_PAGE_URL);
            waitForPageDisplayed(driver, Constant.LEAVE_REQUEST_PAGE_URL, By.id("page-wrapper"));
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

        Then("^Calendar timeFrom display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow()));

        Then("^Calendar timeTo display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow()));

        And("^I choose all day on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseAllDay()));

        And("^Check date is \"([^\"]*)\"$", (String date) -> Assert.assertTrue(leaveRequestPage.checkDateInDateRequest(0, date)));

        When("^I click remove button$", () -> leaveRequestPage.clickRemoveButton(0));

        Given("^I chose type leave is \"([^\"]*)\"$", (String status) -> leaveRequestPage.setNonePaidInTypeOfLeave(status));

        When("^I click submit$", () -> leaveRequestPage.clickSubmit());

        Then("^Show dialog confirmation$", () -> Assert.assertTrue(leaveRequestPage.isShowDialog()));

        Given("^Dialog was showed$", () -> leaveRequestPage.showDialog());

        When("^I click submit on dialog$", () -> leaveRequestPage.clickSubmitButtonInDialog());

        When("^I click cancel on dialog$", () -> leaveRequestPage.clickCancelButtonInDialog());

        Then("^Dialog disappeared$", () -> Assert.assertTrue(leaveRequestPage.isDialogDisappeared()));

        And("^I choose today in timeForm calendar$", () -> leaveRequestPage.chooseTime());

        And("^I choose today in timeTo calendar$", () -> leaveRequestPage.chooseTime());

        Given("^Enter full information leave request$", () -> leaveRequestPage.enterFullInfo());
    }
}
