package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MenuPage;

public class MenuDefinitions extends DriverBase implements En {
    private MenuPage menuPage;

    public MenuDefinitions() {
        menuPage = initPage(getDriver(), MenuPage.class);

        Then("^Show name of account$", () -> Assert.assertTrue(menuPage.checkShowAccountNameShown()));

        And("^Account name is \"([^\"]*)\"$", (String accountName) -> Assert.assertTrue(menuPage.checkTextAccountName(accountName)));

        And("^Home item with color text is \"([^\"]*)\"$", (String whiteColor) -> Assert.assertTrue(menuPage.checkColorItemHomeIsWhite(whiteColor)));


        When("^I hover mouse to account name$", () -> menuPage.hoverMouseToAccountName());
        Then("^Account change color to \"([^\"]*)\"$", (String hoverAccountColor) -> Assert.assertTrue(menuPage.checkColorAccountNameWhenHover(hoverAccountColor)));

        Given("^Item Timesheet close$", () -> Assert.assertTrue(menuPage.checkItemTimeSheetClose()));
        When("^I click in Timesheet item$", () -> menuPage.clickItemMenu());
        Then("^Open Child Timesheet item$", () -> Assert.assertFalse(menuPage.checkItemTimeSheetClose()));


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


        Given("^Item leave close$", () -> Assert.assertTrue(menuPage.checkItemLeaveClose()));
        When("^I click item leave$", () -> menuPage.clickItemLeave());
        Then("^Open child leave item$", () -> Assert.assertFalse(menuPage.checkItemLeaveClose()));
        And("^Item leave change color to \"([^\"]*)\"$", (String color) -> Assert.assertTrue(menuPage.checkColorItemLeaveIsWhite(color)));


        When("^I click my leave$", () -> menuPage.clickMyLeave());
        Then("^Item my leave change color to \"([^\"]*)\"$", (String color) -> Assert.assertTrue(menuPage.checkColorMyLeave(color)));
        And("^Should redirect to my leave page \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
    }

    private void redirectPageWhenClickChildItem(String path) {
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
