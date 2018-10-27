package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HolidaySettingPage extends BasePage<HolidaySettingPage> {

    private static final int TIME_OUT_WAITED_ELEMENT = 5;
    private static final int COLUMNS_HEADER_COUNT = 7;
    private static final int MENU_NAVIGATION_ITEM = 7;
    private static final int CONFIRM_DIALOG_BUTTON_CANCEL = 0;
    private static final int CONFIRM_DIALOG_BUTTON_DELETE = 1;

    @FindBy(id = "side-menu")
    private
    WebElement menuNavigationMain;

    @FindBy(css = ".fc-center")
    private
    WebElement tvTitleCenter;

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

    @FindBy(id = "holiday-setting-dialog-wrapper")
    private
    WebElement dlgCalendar;

    @FindBy(className = "holiday-dialog")
    private
    WebElement calendarDialogItem;

    @FindBy(id = "btn-submit-holiday")
    private
    WebElement btnSave;

    @FindBy(className = "form-control")
    private
    WebElement edtName;

    @FindBy(className = "ui-inputtext")
    private
    WebElement edtDescription;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private
    WebElement tvMessageConfirm;

    @FindBy(id = "btn-delete-holiday")
    private
    WebElement btnDelete;

    @FindBy(id = "confirm-delete-panel-wrapper")
    private
    WebElement dlgDeleteConfirm;

    @Override
    public HolidaySettingPage navigateTo(final WebDriver webDriver) {
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

    public final String checkTitleContent(final WebDriver driver) {
        waitForElement(driver, tvTitleCenter, TIME_OUT_WAITED_ELEMENT);
        WebElement itemTitle = tvTitleCenter.findElement(By.tagName("h2"));
        return itemTitle.getText();
    }

    public final boolean checkDeviceMenuDropDown() {
        WebElement itemDevice = getItemMenuInPosition();
        return itemDevice.findElement(By.tagName("ul")).getRect().height == 0;
    }

    private WebElement getItemMenuInPosition() {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = menuNavigationMain.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(MENU_NAVIGATION_ITEM);
    }

    public final Boolean isDisplayCalendarContent(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        return calendarContent.isEnabled();
    }

    public final Boolean isDisplayButtonControl() {
        return btnPrevious.isEnabled() && btnNext.isEnabled();
    }

    public final Boolean isButtonTodayDisable(final WebDriver driver) {
        waitForElement(driver, btnToday, TIME_OUT_WAITED_ELEMENT);
        return !btnToday.isEnabled();
    }

    public final void clickOnButtonNext(final WebDriver driver) {
        waitForElement(driver, btnNext, TIME_OUT_WAITED_ELEMENT);
        btnNext.click();
    }

    public final Boolean onButtonTodayClickable(final WebDriver driver) {
        waitForElement(driver, btnToday, TIME_OUT_WAITED_ELEMENT);
        return btnToday.isEnabled();
    }

    public final void clickOnButtonPrevious(final WebDriver driver) {
        waitForElement(driver, btnPrevious, TIME_OUT_WAITED_ELEMENT);
        btnPrevious.click();
    }

    public final Boolean displayFullTitleCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        WebElement itemHeader = calendarContent.findElement(By.cssSelector(".fc-row.ui-widget-header"));
        List<WebElement> headers = itemHeader.findElements(By.tagName("th"));
        for (int i = 1; i < headers.size(); i++) {
            if (i == COLUMNS_HEADER_COUNT && headers.get(i).isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public final Boolean displayFullItemCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
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

    public final void clickItemHolidayCalendar(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = calendarContent.findElements(By.className("fc-content-skeleton"));
        for (WebElement element : items) {
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

    public final Boolean displayHolidayDetailDialog(final WebDriver driver) {
        waitForElement(driver, dlgCalendar, TIME_OUT_WAITED_ELEMENT);
        return dlgCalendar.isDisplayed();
    }

    public final Boolean displayTitleHolidayDialog(final WebDriver driver, final String title) {
        waitForElement(driver, calendarDialogItem, TIME_OUT_WAITED_ELEMENT);
        WebElement titleDialog = calendarDialogItem.findElement(By.className("ui-dialog-titlebar"));
        return titleDialog.getText().equals(title);
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
        waitForElement(driver, dlgCalendar, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        WebElement boxTimeTo = inputDates.get(1).findElement(By.tagName("input"));
        boxTimeTo.click();
    }

    public final Boolean isDisplayCalendarDateTime(final WebDriver driver) {
        waitForElement(driver, dlgCalendar, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        List<WebElement> elements = inputDates.get(1).findElements(By.tagName("div"));
        return elements.get(0).isDisplayed();
    }

    public final void chooseTodayOnDialogCalendar(final WebDriver driver) {
        waitForElement(driver, dlgCalendar, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        WebElement dateItem = inputDates.get(1).findElement(By.tagName("table"));
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

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void inputValueToDescription(final WebDriver driver) {
        waitForElement(driver, edtDescription, TIME_OUT_WAITED_ELEMENT);
        edtDescription.sendKeys("Description for holiday");
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

    public final void clickButtonSave() {
        btnSave.click();
    }

    public final Boolean isMessageConfirmShowing(final WebDriver driver) {
        waitForElement(driver, tvMessageConfirm, TIME_OUT_WAITED_ELEMENT);
        return tvMessageConfirm.isDisplayed();
    }

    public final void clickItemHoliday(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        WebElement itemHoliday = calendarContent.findElement(By.className("fc-event-container"));
        itemHoliday.click();
    }

    public final Boolean isEnableButtonDelete(final WebDriver driver) {
        waitForElement(driver, btnDelete, TIME_OUT_WAITED_ELEMENT);
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete(final WebDriver driver) {
        waitForElement(driver, btnDelete, TIME_OUT_WAITED_ELEMENT);
        btnDelete.click();
    }

    public final Boolean isDisplayDialogDeleteConfirm(final WebDriver driver) {
        waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        return dlgDeleteConfirm.isDisplayed();
    }

    public final void clickButtonConfirmCancel(final WebDriver driver) {
        waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> itemControllers = dlgDeleteConfirm.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
        itemControllers.get(CONFIRM_DIALOG_BUTTON_CANCEL).click();
    }

    public final Boolean isTitleDialogConfirmDisplay(final WebDriver driver, final String content) {
        waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        WebElement titleDialog = dlgDeleteConfirm.findElement(By.tagName("h4"));
        return titleDialog.getText().equals(content);
    }

    public final Boolean isContentDialogConfirmDisplay(final WebDriver driver, final String content) {
        waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        WebElement contentDialog = dlgDeleteConfirm.findElement(By.className("m-b"));
        return contentDialog.getText().equals(content);
    }

    public final Boolean isDismissDialogConfirmDelete(final WebDriver driver) {
        try {
            waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
            return dlgDeleteConfirm.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public final void clickButtonConfirmDelete(final WebDriver driver) {
        clickButtonDelete(driver);
        waitForElement(driver, dlgDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> itemControllers = dlgDeleteConfirm.findElements(By.cssSelector(".btn.btn-default.btn-sm"));
        itemControllers.get(CONFIRM_DIALOG_BUTTON_DELETE).click();
    }

    public final Boolean isHolidayDeleted(final WebDriver driver) {
        waitForElement(driver, calendarContent, TIME_OUT_WAITED_ELEMENT);
        try {
            WebElement itemHoliday = calendarContent.findElement(By.className("fc-event-container"));
            return !itemHoliday.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
