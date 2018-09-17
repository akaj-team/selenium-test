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
        Then("^List filter should display$", () -> {
            Assert.assertTrue(leaveBalancePage.filterElementDisplayed());
        });
        Then("^List balance leave should be (\\d+) item$", (Integer arg0) -> {
            Assert.assertEquals(leaveBalancePage.getFirstPageLeaveBalanceList(webDriver), (int) arg0);
        });
    }
}
