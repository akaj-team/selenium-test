package stepdefs.teams;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.teams.TeamsPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/18/18.
 */
public class TeamsDefinitions extends DriverBase implements En {
    private static final int TIME_OUT_SECONDS_10 = 10;
    private static final int TIME_OUT_SECONDS_20 = 20;
    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";

    private String urlUpdateTeam;
    private WebDriver driver;
    private TeamsPage mTeamsPage;
    private String urlTeamDetail;
    private String urlEmployeeDetail;
    private String urlNewTeam;
    private String nameTeam;

    public TeamsDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTeamsPage = initPage(getDriver(), TeamsPage.class);
        And("^I am stayed in teams page$", () -> {
            driver.get(URL_PAGE_TEAMS);
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_TEAMS, driver.getCurrentUrl());
        });

        //Search teams with enter name team
        When("^Enter search with name is \"([^\"]*)\"$", (String name) -> {
            mTeamsPage.searchNameTeam(name);
            String redirectUrl = URL_PAGE_TEAMS + ";name_cont=" + name;
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(ExpectedConditions.urlToBe(redirectUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(webDriver -> webDriver.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).isDisplayed());
        });
        Then("^I should see list team$", () -> Assert.assertFalse(mTeamsPage.isTeamListEmpty()));
        Then("^I should see list team is empty$", () -> Assert.assertTrue(mTeamsPage.isTeamListEmpty()));
        And("^I should see message \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, mTeamsPage.showMessageEmptyTeam()));

        // Open successfully profile when click on avatar or name team
        When("^I click on avatar of team$", () -> urlTeamDetail = mTeamsPage.onClickAvatarTeam());
        When("^I click on name team$", () -> urlTeamDetail = mTeamsPage.onClickNameTeam());
        Then("^Team details is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(ExpectedConditions.urlToBe(urlTeamDetail));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());

        });
        // Open successfully profile when click on username of manager
        When("^I click on username of manager$", () -> urlEmployeeDetail = mTeamsPage.onClickNameManager());
        Then("^Manager profile is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(ExpectedConditions.urlToBe(urlEmployeeDetail));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

        // Add new team
        When("^I click on button New Team$", () -> urlNewTeam = mTeamsPage.onClickNewTeam());
        Then("^Page is redirected to New Team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(ExpectedConditions.urlToBe(urlNewTeam));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
        });

        // Update team
        When("^I click on button update team$", () -> urlUpdateTeam = mTeamsPage.onClickUpdateTeam());
        Then("^Page is redirected to Update Team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_20).until(ExpectedConditions.urlToBe(urlUpdateTeam));
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
        });

        // Delete team
        When("^I click on button delete team at position is (\\d+)$", (Integer position) -> nameTeam = mTeamsPage.onClickDeleteTeam(position));
        Then("^I should see dialog confirm delete team$", () -> Assert.assertTrue(mTeamsPage.isDialogShowed()));
        And("^Name team is correct with team is chose$", () -> Assert.assertEquals("Are you sure to delete " + nameTeam + " team?", mTeamsPage.getNameTeamIsDeleted()));
        When("^I choose button cancel on dialog$", () -> mTeamsPage.onClickBtnCancelInDialogDelete());
        When("^I choose button ok on dialog$", () -> mTeamsPage.onClickBtnDeleteInDialogDelete());
        Then("^Dialog is dismiss$", () -> Assert.assertFalse(mTeamsPage.isDialogShowed()));
        Then("^Team is chose would be deleted$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
