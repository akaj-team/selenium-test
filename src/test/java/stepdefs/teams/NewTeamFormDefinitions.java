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
import vn.asiantech.page.teams.TeamsPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/21/18.
 */
public class NewTeamFormDefinitions extends DriverBase implements En {
    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";
    private static final String URL_PAGE_NEW_TEAM = "http://portal-stg.asiantech.vn/organisation/teams/new";
    private static final int INDEX_ID_IN_UPDATE_TEAM_URL = 5;
    private static final int TIME_OUT_SECONDS_NORMAL = 10;
    private static final int TIME_OUT_SECOND_MAXIMUM = 20;
    private WebDriver driver;
    private NewTeamFormPage newTeamFormPage;
    private TeamsPage teamsPage;

    public NewTeamFormDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newTeamFormPage = initPage(getDriver(), NewTeamFormPage.class);
        teamsPage = initPage(getDriver(), TeamsPage.class);
        And("^I am stayed in new team page$", () -> {
            driver.get(URL_PAGE_NEW_TEAM);
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_NEW_TEAM, driver.getCurrentUrl());
        });

        And("^I am stayed in update team page at position is (\\d+)$", (Integer position) -> {
            driver.get(URL_PAGE_TEAMS);
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_TEAMS, driver.getCurrentUrl());
            String updateTeamUrl = teamsPage.onClickUpdateTeam(position);
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(updateTeamUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
        });

        // Validate Form
        Then("^Button submit is unable$", () -> Assert.assertFalse(newTeamFormPage.isBtnSubmitEnable()));
        When("^I fill in inputName with \"([^\"]*)\" with while space at begging and end are (.*), (.*)$", (String name, String whileSpaceBegging, String whileSpaceEnd) -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.id("input-name")));
            newTeamFormPage.sendKeyInputName(name, Integer.parseInt(whileSpaceBegging), Integer.parseInt(whileSpaceEnd));
        });
        When("^I fill in inputName with new name$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.id("input-name")));
            newTeamFormPage.sendKeyInputName(newTeamFormPage.generateNameTeam().toString(), 0, 0);
        });
        Then("^Button submit is enable$", () -> Assert.assertTrue((newTeamFormPage.isBtnSubmitEnable())));
        And("^Message error is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> webDriver.findElement(By.className("form-group")).findElement(By.className("help-block")).isDisplayed());
            Assert.assertTrue(newTeamFormPage.isMessageErrorShown());
        });
        And("^Message error is hidden$", () -> Assert.assertFalse(newTeamFormPage.isMessageErrorShown()));

        // Click button Submit, redirect to Team Detail page
        When("^I click on button Submit$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
            newTeamFormPage.onClickBtnSubmit();
        });
        Then("^Open successfully team detail page after update$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

        // Dropdown Manager
        When("^I open dropdown Manager$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            newTeamFormPage.clickDropDownListManager();
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> webDriver.findElement(By.className("ui-dropdown-open")));
        });
        And("^I select a item in manager list$", () -> newTeamFormPage.selectManager());

        // Open successfully team detail page when click button submit
        Then("^Open successfully team detail page after created$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            String id = driver.getCurrentUrl().split("/")[INDEX_ID_IN_UPDATE_TEAM_URL];
            Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE_TEAMS + "/" + id);
        });

        Then("^I should see fail or success message$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
