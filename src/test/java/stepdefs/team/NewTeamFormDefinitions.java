package stepdefs.team;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.team.NewTeamFormPage;
import vn.asiantech.page.team.TeamsPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/21/18.
 */
public class NewTeamFormDefinitions extends DriverBase implements En {
    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";
    private static final String URL_PAGE_NEW_TEAM = "http://portal-stg.asiantech.vn/organisation/teams/new";
    private static final int INDEX_ID_IN_UPDATE_TEAM_URL = 5;
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
            waitForPageDisplayed(driver, URL_PAGE_NEW_TEAM, By.className("ibox-content"));
        });
        And("^I am stayed in update team page at position is (\\d+)$", (Integer position) -> {
            driver.get(URL_PAGE_TEAMS);
            waitForPageDisplayed(driver, URL_PAGE_TEAMS, By.className("ibox-content"));
            String updateTeamUrl = teamsPage.onClickUpdateTeam(position);
            waitForPageRedirected(driver, updateTeamUrl, By.className("ibox-content"));
        });

        // Validate Form
        Then("^Button submit is unable$", () -> Assert.assertFalse(newTeamFormPage.isButtonSubmitEnable()));
        When("^I fill in inputName with \"([^\"]*)\" with while space at begging and end are (.*), (.*)$", (String name, String whileSpaceBegging, String whileSpaceEnd) -> {
            waitVisibilityOfElement(driver, By.name("name"));
            newTeamFormPage.sendKeyInputName(name, Integer.parseInt(whileSpaceBegging), Integer.parseInt(whileSpaceEnd));
        });
        When("^I fill in inputName with new name$", () -> {
            waitVisibilityOfElement(driver, By.name("name"));
            newTeamFormPage.sendKeyInputName(newTeamFormPage.generateNameTeam().toString(), 0, 0);
        });
        Then("^Button submit is enable$", () -> Assert.assertTrue((newTeamFormPage.isButtonSubmitEnable())));
        And("^Message error is displayed$", () -> {
            waitVisibilityOfElement(driver, By.className("help-block"));
            Assert.assertTrue(newTeamFormPage.isMessageErrorShown());
        });
        And("^Message error is hidden$", () -> Assert.assertFalse(newTeamFormPage.isMessageErrorShown()));

        // Click button Submit, redirect to Team Detail page
        When("^I click on button Submit$", () -> {
            waitVisibilityOfElement(driver, By.id("btn-submit-team"));
            newTeamFormPage.onClickButtonSubmit();
        });

        // Dropdown Manager
        When("^I open dropdown Manager$", () -> {
            newTeamFormPage.clickDropDownListManager();
            waitVisibilityOfElement(driver, By.className("ui-dropdown-open"));
        });
        And("^I select a item in manager list$", () -> newTeamFormPage.selectManager());

        Then("^I should see fail or success message$", () -> {
            waitVisibilityOfElement(driver, By.className("app-alert"));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
