package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.NotificationMenuElement;

public class NotificationElementDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private NotificationMenuElement notificationMenuElement;

    public NotificationElementDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notificationMenuElement = initPage(getDriver(), NotificationMenuElement.class);

        When("^I click on notification icon", () -> notificationMenuElement.openNotification());

        Then("^Notification menu will display", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        Given("^Notification menu is displayed", () -> {
            notificationMenuElement.openNotification();
            Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay());
        });

        When("^I click on seeAll", () -> notificationMenuElement.seeAll());

        Then("^Should navigate to correct page", () -> {
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), "http://portal-stg.asiantech.vn/homepage");
        });

        When("^I click on menu item", () -> notificationMenuElement.notificationMenuItemClick());

        Then("^Navigate to correct detail page", () -> {
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), driver.getCurrentUrl());
        });

        When("^I click on reload text$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^List notification should be reload$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^First ten notifications displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        When("^I scroll to notification at ten$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^Next page of notification should displayed$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
