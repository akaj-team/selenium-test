package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.TimeSheetPage;

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
        TimeSheetPage myTimeSheet = initPage(driver, TimeSheetPage.class);
        Given("^Display time sheet page$", () -> {
            driver.get(Constant.TIME_SHEET_PAGE_URL);
            waitForPageDisplayed(getDriver(), Constant.TIME_SHEET_PAGE_URL, By.id("page-wrapper"));
        });
        Then("^My Timesheet page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Display title timesheet content$", () -> Assert.assertTrue(true, myTimeSheet.isDisplayTitle(driver).toString()));
        And("^Display full record timesheet", () -> Assert.assertTrue(true, myTimeSheet.isDisplayFullColumns(driver).toString()));
        And("^Disable button this week and can not click", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet(driver, "btnThisWeek").toString()));
        And("^Disable button submit and can not click", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet(driver, "btnSubmit").toString()));
        When("^Click on back button on timesheet$", () -> myTimeSheet.clickBackButtonOnTimeSheet(driver));
        When("^Click on next button on timesheet$", () -> myTimeSheet.clickNextButtonOnTimeSheet(driver));
        Then("^Can click this week button on timesheet$", () -> Assert.assertTrue(true, myTimeSheet.getClickableTimeSheet(driver, "btnThisWeek").toString()));
        When("^Move to columns timesheet$", () -> myTimeSheet.moveRowTimeSheet(driver));
        Then("^Display button add new timesheet$", () -> Assert.assertTrue(true, myTimeSheet.getAddTimeSheetClickable(driver).toString()));
        When("^Display dialog create timesheet after click button add timesheet$", myTimeSheet::getAddTimeSheetsClickable);
        When("^Click on button timesheet$", () -> myTimeSheet.clickAddTimeSheets(driver));
        When("^Click first button add new timesheet$", () -> myTimeSheet.clickFirstItemAddTimeSheet(driver));
        Then("^Display dialog timesheet$", () -> Assert.assertTrue(true, myTimeSheet.isTimeSheetShowing(driver).toString()));
        And("^Display title item project is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleItemProjectShowing(driver)));
        And("^Display title dropdown default is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultSelectProjectShowing()));
        And("^Display title item task is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleTaskShowing(driver)));
        And("^Display title dropdown task default is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultSelectTaskShowing(driver)));
        And("^Display text area description default value is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.isDescriptionShowing(content)));
        And("^Display text input time is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getDefaultInputTimeShowing()));
        And("^Display button Repeat every day is disable$", () -> Assert.assertTrue(myTimeSheet.checkButtonRepeatClickable(driver)));
        And("^Display button Save is disable$", () -> Assert.assertFalse(myTimeSheet.checkButtonSaveClickable(driver)));
        When("^Create and submit timesheet$", myTimeSheet::getAddTimeSheetsClickable);
        When("^Select first project on list project$", () -> myTimeSheet.selectedFirstValueProject(driver));
        Then("^Select item dialog project is \"([^\"]*)\"$", (String content) -> myTimeSheet.selectItemOnDialogProject(driver, content));
        When("^Select first task on list task$", () -> myTimeSheet.selectedFirstValueTask(driver));
        Then("^Select item dialog task is \"([^\"]*)\"$", (String content) -> myTimeSheet.selectItemOnDialogTask(driver, content));
        Then("^Display dialog task$", () -> Assert.assertTrue(myTimeSheet.displayDialogTask(driver)));
        And("^Display button Repeat every day is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonRepeatEnableClickable(driver)));
        And("^Display button Save is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonSaveClickable(driver)));
        When("^Click button Repeat every day$", () -> myTimeSheet.clickRepeatEveryDay(driver));
        Then("^Display button submit is enable$", () -> Assert.assertTrue(myTimeSheet.checkButtonSubmitEnable(driver)));
        And("^Display Dialog success is enable and show message$", () -> Assert.assertTrue(myTimeSheet.displayDialogAlert(driver)));
        And("^Display element timeSheet$", () -> Assert.assertTrue(myTimeSheet.isExistsElementTimeSheet()));
        When("^Move to title columns timesheet$", () -> myTimeSheet.moveToTitleTimeSheet(driver));
        And("^Display dialog title project$", () -> Assert.assertTrue(true, myTimeSheet.isDialogTitleProjectShowing(driver).toString()));
        When("^Click on columns timesheet$", () -> myTimeSheet.clickColumnsTimeSheet(driver));
        And("^Button delete is enable$", () -> Assert.assertTrue(myTimeSheet.isButtonDeleteShowing(driver)));
        When("^Click on button delete$", () -> myTimeSheet.clickButtonDelete(driver));
        Then("^Display dialog confirm delete$", () -> Assert.assertTrue(myTimeSheet.displayDialogConfirmDelete(driver)));
        And("^Display title is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getTitleConfirmDialog(driver)));
        And("^Display message is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, myTimeSheet.getMessageConfirmDialog(driver)));
        And("^Display button confirm delete$", () -> Assert.assertTrue(myTimeSheet.displayDeleteConfirmDialog(driver)));
        And("^Display button confirm cancel$", () -> Assert.assertTrue(myTimeSheet.displayCancelConfirmDialog()));
        When("^Click button cancel$", myTimeSheet::clickButtonCancelDialogConfirm);
        Then("^Dismiss dialog confirm delete$", () -> Assert.assertFalse(myTimeSheet.dismissDialogConfirmDelete()));
        When("^Click button confirm delete$", () -> myTimeSheet.clickButtonConfirmDelete(driver));
        Then("^Element timeSheet is delete$", () -> Assert.assertFalse(myTimeSheet.isItemTimeSheetDelete(driver)));
        When("^Input search is \"([^\"]*)\"$", myTimeSheet::inputSearch);
        Then("^Display Search result is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.displaySearchResult(driver, content)));
        When("^Scroll edit timesheet$", () -> myTimeSheet.scrollChangeItemTimeSheet(driver));
        Then("^Change time sheet info is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.isTimeSheetChange(driver, content)));
        When("^Click on first item add timesheet$", () -> myTimeSheet.clickFirstItemTimeSheet(driver));
        When("^I fill information for timeSheet$", () -> myTimeSheet.fillInformationForTimeSheet(driver));
        When("^Click button save on timeSheet dialog$", () -> myTimeSheet.clickButtonSave(driver));
        And("^Click button submit on timeSheet$", () -> myTimeSheet.clickButtonSubmit(driver));
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
