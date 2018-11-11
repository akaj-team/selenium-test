package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-phuongdang
 */
public class TimeSheetPage extends BasePage<TimeSheetPage> {

    private static final int DEFAULT_COLUMNS_TIME_SHEET = 7;
    private static final int POSITION_TASK = 3;
    private static final int POSITION_FIRST_ITEM = 1;
    private static final int POSITION_CANCEL_CONFIRM = 1;
    private static final int POSITION_DELETE_CONFIRM = 0;
    private static final int TIME_OUT_WAITED_ELEMENT_DISPLAY = 1000;
    private static final int HEIGHT_ITEM_RESIZE = -100;

    @FindBy(css = ".directional-toolbox")
    private
    WebElement elementToolbox;

    @FindBy(css = ".timesheet-grid")
    private
    WebElement elementCalendar;

    @FindBy(id = "btn-this-week")
    private
    WebElement btnThisWeek;

    @FindBy(id = "btn-submit-timesheet")
    private
    WebElement btnSubmit;

    @FindBy(id = "btn-prev-week")
    private
    WebElement btnBack;

    @FindBy(id = "btn-next-week")
    private
    WebElement btnNext;

    @FindBy(className = "timesheet-body")
    private
    WebElement elementCalendarBody;

    @FindBy(className = "task-record")
    private
    WebElement btnAddTimeSheet;

    @FindBy(id = "task-panel-wrapper")
    private
    WebElement dlgTimeSheet;

    @FindBy(className = "input-time")
    private
    WebElement inputTime;

    @FindBy(css = ".text-muted.ng-star-inserted")
    private
    WebElement btnRepeat;

    @FindBy(id = "btn-save-task")
    private
    WebElement btnSave;

    @FindBy(xpath = "//*[@class ='task-content']/div[2]/div/div[1]/p-dropdown/div[1]")
    private
    WebElement elementTitleTask;

    @FindBy(xpath = "//*[@class ='task-content']/div[2]/div/div[1]/p-dropdown/div/div[4]")
    private
    WebElement dlgTask;

    @FindBy(id = "btn-repeat-task")
    private
    WebElement btnRespeatEnable;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private
    WebElement alertStar;

    @FindBy(css = ".timesheet-body")
    private
    WebElement elementTimeSheetBody;

    @FindBy(id = "btn-delete-task")
    private
    WebElement btnDelete;

    @FindBy(id = "confirm-delete-task-panel-wrapper")
    private
    WebElement dlgConfirmDelete;

    @FindBy(className = "box-note")
    private
    WebElement inputDescription;

    @FindBy(className = "ui-dropdown-filter")
    private
    WebElement inputSearch;

    @Override
    public final TimeSheetPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void moveRowTimeSheet(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
    }

    public final Boolean isDisplayTitle(final WebDriver driver) {
        waitForElement(driver, elementToolbox);
        return elementToolbox.findElement(By.className("content")).isDisplayed();
    }

    public final Boolean isDisplayFullColumns(final WebDriver driver) {
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

    public final Boolean getAddTimeSheetClickable(final WebDriver driver) {
        waitForElement(driver, btnAddTimeSheet);
        return btnAddTimeSheet.isEnabled();
    }

    public final Boolean getClickableTimeSheet(final WebDriver driver, final String view) {
        if (view.equals(btnThisWeek)) {
            waitForElement(driver, btnThisWeek);
            return btnSubmit.isEnabled();
        }
        waitForElement(driver, btnSubmit);
        return btnSubmit.isEnabled();
    }

    public final void clickBackButtonOnTimeSheet(final WebDriver driver) {
        waitForElement(driver, btnBack);
        btnBack.click();
    }

    public final void clickNextButtonOnTimeSheet(final WebDriver driver) {
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

    public final void clickAddTimeSheets(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = elementCalendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement listItem : listItems) {
            listItem.click();
        }
    }

    public final void clickFirstItemAddTimeSheet(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, elementCalendarBody);
        List<WebElement> elementCalendar = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = elementCalendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        listItems.get(0).click();
    }

    public final Boolean isTimeSheetShowing(final WebDriver driver) {
        waitForElement(driver, dlgTimeSheet);
        return dlgTimeSheet.isDisplayed();
    }

    public final String getTitleItemProjectShowing(final WebDriver driver) {
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

    public final String getTitleTaskShowing(final WebDriver driver) {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> taskList = dlgTimeSheet.findElements(By.cssSelector("div.task-content"));
        List<WebElement> taskElement = taskList.get(0).findElements(By.cssSelector("div.row"));
        return taskElement.get(POSITION_FIRST_ITEM).findElement(By.tagName("span")).getText();
    }

    public final String getDefaultSelectTaskShowing(final WebDriver driver) {
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

    public final Boolean checkButtonRepeatClickable(final WebDriver driver) {
        waitForElement(driver, btnRepeat);
        return btnRepeat.isEnabled();
    }

    public final Boolean checkButtonSaveClickable(final WebDriver driver) {
        waitForElement(driver, btnSave);
        return btnSave.isEnabled();
    }


    public final void selectedFirstValueProject(final WebDriver driver) {
        waitForElement(driver, dlgTimeSheet);
        List<WebElement> itemDropDowns = dlgTimeSheet.findElements(By.tagName("p-dropdown"));
        itemDropDowns.get(0).click();
    }

    public final void selectItemOnDialogProject(final WebDriver driver, final String content) {
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

    public final void selectedFirstValueTask(final WebDriver driver) {
        waitForElement(driver, elementTitleTask);
        elementTitleTask.click();
    }

    public final void selectItemOnDialogTask(final WebDriver driver, final String content) {
        waitForElement(driver, elementTitleTask);
        List<WebElement> items = elementTitleTask.findElements(By.tagName("div"));
        List<WebElement> itemTask = items.get(POSITION_TASK).findElements(By.tagName("div"));
        WebElement element = itemTask.get(POSITION_FIRST_ITEM).findElement(By.tagName("ul"));
        waitForElement(driver, element);
        List<WebElement> listUri = element.findElements(By.tagName("li"));
        for (WebElement item : listUri) {
            if (item.getText().equals(content)) {
                item.click();
                return;
            }
        }
    }

    public final Boolean displayDialogTask(final WebDriver driver) {
        try {
            waitForElement(driver, dlgTask);
            return dlgTask.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public final Boolean checkButtonRepeatEnableClickable(final WebDriver driver) {
        waitForElement(driver, btnRespeatEnable);
        return btnRespeatEnable.isEnabled();
    }

    public final Boolean checkButtonSubmitEnable(final WebDriver driver) {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isDisplayed();
    }

    public final Boolean displayDialogAlert(final WebDriver driver) {
        waitForElement(driver, alertStar);
        return alertStar.isDisplayed();
    }

    public final void clickRepeatEveryDay(final WebDriver driver) {
        waitForElement(driver, btnRespeatEnable);
        btnRespeatEnable.click();
    }

    public final Boolean isExistsElementTimeSheet() {
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

    public final void moveToTitleTimeSheet(final WebDriver driver) {
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

    public final Boolean isDialogTitleProjectShowing(final WebDriver driver) {
        return driver.getPageSource().contains("Hover message");
    }

    public final void clickColumnsTimeSheet(final WebDriver driver) {
        waitForElement(driver, elementTimeSheetBody);
        List<WebElement> items = elementTimeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (int i = 0; i < items.size() - 2; i++) {
            WebElement element = items.get(i).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
            WebElement content = element.findElement(By.className("info-content"));
            if (content != null && content.isDisplayed()) {
                content.click();
                return;
            }
        }
    }

    public final Boolean isButtonDeleteShowing(final WebDriver driver) {
        waitForElement(driver, btnDelete);
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete(final WebDriver driver) {
        waitForElement(driver, btnDelete);
        btnDelete.click();
    }

    public final Boolean displayDialogConfirmDelete(final WebDriver driver) {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.isDisplayed();
    }

    public final String getTitleConfirmDialog(final WebDriver driver) {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.findElement(By.tagName("h4")).getText();
    }

    public final String getMessageConfirmDialog(final WebDriver driver) {
        waitForElement(driver, dlgConfirmDelete);
        return dlgConfirmDelete.findElement(By.cssSelector(".m-b")).getText();
    }

    public final Boolean displayDeleteConfirmDialog(final WebDriver driver) {
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

    public final void clickButtonConfirmDelete(final WebDriver driver) {
        clickButtonDelete(driver);
        WebElement btnDeleteConfirm = getElementControlDelete(POSITION_DELETE_CONFIRM);
        waitForElement(driver, btnDeleteConfirm);
        btnDeleteConfirm.click();
    }

    public final Boolean isItemTimeSheetDelete(final WebDriver driver) {
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

    public final Boolean displaySearchResult(final WebDriver driver, final String content) {
        waitForElement(driver, dlgTask);
        if (dlgTask != null && dlgTask.isDisplayed()) {
            WebElement resultItem = dlgTask.findElement(By.className("ui-dropdown-items-wrapper"));
            WebElement element = resultItem.findElement(By.tagName("ul"));
            List<WebElement> searchResults = element.findElements(By.tagName("li"));
            for (WebElement result : searchResults) {
                if (result.getText().equals(content)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void scrollChangeItemTimeSheet(final WebDriver driver) {
        scrollToBottomPage(driver);
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(driver, elementCalendarBody);
        Actions actionResize = new Actions(driver);
        List<WebElement> items = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement content = items.get(0).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
        WebElement resize = content.findElement(By.className("resize-handle-icon"));
        actionResize.moveToElement(resize).clickAndHold().moveByOffset(0, HEIGHT_ITEM_RESIZE).release().perform();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollToBottomPage(final WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        actions.keyDown(Keys.CONTROL).release().perform();
    }


    public final boolean isTimeSheetChange(final WebDriver driver, final String content) {
        waitForElement(driver, elementCalendarBody);
        List<WebElement> items = elementCalendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement item = items.get(0).findElement(By.className("task-record"));
        WebElement timeInfo = item.findElement(By.className("item-date"));
        return timeInfo.getText().equals(content);
    }

    private WebElement getElementControlDelete(final int position) {
        List<WebElement> itemsControl = dlgConfirmDelete.findElements(By.tagName("button"));
        return itemsControl.get(position);
    }

    public final void fillInformationForTimeSheet(final WebDriver driver) {
        waitForElement(driver, dlgTimeSheet);
        selectedFirstValueProject(driver);
        selectItemOnDialogProject(driver, "Non-Project");
        selectedFirstValueTask(driver);
        selectItemOnDialogTask(driver, "Coding");
    }

    public final void clickButtonSave(final WebDriver driver) {
        waitForElement(driver, dlgTimeSheet);
        WebElement btnSaveConfirm = dlgTimeSheet.findElement(By.id("btn-save-task"));
        btnSaveConfirm.click();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickFirstItemTimeSheet(final WebDriver driver) {
        waitForElement(driver, elementCalendarBody);
        List<WebElement> listItems = elementCalendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        listItems.get(0).click();
        try {
            Thread.sleep(TIME_OUT_WAITED_ELEMENT_DISPLAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickButtonSubmit(final WebDriver driver) {
        waitForElement(driver, btnSubmit);
        btnSubmit.click();
    }
}
