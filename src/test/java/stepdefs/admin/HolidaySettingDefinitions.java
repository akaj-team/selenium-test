package stepdefs.admin;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.admin.HolidaySettingPage;

import static vn.asiantech.base.Constant.HOLIDAY_SETTING_URL;

/**
 * @author at-phuongdang
 */
public class HolidaySettingDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private HolidaySettingPage holidaySettingPage;

    public HolidaySettingDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        holidaySettingPage = initPage(driver, HolidaySettingPage.class);

        Given("^Display holiday setting page$", () -> {
            driver.get(HOLIDAY_SETTING_URL);
            waitForPageDisplayed(getDriver(), HOLIDAY_SETTING_URL, By.id("page-wrapper"));
        });

        Then("^Holiday setting page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);

        And("^Display title content", () -> Assert.assertTrue(holidaySettingPage.isDisplayTitleContent()));

        And("^Display calendar content$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayCalendarContent().toString()));

        And("^Display button next and previous$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonControl().toString()));

        And("^Disable button today and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonTodayDisable().toString()));

        When("^Click button next on header$", () -> holidaySettingPage.onClickOnButtonNext());

        When("^Click button previous on header$", () -> holidaySettingPage.onClickOnButtonPrevious());

        Then("^Can click today button on header$", () -> Assert.assertTrue(true, holidaySettingPage.onButtonTodayClickable().toString()));

        Then("^Display full seven columns title header calendar$", () -> Assert.assertTrue(true, holidaySettingPage.displayFullTitleCalendar().toString()));

        And("^Display full item calendar day of month$", () -> Assert.assertTrue(true, holidaySettingPage.displayFullItemCalendar().toString()));

        When("^Click item calendar holiday$", () -> holidaySettingPage.clickItemHolidayCalendar());

        Then("^Display holiday detail dialog$", () -> Assert.assertTrue(true, holidaySettingPage.displayHolidayDetailDialog().toString()));

        And("^Display title holiday detail dialog is \"([^\"]*)\"$", (String title) -> Assert.assertEquals(title, holidaySettingPage.getTitleHolidayDialog()));

        And("^Display title input name$", () -> Assert.assertTrue(true, holidaySettingPage.displayTitleName().toString()));

        And("^Display title holiday time$", () -> Assert.assertTrue(true, holidaySettingPage.displayHolidayTime().toString()));

        And("^Display input description$", () -> Assert.assertTrue(true, holidaySettingPage.displayDescription().toString()));

        And("^Disable button save$", () -> Assert.assertTrue(true, holidaySettingPage.disableButtonSave().toString()));

        When("^Input Name is empty$", () -> holidaySettingPage.inputNameIsEmpty());

        Then("^Show message error validate is \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, holidaySettingPage.getMessageValidate()));

        When("^Input name is \"([^\"]*)\"$", (String value) -> holidaySettingPage.inputNameValue(value));

        When("^Click on box holiday time to$", () -> holidaySettingPage.clickBoxHolidayTimeTo());

        Then("^Display calendar date time$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayCalendarDateTime().toString()));

        When("^Choose today on dialog calendar date time$", () -> holidaySettingPage.chooseTodayOnDialogCalendar());

        Then("^Enable button save$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonSaveEnable().toString()));

        Given("^Enter full information$", () -> holidaySettingPage.enterFullInfo());

        When("^Click button save$", () -> holidaySettingPage.clickButtonSave());

        Then("^Message success is showing$", () -> Assert.assertTrue(true, holidaySettingPage.isMessageConfirmShowing().toString()));

        When("^Click item holiday$", () -> holidaySettingPage.clickItemHoliday());

        And("^Enable button delete$", () -> Assert.assertTrue(true, holidaySettingPage.isEnableButtonDelete().toString()));

        When("^Click button delete on dialog$", () -> holidaySettingPage.clickButtonDelete());

        Then("^Display dialog confirm delete$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogDeleteConfirm().toString()));

        And("^Display title dialog confirm is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.getTitleDialogConfirmDisplay()));

        And("^Display content dialog confirm is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.getContentDialogConfirmDisplay()));

        When("^Click button cancel on dialog confirm delete$", () -> holidaySettingPage.clickButtonConfirmCancel());

        Then("^Dismiss dialog confirm delete$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmDelete().toString()));

        When("^Click button delete on dialog confirm delete$", () -> holidaySettingPage.clickButtonConfirmDelete());

        Then("^Item holiday is delete$", () -> Assert.assertTrue(true, holidaySettingPage.isHolidayDeleted().toString()));
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
