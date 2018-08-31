package stepdefs;

import cucumber.api.Pending;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MyLeavePage;

public class MyLeaveDefinitions extends DriverBase implements En {
    private WebDriver driver;

    private MyLeavePage myLeavePage

    public MyLeaveDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Given("^I logged in with a employee account$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on Leave in menu$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Menu Leave drop down$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on line My Leave$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^My Leave page is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^Notification displayed a notification$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on item My Leave$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on menu Status$", () -> {
            myLeavePage.clickMenuStatus();
        });
        Then("^Menu drop down$", () -> {
            Assert.assertTrue(myLeavePage.dropDownMenuStatus());
        });
        When("^I click on Pending in menu$", () -> {
            myLeavePage.clickItemMenuStatus("Pending");
        });
        Then("^My Leave page with status Pending is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on Approved in menu$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^My Leave page with status Approved is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^My Leave page display No records found$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on Rejected in menu$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^My Leave page with status Rejected is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on a SYSID$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Leave Detail page is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on a name Manager$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Employee Detail page is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on icon search under Action element$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on Leave Request button$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^Leave Request page is displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
