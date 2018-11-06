package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.PositionsPage;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author at-trungnguyen
 */
public class PositionsDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private PositionsPage positionsPage;

    public PositionsDefinitions() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Display \"([^\"]*)\" page$", (String arg0) -> {
            driver.get("http://portal-stg.asiantech.vn" + arg0);
            positionsPage = initPage(getDriver(), PositionsPage.class);
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn" + arg0, driver.getCurrentUrl());
        });

        Then("^I see header title \"([^\"]*)\" is display$", (String arg0) -> Assert.assertEquals(positionsPage.getTitle(driver), arg0));

        Then("^I see button new position and career path is display$", () -> {
            Assert.assertTrue(positionsPage.getBtnCareerPath(driver).isDisplayed());
            Assert.assertTrue(positionsPage.getBtnCareerPath(driver).isDisplayed());
        });

        When("^I click on new position button$", () -> positionsPage.getBtnNewPosition(driver).click());

        Then("^I move to new position page$", () -> {
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-submit")));
            Assert.assertEquals(driver.getCurrentUrl(), "http://portal-stg.asiantech.vn/organisation/positions/new");
        });

        When("^I click on career path button$", () -> positionsPage.getBtnCareerPath(driver).click());

        Then("^I move to career path page$", () -> {
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.className("panel-heading")));
            Assert.assertEquals(driver.getCurrentUrl(), "http://portal-stg.asiantech.vn/organisation/positions/tree");
        });

        When("^I click on a row in table position$", () -> {
            WebElement webElement = positionsPage.getCellDataTable(driver, 1, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I move to position detail page$", () -> {
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(driver.getCurrentUrl(), positionsPage.getUrlOfCell());
        });

        When("^I click on update button in a row$", () -> {
            WebElement webElement = positionsPage.getCellEditInTable(driver, 1, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I move to update position page$", () -> {
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-submit")));
            Assert.assertEquals(driver.getCurrentUrl(), positionsPage.getUpdatePositionUrl());
        });

        When("^I click on delete button in a row$", () -> {
            WebElement webElement = positionsPage.getCellDeleteInTable(driver, 1, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I see dialog confirm delete position is display$", () -> Assert.assertTrue(positionsPage.isDialogConfirmDeleteDisplay(driver)));

        When("^I write \"([^\"]*)\" and press enter on search field$", (String arg0) -> {
            positionsPage.searchPosition(driver, arg0);
            waitVisibilityOfElement(driver, By.cssSelector(".ui-widget-content.ng-star-inserted"));
        });

        Then("^The table show positions with long name are contains \"([^\"]*)\" value$", (String arg0) -> {
            List<String> names = positionsPage.getLongNameDataInTable(driver);
            boolean isMatch = true;
            for (String name : names) {
                if (!Pattern.compile(Pattern.quote(arg0), Pattern.CASE_INSENSITIVE).matcher(name).find()) {
                    isMatch = false;
                }
            }
            Assert.assertTrue(isMatch);
        });
    }
}
