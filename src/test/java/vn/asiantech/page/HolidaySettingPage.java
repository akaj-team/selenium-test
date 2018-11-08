package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author at-phuongdang
 */
public class HolidaySettingPage extends BasePage<HolidaySettingPage> {

    private static final int COLUMNS_HEADER_COUNT = 7;
    private static final int MENU_NAVIGATION_ITEM = 7;
    private static final int CONFIRM_DIALOG_BUTTON_CANCEL = 0;
    private static final int CONFIRM_DIALOG_BUTTON_DELETE = 1;
    private static final int END_DATE_POSITION = 1;
    private static final int FIRST_ITEM_SELECTED = 0;

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
    public final HolidaySettingPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void clickItemHolidaySetting() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        itemHolidaySetting.click();
    }

    public final void clickMenuHolidaySetting() {
        WebElement itemHolidaySetting = getItemMenuInPosition();
        WebElement administration = itemHolidaySetting.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        administration.click();
    }

    public final String checkTitleContent() {
        WebElement itemTitle = tvTitleCenter.findElement(By.tagName("h2"));
        return itemTitle.getText();
    }

    public final boolean checkAdministrationMenuDropDown() {
        WebElement itemAdministration = getItemMenuInPosition();
        return itemAdministration.findElement(By.tagName("ul")).getRect().height == 0;
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
        btnNext.click();
    }

    public final Boolean onButtonTodayClickable() {
        return btnToday.isEnabled();
    }

    public final void onClickOnButtonPrevious() {
        btnPrevious.click();
    }

    public final Boolean displayFullTitleCalendar() {
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

    public final void clickItemHolidayCalendar(final WebDriver driver) {
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

    public final Boolean displayHolidayDetailDialog(final WebDriver driver) {
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

    public final String getMessageValidate(final WebDriver driver) {
        waitForElement(driver, calendarDialogItem);
        WebElement titleMessage = calendarDialogItem.findElement(By.className("help-block"));
        return titleMessage.getText();
    }

    public final void inputNameValue(final String value) {
        edtName.clear();
        edtName.sendKeys(value);
    }

    public final void clickBoxHolidayTimeTo() {
        List<WebElement> inputDates = dlgCalendar.findElements(By.cssSelector("p-calendar"));
        WebElement boxTimeTo = inputDates.get(END_DATE_POSITION).findElement(By.tagName("input"));
        boxTimeTo.click();
    }

    public final Boolean isDisplayCalendarDateTime(final WebDriver driver) {
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

    private String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void inputValueToDescription() {
        edtDescription.sendKeys("Description for holiday");
    }

    public final Boolean isButtonSaveEnable(final WebDriver driver) {
        waitForElement(driver, btnSave);
        return btnSave.isDisplayed();
    }

    public final void enterFullInfo(final WebDriver driver) {
        clickItemHolidaySetting();
        clickMenuHolidaySetting();
        clickItemHolidayCalendar(driver);
        inputNameValue("Request tet holiday");
        clickBoxHolidayTimeTo();
        chooseTodayOnDialogCalendar();
        inputValueToDescription();
    }

    public final void clickButtonSave() {
        btnSave.click();
    }

    public final Boolean isMessageConfirmShowing(final WebDriver driver) {
        waitForElement(driver, tvMessageConfirm);
        return tvMessageConfirm.isDisplayed();
    }

    public final void clickItemHoliday() {
        WebElement itemHoliday = calendarContent.findElement(By.className("fc-event-container"));
        itemHoliday.click();
    }

    public final Boolean isEnableButtonDelete() {
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete() {
        btnDelete.click();
    }

    public final Boolean isDisplayDialogDeleteConfirm(final WebDriver driver) {
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

    public final Boolean isDismissDialogConfirmDelete(final WebDriver driver) {
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
}
