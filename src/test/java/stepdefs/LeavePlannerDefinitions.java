package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeavePlannerPage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

/**
 * @author at-hangtran
 */
public class LeavePlannerDefinitions extends DriverBase implements En {

    private LeavePlannerPage leavePlanerPage;
    private WebDriver driver;
    private String profileLink;


    public LeavePlannerDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^Display leave planner page$", () -> {
            driver.get("http://portal-stg.asiantech.vn/leave/planning");
            leavePlanerPage = initPage(getDriver(), LeavePlannerPage.class);
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/planning", driver.getCurrentUrl());
        });

        Then("^Can not click this week button$", () -> {
            Assert.assertFalse(false, leavePlanerPage.getClickable(driver).toString());
        });

        When("^Click on back button$", () -> {
            leavePlanerPage.clickBackButton(driver);
        });

        Then("^Can click this week button$", () -> {
            Assert.assertTrue(true, leavePlanerPage.getClickable(driver).toString());
        });

        When("^Click on next button$", () -> {
            leavePlanerPage.clickNextButton(driver);
        });

        When("^Click username$", () -> {
            profileLink = leavePlanerPage.clickUserName();
        });

        Then("^Open successfully profile page of that user$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(profileLink, driver.getCurrentUrl());
        });

        When("^Move to avatar user$", () -> {
            leavePlanerPage.moveToAvatar(driver);
        });

        Then("^Display leave message$", () -> {
            Assert.assertTrue(true, leavePlanerPage.isShowLeaveMessage(driver).toString());
        });

        Then("^Display full seven columns$", () -> {
            Assert.assertTrue(true, leavePlanerPage.isDisplayFullColumns(driver).toString());
        });

        Then("^Display time correctly$", () -> {
            Assert.assertEquals(leavePlanerPage.getTableTime(driver), leavePlanerPage.getHeaderTime());
        });
    }
}
