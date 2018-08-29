package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.MenuPage;

public class MenuDefinitions extends DriverBase implements En {
    private MenuPage menuPage;
    private WebDriver driver;

    public MenuDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        menuPage = initPage(getDriver(), MenuPage.class);

        Then("^Show name of account$", () -> {
            Assert.assertTrue(menuPage.checkShowAccountNameShown());
        });

        And("^Account name is \"([^\"]*)\"$", (String accountName) -> {
            Assert.assertTrue(menuPage.checkTextAccountName(accountName));
        });

        And("^Home item with color text is \"([^\"]*)\"$", (String whiteColor) -> {
            Assert.assertTrue(menuPage.checkColorItemMenuIsWhite(whiteColor));
        });

        When("^I hover mouse to account name$", () -> {
            menuPage.hoverMouseToAccountName();
        });
        Then("^Account change color to \"([^\"]*)\"$", (String hoverAccountColor) -> {
            Assert.assertTrue(menuPage.checkColorAccountNameWhenHover(hoverAccountColor));
        });

        Given("^Item Timesheet close$", () -> {
            Assert.assertTrue(menuPage.checkItemTimeSheetClose());
        });
        When("^I click in Timesheet item$", () -> {
            menuPage.clickItemMenu();
        });
        Then("^Open Child Timesheet item$", () -> {
            Assert.assertFalse(menuPage.checkItemTimeSheetClose());
        });
    }
}
