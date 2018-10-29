package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
            if(!isButtonLogoutDisplayed()){
                Account account = accounts.get(position);
                getDriver().get("http://portal-stg.asiantech.vn/auth/login");
                loginPage = initPage(getDriver(), LoginPage.class);
                loginPage.waitForLoginButton();
                loginPage.withUsername(account.email).withPassword(account.password).login();

                HomePage homePage = initPage(getDriver(), HomePage.class);
                homePage.waitForWelcomeMessage(getDriver());
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

    private void initMap() {
        accounts.put("EM", new Account("stg.tien.hoang@asiantech.vn", "Abc123@@"));
    }
}
