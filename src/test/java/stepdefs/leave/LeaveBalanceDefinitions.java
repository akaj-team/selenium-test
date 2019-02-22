package stepdefs.leave;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.leave.LeaveBalancePage;

/**
 * @author at-vinhhuynh
 */
public class LeaveBalanceDefinitions extends BaseDefinitions implements En {

    private WebDriver driver;
    private LeaveBalancePage leaveBalancePage;
    private String selectedTeam;

    public LeaveBalanceDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        leaveBalancePage = initPage(getDriver(), LeaveBalancePage.class);

        And("^Display leave balance page$", () -> {
            driver.get(Constant.LEAVE_BALANCE_PAGE_URL);
            waitForPageDisplayed(getDriver(), Constant.LEAVE_BALANCE_PAGE_URL, null);
        });

        Given("^I click on all team filter$", () -> {
            waitRedirectToPage(Constant.LEAVE_BALANCE_PAGE_URL);
            leaveBalancePage.openFilter();
        });

        Then("^List filter should display$", () -> Assert.assertTrue(leaveBalancePage.filterElementDisplayed()));

        Then("^List balance leave should be (\\d+) item$", (Integer itemCount) -> Assert.assertEquals(leaveBalancePage.getLeaveBalanceListCount(), (int) itemCount));

        Given("^I click to avatar of an employee$", () -> leaveBalancePage.avatarClick());

        Then("^Should open correct profile page of that employee$", () -> {
            waitRedirectToPage(leaveBalancePage.getCurrentProfileUrl());
            Assert.assertEquals(leaveBalancePage.getCurrentProfileUrl(), driver.getCurrentUrl());
        });

        Given("^I click to search icon of an employee on leave list$", () -> leaveBalancePage.openLeaveHistory());

        Then("^Should open correct history page of that employee$", () -> {
            waitRedirectToPage(leaveBalancePage.getCurrentLeaveHistoryUrl());
            Assert.assertEquals(leaveBalancePage.getCurrentLeaveHistoryUrl(), driver.getCurrentUrl());
        });

        Given("^I fill in search view with name is \"([^\"]*)\"$", (String key) -> leaveBalancePage.searchWithKey(key));

        Then("^Should show list result of employ with name is \"([^\"]*)\"$", (String employeeName) -> {
            new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> !webDriver.getCurrentUrl().equals(Constant.LEAVE_BALANCE_PAGE_URL));

            Assert.assertEquals(leaveBalancePage.getCurrentEmployeeName(), employeeName);
        });

        When("^I select first item$", () -> selectedTeam = leaveBalancePage.filterItemClick(0));

        Then("^Team will display correctly$", () -> Assert.assertEquals(leaveBalancePage.getFilterLabel(), selectedTeam));

        And("^Show list result of employees$", () -> Assert.assertTrue(driver.findElement(By.className("ui-datatable-scrollable-view")).isDisplayed()));

        Then("^Year on this page should be \"([^\"]*)\"$", (String year) -> Assert.assertEquals(leaveBalancePage.getYear(), year));
        When("^I click on previous$", () -> leaveBalancePage.previous());

        When("^I click on page number (\\d+)$", (Integer pageNum) -> leaveBalancePage.gotoPage(pageNum - 1));

        Then("^Should load data at page (\\d+)$", (Integer pageIndex) -> {
            waitRedirectToPage(Constant.LEAVE_BALANCE_PAGE_URL + ";page=" + pageIndex);
            Assert.assertEquals(Constant.LEAVE_BALANCE_PAGE_URL + ";page=" + pageIndex, driver.getCurrentUrl());
        });
        When("^I click to name of an employee$", () -> leaveBalancePage.userNameClick());
        Given("^I click to sysId of an employee on leave list$", () -> leaveBalancePage.sysIdClick());
    }
}
