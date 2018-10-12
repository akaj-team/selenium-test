package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author at-phuongdang
 */
public class TimeSheetPage extends BasePage<TimeSheetPage> {

    private static final int TIME_OUT_WAITED_ELEMENT = 5;
    private static final int DEFAULT_COLUMNS_TIME_SHEET = 7;
    private static final int POSITION_TASK = 3;
    private static final int POSITION_FIRST_ITEM = 1;

    @FindBy(id = "side-menu")
    private
    WebElement sideMenu;

    @FindBy(css = ".directional-toolbox")
    private
    WebElement toolbox;

    @FindBy(css = ".timesheet-grid")
    private
    WebElement calendar;

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

    @FindBy(id = "timesheet-body-wrapper")
    private
    WebElement calendarBody;

    @FindBy(id = "btn-creat-task")
    private
    WebElement btnAddTimeSheet;

    @FindBy(id = "panel-task-wrapper")
    private
    WebElement dialogTimeSheet;

    @FindBy(id = "dropdown-select-project")
    private
    WebElement dropdownProject;

    @FindBy(css = ".box-note")
    private
    WebElement boxNote;

    @FindBy(id = "dropdown-select-task")
    private
    WebElement taskContent;

    @FindBy(id = "input-time")
    private
    WebElement edtTime;

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
    WebElement dialogTask;

    @FindBy(id = "btn-repeat-timesheet")
    private
    WebElement btnRespeatEnable;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private
    WebElement alertStar;

    @FindBy(css = ".timesheet-body")
    private
    WebElement timeSheetBody;

    @FindBy(id = "btn-delete-task")
    private
    WebElement btnDelete;

    @FindBy(id = "btn-cancel-confirm-delete-task")
    private
    WebElement btnCancelConfirm;

    @FindBy(id = "btn-agree-confirm-delete-task")
    private
    WebElement btnDeleteConfirm;

    @FindBy(id = "confirm-delete-task-wrapper")
    private
    WebElement dialogConfirmDelete;

    @FindBy(id = "btn-resize-task-time")
    private
    WebElement btnResizeTask;

    @FindBy(id = "input-description")
    private
    WebElement edtDescription;

    @FindBy(className = "ui-dropdown-filter")
    private
    WebElement edtInputSearch;

    @Override
    public TimeSheetPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void clickItemTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition();
        itemTimeSheet.click();
    }

    public final void clickMyTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition();
        WebElement timeSheetItem = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        timeSheetItem.click();
    }


    public final void moveRowTimeSheet(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
    }

    public final boolean checkTextStatusMenu(final String status) {
        return toolbox.findElement(By.className("content")).getText().equals(status);
    }

    public final Boolean isDisplayFullColumns(final WebDriver driver) {
        boolean isFull = true;
        waitForElement(driver, calendar, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> divs = calendar.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            int columns = div.findElements(By.tagName("div")).size();
            if (columns != DEFAULT_COLUMNS_TIME_SHEET) {
                isFull = false;
            }
        }
        return isFull;
    }

    public final Boolean getAddTimeSheetClickable(final WebDriver driver) {
        waitForElement(driver, btnAddTimeSheet, TIME_OUT_WAITED_ELEMENT);
        return btnAddTimeSheet.isEnabled();
    }

    public final Boolean getClickableTimeSheet(final WebDriver driver, final String view) {
        if (view.equals(btnThisWeek)) {
            waitForElement(driver, btnThisWeek, TIME_OUT_WAITED_ELEMENT);
            return btnSubmit.isEnabled();
        }
        waitForElement(driver, btnSubmit, TIME_OUT_WAITED_ELEMENT);
        return btnSubmit.isEnabled();
    }

    public final void clickBackButtonOnTimeSheet(final WebDriver driver) {
        waitForElement(driver, btnBack, TIME_OUT_WAITED_ELEMENT);
        btnBack.click();
    }

    public final void clickNextButtonOnTimeSheet(final WebDriver driver) {
        waitForElement(driver, btnNext, TIME_OUT_WAITED_ELEMENT);
        btnNext.click();
    }

    public final boolean checkLeaveMenuDropDown() {
        WebElement itemLeave = getItemMenuInPosition();
        return itemLeave.findElement(By.tagName("ul")).getRect().height == 0;
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
        return itemMenus.get(2);
    }

    public final Boolean getAddTimeSheetsClickable() {
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement item : listItems) {
            if (item.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public final void clickAddTimeSheets(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement listItem : listItems) {
            listItem.click();
        }
    }

    public final void clickFirstItemAddTimeSheet(final WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        listItems.get(0).click();
    }

    public final Boolean isTimeSheetShowing(final WebDriver driver) {
        waitForElement(driver, dialogTimeSheet, TIME_OUT_WAITED_ELEMENT);
        return dialogTimeSheet.isDisplayed();
    }

    public final Boolean isTitleItemProjectShowing(final WebDriver driver, final String title) {
        waitForElement(driver, dialogTimeSheet, TIME_OUT_WAITED_ELEMENT);
        WebElement titleProjectContent = dialogTimeSheet.findElement(By.cssSelector(".col-md-12.project-form"));
        WebElement titleProject = titleProjectContent.findElement(By.className("title"));
        return titleProject.isDisplayed() && titleProject.getText().equals(title);
    }

    public final Boolean isDefaultSelectProjectShowing(final WebDriver driver, final String content) {
        waitForElement(driver, dropdownProject, TIME_OUT_WAITED_ELEMENT);
        WebElement titleItemProjects = dropdownProject.findElement(By.tagName("select"));
        String tittle = titleItemProjects.getAttribute("aria-label");
        if (!tittle.trim().isEmpty()) {
            return tittle.equals(content);
        }
        return titleItemProjects.getAttribute("value").equals(content);
    }

    public final Boolean isTitleTaskShowing(final WebDriver driver, final String title) {
        waitForElement(driver, dialogTimeSheet, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> taskList = dialogTimeSheet.findElements(By.cssSelector("div.task-content"));
        List<WebElement> taskElement = taskList.get(0).findElements(By.cssSelector("div.row"));
        String text = taskElement.get(POSITION_FIRST_ITEM).findElement(By.tagName("span")).getText();
        return text.equals(title);
    }

    public final Boolean isDefaultSelectTaskShowing(final WebDriver driver, final String title) {
        waitForElement(driver, taskContent, TIME_OUT_WAITED_ELEMENT);
        String text = taskContent.findElement(By.tagName("label")).getText();
        return text.equals(title);
    }

    public final Boolean isDescriptionShowing(final WebDriver driver, final String title) {
        waitForElement(driver, edtDescription, TIME_OUT_WAITED_ELEMENT);
        return edtDescription.getAttribute("placeholder").equals(title);
    }

    public final Boolean isDefaultInputTimeShowing(final WebDriver driver, final String title) {
        waitForElement(driver, edtTime, TIME_OUT_WAITED_ELEMENT);
        return edtTime.getAttribute("value").equals(title);
    }

    public final Boolean checkButtonRepeatClickable(final WebDriver driver) {
        waitForElement(driver, btnRepeat, TIME_OUT_WAITED_ELEMENT);
        return btnRepeat.isEnabled();
    }

    public final Boolean checkButtonSaveClickable(final WebDriver driver) {
        waitForElement(driver, btnSave, TIME_OUT_WAITED_ELEMENT);
        return btnSave.isEnabled();
    }


    public final void selectedFirstValueProject(final WebDriver driver) {
        waitForElement(driver, dropdownProject, TIME_OUT_WAITED_ELEMENT);
        dropdownProject.click();
        WebElement item = getItemMenuProjectPosition();
        item.click();
    }

    private WebElement getItemMenuProjectPosition() {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = dropdownProject.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(0);
    }

    public final Boolean disableProjectDialog(final WebDriver driver) {
        waitForElement(driver, boxNote, TIME_OUT_WAITED_ELEMENT);
        boxNote.click();
        return dialogTimeSheet.isDisplayed();
    }

    public final void selectItemOnDialogProject(final WebDriver driver, final String content) {
        waitForElement(driver, dropdownProject, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> titleItemProjects = dropdownProject.findElements(By.cssSelector(".ng-tns-c2-2.ng-star-inserted"));
        for (WebElement item : titleItemProjects) {
            if (item.getText().equals(content)) {
                item.click();
            }
        }
    }

    public final void selectedFirstValueTask(final WebDriver driver) {
        waitForElement(driver, elementTitleTask, TIME_OUT_WAITED_ELEMENT);
        elementTitleTask.click();
    }

    public final void selectItemOnDialogTask(final WebDriver driver, final String content) {
        waitForElement(driver, elementTitleTask, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = elementTitleTask.findElements(By.tagName("div"));
        List<WebElement> itemTask = items.get(POSITION_TASK).findElements(By.tagName("div"));
        WebElement element = itemTask.get(POSITION_FIRST_ITEM).findElement(By.tagName("ul"));
        waitForElement(driver, element, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> listUri = element.findElements(By.tagName("li"));
        for (WebElement e : listUri) {
            if (e.getText().equals(content)) {
                e.click();
                return;
            }
        }
    }

    public final Boolean displayDialogTask(final WebDriver driver) {
        try {
            waitForElement(driver, dialogTask, TIME_OUT_WAITED_ELEMENT);
            return dialogTask.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public final Boolean checkButtonRepeatEnableClickable(final WebDriver driver) {
        waitForElement(driver, btnRespeatEnable, TIME_OUT_WAITED_ELEMENT);
        return btnRespeatEnable.isEnabled();
    }

    public final Boolean checkButtonSubmitEnable(final WebDriver driver) {
        waitForElement(driver, btnSubmit, TIME_OUT_WAITED_ELEMENT);
        return btnSubmit.isDisplayed();
    }

    public final Boolean displayDialogAlert(final WebDriver driver) {
        waitForElement(driver, alertStar, TIME_OUT_WAITED_ELEMENT);
        return alertStar.isDisplayed();
    }

    public final void clickRepeatEveryDay(final WebDriver driver) {
        waitForElement(driver, btnRespeatEnable, TIME_OUT_WAITED_ELEMENT);
        btnRespeatEnable.click();
    }

    public final Boolean isExistsElementTimeSheet(final WebDriver driver) {
        waitForElement(driver, timeSheetBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = timeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
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
        waitForElement(driver, timeSheetBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = timeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
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
        waitForElement(driver, timeSheetBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = timeSheetBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (int i = 0; i < items.size() - 2; i++) {
            WebElement element = items.get(i).findElement(By.cssSelector(".task-record.saved.ng-star-inserted"));
            WebElement content = element.findElement(By.cssSelector(".info-content"));
            if (content != null && content.isDisplayed()) {
                content.click();
                return;
            }
        }
    }

    public final Boolean isButtonDeleteShowing(final WebDriver driver) {
        waitForElement(driver, btnDelete, TIME_OUT_WAITED_ELEMENT);
        return btnDelete.isEnabled();
    }

    public final void clickButtonDelete(final WebDriver driver) {
        waitForElement(driver, btnDelete, TIME_OUT_WAITED_ELEMENT);
        if (btnDelete != null && btnDelete.isEnabled()) {
            btnDelete.click();
        }
    }

    public final Boolean displayDialogConfirmDelete(final WebDriver driver) {
        waitForElement(driver, dialogConfirmDelete, TIME_OUT_WAITED_ELEMENT);
        return dialogConfirmDelete.isDisplayed();
    }

    public final Boolean displayTitleConfirmDialog(final WebDriver driver, final String content) {
        waitForElement(driver, dialogConfirmDelete, TIME_OUT_WAITED_ELEMENT);
        WebElement title = dialogConfirmDelete.findElement(By.tagName("h4"));
        return title.isDisplayed() && title.getText().equals(content);
    }

    public final Boolean displayMessageConfirmDialog(final WebDriver driver, final String content) {
        waitForElement(driver, dialogConfirmDelete, TIME_OUT_WAITED_ELEMENT);
        WebElement message = dialogConfirmDelete.findElement(By.cssSelector(".m-b"));
        return message.isDisplayed() && message.getText().equals(content);
    }

    public final Boolean displayDeleteConfirmDialog(final WebDriver driver) {
        waitForElement(driver, dialogConfirmDelete, TIME_OUT_WAITED_ELEMENT);
        WebElement btnDelete = dialogConfirmDelete.findElement(By.cssSelector(".btn.btn-default.btn-sm.btn-delete"));
        return btnDelete.isDisplayed();
    }

    public final Boolean displayCancelConfirmDialog(final WebDriver driver) {
        waitForElement(driver, btnCancelConfirm, TIME_OUT_WAITED_ELEMENT);
        return btnCancelConfirm.isDisplayed();
    }

    public final void clickButtonCancelDialogConfirm(final WebDriver driver) {
        waitForElement(driver, btnCancelConfirm, TIME_OUT_WAITED_ELEMENT);
        if (btnCancelConfirm != null && btnCancelConfirm.isEnabled()) {
            btnCancelConfirm.click();
        }
    }

    public final Boolean dismissDialogConfirmDelete() {
        try {
            return dialogConfirmDelete.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void clickButtonConfirmDelete(final WebDriver driver) {
        clickButtonDelete(driver);
        waitForElement(driver, btnDeleteConfirm, TIME_OUT_WAITED_ELEMENT);
        if (btnDeleteConfirm != null && btnDeleteConfirm.isEnabled()) {
            btnDeleteConfirm.click();
        }
    }

    public final Boolean isItemTimeSheetDelete(final WebDriver driver) {
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        try {
            WebElement element = items.get(0).findElement(By.className("cell-content"));
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void inputSearch(final WebDriver driver, final String content) {
        waitForElement(driver, edtInputSearch, TIME_OUT_WAITED_ELEMENT);
        if (edtInputSearch != null && edtInputSearch.isDisplayed()) {
            edtInputSearch.clear();
            edtInputSearch.sendKeys(content);
        }
    }

    public final Boolean displaySearchResult(final WebDriver driver, final String content) {
        waitForElement(driver, dialogTask, TIME_OUT_WAITED_ELEMENT);
        if (dialogTask != null && dialogTask.isDisplayed()) {
            WebElement resultItem = dialogTask.findElement(By.className("ui-dropdown-items-wrapper"));
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
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        Actions actionResize = new Actions(driver);
        List<WebElement> items = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement content = items.get(0).findElement(By.className("task-record"));
        WebElement resize = content.findElement(By.id("btn-resize-task-time"));
        actionResize.moveToElement(resize).clickAndHold().moveByOffset(0, -100).release().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isTimeSheetChange(final WebDriver driver, final String content) {
        waitForElement(driver, calendarBody, TIME_OUT_WAITED_ELEMENT);
        List<WebElement> items = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        WebElement item = items.get(0).findElement(By.className("task-record"));
        WebElement timeInfo = item.findElement(By.className("item-date"));
        return timeInfo.getText().equals(content);
    }
}
