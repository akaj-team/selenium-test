package stepdefs.notice;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.notice.NotificationMenuElement;

/**
 * NotificationElementDefinition
 *
 * @author at-vinh.huynh
 */
public class NotificationElementDefinitions extends DriverBase implements En {
    private static final int NOTIFICATION_PER_PAGE = 10;

    private NotificationMenuElement notificationMenuElement;

    /**
     * Default constructor method
     */
    public NotificationElementDefinitions() {
        notificationMenuElement = initPage(getDriver(), NotificationMenuElement.class);

        When("^I click on notification icon$", () -> notificationMenuElement.openNotification());

        Then("^Notification menu will display$", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        Given("^Notification menu is displayed$", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        When("^I click on seeAll$", () -> notificationMenuElement.seeAll());

        Then("^Should navigate to correct page", () -> {
            waitAjaxLoadSuccess();
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), Constant.HOME_PAGE_URL);
        });

        When("^I click on menu item$", () -> notificationMenuElement.notificationMenuItemClick());

        Then("^Navigate to correct detail page", () -> {
            waitAjaxLoadSuccess();
            waitForPageDisplayed(getDriver(), notificationMenuElement.getDestinationPath(), By.id("page-wrapper"));
        });

        When("^I click on reload text$", () -> notificationMenuElement.reload());

        Then("^List notification should be reload$", () -> {
            notificationMenuElement.waitForNotificationReload();
            waitAjaxLoadSuccess();
            Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay());
        });

        Then("^First ten notifications displayed$", () -> Assert.assertEquals(notificationMenuElement.getNotificationList(), NOTIFICATION_PER_PAGE));

        When("^I scroll to item at last of list notification$", () -> {
            // Write code here that turns the phrase above into concrete actions
            notificationMenuElement.scrollToEndOfList();
        });

        Then("^Next page of notification should displayed$", () -> Assert.assertEquals(notificationMenuElement.getNotificationList(), NOTIFICATION_PER_PAGE * 2));

        Given("^I open portal page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            getDriver().get(Constant.PORTAL_URL);
        });

        When("^I click on mark all as read text$", () -> notificationMenuElement.markAllAsRead());

        Then("^List notification should be hide$", () -> Assert.assertFalse(notificationMenuElement.notificationElementIsDisplay()));
    }

    private void waitAjaxLoadSuccess() {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
