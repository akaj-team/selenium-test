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

        // Validate input name
        When("^I fill in inputName with \"([^\"]*)\" with while space at beginning and end are (.*), (.*)$", (String name, String whileSpaceBegging, String whileSpaceEnd) ->
                newTeamFormPage.sendKeyInputName(name, Integer.parseInt(whileSpaceBegging), Integer.parseInt(whileSpaceEnd)));
        When("^I fill in inputName with new name$", () -> newTeamFormPage.sendKeyInputName(newTeamFormPage.generateNameTeam(), 0, 0));
        And("^Message error validate name is displayed$", () -> Assert.assertTrue(newTeamFormPage.isMessageErrorValidateNameShown()));
        And("^Message error validate name is hidden$", () -> Assert.assertFalse(newTeamFormPage.isMessageErrorValidateNameShown()));

        // Dropdown Manager
        When("^I open dropdown Manager$", () -> {
            newTeamFormPage.clickDropDownListManager();
            waitVisibilityOfElement(driver, By.className("ui-dropdown-open"));
        });
        And("^I select a item in manager list$", () -> newTeamFormPage.selectManager());

        // Validate input email
        When("^I fill in inputEmail with \"([^\"]*)\" with while space at beginning and end are \"([^\"]*)\", \"([^\"]*)\"$", (String email, String whileSpaceBegging, String whileSpaceEnd) ->
                newTeamFormPage.sendKeyInputEmail(email, Integer.parseInt(whileSpaceBegging), Integer.parseInt(whileSpaceEnd)));
        And("^I fill in inputEmail with new email$", () -> newTeamFormPage.sendKeyInputEmail(newTeamFormPage.generateEmail(), 0, 0));
        And("^Message error validate email is displayed$", () -> Assert.assertTrue(newTeamFormPage.isMessageErrorValidateEmailShown()));
        And("^Message error validate email is hidden$", () -> Assert.assertFalse(newTeamFormPage.isMessageErrorValidateEmailShown()));

        // Click and status enable of button Submit
        When("^I click on button Submit$", () -> newTeamFormPage.onClickButtonSubmit());
        Then("^Button submit is enable$", () -> Assert.assertTrue((newTeamFormPage.isButtonSubmitEnable())));
        Then("^Button submit is unable$", () -> Assert.assertFalse(newTeamFormPage.isButtonSubmitEnable()));

        Then("^I should see fail or success message$", () -> {
            waitVisibilityOfElement(driver, By.className("app-alert"));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
