package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.PositionsPage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

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
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn" + arg0, driver.getCurrentUrl());
        });

        Then("^I see header title \"([^\"]*)\" is display$", (String arg0) -> {
            Assert.assertEquals(positionsPage.getTitle(driver).getText(), arg0);
        });

        Then("^I see button new position and career path is display$", () -> {
            Assert.assertTrue(positionsPage.getBtnCareerPath(driver).isDisplayed());
            Assert.assertTrue(positionsPage.getBtnCareerPath(driver).isDisplayed());
        });

        When("^I click on new position button$", () -> {
            positionsPage.getBtnNewPosition(driver).click();
        });

        Then("^I move to new position page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-submit")));
            Assert.assertEquals(driver.getCurrentUrl(), "http://portal-stg.asiantech.vn/organisation/positions/new");
        });
    }
}
