package stepdefs.team;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.team.NewTeamFormPage;
import vn.asiantech.page.team.TeamsPage;

import static vn.asiantech.base.Constant.NEW_TEAM_PAGE_URL;
import static vn.asiantech.base.Constant.TEAM_PAGE_URL;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/21/18.
 */
public class NewTeamFormDefinitions extends DriverBase implements En {
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
            driver.get(NEW_TEAM_PAGE_URL);
            waitForPageDisplayed(driver, NEW_TEAM_PAGE_URL, By.className("ibox-content"));
        });
        And("^I am stayed in update team page at position is (\\d+)$", (Integer position) -> {
            driver.get(TEAM_PAGE_URL);
            waitForPageDisplayed(driver, TEAM_PAGE_URL, By.className("ibox-content"));
            String updateTeamUrl = teamsPage.onClickUpdateTeam(position);
            waitForPageRedirected(driver, updateTeamUrl, By.className("ibox-content"));
        });

        // Validate Form
        Then("^Button submit is unable$", () -> Assert.assertFalse(newTeamFormPage.isButtonSubmitEnable()));
        When("^I fill in inputName with \"([^\"]*)\" with while space at beginning and end are (.*), (.*)$", (String name, String whileSpaceBegging, String whileSpaceEnd) -> {
            waitVisibilityOfElement(driver, By.name("name"));
            newTeamFormPage.sendKeyInputName(name, Integer.parseInt(whileSpaceBegging), Integer.parseInt(whileSpaceEnd));
        });
        When("^I fill in inputName with new name$", () -> {
            waitVisibilityOfElement(driver, By.name("name"));
            newTeamFormPage.sendKeyInputName(newTeamFormPage.generateNameTeam(), 0, 0);
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
