package stepdefs;

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

        Then("^Navigate to correct page", () -> {
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(notificationMenuElement.getDestinationPath(), driver.getCurrentUrl());
        });
    }
}
