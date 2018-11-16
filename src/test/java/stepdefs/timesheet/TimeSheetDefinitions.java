package stepdefs.timesheet;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.timesheet.TimeSheetPage;

/**
 * @author at-phuongdang
 */
public class TimeSheetDefinitions extends DriverBase implements En {
    private WebDriver driver;

    public TimeSheetDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeSheetPage myTimeSheet = initPage(getDriver(), TimeSheetPage.class);
        Given("^Display time sheet page$", () -> {
            driver.get(Constant.TIME_SHEET_PAGE_URL);
            waitForPageDisplayed(getDriver(), Constant.TIME_SHEET_PAGE_URL, By.id("page-wrapper"));
        });
        And("^Check item add timesheet exist$", myTimeSheet::clearTimeSheetItem);
        Then("^My Timesheet page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Display title timesheet content$", () -> Assert.assertTrue(myTimeSheet.isDisplayTitle()));
        And("^Display full record timesheet", () -> Assert.assertTrue(true, myTimeSheet.isDisplayFullColumns().toString()));
        And("^Disable button this week and can not click on timesheet$", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet("btnThisWeek").toString()));
        And("^Disable button submit and can not click on timesheet$", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet("btnSubmit").toString()));
        When("^Click on back button on timesheet$", myTimeSheet::clickBackButtonOnTimeSheet);
        When("^Click on next button on timesheet$", myTimeSheet::clickNextButtonOnTimeSheet);
        Then("^Can click this week button on timesheet$", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet("btnThisWeek").toString()));
        When("^Move to columns timesheet$", myTimeSheet::moveRowTimeSheet);
        Then("^Display button add new timesheet$", () -> Assert.assertTrue(myTimeSheet.getAddTimeSheetClickable()));
        When("^Display dialog create timesheet after click button add timesheet$", myTimeSheet::getAddTimeSheetsClickable);
        When("^Click on button timesheet$", myTimeSheet::clickAddTimeSheets);
        When("^Click first button add new timesheet$", myTimeSheet::clickFirstItemAddTimeSheet);
        Then("^Display dialog timesheet$", () -> Assert.assertTrue(myTimeSheet.isTimeSheetShowing()));
        And("^Display title item project is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleItemProjectShowing()));
        And("^Display title dropdown default is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultSelectProjectShowing()));
        And("^Display title item task is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleTaskShowing()));
        And("^Display title dropdown task default is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultSelectTaskShowing()));
        And("^Display text area description default value is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.isDescriptionShowing(content)));
        And("^Display text input time is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultInputTimeShowing()));
        And("^Display button Repeat every day is disable$", () -> Assert.assertTrue(myTimeSheet.checkButtonRepeatClickable()));
        And("^Display button Save is disable$", () -> Assert.assertFalse(myTimeSheet.checkButtonSaveClickable()));
        When("^Create and submit timesheet$", () -> Assert.assertTrue(myTimeSheet.getAddTimeSheetsClickable()));
        When("^Select first project on list project$", myTimeSheet::selectedFirstValueProject);
        Then("^Select item dialog project is \"([^\"]*)\"$", myTimeSheet::selectItemOnDialogProject);
        When("^Select first task on list task$", myTimeSheet::selectedFirstValueTask);
        Then("^Select item dialog task is \"([^\"]*)\"$", myTimeSheet::selectItemOnDialogTask);
        Then("^Display dialog task$", () -> Assert.assertTrue(myTimeSheet.displayDialogTask()));
        And("^Display button Repeat every day is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonRepeatEnableClickable()));
        And("^Display button Save is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonSaveClickable()));
        When("^Click button Repeat every day$", myTimeSheet::clickRepeatEveryDay);
        Then("^Display button submit is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonSubmitEnable()));
        And("^Display Dialog success is enable and show message timesheet$", () -> Assert.assertTrue(myTimeSheet.displayDialogAlert()));
        And("^Display element timeSheet$", () -> Assert.assertTrue(myTimeSheet.isExistsElementTimeSheet()));
        When("^Move to title columns timesheet$", myTimeSheet::moveToTitleTimeSheet);
        And("^Display dialog title project timesheet$", () -> Assert.assertTrue(true, myTimeSheet.isDialogTitleProjectShowing().toString()));
        When("^Click on columns timesheet$", myTimeSheet::clickColumnsTimeSheet);
        And("^Button delete timesheet is enable$", () -> Assert.assertTrue(myTimeSheet.isButtonDeleteShowing()));
        When("^Click on button delete timesheet$", myTimeSheet::clickButtonDelete);
        Then("^Display dialog confirm delete timesheet$", () -> Assert.assertTrue(myTimeSheet.displayDialogConfirmDelete()));
        And("^Display title timesheet is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleConfirmDialog()));
        And("^Display message timesheet is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getMessageConfirmDialog()));
        And("^Display button confirm delete timesheet$", () -> Assert.assertTrue(myTimeSheet.displayDeleteConfirmDialog()));
        And("^Display button confirm cancel timesheet$", () -> Assert.assertTrue(myTimeSheet.displayCancelConfirmDialog()));
        When("^Click button cancel timesheet$", myTimeSheet::clickButtonCancelDialogConfirm);
        Then("^Dismiss dialog confirm delete timesheet$", () -> Assert.assertFalse(myTimeSheet.dismissDialogConfirmDelete()));
        When("^Click button confirm delete timesheet$", myTimeSheet::clickButtonConfirmDelete);
        Then("^Element timeSheet is delete timesheet$", () -> Assert.assertFalse(myTimeSheet.isItemTimeSheetDelete()));
        When("^Input search is \"([^\"]*)\"$", myTimeSheet::inputSearch);
        Then("^Display Search result is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.displaySearchResult(content)));
        When("^Scroll edit timesheet$", myTimeSheet::scrollChangeItemTimeSheet);
        Then("^Change time sheet info is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.isTimeSheetChange(content)));
        When("^Click on first item add timesheet$", myTimeSheet::clickFirstItemTimeSheet);
        When("^I fill information for timeSheet$", myTimeSheet::fillInformationForTimeSheet);
        When("^Click button save on timeSheet dialog$", myTimeSheet::clickButtonSave);
        And("^Click button submit on timeSheet$", myTimeSheet::clickButtonSubmit);
        When("^Add timesheet record full$", myTimeSheet::addTimeSheetFullRecord);
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
