package stepdefs.menu;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.menu.MenuPage;

/**
 * @author at-hanhnguyen
 */
public class MenuDefinitions extends DriverBase implements En {

    private MenuPage menuPage;

    public MenuDefinitions() {
        menuPage = initPage(getDriver(), MenuPage.class);

        Then("^Show name of account$", () -> Assert.assertTrue(menuPage.checkShowAccountNameShown()));

        And("^Account name is \"([^\"]*)\"$", (String accountName) -> Assert.assertTrue(menuPage.checkTextAccountName(accountName)));

        When("^I click \"([^\"]*)\" item$", (String title) -> menuPage.clickParentItem(title));

        Then("^\"([^\"]*)\" Item changes color to \"([^\"]*)\"$", (String title, String color) -> Assert.assertTrue(menuPage.checkColorParentItemIsWhite(title, color)));

        And("^Should redirect to home page \"([^\"]*)\"$", (String path) -> {
            new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> webDriver.findElement(By.className("notification-header")).findElement(By.tagName("h2")).isDisplayed()
            );
            String url = getDriver().getCurrentUrl();
            Assert.assertEquals(path, url.substring(url.length() - path.length()));
        });

        Given("^\"([^\"]*)\" Item closes$", (String title) -> Assert.assertTrue(menuPage.checkMenuItemClose(title)));

        Then("^Open Child \"([^\"]*)\" item$", (String title) -> Assert.assertFalse(menuPage.checkMenuItemClose(title)));

        When("^I click \"([^\"]*)\" child item$", (String title) -> menuPage.clickChildItem(title));

        Then("^\"([^\"]*)\" child item changes color to \"([^\"]*)\"$", (String title, String color) -> Assert.assertTrue(menuPage.checkColorChildItemIsWhite(title, color)));

        And("^Should redirect to page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(Constant.PORTAL_URL + path, url);
    }
}
