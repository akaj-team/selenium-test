package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.object.Account;
import vn.asiantech.page.HomePage;
import vn.asiantech.page.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class AuthenticateDefinitions extends DriverBase implements En {
    private Map<String, Account> accounts = new HashMap<>();

    private LoginPage loginPage;

    public AuthenticateDefinitions() {
        initMap();
        Given("^I am logged in as (a|an) \"([^\"]*)\"$", (String arg0, String position) -> {
            waitButtonLogout();
            if (!isButtonLogoutDisplayed()) {
                clearCookies();

                if (!getDriver().getCurrentUrl().startsWith("data")) {
                    getDriver().executeScript("window.localStorage.clear();");
                }
                Account account = accounts.get(position);
                getDriver().get(Constant.LOGIN_PAGE_URL);
                loginPage = initPage(getDriver(), LoginPage.class);
                loginPage.waitForLoginButton(getDriver());
                loginPage.withUsername(account.email).withPassword(account.password).login();

                HomePage homePage = initPage(getDriver(), HomePage.class);
                homePage.waitForWelcomeMessage(getDriver());
                Assert.assertTrue(homePage.welcomeTestIsDisplayed());
            }
        });

        Given("^I am an unauthenticated user$", this::logoutCurrentSession);
    }

    private void logoutCurrentSession() {
        if (isButtonLogoutDisplayed()) {
            clearCookies();
            if (!getDriver().getCurrentUrl().startsWith("data")) {
                getDriver().executeScript("window.localStorage.clear();");
            }
        }
    }

    private void initMap() {
        accounts.put("EM", new Account("stg.tien.hoang@asiantech.vn", "Abc123@@"));
    }

    private void waitButtonLogout() {
        try {
            new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-logout")));
        } catch (TimeoutException exception) {
            //no-opt
        }
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
