package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

public class LoginPage extends BasePage<LoginPage> {

    @FindBy(css = "input[type=email]")
    private WebElement usernameInput;

    @FindBy(css = "input[type=password]")
    private WebElement passwordInput;

    @FindBy(css = ".btn.btn-primary.block.full-width.m-b")
    private WebElement loginButton;

    @FindBy(className = "text-danger")
    private WebElement errorText;

    private WebDriver driver;

    public LoginPage(final WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final LoginPage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.LOGIN_PAGE_URL);
        return this;
    }

    public final LoginPage login() {
        loginButton.click();
        return this;
    }

    public final LoginPage withUsername(final String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public final LoginPage withPassword(final String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public final void waitForLoginButton() {
        waitForElement(driver, loginButton, Constant.DEFAULT_TIME_OUT);
    }

    public final boolean hasEmail() {
        return isElementPresented(usernameInput);
    }

    public final boolean errorMessageIsDisplayed() {
        return errorText.isDisplayed();
    }

    public final String getErrorMessage() {
        return errorText.getText();
    }

    public final WebElement getLoginButton() {
        return loginButton;
    }

    public final void waitForErrorMessage(final WebDriver driver, final int timeOutInSecond) {
        waitForElement(driver, errorText, timeOutInSecond);
    }
}
