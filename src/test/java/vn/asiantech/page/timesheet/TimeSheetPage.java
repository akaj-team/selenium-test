package vn.asiantech.page.timesheet;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;

/**
 * @author at-phuongdang
 */
public class TimeSheetPage extends BasePage<TimeSheetPage> {

    private static final int DEFAULT_COLUMNS_TIME_SHEET = 7;
    private static final int POSITION_FIRST_ITEM = 1;
    private static final int POSITION_CANCEL_CONFIRM = 1;
    private static final int POSITION_DELETE_CONFIRM = 0;
    private static final int TIME_OUT_WAITED_ELEMENT_DISPLAY = 1000;
    private static final int HEIGHT_ITEM_RESIZE = -100;

    @FindBy(css = ".directional-toolbox")
    private WebElement elementToolbox;
    @FindBy(css = ".timesheet-grid")
    private WebElement elementCalendar;
    @FindBy(id = "btn-this-week")
    private WebElement btnThisWeek;
    @FindBy(id = "btn-submit-timesheet")
    private WebElement btnSubmit;
    @FindBy(id = "btn-prev-week")
    private WebElement btnBack;
    @FindBy(id = "btn-next-week")
    private WebElement btnNext;
    @FindBy(className = "timesheet-body")
    private WebElement elementCalendarBody;
    @FindBy(className = "task-record")
    private WebElement btnAddTimeSheet;
    @FindBy(id = "task-panel-wrapper")
    private WebElement dlgTimeSheet;
    @FindBy(className = "input-time")
    private WebElement inputTime;
    @FindBy(css = ".text-muted.ng-star-inserted")
    private WebElement btnRepeat;
    @FindBy(id = "btn-save-task")
    private WebElement btnSave;
    @FindBy(className = "ui-dropdown-filter-container")
    private WebElement dlgTask;
    @FindBy(id = "btn-repeat-task")
    private WebElement btnRespeatEnable;
    @FindBy(css = ".app-alert.ng-star-inserted")
    private WebElement alertStar;
    @FindBy(css = ".timesheet-body")
    private WebElement elementTimeSheetBody;
    @FindBy(id = "btn-delete-task")
    private WebElement btnDelete;
    @FindBy(id = "confirm-delete-task-panel-wrapper")
    private WebElement dlgConfirmDelete;
    @FindBy(className = "box-note")
    private WebElement inputDescription;
    @FindBy(className = "ui-dropdown-filter")
    private WebElement inputSearch;
    private WebDriver driver;
    private int positionFirst;

    public TimeSheetPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final TimeSheetPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void moveRowTimeSheet() {
        Actions action = new Actions(driver);
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
    }

    public final Boolean isDisplayTitle() {
        waitForElement(driver, elementToolbox);
        return elementToolbox.findElement(By.className("content")).isDisplayed();
    }

    public final Boolean isDisplayFullColumns() {
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean isFull = true;
        waitForElement(driver, elementCalendar);
        List<WebElement> divs = elementCalendar.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            int columns = div.findElements(By.tagName("div")).size();
            if (columns != DEFAULT_COLUMNS_TIME_SHEET) {
                isFull = false;
            }
        }
        return isFull;
    }

    public final Boolean getAddTimeSheetClickable() {
        waitForElement(driver, btnAddTimeSheet);
        return btnAddTimeSheet.isEnabled();
    }

    public final Boolean getClickableTimeSheet(final String view) {
        if (view.equals(btnThisWeek.toString())) {
            waitForElement(driver, btnThisWeek);
            return btnSubmit.isEnabled();
        }
        waitForElement(driver, btnSubmit);
        return btnSubmit.isEnabled();
    }

    public final void clickBackButtonOnTimeSheet() {
        waitForElement(driver, btnBack);
        btnBack.click();
    }

    public final void clickNextButtonOnTimeSheet() {
        waitForElement(driver, btnNext);
        btnNext.click();
    }

    public final Boolean getAddTimeSheetsClickable() {
        List<WebElement> listItems = elementCalendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement item : listItems) {
            if (item.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public final void clickFirstItemAddTimeSheet() {
        Actions action = new Actions(driver);
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.className("cell-content"));
        for (int i = 0; i < elementCalendar.size(); i++) {
            try {
                if (elementCalendar.get(i).findElement(By.className("info")).isDisplayed()) {
                    action.moveToElement(elementCalendar.get(i)).build().perform();
                }
            } catch (Exception e) {
                positionFirst = i;
                action.moveToElement(elementCalendar.get(positionFirst)).build().perform();
                elementCalendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted")).get(positionFirst).click();
                return;
            }
        }
    }

    public final Boolean isTimeSheetShowing() {
        waitForElementDisplay(driver, dlgTimeSheet, Constant.DEFAULT_TIME_OUT);
        return dlgTimeSheet.isDisplayed();
    }

    public final String getTitleItemProjectShowing() {
        waitForElement(driver, dlgTimeSheet);
        WebElement titleProjectContent = dlgTimeSheet.findElement(By.cssSelector(".col-md-12.project-form"));
        WebElement titleProject = titleProjectContent.findElement(By.className("title"));
        return titleProject.getText();
    }

    public final String getDefaultSelectProjectShowing() {
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        WebElement titleItemProjects = itemDropDowns.get(0).findElement(By.tagName("label"));
        return titleItemProjects.getText();
    }

    public final String getTitleTaskShowing() {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> taskList = dlgTimeSheet.findElements(By.cssSelector("div.task-content"));
        List<WebElement> taskElement = taskList.get(0).findElements(By.cssSelector("div.row"));
        return taskElement.get(POSITION_FIRST_ITEM).findElement(By.tagName("span")).getText();
    }

    public final String getDefaultSelectTaskShowing() {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> itemDropDown = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        return itemDropDown.get(1).findElement(By.tagName("label")).getText();
    }

    public final Boolean isDescriptionShowing(final String title) {
        WebElement itemTextArea = inputDescription.findElement(By.className("form-control "));
        return itemTextArea.getAttribute("placeholder").equals(title);
    }

    public final String getDefaultInputTimeShowing() {
        return inputTime.getAttribute("value");
    }

    public final Boolean checkButtonRepeatClickable() {
        waitForElement(driver, btnRepeat);
        return btnRepeat.isEnabled();
    }

    public final Boolean checkButtonSaveClickable() {
        waitForElement(driver, btnSave);
        return btnSave.isEnabled();
    }


    public final void selectedFirstValueProject() {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        itemDropDowns.get(0).click();
    }

    public final void selectItemOnDialogProject(final String content) {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        WebElement dialogSelectProject = itemDropDowns.get(0).findElement(By.className("ui-dropdown-items-wrapper"));
        if (dialogSelectProject.isDisplayed()) {
            List<WebElement> items = dialogSelectProject.findElements(By.tagName("li"));
            for (WebElement element : items) {
                if (element.getText().equals(content)) {
                    element.click();
                    return;
                }
            }
        }

    }

    public final void selectedFirstValueTask() {
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        WebElement titleItemProjects = itemDropDowns.get(1).findElement(By.tagName("label"));
        waitForElement(driver, titleItemProjects);
        titleItemProjects.click();
    }

    public final void selectItemOnDialogTask(final String content) {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        WebElement dialogSelectProject = itemDropDowns.get(1).findElement(By.className("ui-dropdown-items-wrapper"));
        if (dialogSelectProject.isDisplayed()) {
            List<WebElement> items = dialogSelectProject.findElements(By.tagName("li"));
            for (WebElement element : items) {
                if (element.getText().equals(content)) {
                    element.click();
                    return;
                }
            }
        }
    }

    public final Boolean displayDialogTask() {
        waitForElement(driver, dlgTask);
        return dlgTask.isEnabled();
    }

    public final Boolean checkButtonRepeatEnableClickable() {
        waitForElement(driver, btnRespeatEnable);
        return btnRespeatEnable.isEnabled();
    }

    public final Boolean checkButtonSubmitEnable() {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isDisplayed();
    }

    public final Boolean displayDialogAlert() {
        waitForElement(driver, alertStar);
        return alertStar.isDisplayed();
    }

    public final void clickRepeatEveryDay() {
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(driver, btnRespeatEnable);
        btnRespeatEnable.click();
    }

    public final Boolean isExistsElementTimeSheet() {
        waitForElement(driver, elementTimeSheetBody);
        List<WebElement> items = elementTimeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (int i = 0; i < items.size() - 2; i++) {
            WebElement element = items.get(i).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
            WebElement content = element.findElement(By.cssSelector(".info-content"));
            WebElement titleProject = content.findElement(By.tagName("h4"));
            WebElement taskProject = content.findElement(By.tagName("span"));
            WebElement dateTime = content.findElement(By.cssSelector(".info-hour")).findElement(By.cssSelector(".item-date"));
            if (!content.isEnabled() || !titleProject.isEnabled() || !taskProject.isEnabled() || !dateTime.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    public final void moveToTitleTimeSheet() {
        Actions action = new Actions(driver);
        waitForElement(driver, elementTimeSheetBody);
        List<WebElement> items = elementTimeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (int i = 0; i < items.size() - 2; i++) {
            WebElement element = items.get(i).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
            WebElement content = element.findElement(By.cssSelector(".info-content"));
            WebElement titleProject = content.findElement(By.tagName("h4"));
            if (titleProject != null && titleProject.isDisplayed()) {
                action.moveToElement(titleProject).build().perform();
            }
        }
    }

    public final Boolean isDialogTitleProjectShowing() {
        return driver.getPageSource().contains("Hover message");
    }

    public final void clickColumnsTimeSheet() {
        waitForElementDisplay(driver, elementTimeSheetBody, Constant.MAXIMUM_TIME_OUT);
        List<WebElement> items = elementTimeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement element = items.get(positionFirst).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
        WebElement content = element.findElement(By.className("info-content"));
        content.click();
    }

    public final Boolean isButtonDeleteShowing() {
        waitForElement(driver, btnDelete);
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete() {
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(driver, btnDelete);
        btnDelete.click();
    }

    public final Boolean displayDialogConfirmDelete() {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.isDisplayed();
    }

    public final String getTitleConfirmDialog() {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.findElement(By.tagName("h4")).getText();
    }

    public final String getMessageConfirmDialog() {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.findElement(By.cssSelector(".m-b")).getText();
    }

    public final Boolean displayDeleteConfirmDialog() {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.findElement(By.cssSelector(".btn.btn-default.btn-sm.btn-delete")).isDisplayed();
    }

    public final Boolean displayCancelConfirmDialog() {
        WebElement btnCancelConfirm = getElementControlDelete(POSITION_CANCEL_CONFIRM);
        return btnCancelConfirm.isDisplayed();
    }

    public final void clickButtonCancelDialogConfirm() {
        WebElement btnCancelConfirm = getElementControlDelete(POSITION_CANCEL_CONFIRM);
        btnCancelConfirm.click();
    }

    public final Boolean dismissDialogConfirmDelete() {
        try {
            return dlgConfirmDelete.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void clickButtonConfirmDelete() {
        clickButtonDelete();
        WebElement btnDeleteConfirm = getElementControlDelete(POSITION_DELETE_CONFIRM);
        waitForElement(driver, btnDeleteConfirm);
        btnDeleteConfirm.click();
    }

    public final Boolean isItemTimeSheetDelete() {
        waitForElement(driver, elementCalendarBody);
        List<WebElement> items = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        try {
            WebElement element = items.get(0).findElement(By.className("cell-content"));
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void inputSearch(final String content) {
        inputSearch.clear();
        inputSearch.sendKeys(content);
    }

    public final Boolean displaySearchResult(final String content) {
        List<WebElement> searchItem = driver.findElements(By.className("ui-dropdown-items-wrapper"));
        WebElement element = searchItem.get(1).findElement(By.tagName("ul"));
        List<WebElement> searchResults = element.findElements(By.tagName("li"));
        for (WebElement result : searchResults) {
            if (result.getText().equals(content)) {
                return true;
            }
        }
        return false;
    }

    public final void scrollChangeItemTimeSheet() {
        scrollToBottomPage();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(driver, elementCalendarBody);
        Actions actionResize = new Actions(driver);
        List<WebElement> items = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement content = items.get(positionFirst).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
        WebElement resize = content.findElement(By.className("resize-handle-icon"));
        actionResize.moveToElement(resize).clickAndHold().moveByOffset(1, HEIGHT_ITEM_RESIZE).release().perform();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final Boolean isTimeSheetChange(final String content) {
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.className("cell-content"));
        WebElement item = elementCalendar.get(positionFirst).findElement(By.className("task-record"));
        WebElement timeInfo = item.findElement(By.className("item-date"));
        return timeInfo.getText().equals(content);
    }

    public final void fillInformationForTimeSheet() {
        waitForElement(driver, dlgTimeSheet);
        selectedFirstValueProject();
        selectItemOnDialogProject("Non-Project");
        selectedFirstValueTask();
        selectItemOnDialogTask("Coding");
    }

    public final void clickButtonSave() {
        waitForElement(driver, dlgTimeSheet);
        WebElement btnSaveConfirm = dlgTimeSheet.findElement(By.id("btn-save-task"));
        btnSaveConfirm.click();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickButtonSubmit() {
        waitForElement(driver, btnSubmit);
        btnSubmit.click();
    }

    public final void clearTimeSheetItem() {
        while (true) {
            try {
                WebElement elements = elementTimeSheetBody.findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
                elements.click();
                Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
                btnDelete.click();
                Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
                dlgConfirmDelete.findElement(By.cssSelector(".btn.btn-default.btn-sm.btn-delete")).click();
                Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
            } catch (Exception e) {
                return;
            }
        }
    }

    public final void addTimeSheetFullRecord() {
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
            clickFirstItemAddTimeSheet();
            fillInformationForTimeSheet();
            clickRepeatEveryDay();
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollToBottomPage() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        actions.keyDown(Keys.CONTROL).release().perform();
    }

    private WebElement getElementControlDelete(final int position) {
        List<WebElement> itemsControl = dlgConfirmDelete.findElements(By.tagName("button"));
        return itemsControl.get(position);
    }
}
