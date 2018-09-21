package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveBalancePage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

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
            driver.get("http://portal-stg.asiantech.vn/leave/balance");
            leaveBalancePage = initPage(getDriver(), LeaveBalancePage.class);
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/balance", driver.getCurrentUrl());
        });

        Given("^I click on all team filter$", () -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance"));
            leaveBalancePage.openFilter(driver);
        });

        Then("^List filter should display$", () -> Assert.assertTrue(leaveBalancePage.filterElementDisplayed()));

        Then("^List balance leave should be (\\d+) item$", (Integer arg0) -> Assert.assertEquals(leaveBalancePage.getLeaveBalanceListCount(driver), (int) arg0));

        Given("^I click to avatar of an employee$", () -> leaveBalancePage.avatarClick(driver));

        Then("^Should open correct profile page of that employee$", () -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals(leaveBalancePage.getCurrentProfileUrl()));
            Assert.assertEquals(leaveBalancePage.getCurrentProfileUrl(), driver.getCurrentUrl());
        });

        Given("^I click to search icon of an employee on leave list$", () -> leaveBalancePage.openLeaveHistory(driver));

        Then("^Should open correct history page of that employee$", () -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals(leaveBalancePage.getCurrentLeaveHistoryUrl()));
            Assert.assertEquals(leaveBalancePage.getCurrentLeaveHistoryUrl(), driver.getCurrentUrl());
        });

        Given("^I fill in search view with name is \"([^\"]*)\"$", (String arg0) -> leaveBalancePage.searchWithKey(driver, arg0));

        Then("^Should show list result of employ with name is \"([^\"]*)\"$", (String arg0) -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> !webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance"));
            Assert.assertEquals(leaveBalancePage.getCurrentEmployeeName(driver), arg0);
        });

        When("^I select item \"([^\"]*)\"$", (String arg0) -> leaveBalancePage.filterItemClick(1));

        Then("^Team will display \"([^\"]*)\"$", (String arg0) -> Assert.assertEquals(leaveBalancePage.getFilterLabel(), arg0));

        And("^Show list result of employ on Android team$", () -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance;team_id_eq=24"));
            Assert.assertEquals(DEFAULT_PAGE_COUNT, leaveBalancePage.getLeaveBalanceListCount(driver));
        });

        Then("^Year on this page should be \"([^\"]*)\"$", (String arg0) -> Assert.assertEquals(leaveBalancePage.getYear(driver), arg0));
        When("^I click on previous$", () -> leaveBalancePage.previous(driver));

        When("^I click on page number (\\d+)$", (Integer arg0) -> {
            leaveBalancePage.gotoPage(driver, arg0 - 1);
        });

        Then("^Should load data at page (\\d+)$", (Integer arg0) -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance;page=" + arg0));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/balance;page=" + arg0, driver.getCurrentUrl());
        });
        When("^I click to name of an employee$", () -> {
            leaveBalancePage.userNameClick(driver);
        });
        Given("^I click to sysId of an employee on leave list$", () -> leaveBalancePage.sysIdClick(driver));
    }
}
