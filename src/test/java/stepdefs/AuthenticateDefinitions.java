package stepdefs;

import cucumber.api.java8.En;
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
            if (!isLogged) {
                clearCookies();

                if (!getDriver().getCurrentUrl().startsWith("data")) {
                    getDriver().executeScript("window.localStorage.clear();");
                }
                Account account = accounts.get(position);
                getDriver().get(Constant.LOGIN_PAGE_URL);
                loginPage = initPage(getDriver(), LoginPage.class);
                loginPage.waitForLoginButton();
                loginPage.withUsername(account.email).withPassword(account.password).login();

                HomePage homePage = initPage(getDriver(), HomePage.class);
                homePage.waitForWelcomeMessage(getDriver());
                Assert.assertTrue(homePage.welcomeTestIsDisplayed());
                isLogged = true;
            }
        });

        Given("^I am an unauthenticated user$", this::logoutCurrentSession);
    }

    private void logoutCurrentSession() {
        clearCookies();
        if (!getDriver().getCurrentUrl().startsWith("data")) {
            getDriver().executeScript("window.localStorage.clear();");
        }
        isLogged = false;
    }

    private void initMap() {
        accounts.put("EM", new Account("stg.tien.hoang@asiantech.vn", "Abc123@@"));
    }
}
