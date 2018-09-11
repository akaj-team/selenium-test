package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

public class HomePage extends BasePage<HomePage> {
    @FindBy(className = "welcome-message")
    private WebElement welcomeText;

    @FindBy(className = "fa-sign-out")
    private WebElement logoutButton;

    @FindBy(css = ".text-muted.text-xs.block")
    private WebElement employeeCodeText;

    @Override
    public HomePage navigateTo(WebDriver webDriver) {
        webDriver.get(Constant.HOME_PAGE_URL);
        return this;
    }

    public final boolean hasWelcomeMessage() {
        return isElementPresented(welcomeText);
    }

    public final boolean welcomeTestIsDisplayed() {
        return welcomeText.isDisplayed();
    }

    public final boolean hasLogoutButton() {
        return isElementPresented(logoutButton);
    }

    public final void waitForWelcomeMessage(final WebDriver driver) {
        waitForElementPresented(driver, welcomeText, Constant.DEFAULT_TIME_OUT / 2);
    }

    public HomePage logout() {
        logoutButton.click();
        return this;
    }

    public final String getEmployeeCode(final WebDriver driver) {
        waitForElementDisplay(driver, employeeCodeText, Constant.DEFAULT_TIME_OUT);
        return employeeCodeText.getText().split("-")[0].trim();
    }
}
