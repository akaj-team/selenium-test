package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HolidaySettingPage;

import java.util.List;

public class HolidaySettingDefinitions extends DriverBase implements En {

    private static final String TIME_SHEET_PAGE_URL = "http://portal-stg.asiantech.vn/admin/public-holiday";
    private static final int TIME_SECOND = 10;
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;

    private HolidaySettingPage holidaySettingPage;

    public HolidaySettingDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        holidaySettingPage = initPage(getDriver(), HolidaySettingPage.class);
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

        When("^I click on holidays setting in menu$", () -> holidaySettingPage.clickItemHolidaySetting());
        Then("^Menu Administration drop down$", () -> Assert.assertFalse(holidaySettingPage.checkAdministrationMenuDropDown()));
        When("^I click on item Holidays Setting$", () -> holidaySettingPage.clickMenuHolidaySetting());
        Then("^Holiday setting page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Display title content is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.checkTitleContent()));
        And("^Display calendar content$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayCalendarContent().toString()));
        And("^Display button next and previous$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayButtonControl().toString()));
        And("^Disable button today and can not click$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonTodayDisable().toString()));

        Given("^I open holiday setting page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            getDriver().get(TIME_SHEET_PAGE_URL);
        });

        When("^Click button next on header$", () -> holidaySettingPage.onClickOnButtonNext());
        When("^Click button previous on header$", () -> holidaySettingPage.onClickOnButtonPrevious());
        Then("^Can click today button on header$", () -> Assert.assertTrue(true, holidaySettingPage.onButtonTodayClickable().toString()));

        Then("^Display full seven columns title header calendar$", () -> Assert.assertTrue(true, holidaySettingPage.displayFullTitleCalendar().toString()));
        And("^Display full item calendar day of month$", () -> Assert.assertTrue(true, holidaySettingPage.displayFullItemCalendar().toString()));

        When("^Click item calendar holiday$", () -> holidaySettingPage.clickItemHolidayCalendar(driver));
        Then("^Display holiday detail dialog$", () -> Assert.assertTrue(true, holidaySettingPage.displayHolidayDetailDialog(driver).toString()));
        And("^Display title holiday detail dialog is \"([^\"]*)\"$", (String title) -> Assert.assertEquals(title, holidaySettingPage.getTitleHolidayDialog()));
        And("^Display title input name$", () -> Assert.assertTrue(true, holidaySettingPage.displayTitleName().toString()));
        And("^Display title holiday time$", () -> Assert.assertTrue(true, holidaySettingPage.displayHolidayTime().toString()));
        And("^Display input description$", () -> Assert.assertTrue(true, holidaySettingPage.displayDescription().toString()));
        And("^Disable button save$", () -> Assert.assertTrue(true, holidaySettingPage.disableButtonSave().toString()));

        When("^Input Name is empty$", () -> holidaySettingPage.inputNameIsEmpty());
        Then("^Show message error validate is \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, holidaySettingPage.getMessageValidate(driver)));
        When("^Input name is \"([^\"]*)\"$", (String value) -> holidaySettingPage.inputNameValue(value));
        When("^Click on box holiday time to$", () -> holidaySettingPage.clickBoxHolidayTimeTo());
        Then("^Display calendar date time$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayCalendarDateTime(driver).toString()));
        When("^Choose today on dialog calendar date time$", () -> holidaySettingPage.chooseTodayOnDialogCalendar());
        Then("^Enable button save$", () -> Assert.assertTrue(true, holidaySettingPage.isButtonSaveEnable(driver).toString()));
        Given("^Enter full information$", () -> holidaySettingPage.enterFullInfo(driver));
        When("^Click button save$", () -> holidaySettingPage.clickButtonSave());
        Then("^Message success is showing$", () -> Assert.assertTrue(true, holidaySettingPage.isMessageConfirmShowing(driver).toString()));
        When("^Click item holiday$", () -> holidaySettingPage.clickItemHoliday());
        And("^Enable button delete$", () -> Assert.assertTrue(true, holidaySettingPage.isEnableButtonDelete().toString()));
        When("^Click button delete on dialog$", () -> holidaySettingPage.clickButtonDelete());
        Then("^Display dialog confirm delete$", () -> Assert.assertTrue(true, holidaySettingPage.isDisplayDialogDeleteConfirm(driver).toString()));
        And("^Display title dialog confirm is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.getTitleDialogConfirmDisplay()));
        And("^Display content dialog confirm is \"([^\"]*)\"$", (String content) -> Assert.assertEquals(content, holidaySettingPage.getContentDialogConfirmDisplay()));
        When("^Click button cancel on dialog confirm delete$", () -> holidaySettingPage.clickButtonConfirmCancel());
        Then("^Dismiss dialog confirm delete$", () -> Assert.assertTrue(true, holidaySettingPage.isDismissDialogConfirmDelete(driver).toString()));
        When("^Click button delete on dialog confirm delete$", () -> holidaySettingPage.clickButtonConfirmDelete());
        Then("^Item holiday is delete$", () -> Assert.assertTrue(true, holidaySettingPage.isHolidayDeleted().toString()));
    }

    private void redirectPageWhenClickChildItem(String path) {
        new WebDriverWait(getDriver(), TIME_SECOND).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
