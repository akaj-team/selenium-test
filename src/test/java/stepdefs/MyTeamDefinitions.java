package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MyTeamPage;

/**
 * @author at-huethai
 */
public class MyTeamDefinitions extends DriverBase implements En {
    private static final String URL_TEAM_PAGE = "http://portal-stg.asiantech.vn/organisation/teams/";
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

        Given("^Display My Team page$", () -> {
            driver.get(URL_TEAM_PAGE + TEAM_CODE);
            myTeamPage = initPage(getDriver(), MyTeamPage.class);
            waitForPageDisplayed(getDriver(), URL_TEAM_PAGE + TEAM_CODE, By.id("page-wrapper"));
        });

        Then("^The member of Android team is displayed$", () -> Assert.assertEquals(TEAM_MEMBER_COUNT, myTeamPage.checkNumberofTeam(driver)));

        And("^The \"([^\"]*)\" value is \"([^\"]*)\"$", (String key, String value) -> myTeamPage.verifyMyTeamInfo(driver, key, value));

        When("^I click on 'Update Team' button$", () -> {
            myTeamPage.clickUpdateTeamBtn(driver);
            Thread.sleep(TIME_SLEEP);
        });

        Then("^The web page navigates to the \"([^\"]*)\" page$", (String page) -> myTeamPage.redirectPage(driver, page));

        When("^I click on 'Teams' button$", () -> myTeamPage.clickTeamsBtn(driver));

        When("^I click on New Member button$", () -> myTeamPage.clickAddMemberBtn(driver));

        Then("^The Add Member popup is displayed$", () -> Assert.assertTrue(myTeamPage.getAddMemberPopupName()));

        When("^I input \"([^\"]*)\" into search textbox to add member$", (String username) -> myTeamPage.inputUserNametoAdd(driver, username));

        Then("^I verify that search result list is correct with \"([^\"]*)\"$", (String n) -> Assert.assertTrue(myTeamPage.verifySearchResult(driver, n)));

        When("^I click on Add button$", () -> myTeamPage.clickAddBtn(driver));

        When("^I click on Close button$", () -> myTeamPage.clickCloseBtn(driver));

        Then("^The Add Member popup is disappeared$", () -> Assert.assertTrue(myTeamPage.verifyAddMemberPopupDisappeared(driver)));

        When("^I input \"([^\"]*)\" into search textbox to search member$", (String username) -> myTeamPage.inputUserNametoSearch(driver, username));

        Then("^I verify that members of team are displayed correctly as \"([^\"]*)\"$", (String record) -> myTeamPage.verifySearchMemberResult(driver, record));

        When("^I input \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$",
                (String name, String manager, String teamofficer1, String teamofficer2, String logo, String teamfolder, String description) -> myTeamPage.updateTeamInfo(driver, name, manager, teamofficer1, teamofficer2, logo, teamfolder, description));

        And("^I click on Submit button$", () -> myTeamPage.clickSubmitBtntoUpload(driver));

        And("^I click on Delete button to delete searched user$", () -> myTeamPage.clickDeleteBtn(driver));

        Then("^I verify that deleting user successful$", () -> myTeamPage.verifyDeleteUserSuccessful(driver));
    }
}

