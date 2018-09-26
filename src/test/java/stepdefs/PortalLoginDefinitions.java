package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HomePage;
import vn.asiantech.page.LoginPage;

public class PortalLoginDefinitions extends DriverBase implements En {
    private LoginPage loginPage;
    private HomePage homePage;

    public PortalLoginDefinitions() {
        clearCookies();
        Given("^I open home page$", () -> getDriver().get("http://portal-stg.asiantech.vn"));

        Then("^browser should redirect to \"([^\"]*)\"$", (String path) -> {
            new WebDriverWait(getDriver(), 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = getDriver().getCurrentUrl();
            Assert.assertEquals(path, url.substring(url.length() - path.length()));
            loginPage = initPage(getDriver(), LoginPage.class);
        });

        Given("^I enter my username with \"([^\"]*)\"$", (String email) -> {
            loginPage.withUsername(email);
        });

        And("^I fill in password with \"([^\"]*)\"$", (String pwd) -> {
            loginPage.withPassword(pwd);
        });

        When("^I click on login button$", () -> {
            loginPage.login();
        });

        Then("^I should see the welcome message$", () -> {
            homePage = initPage(getDriver(), HomePage.class);
            homePage.waitForWelcomeMessage(getDriver());
            Assert.assertTrue(homePage.hasWelcomeMessage());
            Assert.assertTrue(homePage.welcomeTestIsDisplayed());
        });

        Then("^Login button will be disabled$", () -> Assert.assertFalse(loginPage.getLoginButton().isEnabled()));

        Then("^Error message should display and show \"([^\"]*)\"$", (String warning) -> {
            loginPage.waitForErrorMessage(getDriver(), 5);
            Assert.assertTrue(loginPage.errorMessageIsDisplayed());
            Assert.assertEquals(warning, loginPage.getErrorMessage());
        });
        Then("^I click on logout button$", () -> {
            homePage.logout();
        });
    }
}
