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
import vn.asiantech.page.LeaveRequestPage;

import java.util.List;

import static vn.asiantech.base.DriverBase.getDriver;

public class LeaveRequestDefinitions implements En {
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
        And("^And Annual Leave is \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^Marriage Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextMarriageLeave(getDriver(), number)));

        And("^Overtime Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextOvertimeLeave(getDriver(), number)));

        And("^Paternal Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextPaternalLeave(getDriver(), number)));

        When("^I enter the message$", () -> leaveRequestPage.withMessage());

        Then("^Submit button still not enabled$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        And("^Annual Leave is \"([^\"]*)\"$", (String number) -> Assert.assertTrue(leaveRequestPage.checkTextAnnualLeave(getDriver(), number)));

        And("^I choose type leave is \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Show message is \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on box leave time$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Calendar not display$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on time from box leave time$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^I click on time to box leave time$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Calendar display$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I choose one day in calendar$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Show \"([^\"]*)\" in time from box$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Show \"([^\"]*)\" in time to box$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^Show date request$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I choose afternoon on show date request$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Move circle form all day to afternoon$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I choose morning on show date request$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Move circle form afternoon to morning$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Submit button is enabled$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Submit button still disabled$", () -> Assert.assertFalse(leaveRequestPage.isEnableSubmitButton()));
    }
}
