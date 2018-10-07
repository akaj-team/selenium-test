package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HolidaySettingPage extends BasePage<HolidaySettingPage> {

    private static final int TIME_OUT_WAITED_ELEMENT = 5;
    private static final int COLUMNS_HEADER_COUNT = 7;

    @FindBy(id = "side-menu")
    private
    WebElement sideMenu;

    @FindBy(css = ".fc-center")
    private
    WebElement titleCotent;

    @FindBy(css = ".fc-right")
    private
    WebElement btnToday;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private
    WebElement btnSubmit;

    @FindBy(css = ".fc-prev-button.ui-button.ui-state-default.ui-corner-left")
    private
    WebElement btnPrevious;

    @FindBy(css = ".fc-next-button.ui-button.ui-state-default.ui-corner-right")
    private
    WebElement btnNext;

    @FindBy(css = ".ibox-content.main-content")
    private
    WebElement calendarContent;

    @FindBy(id = "dialog-holiday-wrapper")
    private
    WebElement calendarDialog;

    @FindBy(className = "holiday-dialog")
    private
    WebElement calendarDialogItem;

    @FindBy(id = "btn-save-holiday")
    private
    WebElement btnSave;

    @FindBy(id = "input-holiday-name")
    private
    WebElement edtName;

    @FindBy(id = "calendar-end-date-holiday")
    private
    WebElement edtCalendarEndDay;

    @FindBy(id = "input-description")
    private
    WebElement edtDescription;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private
    WebElement messageConfirm;

    @Override
    public HolidaySettingPage navigateTo(WebDriver webDriver) {
        return this;
    }

    public final void clickItemHolidaySetting() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        itemHolidaySetting.click();
    }

    public final void clickMenuHolidaySetting() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        WebElement device = itemHolidaySetting.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        device.click();
    }

    public final boolean checkTitleContent(final WebDriver driver, final String title) {
        waitForElement(driver, titleCotent, TIME_OUT_WAITED_ELEMENT);
        if (titleCotent != null) {
            WebElement itemTitle = titleCotent.findElement(By.tagName("h2"));
            if (itemTitle != null && itemTitle.isEnabled()) {
                return itemTitle.getText().equals(title);
            }
        }
        return false;
    }

    public final boolean checkDeviceMenuDropDown() {
        WebElement itemDevice = getItemMenuInPosition();
        return itemDevice.findElement(By.tagName("ul")).getRect().height == 0;
    }

    private WebElement getItemMenuInPosition() {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = sideMenu.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(7);
    }

    public final Boolean isDisplayCalendarContent(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        return calendarContent.isEnabled();
    }

    public final Boolean isDisplayButtonControl() {
        if (btnNext != null && btnPrevious != null) {
            return btnPrevious.isEnabled() && btnNext.isEnabled();
        }
        return false;
    }

    public final Boolean isButtonTodayDisable(final WebDriver driver) {
        waitForElement(driver, btnToday, TIME_OUT_WAITED_ELEMENT);
        return !btnToday.isEnabled();
    }

    public final void clickOnButtonNext(final WebDriver driver) {
        waitForElement(driver, btnNext, TIME_OUT_WAITED_ELEMENT);
        if (btnNext != null && btnNext.isEnabled()) {
            btnNext.click();
        }
    }

    public final Boolean onButtonTodayClickable(final WebDriver driver) {
        waitForElement(driver, btnToday, TIME_OUT_WAITED_ELEMENT);
        if (btnToday != null) {
            return btnToday.isEnabled();
        }
        return false;
    }

    public final void clickOnButtonPrevious(final WebDriver driver) {
        waitForElement(driver, btnPrevious, TIME_OUT_WAITED_ELEMENT);
        if (btnPrevious != null && btnPrevious.isEnabled()) {
            btnPrevious.click();
        }
    }

    public final Boolean displayFullTitleCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        if (calendarContent != null) {
            WebElement itemHeader = calendarContent.findElement(By.cssSelector(".fc-row.ui-widget-header"));
            List<WebElement> headers = itemHeader.findElements(By.tagName("th"));
            for (int i = 1; i < headers.size(); i++) {
                if (i == COLUMNS_HEADER_COUNT && headers.get(i).isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Boolean displayFullItemCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        if (calendarContent != null) {
            WebElement itemCalendar = calendarContent.findElement(By.cssSelector(".fc-scroller.fc-day-grid-container"));
            if (itemCalendar != null) {
                List<WebElement> rowsCalendar = itemCalendar.findElements(By.cssSelector(".fc-bg"));
                for (WebElement rowItem : rowsCalendar) {
                    List<WebElement> columnsCalendar = rowItem.findElements(By.tagName("td"));
                    for (int i = 1; i < columnsCalendar.size(); i++) {
                        if (i == COLUMNS_HEADER_COUNT && columnsCalendar.get(i).isEnabled()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final void clickItemHolidayCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        if (calendarContent != null) {
            WebElement itemCalendar = calendarContent.findElement(By.cssSelector(".fc-scroller.fc-day-grid-container"));
            if (itemCalendar != null) {
                List<WebElement> rowsCalendar = itemCalendar.findElements(By.cssSelector(".fc-bg"));
                for (WebElement rowItem : rowsCalendar) {
                    List<WebElement> columnsCalendar = rowItem.findElements(By.tagName("td"));
                    columnsCalendar.get(2).click();
                    return;
                }
            }
        }
    }

    public final Boolean displayHolidayDetailDialog(final WebDriver driver) {
        waitForElement(driver, calendarDialog, TIME_OUT_WAITED_ELEMENT);
        return calendarDialog.isDisplayed();
    }

    public final Boolean displayTitleHolidayDialog(final WebDriver driver, final String title) {
        waitForElement(driver, calendarDialogItem, TIME_OUT_WAITED_ELEMENT);
        WebElement titleDialog = calendarDialogItem.findElement(By.className("ui-dialog-titlebar"));
        if (titleDialog != null) {
            return titleDialog.getText().equals(title);
        }
        return false;
    }

    public final Boolean displayTitleName(final WebDriver driver) {
        waitForElement(driver, calendarDialogItem, TIME_OUT_WAITED_ELEMENT);
        WebElement itemParent = calendarDialogItem.findElement(By.className("row"));
        WebElement titleInputName = itemParent.findElement(By.className("required"));
        return titleInputName.isEnabled();
    }

    public final Boolean displayHolidayTime(final WebDriver driver) {
        waitForElement(driver, calendarDialogItem, TIME_OUT_WAITED_ELEMENT);
        WebElement itemParent = calendarDialogItem.findElement(By.cssSelector(".row.m-b"));
        WebElement titleInputTime = itemParent.findElement(By.className("required"));
        return titleInputTime.isEnabled();
    }

    public final Boolean displayDescription(final WebDriver driver) {
        waitForElement(driver, edtDescription, TIME_OUT_WAITED_ELEMENT);
        return edtDescription.isEnabled();
    }

    public final Boolean disableButtonSave(final WebDriver driver) {
        waitForElement(driver, btnSave, TIME_OUT_WAITED_ELEMENT);
        return !btnSave.isEnabled();
    }

    public final void inputNameIsEmpty(final WebDriver driver) {
        waitForElement(driver, edtName, TIME_OUT_WAITED_ELEMENT);
        edtName.clear();
        edtName.sendKeys("s");
        edtName.sendKeys(Keys.BACK_SPACE);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final Boolean displayMessageValidate(final WebDriver driver, final String message) {
        waitForElement(driver, calendarDialogItem, TIME_OUT_WAITED_ELEMENT);
        WebElement titleMessage = calendarDialogItem.findElement(By.className("help-block"));
        return titleMessage.isEnabled() && titleMessage.getText().equals(message);
    }

    public final void inputNameValue(final WebDriver driver, final String value) {
        waitForElement(driver, edtName, TIME_OUT_WAITED_ELEMENT);
        edtName.clear();
        edtName.sendKeys(value);
    }

    public final void clickBoxHolidayTimeTo(final WebDriver driver) {
        waitForElement(driver, edtCalendarEndDay, TIME_OUT_WAITED_ELEMENT);
        WebElement boxTimeTo = edtCalendarEndDay.findElement(By.tagName("input"));
        if (boxTimeTo != null) {
            boxTimeTo.click();
        }
    }

    public final Boolean isDisplayCalendarDateTime(final WebDriver driver) {
        waitForElement(driver, edtCalendarEndDay, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> elements = edtCalendarEndDay.findElements(By.tagName("div"));
        return elements.get(0).isDisplayed();
    }

    public final void chooseTodayOnDialogCalendar(final WebDriver driver) {
        waitForElement(driver, edtCalendarEndDay, TIME_OUT_WAITED_ELEMENT);
        Calendar calendar = Calendar.getInstance();
        String currentDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        WebElement dateItem = edtCalendarEndDay.findElement(By.tagName("tbody"));
        if (dateItem != null) {
            List<WebElement> rows = dateItem.findElements(By.tagName("tr"));
            for (WebElement row : rows) {
                List<WebElement> columns = row.findElements(By.tagName("td"));
                for (WebElement column : columns) {
                    WebElement itemToday = column.findElement(By.tagName("a"));
                    if (itemToday != null && itemToday.getText().equals(currentDate)) {
                        itemToday.click();
                        return;
                    }
                }
            }
        }
    }

    private void inputValueToDescription(final WebDriver driver) {
        waitForElement(driver, edtDescription, TIME_OUT_WAITED_ELEMENT);
        if (edtDescription != null && edtDescription.isEnabled()) {
            edtDescription.sendKeys("Description for holiday");
        }
    }

    public final Boolean isButtonSaveEnable(final WebDriver driver) {
        waitForElement(driver, btnSave, TIME_OUT_WAITED_ELEMENT);
        return btnSave.isDisplayed();
    }

    public final void enterFullInfo(final WebDriver driver) {
        clickItemHolidaySetting();
        clickMenuHolidaySetting();
        clickItemHolidayCalendar(driver);
        inputNameValue(driver, "Request tet holiday");
        clickBoxHolidayTimeTo(driver);
        chooseTodayOnDialogCalendar(driver);
        inputValueToDescription(driver);
    }

    public final void clickButtonSave(final WebDriver driver) {
        waitForElement(driver, btnSave, TIME_OUT_WAITED_ELEMENT);
        if (btnSave != null && btnSave.isEnabled()) {
            btnSave.click();
        }
    }

    public final Boolean isMessageConfirmShowing(final WebDriver driver) {
        waitForElement(driver, messageConfirm, TIME_OUT_WAITED_ELEMENT);
        return messageConfirm.isDisplayed();
    }
}
