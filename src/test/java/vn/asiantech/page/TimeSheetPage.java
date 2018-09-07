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
    private WebElement sideMenu;

    @FindBy(css = ".toolbox-item.dropdown-md")
    private WebElement menuStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement itemMenuStatus;

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement data;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement leaveBalance;

    @FindBy(css = ".directional-toolbox")
    private WebElement toolbox;

    @FindBy(css = ".timesheet-grid")
    private WebElement calendar;

    @FindBy(css = ".btn.btn-sm.btn-white")
    private WebElement btnThisWeek;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private WebElement btnSubmit;

    @FindBy(css = ".btn.control-item.prev")
    private WebElement btnBack;

    @FindBy(css = ".btn.control-item.next")
    private WebElement btnNext;

    @FindBy(css = ".timesheet-body")
    private WebElement calendarBody;

    @FindBy(css = ".task-record.create-task.panel-left.ng-star-inserted")
    private WebElement btnAddTimeSheet;

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


    public void moveToAvatar(WebDriver driver) {
        Actions action = new Actions(driver);
        waitForElement(driver, btnSubmit, 10);
        List<WebElement> avatar = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        for (WebElement anAvatar : avatar) {
            action.moveToElement(anAvatar).build().perform();
        }
    }


    private WebElement getItemInfor(Integer column) {
        List<WebElement> columns = calendarBody.findElements(By.cssSelector(".timesheet-cell.ng-star-inserted"));
        if (columns.size() != 0 && column < columns.size()) {
            List<WebElement> columnsItems = columns.get(column).findElements(By.cssSelector(".cell-content>div"));
            System.out.println(columnsItems.size() + "xxx" + columnsItems.get(0));
            return columnsItems.get(column).findElement(By.cssSelector(".cell-info"));
        }
        return columns.get(0);
    }

    public boolean checkTextStatusMenu(String status) {
        return toolbox.findElement(By.className("content")).getText().equals(status);
    }

    public Boolean isDisplayFullColumns(WebDriver driver) {
        boolean isFull = true;
        waitForElement(driver, calendar, 10);
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
        waitForElement(driver, btnAddTimeSheet, 10);
        return btnAddTimeSheet.isEnabled();
    }

    public Boolean getClickable(WebDriver driver, String view) {
        if (view.equals(Constant.VIEW_BTN_THIS_WEEK)) {
            waitForElement(driver, btnThisWeek, 10);
            return btnSubmit.isEnabled();
        }
        waitForElement(driver, btnSubmit, 10);
        return btnSubmit.isEnabled();
    }

    public void clickBackButton(WebDriver driver) {
        waitForElement(driver, btnBack, 10);
        btnBack.click();
    }

    public void clickNextButton(WebDriver driver) {
        waitForElement(driver, btnNext, 10);
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

}
