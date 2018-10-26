package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

public class LoginPage extends BasePage<LoginPage> {

    @FindBy(css = "input[formcontrolname=email]")
    private WebElement usernameInput;
    @FindBy(css = "input[formcontrolname=password]")
    private WebElement passwordInput;
    @FindBy(css = ".btn.btn-primary.block.full-width.m-b")
    private WebElement loginButton;
    @FindBy(className = "text-danger")
    private WebElement errorText;

    @Override
    public LoginPage navigateTo(WebDriver webDriver) {
        webDriver.get(Constant.LOGIN_PAGE_URL);
        return this;
    }

    public LoginPage login() {
        loginButton.click();
        return this;
    }

    public LoginPage withUsername(String username) {
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage withPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public final void waitForLoginButton(final WebDriver driver) {
        waitForElement(driver, loginButton, Constant.DEFAULT_TIME_OUT);
    }

    public boolean hasEmail() {
        return isElementPresented(usernameInput);
    }

    public boolean errorMessageIsDisplayed() {
        return errorText.isDisplayed();
    }

    public String getErrorMessage() {
        return errorText.getText();
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public final void waitForErrorMessage(final WebDriver driver) {
        waitForElement(driver, errorText, Constant.DEFAULT_TIME_OUT);
    }
}
