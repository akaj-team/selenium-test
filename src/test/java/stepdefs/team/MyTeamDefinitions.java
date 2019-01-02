package stepdefs.team;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.team.MyTeamPage;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;
import static vn.asiantech.base.Constant.URL_MYTEAM_PAGE;

/**
 * @author at-huethai
 */
public class MyTeamDefinitions extends DriverBase implements En {
    private static final int TEAM_CODE = 24;
    private static final int TEAM_MEMBER_COUNT = 44;

    private WebDriver driver;
    private MyTeamPage myTeamPage;

    public MyTeamDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        myTeamPage = initPage(driver, MyTeamPage.class);

        Given("^Display My Team page$", () -> {
            driver.get(URL_MYTEAM_PAGE + TEAM_CODE);
            waitForPageDisplayed(getDriver(), URL_MYTEAM_PAGE + TEAM_CODE, By.id("page-wrapper"));
        });

        Given("^The member of Android team is displayed$", () -> Assert.assertEquals(TEAM_MEMBER_COUNT, myTeamPage.checkNumberOfTeam()));

        And("^The \"([^\"]*)\" value is \"([^\"]*)\"$", (String key, String value) -> myTeamPage.verifyMyTeamInfo(key, value));

        When("^I click on 'Update Team' button$", () -> {
            myTeamPage.clickUpdateTeamButton();
            waitForMyTeamDetailDismissed();
        });

        Then("^The web page navigates to the \"([^\"]*)\" page$", (String page) -> myTeamPage.redirectPage(page));

        When("^I click on Teams button$", () -> {
            myTeamPage.clickTeamsButton();
            waitForMyTeamDetailDismissed();
        });

        When("^I click on New Member button$", () -> myTeamPage.clickAddMemberButton());

        Then("^The Add Member popup is displayed$", () -> Assert.assertTrue(myTeamPage.getAddMemberPopupName()));

        When("^I input \"([^\"]*)\" into search input to add member$", (String username) -> myTeamPage.inputUserNameToAdd(username));

        Then("^I verify that search result list is correct$", () -> Assert.assertTrue(myTeamPage.verifySearchResult()));

        When("^I click on Add button$", () -> myTeamPage.clickAddButton());

        When("^I click on Close button$", () -> myTeamPage.clickCloseButton());

        Then("^The Add Member popup is disappeared$", () -> myTeamPage.verifyAddMemberPopupDisappeared());

        When("^I input \"([^\"]*)\" into search input to search member$", (String username) -> myTeamPage.inputUserNameToSearch(username));

        Then("^I verify that members of team are displayed correctly as \"([^\"]*)\"$", (String record) -> myTeamPage.verifySearchMemberResult(record));

        When("^I input \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$",
                (String name, String manager, String teamOfficer1, String teamOfficer2, String email, String teamFolder, String description) ->
                        myTeamPage.fillTeamName(name)
                                .selectTeamManager(manager)
                                .selectTeamOfficers(teamOfficer1, teamOfficer2)
                                .fillEmail(email).fillTeamFolder(teamFolder)
                                .fillDescription(description));

        And("^I click on Submit button$", () -> myTeamPage.clickSubmitButtonToUpload());

        And("^I click on Delete button to delete searched user$", () -> myTeamPage.clickDeleteButton());

        Then("^I verify that deleting user successful$", () -> myTeamPage.verifyDeleteUserSuccessful());
    }

    private void waitForMyTeamDetailDismissed() {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".wrapper.wrapper-content.page-detail.team-detail"))));
    }
}
