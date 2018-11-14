package vn.asiantech.page.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.Calendar;
import java.util.List;

/**
 * @author at-phuongdang
 */
public class HolidaySettingPage extends BasePage<HolidaySettingPage> {

    private static final int COLUMNS_HEADER_COUNT = 7;
    private static final int CONFIRM_DIALOG_BUTTON_CANCEL = 0;
    private static final int CONFIRM_DIALOG_BUTTON_DELETE = 1;
    private static final int END_DATE_POSITION = 1;
    private static final int FIRST_ITEM_SELECTED = 0;

    @FindBy(className = "fc-center")
    private WebElement tvTitleCenter;

    @FindBy(className = "fc-right")
    private WebElement btnToday;

    @FindBy(className = "fc-prev-button")
    private WebElement btnPrevious;

    @FindBy(className = "fc-next-button")
    private WebElement btnNext;

    @FindBy(css = ".ibox-content.main-content")
    private WebElement calendarContent;

    @FindBy(id = "holiday-setting-dialog-wrapper")
    private WebElement dlgCalendar;

    @FindBy(className = "holiday-dialog")
    private WebElement calendarDialogItem;

    @FindBy(id = "btn-submit-holiday")
    private WebElement btnSave;

    @FindBy(className = "form-control")
    private WebElement edtName;

    @FindBy(className = "ui-inputtext")
    private WebElement edtDescription;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private WebElement tvMessageConfirm;

    @FindBy(id = "btn-delete-holiday")
    private WebElement btnDelete;

    @FindBy(id = "confirm-delete-panel-wrapper")
    private WebElement dlgDeleteConfirm;

    private WebDriver driver;

    public HolidaySettingPage(final WebDriver driver) {
        this.driver = driver;
    }

    public HolidaySettingPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public final HolidaySettingPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final boolean isDisplayTitleContent() {
        return tvTitleCenter.findElement(By.tagName("h2")).isDisplayed();
    }

    public final Boolean isDisplayCalendarContent() {
        return calendarContent.isEnabled();
    }

    public final Boolean isDisplayButtonControl() {
        return btnPrevious.isEnabled() && btnNext.isEnabled();
    }

    public final Boolean isButtonTodayDisable() {
        return !btnToday.isEnabled();
    }

    public final void onClickOnButtonNext() {
        waitForElement(driver, btnNext);
        btnNext.click();
    }

    public final Boolean onButtonTodayClickable() {
        return btnToday.isEnabled();
    }

    public final void onClickOnButtonPrevious() {
        waitForElement(driver, btnPrevious);
        btnPrevious.click();
    }

    public final Boolean displayFullTitleCalendar() {
        waitForElement(driver, calendarContent);
        WebElement itemHeader = calendarContent.findElement(By.cssSelector(".fc-row.ui-widget-header"));
        List<WebElement> headers = itemHeader.findElements(By.tagName("th"));
        for (int i = 1; i < headers.size(); i++) {
            if (i == COLUMNS_HEADER_COUNT && headers.get(i).isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public final Boolean displayFullItemCalendar() {
        WebElement itemCalendar = calendarContent.findElement(By.cssSelector(".fc-scroller.fc-day-grid-container"));
        List<WebElement> rowsCalendar = itemCalendar.findElements(By.cssSelector(".fc-bg"));
        for (WebElement rowItem : rowsCalendar) {
            List<WebElement> columnsCalendar = rowItem.findElements(By.tagName("td"));
            for (int i = 1; i < columnsCalendar.size(); i++) {
                if (i == COLUMNS_HEADER_COUNT && columnsCalendar.get(i).isEnabled()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void clickItemHolidayCalendar() {
        waitForElement(driver, calendarContent);
        List<WebElement> itemCalendars = calendarContent.findElements(By.className("fc-content-skeleton"));
        for (WebElement element : itemCalendars) {
            WebElement body = element.findElement(By.tagName("thead"));
            List<WebElement> columnsCalendar = body.findElements(By.tagName("td"));
            for (WebElement row : columnsCalendar) {
                if (row.getText().equals(getCurrentDay())) {
                    row.click();
                    return;
                }
            }
        }
    }

    public final Boolean displayHolidayDetailDialog() {
        waitForElement(driver, dlgCalendar);
        return dlgCalendar.isDisplayed();
    }

    public final String getTitleHolidayDialog() {
        WebElement titleDialog = calendarDialogItem.findElement(By.className("ui-dialog-titlebar"));
        return titleDialog.getText();
    }

    public final Boolean displayTitleName() {
        WebElement itemParent = calendarDialogItem.findElement(By.className("row"));
        WebElement titleInputName = itemParent.findElement(By.className("required"));
        return titleInputName.isEnabled();
    }

    public final Boolean displayHolidayTime() {
        WebElement itemParent = calendarDialogItem.findElement(By.cssSelector(".row.m-b"));
        WebElement titleInputTime = itemParent.findElement(By.className("required"));
        return titleInputTime.isEnabled();
    }

    public final Boolean displayDescription() {
        return edtDescription.isEnabled();
    }

    public final Boolean disableButtonSave() {
        return !btnSave.isEnabled();
    }

    public final void inputNameIsEmpty() {
        edtName.clear();
        edtName.sendKeys("s");
        edtName.sendKeys(Keys.BACK_SPACE);
    }

    public final String getMessageValidate() {
        waitForElement(driver, calendarDialogItem);
        WebElement titleMessage = calendarDialogItem.findElement(By.className("help-block"));
        return titleMessage.getText();
    }

    public final void inputNameValue(final String value) {
        waitForElement(driver, edtName);
        edtName.clear();
        edtName.sendKeys(value);
    }

    public final void clickBoxHolidayTimeTo() {
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        WebElement boxTimeTo = inputDates.get(END_DATE_POSITION).findElement(By.tagName("input"));
        boxTimeTo.click();
    }

    public final Boolean isDisplayCalendarDateTime() {
        waitForElement(driver, dlgCalendar);
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        List<WebElement> elements = inputDates.get(END_DATE_POSITION).findElements(By.tagName("div"));
        return elements.get(FIRST_ITEM_SELECTED).isDisplayed();
    }

    public final void chooseTodayOnDialogCalendar() {
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        WebElement dateItem = inputDates.get(END_DATE_POSITION).findElement(By.tagName("table"));
        WebElement dateItemBody = dateItem.findElement(By.tagName("tbody"));
        List<WebElement> rows = dateItemBody.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                WebElement itemToday = column.findElement(By.tagName("a"));
                if (itemToday.isEnabled() && itemToday.getText().equals(getCurrentDay())) {
                    itemToday.click();
                    return;
                }
            }
        }
    }

    public final void enterFullInfo() {
        clickItemHolidayCalendar();
        inputNameValue("Request date " + Math.random());
        clickBoxHolidayTimeTo();
        chooseTodayOnDialogCalendar();
        inputValueToDescription();
    }

    public final void clickButtonSave() {
        btnSave.click();
    }

    public final Boolean isMessageConfirmShowing() {
        waitForElement(driver, tvMessageConfirm);
        return tvMessageConfirm.isDisplayed();
    }

    public final void clickItemHoliday() {
        waitForElement(driver, calendarContent);
        List<WebElement> itemHolidays = calendarContent.findElements(By.className("fc-content-skeleton"));
        outerLoop:
        for (WebElement itemHoliday : itemHolidays) {
            List<WebElement> days = itemHoliday.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
            for (WebElement day : days) {
                waitForElement(driver, day);
                if (day.getAttribute("class").equals("fc-event-container")) {
                    day.findElement(By.tagName("a")).click();
                    break outerLoop;
                }
            }
        }
    }

    public final Boolean isEnableButtonDelete() {
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete() {
        btnDelete.click();
    }

    public final Boolean isDisplayDialogDeleteConfirm() {
        waitForElement(driver, dlgDeleteConfirm);
        return dlgDeleteConfirm.isDisplayed();
    }

    public final void clickButtonConfirmCancel() {
        List<WebElement> itemControllers = dlgDeleteConfirm.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
        itemControllers.get(CONFIRM_DIALOG_BUTTON_CANCEL).click();
    }

    public final String getTitleDialogConfirmDisplay() {
        WebElement titleDialog = dlgDeleteConfirm.findElement(By.tagName("h4"));
        return titleDialog.getText();
    }

    public final String getContentDialogConfirmDisplay() {
        WebElement contentDialog = dlgDeleteConfirm.findElement(By.className("m-b"));
        return contentDialog.getText();
    }

    public final Boolean isDismissDialogConfirmDelete() {
        try {
            waitForElement(driver, dlgDeleteConfirm);
            return dlgDeleteConfirm.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public final void clickButtonConfirmDelete() {
        clickButtonDelete();
        List<WebElement> itemControllers = dlgDeleteConfirm.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
        itemControllers.get(CONFIRM_DIALOG_BUTTON_DELETE).click();
    }

    public final Boolean isHolidayDeleted() {
        try {
            WebElement itemHoliday = calendarContent.findElement(By.className("fc-event-container"));
            return !itemHoliday.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void inputValueToDescription() {
        edtDescription.sendKeys("Description for holiday");
    }

    public final Boolean isButtonSaveEnable() {
        waitForElement(driver, btnSave);
        return btnSave.isDisplayed();
    }
}
