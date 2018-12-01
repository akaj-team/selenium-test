package stepdefs.team;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.team.TeamsPage;

import static vn.asiantech.base.Constant.TEAM_PAGE_URL;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/18/18.
 */
public class TeamsDefinitions extends DriverBase implements En {
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
            driver.get(TEAM_PAGE_URL);
            waitForPageDisplayed(driver, TEAM_PAGE_URL, By.className("ibox-content"));
        });
        And("^I am stayed in dialog confirm delete team at position is (\\d+)$", (Integer position) -> {
            driver.get(TEAM_PAGE_URL);
            waitForPageDisplayed(driver, TEAM_PAGE_URL, By.className("ibox-content"));
            nameTeam = teamsPage.onClickDeleteTeam(position);
            Assert.assertTrue(teamsPage.isDeleteDialogShown());
        });

        //Search teams with enter name team
        When("^Enter search with name is \"([^\"]*)\"$", (String searchData) -> {
            teamsPage.searchNameTeam(searchData);
            String redirectUrl = TEAM_PAGE_URL + ";name_cont=" + searchData;
            waitForPageRedirected(driver, redirectUrl, By.cssSelector(".ui-datatable-data.ui-widget-content"));
        });
        Then("^I should see list team$", () -> Assert.assertFalse(teamsPage.isTeamListEmpty()));
        Then("^I should see list team is empty$", () -> {
            waitVisibilityOfElement(driver, By.className("ui-datatable-emptymessage-row"));
            Assert.assertTrue(teamsPage.isTeamListEmpty());
        });
        And("^I should see alert message \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, teamsPage.showMessageEmptyTeam()));

        // Open successfully profile when click on avatar or name team
        When("^I click on avatar of team$", () -> teamDetailUrl = teamsPage.onClickAvatarTeam());
        When("^I click on name team$", () -> teamDetailUrl = teamsPage.onClickNameTeam());
        Then("^Team details is displayed$", () -> waitForPageRedirected(driver, teamDetailUrl, By.className("section-top")));

        // Open successfully profile when click on username of manager
        When("^I click on username of manager$", () -> employeeDetailUrl = teamsPage.onClickNameManager());
        Then("^Manager profile is displayed$", () -> waitForPageRedirected(driver, employeeDetailUrl, By.className("section-top")));

        // Open successfully new team page when click on button New Team
        When("^I click on button New Team$", () -> newTeamUrl = teamsPage.onClickNewTeam());
        Then("^Page is redirected to New Team page$", () -> waitForPageRedirected(driver, newTeamUrl, By.className("ibox-content")));

        // Open successfully update team page when click on button Update
        When("^I click on button update team at position is (\\d+)$", (Integer position) -> updateTeamUrl = teamsPage.onClickUpdateTeam(position));
        Then("^Page is redirected to Update Team page$", () -> waitForPageRedirected(driver, updateTeamUrl, By.className("ibox-content")));

        // Delete team function
        When("^I click on button delete team at position is (\\d+)$", (Integer position) -> nameTeam = teamsPage.onClickDeleteTeam(position));
        Then("^I should see dialog confirm delete team$", () -> Assert.assertTrue(teamsPage.isDeleteDialogShown()));
        And("^Name team is correct with team is chose$", () -> Assert.assertEquals("Are you sure to delete " + nameTeam + " team?", teamsPage.getNameTeamIsDeleted()));
        When("^I choose button cancel on dialog$", () -> teamsPage.onClickButtonCancelInDialogDelete());
        When("^I choose button ok on dialog$", () -> teamsPage.onClickButtonDeleteInDialogDelete());
        Then("^Dialog is dismiss$", () -> Assert.assertFalse(teamsPage.isDeleteDialogShown()));
        Then("^Team is chose would be deleted$", () -> Assert.assertTrue(teamsPage.isShowAlertDeleteSuccess()));
    }
}
