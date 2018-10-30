package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveBalancePage;

/**
 * @author at-vinhhuynh
 */
public class LeaveBalanceDefinitions extends DriverBase implements En {

    private static final int DEFAULT_PAGE_COUNT = 50;
    private WebDriver driver;
    private LeaveBalancePage leaveBalancePage;

    public LeaveBalanceDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Display leave balance page$", () -> {
            driver.get(Constant.LEAVE_BALANCE_PAGE_URL);
            leaveBalancePage = initPage(getDriver(), LeaveBalancePage.class);
            waitForPageDisplayed(getDriver(), Constant.LEAVE_BALANCE_PAGE_URL, null);
        });

        Given("^I click on all team filter$", () -> {
            waitRedirectToPage(Constant.LEAVE_BALANCE_PAGE_URL);
            leaveBalancePage.openFilter(driver);
        });

        Then("^List filter should display$", () -> Assert.assertTrue(leaveBalancePage.filterElementDisplayed()));

        Then("^List balance leave should be (\\d+) item$", (Integer itemCount) -> Assert.assertEquals(leaveBalancePage.getLeaveBalanceListCount(driver), (int) itemCount));

        Given("^I click to avatar of an employee$", () -> leaveBalancePage.avatarClick(driver));

        Then("^Should open correct profile page of that employee$", () -> {
            waitRedirectToPage(leaveBalancePage.getCurrentProfileUrl());
            Assert.assertEquals(leaveBalancePage.getCurrentProfileUrl(), driver.getCurrentUrl());
        });

        Given("^I click to search icon of an employee on leave list$", () -> leaveBalancePage.openLeaveHistory(driver));

        Then("^Should open correct history page of that employee$", () -> {
            waitRedirectToPage(leaveBalancePage.getCurrentLeaveHistoryUrl());
            Assert.assertEquals(leaveBalancePage.getCurrentLeaveHistoryUrl(), driver.getCurrentUrl());
        });

        Given("^I fill in search view with name is \"([^\"]*)\"$", (String key) -> leaveBalancePage.searchWithKey(driver, key));

        Then("^Should show list result of employ with name is \"([^\"]*)\"$", (String employeeName) -> {
            new WebDriverWait(getDriver(), TIME_OUT_SECONDS_NORMAL).until(
                    webDriver -> !webDriver.getCurrentUrl().equals(Constant.LEAVE_BALANCE_PAGE_URL));

            Assert.assertEquals(leaveBalancePage.getCurrentEmployeeName(driver), employeeName);
        });

        When("^I select item \"([^\"]*)\"$", (String leaveItem) -> leaveBalancePage.filterItemClick(2));

        Then("^Team will display \"([^\"]*)\"$", (String teamName) -> Assert.assertEquals(leaveBalancePage.getFilterLabel(), teamName));

        And("^Show list result of employ on Android team$", () -> {
            waitRedirectToPage(Constant.LEAVE_BALANCE_PAGE_URL + ";team_id_eq=24");
            Assert.assertEquals(DEFAULT_PAGE_COUNT, leaveBalancePage.getLeaveBalanceListCount(driver));
        });

        Then("^Year on this page should be \"([^\"]*)\"$", (String year) -> Assert.assertEquals(leaveBalancePage.getYear(driver), year));
        When("^I click on previous$", () -> leaveBalancePage.previous(driver));

        When("^I click on page number (\\d+)$", (Integer pageNum) -> leaveBalancePage.gotoPage(driver, pageNum - 1));

        Then("^Should load data at page (\\d+)$", (Integer pageIndex) -> {
            waitRedirectToPage(Constant.LEAVE_BALANCE_PAGE_URL + ";page=" + pageIndex);
            Assert.assertEquals(Constant.LEAVE_BALANCE_PAGE_URL + ";page=" + pageIndex, driver.getCurrentUrl());
        });
        When("^I click to name of an employee$", () -> leaveBalancePage.userNameClick(driver));
        Given("^I click to sysId of an employee on leave list$", () -> leaveBalancePage.sysIdClick(driver));
    }
}
