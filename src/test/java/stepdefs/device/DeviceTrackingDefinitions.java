package stepdefs.device;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.device.DeviceTrackingPage;

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
            waitForPageDisplayed(getDriver(), Constant.DEVICE_TRACKING_URL, By.cssSelector(".ibox-content.main-content"));
        });

        Then("^Device tracking page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);

        And("^Display title content device", () -> Assert.assertTrue(holidaySettingPage.isDisplayTitleContent()));

        And("^Display list content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayContentDevice().toString()));

        And("^Display button next and previous device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonControl().toString()));

        And("^Display checkbox select all not tick$", () -> Assert.assertTrue(true, holidaySettingPage.isDefaultCheckboxSelectAll().toString()));

        And("^Disable button this week and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekDisable().toString()));

        When("^Click button next on header device tracking$", holidaySettingPage::clickButtonNext);

        Then("^Can click this week button on header device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekClickable().toString()));

        When("^Click button previous on header device tracking$", holidaySettingPage::clickButtonPrevious);

        Then("^Display full seven columns title header device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceHeader().toString()));

        Then("^Display full seven columns content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceContent().toString()));

        When("^Move to item device$", holidaySettingPage::moveToItemDevice);

        Then("^Display title and border item device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayBorderItemDevice().toString()));

        And("^Disable button submit and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonSubmitDisable().toString()));

        When("^Click item on list device item$", holidaySettingPage::clickItemOnListItemDevice);

        Then("^Display item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayColorDeviceItem().toString()));

        And("^Display button submit and can clickable$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonSubmit().toString()));

        When("^Click checkbox select all tracking$", holidaySettingPage::clickCheckboxSelectAll);

        Then("^Display all item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayAllItemSelected().toString()));

        Then("^Display dialog confirm device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirm().toString()));

        And("^Display title dialog confirm device tracking is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirm(title).toString()));

        And("^Display message dialog confirm is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(true, holidaySettingPage.isDisplayMessageDialogConfirm(message).toString()));

        And("^Display button stay and button leave$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogControl().toString()));

        When("^Click button close dialog device tracking$", holidaySettingPage::clickButtonCloseDialogConfirm);

        Then("^Dismiss dialog confirm device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirm().toString()));

        When("^Click button stay on dialog confirm$", holidaySettingPage::clickButtonStayDialogConfirm);

        Then("^Dismiss dialog confirm and keep state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSaveState().toString()));

        When("^Click button leave on dialog confirm$", holidaySettingPage::clickButtonLeaveDialogConfirm);

        Then("^Dismiss dialog confirm and clear state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmNotSaveState().toString()));

        When("^Click button submit device tracking$", holidaySettingPage::clickButtonSubmit);

        Then("^Display dialog confirm Submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirmSubmit().toString()));

        And("^Display title dialog confirm submit is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirmSubmit(title).toString()));

        And("^Display message dialog confirm submit is \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, holidaySettingPage.getMessageDialogConfirmSubmit()));

        And("^Display button cancel and button submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogSubmitControl().toString()));

        When("^Click button close dialog confirm submit device tracking$", holidaySettingPage::clickButtonCloseDialogConfirmSubmit);

        Then("^Dismiss dialog confirm submit device tracking$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmit().toString()));

        When("^Click button cancel on dialog confirm submit device tracking$", holidaySettingPage::clickButtonCancelDialogConfirmSubmit);

        Then("^Dismiss dialog confirm submit and keep state selected all$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmitSaveState().toString()));
    }

    private void redirectPageWhenClickChildItem(final String path) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
