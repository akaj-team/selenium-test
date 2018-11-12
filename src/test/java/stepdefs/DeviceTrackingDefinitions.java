package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.DeviceTrackingPage;

/**
 * @author at-phuongdang
 */
public class DeviceTrackingDefinitions extends DriverBase implements En {
    private WebDriver driver;

    public DeviceTrackingDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DeviceTrackingPage holidaySettingPage = initPage(driver, DeviceTrackingPage.class);
        Given("^Display device tracking page$", () -> {
            driver.get(Constant.DEVICE_TRACKING_URL);
            waitForPageDisplayed(getDriver(), Constant.DEVICE_TRACKING_URL, By.id("page-wrapper"));
        });
        Then("^Device tracking page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Display title content device", () -> Assert.assertTrue(holidaySettingPage.isDisplayTitleContent(driver)));
        And("^Display list content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayContentDevice(driver).toString()));
        And("^Display button next and previous device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonControl().toString()));
        And("^Display checkbox select all not tick$", () -> Assert.assertTrue(true, holidaySettingPage.isDefaultCheckboxSelectAll(driver).toString()));
        And("^Disable button this week and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekDisable(driver).toString()));
        When("^Click button next on header device tracking$", () -> holidaySettingPage.clickButtonNext(driver));
        Then("^Can click this week button on header device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekClickable(driver).toString()));
        When("^Click button previous on header device tracking$", () -> holidaySettingPage.clickButtonPrevious(driver));
        Then("^Display full seven columns title header device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceHeader(driver).toString()));
        Then("^Display full seven columns content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceContent(driver).toString()));
        When("^move to item device$", () -> holidaySettingPage.moveToItemDevice(driver));
        Then("^Display title and border item device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayBorderItemDevice(driver).toString()));
        And("^Disable button submit and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonSubmitDisable(driver).toString()));
        When("^Click item on list device item$", () -> holidaySettingPage.clickItemOnListItemDevice(driver));
        Then("^Display item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayColorDeviceItem(driver).toString()));
        And("^Display button submit and can clickable$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonSubmit(driver).toString()));
        When("^Click checkbox select all tracking$", () -> holidaySettingPage.clickCheckboxSelectAll(driver));
        Then("^Display all item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayAllItemSelected(driver).toString()));
        Then("^Display dialog confirm device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirm(driver).toString()));
        And("^Display title dialog confirm device tracking is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirm(driver, title).toString()));
        And("^Display message dialog confirm is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(true, holidaySettingPage.isDisplayMessageDialogConfirm(driver, message).toString()));
        And("^Display button stay and button leave$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogControl(driver).toString()));
        When("^Click button close dialog device tracking$", () -> holidaySettingPage.clickButtonCloseDialogConfirm(driver));
        Then("^Dismiss dialog confirm device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirm(driver).toString()));
        When("^Click button stay on dialog confirm$", () -> holidaySettingPage.clickButtonStayDialogConfirm(driver));
        Then("^Dismiss dialog confirm and keep state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSaveState(driver).toString()));
        When("^Click button leave on dialog confirm$", () -> holidaySettingPage.clickButtonLeaveDialogConfirm(driver));
        Then("^Dismiss dialog confirm and clear state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmNotSaveState(driver).toString()));
        When("^Click button submit device tracking$", () -> holidaySettingPage.clickButtonSubmit(driver));
        Then("^Display dialog confirm Submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirmSubmit(driver).toString()));
        And("^Display title dialog confirm submit is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirmSubmit(driver, title).toString()));
        And("^Display message dialog confirm submit is \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, holidaySettingPage.getMessageDialogConfirmSubmit(driver)));
        And("^Display button cancel and button submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogSubmitControl(driver).toString()));
        When("^Click button close dialog confirm submit device tracking$", () -> holidaySettingPage.clickButtonCloseDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmit(driver).toString()));
        When("^Click button cancel on dialog confirm submit device tracking$", () -> holidaySettingPage.clickButtonCancelDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm submit and keep state selected all$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmitSaveState(driver).toString()));
        When("^Click button submit on dialog confirm submit device tracking$", () -> holidaySettingPage.clickButtonSubmitDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm and change state to submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmitChangeStateSubmit(driver).toString()));
        And("^Display message success device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isMessageConfirmShowing(driver).toString()));
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
