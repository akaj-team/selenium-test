package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class LoginPage extends BasePage<LoginPage> {
    @FindBy(css = "input[formcontrolname=email]")
    private WebElement usernameInput;
    @FindBy(css = "input[formcontrolname=password]")
    private WebElement passwordInput;
    @FindBy(className = "btn-primary")
    private WebElement loginButton;
    @FindBy(className = "text-danger")
    private WebElement errorText;

    @Override
    public LoginPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/auth/login");
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
        waitForElementPresented(driver, errorText, timeOutInSecond);
        return this;
    }

}
