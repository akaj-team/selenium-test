package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HomePage;
import vn.asiantech.page.LoginPage;
import vn.asiantech.page.NotificationMenuElement;

public class NotificationElementDefinitions extends DriverBase implements En {
    private NotificationMenuElement notificationMenuElement;
    private HomePage homePage;
    private LoginPage loginPage;

    public NotificationElementDefinitions() {
        notificationMenuElement = initPage(getDriver(), NotificationMenuElement.class);
        homePage = initPage(getDriver(), HomePage.class);
        loginPage = initPage(getDriver(), LoginPage.class);

        When("^I click on notification icon$", () -> notificationMenuElement.openNotification());

        Then("^Notification menu will display$", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        Given("^Notification menu is displayed$", () -> {
            notificationMenuElement.openNotification();
            Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay());
        });

        When("^I click on seeAll$", () -> notificationMenuElement.seeAll());

        Then("^Should navigate to correct page", () -> {
            waitAjaxLoadSuccess();
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), Constant.HOME_PAGE_URL);
        });

        When("^I click on menu item$", () -> {
            notificationMenuElement.notificationMenuItemClick(getDriver());
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

        Given("^I open portal page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            getDriver().get(Constant.PORTAL_URL);
        });

        Given("^Check current account employee code is my employee code \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            switch (getDriver().getCurrentUrl()) {
                case Constant.HOME_PAGE_URL:
                    if (homePage.getEmployeeCode(getDriver()).equals(arg0)) {
                        // Do nothing
                    } else {
                        homePage.logout();
                        new WebDriverWait(getDriver(), 10).until(
                                webDriver -> webDriver.getCurrentUrl().equals(Constant.LOGIN_PAGE_URL));

                        loginPage.withUsername("stg.tien.hoang@asiantech.vn");
                        loginPage.withPassword("Abc123@@");
                        loginPage.login();
                    }
                    break;
                case Constant.LOGIN_PAGE_URL:
                    loginPage.withUsername("stg.tien.hoang@asiantech.vn");
                    loginPage.withPassword("Abc123@@");
                    loginPage.login();
                    break;
            }
        });

        Then("^Employee code should be \"([^\"]*)\"$", (String arg0) -> {
            // Write code here that turns the phrase above into concrete actions
            new WebDriverWait(getDriver(), 10).until(
                    webDriver -> webDriver.getCurrentUrl().equals(Constant.HOME_PAGE_URL));
            Assert.assertEquals(homePage.getEmployeeCode(getDriver()), arg0);
        });
        When("^I click on mark all as read text$", () -> {
            // Write code here that turns the phrase above into concrete actions
            notificationMenuElement.markAllAsRead();
        });
        Then("^List notification should be hide$", () -> {
            // Write code here that turns the phrase above into concrete actions
            Assert.assertFalse(notificationMenuElement.notificationElementIsDisplay());
        });
    }

    private void waitAjaxLoadSuccess() {
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
