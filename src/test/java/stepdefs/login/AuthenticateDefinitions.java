package stepdefs.login;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.object.Account;
import vn.asiantech.page.home.HomePage;
import vn.asiantech.page.login.LoginPage;

public class AuthenticateDefinitions extends DriverBase implements En {

    public AuthenticateDefinitions() {

        LoginPage loginPage = initPage(getDriver(), LoginPage.class);
        HomePage homePage = initPage(getDriver(), HomePage.class);

        Given("^I am logged in as a team manager$", () -> {
            if (!isButtonLogoutDisplayed()) {
                Account account = new Account("stg.lam.le2@asiantech.vn", "Abc123@@");
                getDriver().get(Constant.LOGIN_PAGE_URL);
                loginPage.waitForLoginButton();
                loginPage.withUsername(account.email).withPassword(account.password).login();

                homePage.waitForWelcomeMessage();
                Assert.assertTrue(homePage.welcomeTestIsDisplayed());
            }
        });

        Given("^I am an unauthenticated user$", this::logoutCurrentSession);
    }

    private void logoutCurrentSession() {
        try {
            getDriver().findElement(By.id("btn-logout")).click();
        } catch (NotFoundException exception) {
            //no-opt
        }
        waitVisibilityOfElement(getDriver(), By.cssSelector(".middle-box.text-center.loginscreen"));
    }

    private boolean isButtonLogoutDisplayed() {
        try {
            getDriver().findElement(By.id("btn-logout")).isDisplayed();
            return true;
        } catch (NotFoundException exception) {
            return false;
        }
    }
}
