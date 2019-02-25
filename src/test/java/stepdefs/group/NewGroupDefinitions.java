package stepdefs.group;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.group.NewGroupPage;

/**
 * @author at-phuongdang
 */
public class NewGroupDefinitions extends BaseDefinitions implements En {

    private static final int NAME_POSITION = 0;
    private static final int EMAIL_POSITION = 3;
    private NewGroupPage newGroupPage;
    private WebDriver driver;

    public NewGroupDefinitions() {
        newGroupPage = initPage(getDriver(), NewGroupPage.class);
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Then("^Displayed new group page$", () -> {
            driver.get(Constant.NEW_GROUP_URL);
            waitForPageDisplayed(getDriver(), Constant.NEW_GROUP_URL, By.id("page-wrapper"));
        });
        When("^I enter name of group field with text is \"([^\"]*)\" and first space is \"([^\"]*)\" and last space is \"([^\"]*)\"$", (String nameGroup, String firstSpace, String lastSpace) -> newGroupPage.validateInputNameOfGroup(nameGroup, Integer.valueOf(firstSpace), Integer.valueOf(lastSpace)));
        Then("^Show name of group message error with text \"([^\"]*)\"$", (String message) -> Assert.assertTrue(newGroupPage.isShowMessageError(message, NAME_POSITION)));
        When("^I enter correct name group with text is \"([^\"]*)\"$", (String nameGroup) -> newGroupPage.inputNameGroup(nameGroup));
        Then("^Hide name of group message error$", () -> Assert.assertFalse(newGroupPage.isMessageErrorHide(NAME_POSITION)));
        When("^I open dropdown Leader$", () -> newGroupPage.openDropDownLeader());
        Then("^I select a item in employee list$", () -> newGroupPage.selectedItemDropdown());
        And("^Button submit of new groups is unable$", () -> Assert.assertFalse(newGroupPage.isDisplayButtonSubmit()));
        When("^I enter email of group in inputEmail with \"([^\"]*)\" with first space is \"([^\"]*)\" and last space is \"([^\"]*)\"$", (String email, String firstSpace, String lastSpace) -> newGroupPage.validateInputEmailOfGroup(email, Integer.valueOf(firstSpace), Integer.valueOf(lastSpace)));
        And("^Message error validate email of new group is displayed$", () -> Assert.assertTrue(newGroupPage.isMessageErrorHide(EMAIL_POSITION)));
        When("^I enter Group Folder of group in input value with \"([^\"]*)\" with first space is \"([^\"]*)\" and last space is \"([^\"]*)\"$", (String groupFolder, String firstSpace, String lastSpace) -> newGroupPage.validateInputGroupFolder(groupFolder, Integer.valueOf(firstSpace), Integer.valueOf(lastSpace)));
        And("^Message error validate groupFolder of new group is displayed$", () -> Assert.assertTrue(newGroupPage.isMessageErrorShowing()));
        Then("^Button submit of new groups is display$", () -> Assert.assertTrue(newGroupPage.isDisplayButtonSubmit()));
        When("^I click on button submit of new group$", () -> newGroupPage.clickButtonSubmitOnNewGroup());
        Then("^I should see fail or success message of new group$", () -> {
            waitVisibilityOfElement(getDriver(), By.className("app-alert"));
            Assert.assertTrue(getDriver().findElement(By.className("app-alert")).isDisplayed());
        });
    }
}
