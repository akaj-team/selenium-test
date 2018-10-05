package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.WikiPage;

import java.util.List;

/**
 *
 * @author at-anh.quach
 * WikiDefinitions
 */

public class WikiDefinitions extends DriverBase implements En {
    private static final int TIMEOUTINSECONDS = 5;

    private WebDriver driver;
    private WikiPage wikiPage;
    private WebElement usernameInput;
    private WebElement passwordInput;

    public WikiDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^I am logged in as an team member$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            wikiPage = initPage(getDriver(), WikiPage.class);
            new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                //Not logged in
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.thien.dang2@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
        });

        When("^I click on name author$", () -> wikiPage.clickAuthor());

        Then("^Redirect employee detail page$", () -> displayPage("/organisation/employees/42"));

        When("^I click on child page button$", () -> wikiPage.clickChildPageButton());

        Then("^Redirect new page page$", () -> displayPage("/wiki/168/new"));

        When("^I click on update button$", () -> wikiPage.clickUpdateButton());

        Then("^Redirect update wiki page$", () -> displayPage("/wiki/168/update"));

        When("^I click on child page title$", () -> wikiPage.clickTitle());

        Then("^Redirect wiki detail page$", () -> displayPage("/wiki/190"));

        Then("^Can not click delete button$", () -> Assert.assertFalse(wikiPage.isEnableDeleteButton()));

        When("^I click on icon package Wiki$", () -> wikiPage.clickIconPackageWikiHome());

        And("^Wiki page displayed$", () -> displayPage("/wiki/168"));

        Then("^Categories disappeared$", () -> Assert.assertTrue(wikiPage.isRemoveCategories()));

        When("^I click on icon package Company Document$", () -> wikiPage.clickIconPackageCompanyDocument());

        Then("^Categories inside hide$", () -> Assert.assertFalse(wikiPage.isHideCategoryInside()));

        When("^I click on icon package Company Document again$", () -> wikiPage.clickIconPackageCompanyDocumentAgain());

        Then("^Categories inside show$", () -> Assert.assertTrue(wikiPage.isHideCategoryInside()));
    }

    private void displayPage(final String path) {
        driver.get("http://portal-stg.asiantech.vn" + path);
        new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, TIMEOUTINSECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
        Assert.assertEquals("http://portal-stg.asiantech.vn" + path, driver.getCurrentUrl());
    }
}
