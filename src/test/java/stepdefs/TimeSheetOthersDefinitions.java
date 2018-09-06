package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.TimeSheetOthers;

public class TimeSheetOthersDefinitions extends DriverBase implements En {
    private TimeSheetOthers timeSheetOthers;
    private WebDriver driver;

    public TimeSheetOthersDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^I move to timeSheet of others page$", () -> {
            driver.get("http://portal-stg.asiantech.vn/timesheet/approval");
            timeSheetOthers = initPage(driver, TimeSheetOthers.class);
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn/timesheet/approval", driver.getCurrentUrl());
        });

        Then("^I see button this week is disable$", () -> {
            Assert.assertFalse(timeSheetOthers.getThisWeekButton(driver).isEnabled());
        });

        Then("^I see button pre and next is enable$", () -> {
            Assert.assertTrue(timeSheetOthers.getPreButton(driver).isEnabled());
            Assert.assertTrue(timeSheetOthers.getNextButton(driver).isEnabled());
        });

        Then("^I see tab \"([^\"]*)\" is selected$", (String arg0) -> {
            Assert.assertEquals(timeSheetOthers.getSelectedTab(driver).getText(), arg0);
        });

        And("^I see dropdown \"([^\"]*)\" is default value$", (String arg0) -> {
            Assert.assertEquals(timeSheetOthers.getDropDownStatus(driver).getText(), arg0);
        });

        Then("^I see list label status on bottom is show$", () -> {
            Assert.assertTrue(timeSheetOthers.getLabelSubmitted(driver).isDisplayed());
            Assert.assertTrue(timeSheetOthers.getLabelRejected(driver).isDisplayed());
            Assert.assertTrue(timeSheetOthers.getLabelApproved(driver).isDisplayed());
        });

        Then("^I see label \"([^\"]*)\" is show$", (String arg0) -> {
            Assert.assertEquals(timeSheetOthers.getTimeSheetTile(driver).getText(), arg0);
            Assert.assertTrue(timeSheetOthers.getTimeSheetTile(driver).isDisplayed());
        });

        Then("^I see dropdown status with \"([^\"]*)\" is default value$", (String arg0) -> {
            Assert.assertEquals(timeSheetOthers.getDropDownStatus(driver).getText(), arg0);
        });

        Then("^I see dropdown subordinator with \"([^\"]*)\" is default value$", (String arg0) -> {
            Assert.assertEquals(timeSheetOthers.getDropDownSubordinator(driver).getText(), arg0);

        });
    }
}
