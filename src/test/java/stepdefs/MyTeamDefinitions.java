package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.team.MyTeamPage;

import static vn.asiantech.base.Constant.URL_MYTEAM_PAGE;

/**
 * @author at-huethai
 */
public class MyTeamDefinitions extends DriverBase implements En {
    private static final int TEAM_CODE = 24;
    private static final int TEAM_MEMBER_COUNT = 39;
    private static final int TIME_SLEEP = 300;

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

        Then("^The member of Android team is displayed$", () -> Assert.assertEquals(TEAM_MEMBER_COUNT, myTeamPage.checkNumberofTeam()));

        And("^The \"([^\"]*)\" value is \"([^\"]*)\"$", (String key, String value) -> myTeamPage.verifyMyTeamInfo(key, value));

        When("^I click on 'Update Team' button$", () -> {
            myTeamPage.clickUpdateTeamButton();
            Thread.sleep(TIME_SLEEP);
        });

        Then("^The web page navigates to the \"([^\"]*)\" page$", (String page) -> myTeamPage.redirectPage(page));

        When("^I click on 'Teams' button$", () -> myTeamPage.clickTeamsBtn());

        When("^I click on New Member button$", () -> myTeamPage.clickAddMemberBtn());

        Then("^The Add Member popup is displayed$", () -> Assert.assertTrue(myTeamPage.getAddMemberPopupName()));

        When("^I input \"([^\"]*)\" into search textbox to add member$", (String username) -> myTeamPage.inputUserNametoAdd(username));

        Then("^I verify that search result list is correct with \"([^\"]*)\"$", (String n) -> myTeamPage.verifySearchResult(n));

        When("^I click on Add button$", () -> myTeamPage.clickAddButton());

        When("^I click on Close button$", () -> myTeamPage.clickCloseBtn());

        Then("^The Add Member popup is disappeared$", () -> Assert.assertTrue(myTeamPage.verifyAddMemberPopupDisappeared()));

        When("^I input \"([^\"]*)\" into search textbox to search member$", (String username) -> myTeamPage.inputUserNametoSearch(username));

        Then("^I verify that members of team are displayed correctly as \"([^\"]*)\"$", (String record) -> myTeamPage.verifySearchMemberResult(record));

        When("^I input \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$",
                (String name, String manager, String teamofficer1, String teamofficer2, String logo, String teamfolder, String description) -> myTeamPage.updateTeamInfo(name, manager, teamofficer1, teamofficer2, logo, teamfolder, description));

        And("^I click on Submit button$", () -> myTeamPage.clickSubmitBtntoUpload());

        And("^I click on Delete button to delete searched user$", () -> myTeamPage.clickDeleteBtn());

        Then("^I verify that deleting user successful$", () -> myTeamPage.verifyDeleteUserSuccessful());
    }
}

