package stepdefs.teams;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.teams.NewTeamFormPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/21/18.
 */
public class NewTeamFormDefinitions extends DriverBase implements En {
    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";
    private static final String URL_PAGE_NEW_TEAM = "http://portal-stg.asiantech.vn/organisation/teams/new";
    private static final int TIME_OUT_SECONDS_NORMAL = 10;
    private static final int TIME_OUT_SECONDS_20 = 20;
    private WebDriver driver;
    private NewTeamFormPage mNewTeamFormPage;
    private static final int INDEX_ID_IN_URL = 5;

    public NewTeamFormDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNewTeamFormPage = initPage(getDriver(), NewTeamFormPage.class);

        And("^I am stayed in new team page$", () -> {
            driver.get(URL_PAGE_NEW_TEAM);
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_NEW_TEAM, driver.getCurrentUrl());
        });

        // Validate Form
        Then("^Button submit is unable$", () -> Assert.assertFalse(mNewTeamFormPage.isBtnSubmitEnable()));
        When("^I fill in inputName with \"([^\"]*)\"$", (String name) -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
            mNewTeamFormPage.sendKeyInputName(name);
        });
        Then("^Button submit is enable$", () -> Assert.assertTrue((mNewTeamFormPage.isBtnSubmitEnable())));
        And("^Message error is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> webDriver.findElement(By.className("form-group")).findElement(By.className("help-block")).isDisplayed());
            Assert.assertTrue(mNewTeamFormPage.isMessageErrorShown());
        });
        And("^Message error is hidden$", () -> Assert.assertFalse(mNewTeamFormPage.isMessageErrorShown()));

        // Click button Submit, redirect to Team Detail page
        When("^I click on button Submit$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
            mNewTeamFormPage.onClickBtnSubmit();
        });
        Then("^Open successfully team detail page after update$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

        // Open successfully teams page when click on button Team in title
        When("^I click on button Team in title$", () -> mNewTeamFormPage.onClickBtnTeams());
        Then("^Open successfully team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.urlToBe(URL_PAGE_TEAMS));
            Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE_TEAMS);
        });

        // Dropdown Manager
        When("^I open dropdown Manager$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            mNewTeamFormPage.clickDropDownListManager();
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> webDriver.findElement(By.className("ui-dropdown-open")));
        });
        And("^I select a item in manager list$", () -> mNewTeamFormPage.selectManager());

        // Open successfully team detail page when click button submit
        Then("^Open successfully team detail page after created$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            String id = driver.getCurrentUrl().split("/")[INDEX_ID_IN_URL];
            Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE_TEAMS + "/" + id);
        });

        Then("^I should see fail or success message$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
        When("^I fill in inputName with new name$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
            mNewTeamFormPage.sendKeyInputName(mNewTeamFormPage.generateNameTeam().toString());
        });
    }
}
