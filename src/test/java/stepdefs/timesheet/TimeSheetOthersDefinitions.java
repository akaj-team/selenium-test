package stepdefs.timesheet;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.timesheet.TimeSheetOthers;

/**
 * @author at-trungnguyen
 */
public class TimeSheetOthersDefinitions extends BaseDefinitions implements En {
    private TimeSheetOthers timeSheetOthers;
    private WebDriver driver;
    private String employeeDetailUrl;

    public TimeSheetOthersDefinitions() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        timeSheetOthers = initPage(driver, TimeSheetOthers.class);

        Given("^I move to timeSheet of others page$", () -> {
            driver.get(Constant.TIME_SHEET_OTHER_URL);
            waitForPageDisplayed(driver, Constant.TIME_SHEET_OTHER_URL, By.id("page-wrapper"));
        });

        Then("^I see label \"([^\"]*)\" is show$", (String text) -> Assert.assertEquals(timeSheetOthers.getTitle().getText(), text));
        And("^I see button this week is disable$", () -> Assert.assertFalse(timeSheetOthers.isEnableButtonThisWeek()));
        And("^I see button pre and next is enable$", () -> {
            Assert.assertTrue(timeSheetOthers.isEnableButtonPrevWeek());
            Assert.assertTrue(timeSheetOthers.isEnableButtonNextWeek());
        });
        And("^I see tab \"([^\"]*)\" is selected$", (String tabName) -> Assert.assertEquals(timeSheetOthers.getNameSelectedTab(), tabName));
        And("^I see dropdown Subordinator with \"([^\"]*)\" is default value$", (String defaultValue) -> Assert.assertEquals(timeSheetOthers.getValueDropDownSubordinator(), defaultValue));
        And("^I see dropdown status with \"([^\"]*)\" is default value$", (String text) -> Assert.assertEquals(timeSheetOthers.getValueDropDownStatus(), text));
        And("^I see list label status on bottom is show$", () -> Assert.assertTrue(timeSheetOthers.isDisplayedLabelBottom()));

        When("^I click on dropdown status$", () -> {
            timeSheetOthers.onClickDropDownStatus();
            waitVisibilityOfElement(driver, By.className("ui-inputwrapper-focus"));
        });
        And("^I choose option \"([^\"]*)\" in Status", (String text) -> timeSheetOthers.getListStatusOption().get(0).click());
        And("^I see dropdown status change to \"([^\"]*)\"$", (String text) -> Assert.assertEquals(timeSheetOthers.getValueDropDownStatus(), text));

        When("^I click on dropdown Subordinator$", () -> {
            timeSheetOthers.onClickDropDownSubordinator();
            waitVisibilityOfElement(driver, By.className("ui-inputwrapper-focus"));
        });
        And("^I choose option \"([^\"]*)\" in Subordinator$", (String text) -> timeSheetOthers.getListSubordinatorOption().get(0).click());
        And("^I see dropdown Subordinator change to \"([^\"]*)\"$", (String text) -> Assert.assertEquals(timeSheetOthers.getValueDropDownSubordinator(), text));

        When("^I click on pre button$", () -> timeSheetOthers.onClickButtonPrevWeek());
        Then("^I see button this week is enable$", () -> Assert.assertTrue(timeSheetOthers.isEnableButtonThisWeek()));

        When("^I click button next (\\d+) times$", (Integer times) -> timeSheetOthers.onClickButtonNextWeek(times));
        Then("^I see button next is disable$", () -> Assert.assertFalse(timeSheetOthers.isEnableButtonNextWeek()));

        When("^I click on tab \"([^\"]*)\"$", (String tabName) -> {
            if (tabName.equals("Project")) {
                timeSheetOthers.onClickButtonInToolBox(0);
            } else {
                timeSheetOthers.onClickButtonInToolBox(1);
            }
        });

        When("^I click on information user in (\\d+)$", (Integer position) -> {
            waitVisibilityOfElement(driver, By.cssSelector(".table.table-vm"));
            employeeDetailUrl = timeSheetOthers.onClickInfoUser(position);
        });
        Then("^I redirected to employee detail page$", () -> waitForPageDisplayed(driver, employeeDetailUrl, By.className("section-top")));

        When("^I click on any has task of employee with position is (\\d+)$", (Integer position) -> {
            timeSheetOthers.onClickHasTask(position);
            waitVisibilityOfElement(driver, By.className("ui-overlaypanel"));
        });
        Then("^I see popup is displayed$", () -> Assert.assertTrue(timeSheetOthers.isShowPopupHasTask()));
    }
}
