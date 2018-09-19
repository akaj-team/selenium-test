package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveRequestPage;

import java.util.List;

public class LeaveRequestDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;
    private LeaveRequestPage leaveRequestPage;


    public LeaveRequestDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^I am logged in as an team member$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            leaveRequestPage = initPage(getDriver(), LeaveRequestPage.class);
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                //Not logged in
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.thien.dang2@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, 5).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
        });

        And("^Display leave request page$", () -> {
            driver.get("http://portal-stg.asiantech.vn/leave/request");
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/request", driver.getCurrentUrl());
        });

        And("^Marriage Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextMarriageLeave(number)));

        And("^Overtime Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextOvertimeLeave(number)));

        And("^Paternal Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextPaternalLeave(number)));

        When("^I enter the message$", () -> leaveRequestPage.withMessage(getDriver()));

        And("^Annual Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextAnnualLeave(getDriver(), number)));

        And("^I choose type leave is \"([^\"]*)\"$", (String status) -> leaveRequestPage.clickItemMenuType(getDriver(), status));

        Then("^Show message is \"([^\"]*)\"$", (String mess) -> Assert.assertTrue(leaveRequestPage.isShowMessage(mess)));

        When("^I click on timeFrom box leave time$", () -> leaveRequestPage.clickTimeFromBox());

        And("^I click on timeTo box leave time$", () -> leaveRequestPage.clickTimeToBox());

        And("^Show date request$", () -> Assert.assertTrue(leaveRequestPage.isDateRequestShow(getDriver())));

        When("^I choose afternoon on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseAfternoon(getDriver())));

        When("^I choose morning on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseMorning(getDriver())));

        Then("^Submit button is enabled$", () -> Assert.assertTrue(leaveRequestPage.isEnableSubmitButton()));

        Then("^Submit button still disabled$", () -> Assert.assertFalse(leaveRequestPage.isEnableSubmitButton()));

        And("^I click menu type of leave$", () -> leaveRequestPage.clickMenuTypeOfLeave());

        Then("^Menu type of leave drop down$", () -> Assert.assertTrue(leaveRequestPage.isMenuDropDown()));

        Then("^Calendar timeFrom display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow("timeFrom")));

        Then("^Calendar timeTo display$", () -> Assert.assertTrue(leaveRequestPage.isCalendarShow("timeTo")));

        And("^I choose all day on show date request$", () -> Assert.assertTrue(leaveRequestPage.chooseAllday(getDriver())));

        And("^Check date is \"([^\"]*)\"$", (String date) -> Assert.assertTrue(leaveRequestPage.checkDateInDateRequest(0, date)));

        When("^I click remove button$", () -> leaveRequestPage.clickRemoveButton(0));

        Then("^Date request is removed$", () -> Assert.assertFalse(leaveRequestPage.isRemoveDateRequest()));

        Given("^I chose type leave is \"([^\"]*)\"$", (String status) -> leaveRequestPage.setNonePaidInTypeOfLeave(getDriver(), status));

        Given("^Enter full information$", () -> leaveRequestPage.enterFullInfor(getDriver()));

        When("^I click submit$", () -> leaveRequestPage.clickSubmit());

        Then("^Show dialog confirmation$", () -> Assert.assertTrue(leaveRequestPage.isShowDialog()));

        Given("^Dialog was showed$", () -> leaveRequestPage.showDialog(getDriver()));

        When("^I click submit on dialog$", () -> leaveRequestPage.clickSubmitButtonInDialog());

        Then("^Redirect to leave detail$", () -> leaveRequestPage.redirectLeaveDetail());

        When("^I click cancel on dialog$", () -> leaveRequestPage.clickCancelButtonInDialog());

        Then("^Dialog disappeared$", () -> Assert.assertTrue(leaveRequestPage.isDialogDisappeared()));

        And("^I choose today in timeForm calendar$", () -> leaveRequestPage.chooseTime("timeFrom"));

        And("^I choose today in timeTo calendar$", () -> leaveRequestPage.chooseTime("timeTo"));
    }
}
