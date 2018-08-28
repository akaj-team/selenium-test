package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
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

        Then("^Notification menu is displayed", () -> Assert.assertTrue(notificationMenuElement.notificationElementIsDisplay()));

        When("^I click on seeAll", () -> notificationMenuElement.seeAll());

        Then("^navigate to home page", () -> {
            notificationMenuElement.navigateToHomePage(driver);
            Assert.assertEquals("http://portal-stg.asiantech.vn/homepage", driver.getCurrentUrl());
        });
    }
}
