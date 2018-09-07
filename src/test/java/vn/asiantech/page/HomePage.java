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
        waitForElementPresented(driver, welcomeText, 5);
    }

    public HomePage logout() {
        logoutButton.click();
        return this;
    }

    public String getEmployeeCode(WebDriver driver) {
        waitForElementDisplay(driver, employeeCodeText, 10);
        return employeeCodeText.getText().split("-")[0].trim();
    }
}
