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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

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

    public LoginPage waitForLoginButton() {
        waitForElement(driver, loginButton, Constant.DEFAULT_TIME_OUT);
        return this;
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

    public LoginPage waitForErrorMessage(WebDriver driver, int timeOutInSecond) {
        waitForElement(driver, errorText, timeOutInSecond);
        return this;
    }

}
