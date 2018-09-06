package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-hangtran
 */
public class LeavePlannerPage extends BasePage<LeavePlannerPage> {

    private static final int USER_NAME = 1;
    private static final int PROFILE_LINK = 2;
    private static final int AVATAR = 3;

    @FindBy(css = ".btn.btn-sm.btn-white")
    private WebElement btnThisWeek;

    @FindBy(css = ".btn.control-item.prev")
    private WebElement btnBack;

    @FindBy(css = ".btn.control-item.next")
    private WebElement btnNext;

    @FindBy(css = ".calendar-body")
    private WebElement calendarBody;

    @FindBy(css = ".calendar-grid")
    private WebElement calendar;

    @FindBy(css = ".calendar-head")
    private WebElement calenderHead;

    @FindBy(css = ".directional-toolbox")
    private WebElement toolBox;

    @Override
    public LeavePlannerPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/planning");
        return this;
    }

    public Boolean getClickable(WebDriver driver) {
        waitForElement(driver, btnThisWeek, 10);
        return btnThisWeek.isEnabled();
    }

    public void clickBackButton(WebDriver driver) {
        waitForElement(driver, btnThisWeek, 10);
        btnBack.click();
    }

    public void clickNextButton(WebDriver driver) {
        waitForElement(driver, btnThisWeek, 10);
        btnNext.click();
    }

    public String clickUserName() {
        for (int column = 0; column < 7; column++) {
            for (int cell = 0; cell < 7; cell++) {
                String link = getUserProfileLink(column, cell);
                WebElement userName = getUserName(column, cell);
                if (!link.equals("") && userName != null) {
                    userName.click();
                    return link;
                }
            }
        }
        return "";
    }

    public void moveToAvatar(WebDriver driver) {
        Actions action = new Actions(driver);
        for (int column = 0; column < 7; column++) {
            for (int cell = 0; cell < 7; cell++) {
                WebElement avatar = getAvatar(column, cell);
                if (avatar != null) {
                    action.moveToElement(avatar).build().perform();
                    break;
                }
            }
        }
    }

    public Boolean isShowLeaveMessage(WebDriver driver) {
        return driver.getPageSource().contains("Hover message");
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

    public String getHeaderTime() {
        return toolBox.findElement(By.tagName("span")).getText();
    }

    public String getTableTime(WebDriver driver) {
        return getColumnTime(driver, 0) + " - " + getColumnTime(driver, 6);
    }

    private String getColumnTime(WebDriver driver, Integer column) {
        waitForElement(driver, calenderHead, 10);
        return calenderHead.findElements(By.cssSelector(".calendar-cell.ng-star-inserted")).get(column).findElement(By.tagName("i")).getText();
    }

    private WebElement getItemInfor(Integer column, Integer position) {
        List<WebElement> columns = calendarBody.findElements(By.cssSelector(".calendar-cell.ng-star-inserted"));
        if (columns.size() != 0 && column < columns.size()) {
            List<WebElement> columnsItems = columns.get(column).findElements(By.cssSelector(".cell-content>div"));
            if (columnsItems.size() != 0 && position < columnsItems.size()) {
                return columnsItems.get(position).findElement(By.cssSelector(".cell-info"));
            }
        }
        return columns.get(0);
    }

    private WebElement getUserName(Integer column, Integer position) {
        if (isElementExisted(getItemInfor(column, position), USER_NAME)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("h4"));
        }
        return null;
    }

    private String getUserProfileLink(Integer column, Integer position) {
        if (isElementExisted(getItemInfor(column, position), PROFILE_LINK)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).getAttribute("href");
        }
        return "";
    }

    private WebElement getAvatar(Integer column, Integer position) {
        if (isElementExisted(getItemInfor(column, position), AVATAR)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("img"));
        }
        return null;
    }

    private boolean isElementExisted(WebElement parentElement, Integer findTye) {
        try {
            switch (findTye) {
                case USER_NAME:
                    parentElement.findElement(By.tagName("a")).findElement(By.tagName("h4"));
                    break;
                case PROFILE_LINK:
                    parentElement.findElement(By.tagName("a")).getAttribute("href");
                    break;
                case AVATAR:
                    parentElement.findElement(By.tagName("a")).findElement(By.tagName("img"));
                    break;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
