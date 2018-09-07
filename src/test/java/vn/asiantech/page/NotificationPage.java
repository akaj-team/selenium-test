package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class NotificationPage extends BasePage<NotificationPage> {

    @FindBy(className = "welcome-message")
    private WebElement welcomeText;

    @FindBy(className = "fa-sign-out")
    private WebElement logoutButton;

    @Override
    public NotificationPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/homepage");
        return this;
    }

    public boolean hasWelcomeMessage() {
        return isElementPresented(welcomeText);
    }

    public boolean welcomeTestIsDisplayed() {
        return welcomeText.isDisplayed();
    }

    public boolean hasLogoutButton() {
        return isElementPresented(logoutButton);
    }

    public void waitForWelcomeMessage(WebDriver driver) {
        waitForElement(driver, welcomeText, 5);
    }

    public NotificationPage logout() {
        logoutButton.click();
        return this;
    }
}
