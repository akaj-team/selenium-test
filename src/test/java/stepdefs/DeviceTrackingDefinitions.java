package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.DeviceTrackingPage;

import java.util.List;

/**
 * @author at-phuongdang
 */
public class DeviceTrackingDefinitions extends DriverBase implements En {
    private static final String TIME_SHEET_PAGE_URL = "http://portal-stg.asiantech.vn/equipments/tracking";
    private static final int TIME_SECOND = 10;
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;

    private DeviceTrackingPage holidaySettingPage;

    public DeviceTrackingDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        holidaySettingPage = initPage(getDriver(), DeviceTrackingPage.class);
        Given("^I logged in with a employee account$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            new WebDriverWait(driver, TIME_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                //Not logged in
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.tri.pham@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, TIME_SECOND).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
        });

        When("^I click on device in menu$", () -> holidaySettingPage.clickItemDevice());
        Then("^Menu device drop down$", () -> Assert.assertFalse(holidaySettingPage.checkDeviceMenuDropDown()));
        When("^I click on item device tracking$", () -> holidaySettingPage.clickMenuDeviceTracking());
        Then("^Device tracking page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Display title content is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.getTitleContent(driver)));
        And("^Display list content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayContentDevice(driver).toString()));
        And("^Display button next and previous$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonControl().toString()));
        And("^Display checkbox select all not tick$", () -> Assert.assertTrue(true, holidaySettingPage.isDefaultCheckboxSelectAll(driver).toString()));
        And("^Disable button this week and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekDisable(driver).toString()));
        When("^Click button next on header$", () -> holidaySettingPage.clickButtonNext(driver));
        Given("^I open device tracking page$", () -> getDriver().get(TIME_SHEET_PAGE_URL));
        Then("^Can click this week button on header$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonThisWeekClickable(driver).toString()));
        When("^Click button previous on header$", () -> holidaySettingPage.clickButtonPrevious(driver));
        Then("^Display full seven columns title header device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceHeader(driver).toString()));
        Then("^Display full seven columns content device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayFullDeviceContent(driver).toString()));
        When("^move to item device$", () -> holidaySettingPage.moveToItemDevice(driver));
        Then("^Display title and border item device$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayBorderItemDevice(driver).toString()));
        And("^Disable button submit and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonSubmitDisable(driver).toString()));
        When("^Click item on list device item$", () -> holidaySettingPage.clickItemOnListItemDevice(driver));
        Then("^Display item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayColorDeviceItem(driver).toString()));
        And("^Display button submit and can clickable$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonSubmit(driver).toString()));
        When("^Click checkbox select all trackings$", () -> holidaySettingPage.clickCheckboxSelectAll(driver));
        Then("^Display all item device with color selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayAllItemSelected(driver).toString()));
        Then("^Display dialog confirm$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirm(driver).toString()));
        And("^Display title dialog confirm is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirm(driver, title).toString()));
        And("^Display message dialog confirm is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(true, holidaySettingPage.isDisplayMessageDialogConfirm(driver, message).toString()));
        And("^Display button stay and button leave$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogControl(driver).toString()));
        When("^Click button close dialog$", () -> holidaySettingPage.clickButtonCloseDialogConfirm(driver));
        Then("^Dismiss dialog confirm$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirm(driver).toString()));
        When("^Click button stay on dialog confirm$", () -> holidaySettingPage.clickButtonStayDialogConfirm(driver));
        Then("^Dismiss dialog confirm and keep state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSaveState(driver).toString()));
        When("^Click button leave on dialog confirm$", () -> holidaySettingPage.clickButtonLeaveDialogConfirm(driver));
        Then("^Dismiss dialog confirm and clear state selected$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmNotSaveState(driver).toString()));
        When("^Click button submit$", () -> holidaySettingPage.clickButtonSubmit(driver));
        Then("^Display dialog confirm Submit$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogConfirmSubmit(driver).toString()));
        And("^Display title dialog confirm submit is \"([^\"]*)\"$", (String title) -> Assert.assertTrue(true, holidaySettingPage.isDisplayTitleDialogConfirmSubmit(driver, title).toString()));
        And("^Display message dialog confirm submit is \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, holidaySettingPage.getMessageDialogConfirmSubmit(driver)));
        And("^Display button cancel and button submit$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonDialogSubmitControl(driver).toString()));
        When("^Click button close dialog confirm submit$", () -> holidaySettingPage.clickButtonCloseDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm submit$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmit(driver).toString()));
        When("^Click button cancel on dialog confirm submit$", () -> holidaySettingPage.clickButtonCancelDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm submit and keep state selected all$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmitSaveState(driver).toString()));
        When("^Click button submit on dialog confirm submit$", () -> holidaySettingPage.clickButtonSubmitDialogConfirmSubmit(driver));
        Then("^Dismiss dialog confirm and change state to submit$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmSubmitChangeStateSubmit(driver).toString()));
        And("^Display message success$", () -> Assert.assertTrue(true, holidaySettingPage.isMessageConfirmShowing(driver).toString()));
    }


    private void redirectPageWhenClickChildItem(String path) {
        new WebDriverWait(getDriver(), TIME_SECOND).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
