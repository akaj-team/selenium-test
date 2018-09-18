package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveBalancePage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

public class LeaveBalanceDefinitions extends DriverBase implements En {

    private static final int DEFAULT_PAGE_COUNT = 50;
    private WebDriver webDriver;
    private LeaveBalancePage leaveBalancePage;

    public LeaveBalanceDefinitions() {
        try {
            webDriver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Display leave balance page$", () -> {
            webDriver.get("http://portal-stg.asiantech.vn/leave/balance");
            leaveBalancePage = initPage(getDriver(), LeaveBalancePage.class);
            new WebDriverWait(webDriver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/balance", webDriver.getCurrentUrl());
        });

        Given("^I click on all team filter$", () -> {
            new WebDriverWait(getDriver(), 5).until(
                    webDriver -> webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance"));
            leaveBalancePage.openFilter(webDriver);
        });

        Then("^List filter should display$", () -> Assert.assertTrue(leaveBalancePage.filterElementDisplayed()));

        Then("^List balance leave should be (\\d+) item$", (Integer arg0) -> Assert.assertEquals(leaveBalancePage.getFirstPageLeaveBalanceList(webDriver), (int) arg0));

        Given("^I click to avatar of an employee$", () -> leaveBalancePage.openProfile(webDriver));

        Then("^Should open correct profile page of that employee$", () -> {
            new WebDriverWait(getDriver(), 5).until(
                    webDriver -> webDriver.getCurrentUrl().equals(leaveBalancePage.getCurrentProfileUrl()));
            Assert.assertEquals(leaveBalancePage.getCurrentProfileUrl(), webDriver.getCurrentUrl());
        });

        Given("^I click to search icon of an employee on leave list$", () -> leaveBalancePage.openLeaveHistory(webDriver));

        Then("^Should open correct history page of that employee$", () -> {
            new WebDriverWait(getDriver(), 5).until(
                    webDriver -> webDriver.getCurrentUrl().equals(leaveBalancePage.getCurrentLeaveHistoryUrl()));
            Assert.assertEquals(leaveBalancePage.getCurrentLeaveHistoryUrl(), webDriver.getCurrentUrl());
        });

        Given("^I fill in search view with name is \"([^\"]*)\"$", (String arg0) -> leaveBalancePage.searchWithKey(webDriver, arg0));

        Then("^Should show list result of employ with name is \"([^\"]*)\"$", (String arg0) -> {
            new WebDriverWait(getDriver(), 5).until(
                    webDriver -> !webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance"));
            Assert.assertEquals(leaveBalancePage.getCurrentEmployeeName(webDriver), arg0);
        });

        When("^I select item \"([^\"]*)\"$", (String arg0) -> leaveBalancePage.filterItemClick(1));

        Then("^Team will display \"([^\"]*)\"$", (String arg0) -> Assert.assertEquals(leaveBalancePage.getFilterLabel(), arg0));

        And("^Show list result of employ on Android team$", () -> {
            new WebDriverWait(getDriver(), 5).until(
                    webDriver -> webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/leave/balance;team_id_eq=24"));
            Assert.assertEquals(DEFAULT_PAGE_COUNT, leaveBalancePage.getFirstPageLeaveBalanceList(webDriver));
        });
    }
}
