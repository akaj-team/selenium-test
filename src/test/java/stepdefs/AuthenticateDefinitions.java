package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.object.Account;
import vn.asiantech.page.HomePage;
import vn.asiantech.page.LoginPage;

public class AuthenticateDefinitions extends DriverBase implements En {

    public AuthenticateDefinitions() {

        LoginPage loginPage = initPage(getDriver(), LoginPage.class);
        HomePage homePage = initPage(getDriver(), HomePage.class);

        Given("^I am logged in as a team manager$", () -> {
            if (!isButtonLogoutDisplayed()) {
                Account account = getAccount();
                getDriver().get(Constant.LOGIN_PAGE_URL);
                loginPage.waitForLoginButton();
                loginPage.withUsername(account.email).withPassword(account.password).login();

                homePage.waitForWelcomeMessage();
                Assert.assertTrue(homePage.welcomeTestIsDisplayed());
            }
        });

        Given("^I am an unauthenticated user$", this::logoutCurrentSession);
    }

    private Account getAccount() {
        Account account;
        switch (getDriver().getCapabilities().getBrowserName()) {
            case Constant.BROWSER_CHROME:
                account = new Account("stg.tien.hoang@asiantech.vn", "Abc123@@");
                break;
            case Constant.BROWSER_FIREFOX:
                account = new Account("stg.tu.le.2@asiantech.vn", "Abc123@@");
                break;
            case Constant.BROWSER_SAFARI:
                account = new Account("stg.thien.dang2@asiantech.vn", "Abc123@@");
                break;
            case Constant.BROWSER_IE:
                account = new Account("stg.hang.nguyen@asiantech.vn", "Abc123@@");
                break;
            case Constant.BROWSER_EDGE:
                account = new Account("stg.lam.le2@asiantech.vn", "Abc123@@");
                break;
            case Constant.BROWSER_OPERA:
                account = new Account("automation-01@asiantech.vn", "Abc123@@");
                break;
            default:
                account = new Account("stg.tien.hoang@asiantech.vn", "Abc123@@");
        }

        return account;
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
