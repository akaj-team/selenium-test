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
    private static final int TIME_OUT_SECONDS_NORMAL = 10;
    private static final int TIME_OUT_SECOND_MAXIMUM = 20;
    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";
    private WebDriver driver;
    private TeamsPage teamsPage;
    private String updateTeamUrl;
    private String teamDetailUrl;
    private String employeeDetailUrl;
    private String newTeamUrl;
    private String nameTeam;

    public TeamsDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        teamsPage = initPage(getDriver(), TeamsPage.class);
        And("^I am stayed in teams page$", () -> {
            driver.get(URL_PAGE_TEAMS);
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_TEAMS, driver.getCurrentUrl());
        });

        And("^I am stayed in dialog confirm delete team at position is (\\d+)$", (Integer position) -> {
            driver.get(URL_PAGE_TEAMS);
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
            Assert.assertEquals(URL_PAGE_TEAMS, driver.getCurrentUrl());
            nameTeam = teamsPage.onClickDeleteTeam(position);
            Assert.assertTrue(teamsPage.isDeleteDialogShown());
        });

        //Search teams with enter name team
        When("^Enter search with name is \"([^\"]*)\"$", (String name) -> {
            teamsPage.searchNameTeam(name);
            String redirectUrl = URL_PAGE_TEAMS + ";name_cont=" + name;
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(redirectUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(webDriver -> webDriver.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).isDisplayed());
        });
        Then("^I should see list team$", () -> Assert.assertFalse(teamsPage.isTeamListEmpty()));
        Then("^I should see list team is empty$", () -> Assert.assertTrue(teamsPage.isTeamListEmpty()));
        And("^I should see message \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, teamsPage.showMessageEmptyTeam()));

        // Open successfully profile when click on avatar or name team
        When("^I click on avatar of team$", () -> teamDetailUrl = teamsPage.onClickAvatarTeam());
        When("^I click on name team$", () -> teamDetailUrl = teamsPage.onClickNameTeam());
        Then("^Team details is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(teamDetailUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());

        });
        // Open successfully profile when click on username of manager
        When("^I click on username of manager$", () -> employeeDetailUrl = teamsPage.onClickNameManager());
        Then("^Manager profile is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(employeeDetailUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

        // Open successfully new team page when click on button New Team
        When("^I click on button New Team$", () -> newTeamUrl = teamsPage.onClickNewTeam());
        Then("^Page is redirected to New Team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(newTeamUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
        });

        // Open successfully update team page when click on button Update
        When("^I click on button update team at position is (\\d+)$", (Integer position) -> updateTeamUrl = teamsPage.onClickUpdateTeam(position));
        Then("^Page is redirected to Update Team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_MAXIMUM).until(ExpectedConditions.urlToBe(updateTeamUrl));
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("ibox-content")));
        });

        // Delete team function
        When("^I click on button delete team at position is (\\d+)$", (Integer position) -> nameTeam = teamsPage.onClickDeleteTeam(position));
        Then("^I should see dialog confirm delete team$", () -> Assert.assertTrue(teamsPage.isDeleteDialogShown()));
        And("^Name team is correct with team is chose$", () -> Assert.assertEquals("Are you sure to delete " + nameTeam + " team?", teamsPage.getNameTeamIsDeleted()));
        When("^I choose button cancel on dialog$", () -> teamsPage.onClickBtnCancelInDialogDelete());
        When("^I choose button ok on dialog$", () -> teamsPage.onClickBtnDeleteInDialogDelete());
        Then("^Dialog is dismiss$", () -> Assert.assertFalse(teamsPage.isDeleteDialogShown()));
        Then("^Team is chose would be deleted$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
