package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MyTeamPage;
import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

/**
 * @author at-huethai
 */
public class MyTeamDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private MyTeamPage myTeamPage;

    public MyTeamDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^Display My Team page$", () -> {
            driver.get("http://portal-stg.asiantech.vn/organisation/teams/24");
            myTeamPage = initPage(getDriver(), MyTeamPage.class);
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
            Assert.assertEquals("http://portal-stg.asiantech.vn/organisation/teams/24", driver.getCurrentUrl());
        });

        Then("^The member of Android team is displayed$", () -> {
            Assert.assertEquals(39, myTeamPage.checkNumberofTeam(driver));
        });

        And("^The \"([^\"]*)\" value is \"([^\"]*)\"$", (String key, String value) -> {
            WebElement dt = driver.findElement(By.xpath("//dt[contains(text(), '" + key + "')]"));
            WebElement dd = dt.findElement(By.xpath("following-sibling::dd"));
            WebElement span = dd.findElement(By.tagName("span"));
            String text = span.getText();

            if (text != "") {
                Assert.assertEquals(text, value);
            } else {
                WebElement a = dd.findElement(By.tagName("a"));
                Assert.assertEquals(a.getText(), value);
            }
        });

        When("^I click on 'Update Team' button$", () -> {
            myTeamPage.clickUpdateTeamBtn(driver);
            Thread.sleep(300);
        });

        Then("^The web page navigates to the \"([^\"]*)\" page$", (String page) -> {
            myTeamPage.redirectPage(driver, page);
        });

        When("^I click on 'Teams' button$", () -> {
            myTeamPage.clickTeamsBtn(driver);
        });
        When("^I click on New Member button$", () -> {
            myTeamPage.clickAddMemberBtn(driver);
        });

        Then("^The Add Member popup is displayed$", () -> {
            Assert.assertTrue(myTeamPage.getAddMemberPopupName(driver));
        });

        When("^I input \"([^\"]*)\" into search textbox to add member$", (String username) -> {
            myTeamPage.inputUserNametoAdd(driver, username);
        });

        Then("^I verify that search result list is correct with \"([^\"]*)\"$", (String n) -> {
            Assert.assertTrue(myTeamPage.verifySearchResult(driver, n));
        });

        When("^I click on Add button$", () -> {
            myTeamPage.clickAddBtn(driver);
        });

        When("^I click on Close button$", () -> {
            myTeamPage.clickCloseBtn(driver);
        });

        Then("^The Add Member popup is disappeared$", () -> {
            Assert.assertTrue(myTeamPage.verifyAddMemberPopupDisappeared(driver));
        });

        When("^I input \"([^\"]*)\" into search textbox to search member$", (String username) -> {
            myTeamPage.inputUserNametoSearch(driver, username);
        });

        Then("^I verify that members of team are displayed correctly as \"([^\"]*)\"$", (String record) -> {
            myTeamPage.verifySearchMemberResult(driver, record);
        });
        When("^I input \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$",
                (String name, String manager, String teamofficer1, String teamofficer2, String logo, String teamfolder, String description) -> {

                    myTeamPage.updateTeamInfo(driver, name, manager, teamofficer1, teamofficer2, logo, teamfolder, description);
                });
        And("^I click on Submit button$", () -> {
            myTeamPage.clickSubmitBtntoUpload(driver);
        });
        And("^I click on Delete button to delete searched user$", () -> {
            myTeamPage.clickDeleteBtn(driver);
        });
        Then("^I verify that deleting user successful$", () -> {
            myTeamPage.verifyDeleteUserSuccessful(driver);
        });
    }
}

