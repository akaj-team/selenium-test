package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MyLeavePage;

/**
 * @author at-anh.quach
 */

public class MyLeaveDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private MyLeavePage myLeavePage;

    public MyLeaveDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        myLeavePage = initPage(getDriver(), MyLeavePage.class);

        Then("^My Leave page is displayed$", () -> displayPage("/leave/my-leave"));

        When("^I click on menu Status$", () -> myLeavePage.clickMenuStatus());

        Then("^Menu drop down$", () -> Assert.assertTrue(myLeavePage.dropDownMenuStatus()));

        When("^I click on Pending in menu$", () -> myLeavePage.clickItemMenuStatus("Pending"));

        Then("^My Leave page with status Pending is displayed$", () -> displayPage("/leave/my-leave;status_eq=pending"));

        When("^I click on Approved in menu$", () -> myLeavePage.clickItemMenuStatus("Approved"));

        Then("^My Leave page with status Approved is displayed$", () -> displayPage("/leave/my-leave;status_eq=approved"));

        When("^I click on Rejected in menu$", () -> myLeavePage.clickItemMenuStatus("Rejected"));

        Then("^My Leave page with status Rejected is displayed$", () ->
                displayPage("/leave/my-leave;status_eq=rejected"));

        When("^I click on a name Manager$", () -> myLeavePage.clickNameManager());

        Then("^Employee Detail page is displayed$", () -> displayPage("/organisation/employees/189"));

        When("^I click on Leave Request button$", () -> myLeavePage.clickBtnLeaveRequest());

        Then("^Leave Request page is displayed$", () -> displayPage("/leave/request"));

        And("^Status Menu is \"([^\"]*)\"$", (String status) -> Assert.assertTrue(myLeavePage.checkTextStatusMenu(status)));

        And("^Status is \"([^\"]*)\"$", (String status) -> Assert.assertTrue(myLeavePage.checkTextStatus(status)));

        And("^Approver is \"([^\"]*)\"$", (String approver) -> Assert.assertTrue(myLeavePage.checkTextApprover(approver)));

        And("^Manager is \"([^\"]*)\"$", (String manager) -> Assert.assertTrue(myLeavePage.checkTextManager(manager)));

        And("^Annual Leave is \"([^\"]*)\"$", (String annualLeave) -> Assert.assertTrue(myLeavePage.checkTextAnnualLeave(annualLeave)));

        And("^Marriage Leave is \"([^\"]*)\"$", (String marriage) -> Assert.assertTrue(myLeavePage.checkTextMarriageLeave(marriage)));

        And("^Overtime Leave is \"([^\"]*)\"$", (String overtime) -> Assert.assertTrue(myLeavePage.checkTextOvertimeLeave(overtime)));

        And("^Paternal Leave is \"([^\"]*)\"$", (String paternal) -> Assert.assertTrue(myLeavePage.checkTextPaternalLeave(paternal)));

        Then("^Menu status drop down$", () -> Assert.assertTrue(myLeavePage.checkMenuStatusDropDown()));

        When("^I click on SYSID \"([^\"]*)\"$", (String sysid) -> myLeavePage.clickSYSID());

        Then("^Leave Detail SYSID \"([^\"]*)\" page is displayed$", (String sysid) -> displayPage("/leave/" + sysid));

        When("^I click on icon search with SYSID \"([^\"]*)\"$", (String sysid) -> myLeavePage.clickIconSearch());

        Given("^My Leave page with status Pending displayed$", () -> displayPage("/leave/my-leave;status_eq=pending"));

        When("^I click on All Status in menu$", () -> myLeavePage.clickItemMenuStatus("All Status"));

        When("^I hover mouse on status$", () -> myLeavePage.hoverMouseToStatus());

        Then("^Tip status display is \"([^\"]*)\"$", (String status) -> Assert.assertTrue(myLeavePage.checkDisplayTipStatus(status)));

        When("^I click an SYSID$", () -> {
            myLeavePage.setSysid();
            myLeavePage.clickSYSID();
        });

        Then("^Leave Detail page is displayed$", () -> displayPage("/leave/" + myLeavePage.getSysid()));

        When("^I click on an icon search$", () -> {
            myLeavePage.setSysid();
            myLeavePage.clickIconSearch();
        });
    }

    private void displayPage(final String path) {
        driver.get(Constant.PORTAL_URL + path);
        waitForPageDisplayed(driver, Constant.PORTAL_URL + path, By.id("page-wrapper"));
    }
}
