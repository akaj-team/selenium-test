package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MenuPage;

/**
 * @author at-hanhnguyen
 */
public class MenuDefinitions extends DriverBase implements En {

    private static final int TIME_WAIT = 10;
    private MenuPage menuPage;

    public MenuDefinitions() {
        menuPage = initPage(getDriver(), MenuPage.class);

        Then("^Show name of account$", () -> Assert.assertTrue(menuPage.checkShowAccountNameShown()));
        And("^Account name is \"([^\"]*)\"$", (String accountName) -> Assert.assertTrue(menuPage.checkTextAccountName(accountName)));
        And("^Home item with color text is \"([^\"]*)\"$", (String whiteColor) -> Assert.assertTrue(menuPage.checkColorItemHomeIsWhite(whiteColor)));


        When("^I hover mouse to account name$", () -> menuPage.hoverMouseToAccountName());


        Given("^Item Timesheet close$", () -> Assert.assertFalse(menuPage.checkItemTimeSheetClose()));
        When("^I click in Timesheet item$", () -> menuPage.clickItemMenu());
        Then("^Open Child Timesheet item$", () -> Assert.assertTrue(menuPage.checkItemTimeSheetClose()));


        When("^I click Home item$", () -> menuPage.clickHomeItem());
        Then("^Item home change color to \"([^\"]*)\"$", (String whiteColor) -> Assert.assertTrue(menuPage.checkColorItemHomeIsWhite(whiteColor)));
        And("^Should redirect to home page \"([^\"]*)\"$", (String path) -> {
            new WebDriverWait(getDriver(), 10).until(
                    webDriver -> webDriver.findElement(By.className("notification-header")).findElement(By.tagName("h2")).isDisplayed()
            );
            String url = getDriver().getCurrentUrl();
            Assert.assertEquals(path, url.substring(url.length() - path.length()));
        });


        When("^I click My TimeSheet$", () -> menuPage.clickMyTimeSheet());
        Then("^Item my TimeSheet change color to \"([^\"]*)\"$", (String whiteColor) -> Assert.assertTrue(menuPage.checkMyTimeSheetColor(whiteColor)));
        And("^Should redirect to \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);


        When("^I click TimeSheet Of Other$", () -> menuPage.clickTimeSheetOfOthers());
        Then("^Item TimeSheet Of Other change color to \"([^\"]*)\"$", (String whiteColor) -> Assert.assertTrue(menuPage.checkTimeSheetOfOtherColor(whiteColor)));
        And("^Should redirect to time sheet of other page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);


        Given("^Item leave close$", () -> Assert.assertFalse(menuPage.checkItemLeaveClose()));
        When("^I click item leave$", () -> menuPage.clickItemLeave());
        Then("^Open child leave item$", () -> Assert.assertTrue(menuPage.checkItemLeaveClose()));
        And("^Item leave change color to \"([^\"]*)\"$", (String color) -> Assert.assertTrue(menuPage.checkColorItemLeaveIsWhite(color)));


        When("^I click my leave$", () -> menuPage.clickMyLeave());
        Then("^Item my leave change color to \"([^\"]*)\"$", (String color) -> Assert.assertTrue(menuPage.checkColorMyLeave(color)));
        And("^Should redirect to my leave page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        When("^I click leave planners$", () -> menuPage.clickLeavePlanner());
        Then("^Should redirect to leave planners page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        When("^I click leave of others$", () -> menuPage.clickLeaveOfOther());
        Then("^Should redirect to leave of others page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        When("^I click leave balance$", () -> menuPage.clickLeaveBalance());
        Then("^Should redirect to leave balance page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);


        Given("^Item organisation close$", () -> Assert.assertFalse(menuPage.checkItemOrganisationClose()));
        When("^I click item organisation$", () -> menuPage.clickItemOrganisation());
        Then("^Open child organisation item$", () -> Assert.assertTrue(menuPage.checkItemOrganisationClose()));


        When("^I click child item organisation with position \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemOrganisation(position));
        Then("^if count child item is zero should redirect to page \"([^\"]*)\"$", (String path) -> {
            if (menuPage.checkCountMyTeamIsZero()) {
                redirectPageWhenClickChildItem(path);
            }
        });


        Given("^I click item project management$", () -> menuPage.clickItemProjectManagement());
        When("^I click child item project management with position \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemProjectManagement(position));
        Then("^Should redirect to page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);


        When("^I click item wiki$", () -> menuPage.clickItemWiki());
        Then("^Should redirect to wiki page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);


        Given("^I click item administration$", () -> menuPage.clickItemAdministration());
        When("^I click child item administration with position \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemAdministration(position));


        Given("^I click item device$", () -> menuPage.clickItemDevice());
        When("^I click child item device with \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemDevice(position));


        Given("^I click item tools$", () -> menuPage.clickItemTools());
        When("^I click child item tools with \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemTools(position));

        Given("^I click item career$", () -> menuPage.clickItemCareer());
        When("^I click child item career with \"([^\"]*)\"$", (String position) -> menuPage.clickChildItemCareer(position));
    }

    private void redirectPageWhenClickChildItem(String path) {
        new WebDriverWait(getDriver(), TIME_WAIT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
