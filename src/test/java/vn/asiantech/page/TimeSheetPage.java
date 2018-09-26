package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.ArrayList;
import java.util.List;

public class TimeSheetPage extends BasePage<TimeSheetPage> {

    @FindBy(id = "side-menu")
    private
    WebElement sideMenu;

    @FindBy(css = ".directional-toolbox")
    private
    WebElement toolbox;

    @FindBy(css = ".timesheet-grid")
    private
    WebElement calendar;

    @FindBy(css = ".btn.btn-sm.btn-white")
    private
    WebElement btnThisWeek;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private
    WebElement btnSubmit;

    @FindBy(css = ".btn.control-item.prev")
    private
    WebElement btnBack;

    @FindBy(css = ".btn.control-item.next")
    private
    WebElement btnNext;

    @FindBy(css = ".timesheet-body")
    private
    WebElement calendarBody;

    @FindBy(css = ".task-record.create-task.panel-left.ng-star-inserted")
    private
    WebElement btnAddTimeSheet;

    @FindBy(css = ".ng-tns-c1-0")
    private
    WebElement dialogTimeSheet;

    @FindBy(css = ".col-md-12.project-form")
    private
    WebElement dropdownProject;

    @FindBy(css = ".box-note")
    private
    WebElement boxNote;

    @FindBy(css = "div.task-panel.ng-star-inserted")
    private
    WebElement taskContent;

    @FindBy(css = ".box-spent")
    private
    WebElement boxSpent;

    @FindBy(css = ".text-muted.ng-star-inserted")
    private
    WebElement btnRepeat;

    @FindBy(css = ".btn.btn-sm.btn-primary")
    private
    WebElement btnSave;

    @FindBy(xpath = "//*[@class ='task-content']/div[2]/div/div[1]/p-dropdown/div[1]")
    private
    WebElement elementTitleTask;

    @FindBy(xpath = "//*[@class ='task-content']/div[1]/div/div[1]/p-dropdown/div/div[4]")
    private
    WebElement dialogProject;

    @FindBy(xpath = "//*[@class ='task-content']/div[2]/div/div[1]/p-dropdown/div/div[4]")
    private
    WebElement dialogTask;

    @Override
    public TimeSheetPage navigateTo(WebDriver webDriver) {
        return this;
    }


    public void clickItemTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition();
        itemTimeSheet.click();
    }

    public void clickMyTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition();
        WebElement timeSheetItem = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        timeSheetItem.click();
    }


    public void moveRowTimeSheet(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, btnSubmit, 5);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
    }

    public boolean checkTextStatusMenu(String status) {
        return toolbox.findElement(By.className("content")).getText().equals(status);
    }

    public Boolean isDisplayFullColumns(WebDriver driver) {
        boolean isFull = true;
        waitForElement(driver, calendar, 5);
        List<WebElement> divs = calendar.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            int columns = div.findElements(By.tagName("div")).size();
            if (columns != 7) {
                isFull = false;
            }
        }
        return isFull;
    }

    public Boolean getAddTimeSheetClickable(WebDriver driver) {
        waitForElement(driver, btnAddTimeSheet, 5);
        return btnAddTimeSheet.isEnabled();
    }

    public Boolean getClickable(WebDriver driver, String view) {
        if (view.equals(Constant.VIEW_BTN_THIS_WEEK)) {
            waitForElement(driver, btnThisWeek, 5);
            return btnSubmit.isEnabled();
        }
        waitForElement(driver, btnSubmit, 5);
        return btnSubmit.isEnabled();
    }

    public void clickBackButton(WebDriver driver) {
        waitForElement(driver, btnBack, 5);
        btnBack.click();
    }

    public void clickNextButton(WebDriver driver) {
        waitForElement(driver, btnNext, 5);
        btnNext.click();
    }

    public boolean checkLeaveMenuDropDown() {
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

    public Boolean getAddTimeSheetsClickable() {
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement item : listItems) {
            if (item.isEnabled()) return true;
        }
        return false;
    }

    public void clickAddTimeSheets(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, calendarBody, 5);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        for (WebElement listItem : listItems) {
            listItem.click();
        }
    }

    public void clickFirstItemAddTimeSheet(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, calendarBody, 5);
        List<WebElement> elementCalendar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : elementCalendar) {
            action.moveToElement(anAvatar).build().perform();
        }
        List<WebElement> listItems = calendarBody.findElements(By.cssSelector(".task-record.create-task.ng-star-inserted"));
        listItems.get(0).click();
    }

    public Boolean isTimeSheetShowing(WebDriver driver) {
        waitForElement(driver, dialogTimeSheet, 5);
        return dialogTimeSheet.isDisplayed();
    }

    public Boolean isTitleItemProjectShowing(WebDriver driver, String title) {
        waitForElement(driver, dropdownProject, 5);
        WebElement titleProject = dropdownProject.findElement(By.cssSelector(".title"));
        return titleProject.isDisplayed() && titleProject.getText().equals(title);
    }

    public Boolean isDefaultSelectProjectShowing(WebDriver driver, String content) {
        waitForElement(driver, dropdownProject, 5);
        WebElement titleItemProjects = dropdownProject.findElement(By.tagName("select"));
        String tittle = titleItemProjects.getAttribute("aria-label");
        if (!tittle.trim().isEmpty()) {
            return tittle.equals(content);
        }
        return titleItemProjects.getAttribute("value").equals(content);
    }

    public Boolean isTitleTaskShowing(WebDriver driver, String title) {
        waitForElement(driver, taskContent, 5);
        List<WebElement> taskList = taskContent.findElements(By.cssSelector("div.task-content"));
        List<WebElement> taskElement = taskList.get(0).findElements(By.cssSelector("div.row"));
        String text = taskElement.get(1).findElement(By.tagName("span")).getText();

        return text.equals(title);
    }

    public Boolean isDefaultSelectTaskShowing(WebDriver driver, String title) {
        waitForElement(driver, taskContent, 5);
        List<WebElement> taskList = taskContent.findElements(By.cssSelector("div.task-content"));
        List<WebElement> taskElement = taskList.get(0).findElements(By.cssSelector("div.row"));
        String text = taskElement.get(1).findElement(By.tagName("label")).getText();
        return text.equals(title);
    }

    public Boolean isDescriptionShowing(WebDriver driver, String title) {
        waitForElement(driver, boxNote, 5);
        WebElement textArea = boxNote.findElement(By.tagName("textarea"));
        String text = textArea.getAttribute("placeholder");
        return text.equals(title);
    }

    public Boolean isDefaultInputTimeShowing(WebDriver driver, String title) {
        waitForElement(driver, boxSpent, 5);
        WebElement timeInput = boxSpent.findElement(By.tagName("input"));
        return timeInput.getAttribute("value").equals(title);
    }

    public Boolean checkButtonRepeatClickable(WebDriver driver) {
        waitForElement(driver, btnRepeat, 5);
        return btnRepeat.isEnabled();
    }

    public Boolean checkButtonSaveClickable(WebDriver driver) {
        waitForElement(driver, btnSave, 5);
        return btnSave.isEnabled();
    }


    public void selectedFirstValueProject(WebDriver driver) {
        waitForElement(driver, dropdownProject, 5);
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

    public Boolean disableProjectDialog(WebDriver driver) {
        waitForElement(driver, boxNote, 5);
        boxNote.click();
        return dialogProject.isDisplayed();
    }

    public void selectItemOnDialogProject(WebDriver driver, String content) {
        waitForElement(driver, dropdownProject, 5);
        List<WebElement> titleItemProjects = dropdownProject.findElements(By.cssSelector(".ng-tns-c2-2.ng-star-inserted"));
        for (WebElement item : titleItemProjects) {
            if (item.getText().equals(content)) {
                item.click();
            }
        }
    }

    public void selectedFirstValueTask(WebDriver driver) {
        waitForElement(driver, elementTitleTask, 5);
        elementTitleTask.click();
    }

    public void selectItemOnDialogTask(WebDriver driver, String content) {
        waitForElement(driver, elementTitleTask, 5);
        List<WebElement> items = elementTitleTask.findElements(By.tagName("div"));
        List<WebElement> i = items.get(3).findElements(By.tagName("div"));
        WebElement es = i.get(1).findElement(By.tagName("ul"));
        waitForElement(driver, es, 5);
        List<WebElement> listUri = es.findElements(By.tagName("li"));
        for (WebElement e : listUri) {
            if (e.getText().equals(content)) {
                e.click();
                return;
            }
        }
    }

    public Boolean displayDialogTask(WebDriver driver) {
        try {
            waitForElement(driver, dialogTask, 5);
            return dialogTask.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
