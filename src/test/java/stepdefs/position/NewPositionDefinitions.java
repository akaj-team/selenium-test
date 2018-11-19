package stepdefs.position;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.position.NewPositionPage;

/**
 * @author hanhnguyenm.
 */
public class NewPositionDefinitions extends DriverBase implements En {
    private NewPositionPage newPositionPage;
    private static final int POS_LONG_NAME_ERROR = 0;
    private static final int POS_SHORT_NAME_ERROR = 1;

    public NewPositionDefinitions() {
        newPositionPage = initPage(getDriver(), NewPositionPage.class);

        And("^Displayed new position page$", () -> {
            getDriver().get(Constant.NEW_POSITION_URL);
            waitForPageDisplayed(getDriver(), Constant.NEW_POSITION_URL, By.id("page-wrapper"));
        });

        Given("^I click on button position$", () -> newPositionPage.clickOnPositionPage());

        Given("^I enter long name field with text is \"([^\"]*)\" and first space is \"([^\"]*)\" and last space is \"([^\"]*)\"$", (final String longName, final String firstSpace, final String lastSpace) -> newPositionPage.inputLongName(longName, Integer.valueOf(firstSpace), Integer.valueOf(lastSpace)));
        Then("^Show long name message error with text \"([^\"]*)\"$", (final String message) -> Assert.assertTrue(newPositionPage.checkMessageError(message, POS_LONG_NAME_ERROR)));
        And("^Short name text is \"([^\"]*)\"$", (final String shortName) -> Assert.assertTrue(newPositionPage.checkShortNameText(shortName)));

        Given("^I enter correct long name with text is \"([^\"]*)\"$", (final String longName) -> newPositionPage.inputCorrectLongName(longName));
        And("^Hide long name message error$", () -> Assert.assertFalse(newPositionPage.checkMessageErrorHide(POS_LONG_NAME_ERROR)));

        Given("^I enter short name field with text is \"([^\"]*)\" and first space is \"([^\"]*)\" and last space is \"([^\"]*)\"$", (final String shortName, final String firstSpace, final String lastSpace) -> newPositionPage.inputShortName(shortName, Integer.valueOf(firstSpace), Integer.valueOf(lastSpace)));
        Then("^Show short name message error with text \"([^\"]*)\"$", (final String message) -> Assert.assertTrue(newPositionPage.checkMessageError(message, POS_SHORT_NAME_ERROR)));

        When("^Short name empty$", () -> Assert.assertTrue(newPositionPage.checkShortNameEmpty()));
        Then("^Short name hint text is \"([^\"]*)\"$", (final String hintText) -> Assert.assertTrue(newPositionPage.checkShortNameHintText(hintText)));

        And("^I click first position on list position$", () -> newPositionPage.clickFirstItemPosition());
        Then("^Displayed button save$", () -> Assert.assertTrue(newPositionPage.checkButtonSubmitDisplay()));
        When("^I click button save position$", () -> newPositionPage.clickButtonSubmit());
        Then("^Redirect to position detail or show error massage$", () -> {
            if (newPositionPage.checkPositionIsExits()) {
                Assert.assertTrue(newPositionPage.showMessageErrorWhenSubmit());
            } else {
                waitVisibilityOfElement(getDriver(), By.className("page-detail"));
                Assert.assertTrue(getDriver().findElement(By.className("page-detail")).isDisplayed());
            }
        });
    }
}
