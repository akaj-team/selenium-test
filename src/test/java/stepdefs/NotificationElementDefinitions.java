package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.NotificationMenuElement;

public class NotificationElementDefinitions extends DriverBase implements En {
    private NotificationMenuElement notificationMenuElement;

    public NotificationElementDefinitions() {
        notificationMenuElement = initPage(getDriver(), NotificationMenuElement.class);

        When("^I click on notification icon$", () -> notificationMenuElement.openNotification());

        Then("^Notification menu will display$", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        Given("^Notification menu is displayed$", () -> {
            notificationMenuElement.openNotification();
            Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay());
        });

        When("^I click on seeAll$", () -> notificationMenuElement.seeAll());

        Then("^Should navigate to correct page", () -> {
            waitAjaxLoadSuccess();
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), "http://portal-stg.asiantech.vn/homepage");
        });

        When("^I click on menu item$", () -> {
            notificationMenuElement.notificationMenuItemClick();
            waitAjaxLoadSuccess();
        });

        Then("^Navigate to correct detail page", () -> {
            Assert.assertEquals(getDriver().getCurrentUrl(), notificationMenuElement.getDestinationPath());
        });

        When("^I click on reload text$", () -> notificationMenuElement.reload());

        Then("^List notification should be reload$", () -> {
            notificationMenuElement.waitForNotificationReload(getDriver());
            Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay());
        });

        Then("^First ten notifications displayed$", () -> Assert.assertEquals(notificationMenuElement.getNotificationList(getDriver()), 10));

        When("^I scroll to item at last of list notification$", () -> {
            // Write code here that turns the phrase above into concrete actions
            notificationMenuElement.scrollToEndOfList(getDriver());
        });

        Then("^Next page of notification should displayed$", () -> {
            Assert.assertEquals(notificationMenuElement.getNotificationList(getDriver()), 20);
        });
    }

    private void waitAjaxLoadSuccess() {
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
