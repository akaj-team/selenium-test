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

    public static final int TIME_OUT_SECOND = 10;
    private static final int USER_NAME = 1;
    private static final int PROFILE_LINK = 2;
    private static final int AVATAR = 3;
    private static final int COLUMN_NUMBER = 7;

    @FindBy(css = ".btn.btn-sm.btn-white")
    private WebElement btnThisWeek;

    @FindBy(css = ".btn.control-item.prev")
    private WebElement btnBack;

    @FindBy(css = ".btn.control-item.next")
    private WebElement btnNext;

    @FindBy(css = ".calendar-grid")
    private WebElement calendar;

    @FindBy(css = ".calendar-body")
    private WebElement calendarBody;

    @FindBy(css = ".calendar-head")
    private WebElement calenderHead;

    @FindBy(css = ".directional-toolbox")
    private WebElement toolBox;

    public LeavePlannerPage() {
    }

    @Override
    public final LeavePlannerPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/planning");
        return this;
    }

    public final Boolean getClickable(final WebDriver driver) {
        waitForElement(driver, btnThisWeek, TIME_OUT_SECOND);
        return btnThisWeek.isEnabled();
    }

    public final void clickBackButton(final WebDriver driver) {
        waitForElement(driver, btnThisWeek, TIME_OUT_SECOND);
        btnBack.click();
    }

    public final void clickNextButton(final WebDriver driver) {
        waitForElement(driver, btnThisWeek, TIME_OUT_SECOND);
        btnNext.click();
    }

    public final String clickUserName() {
        for (int column = 0; column < COLUMN_NUMBER; column++) {
            for (int cell = 0; cell < COLUMN_NUMBER; cell++) {
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

    public final void moveToAvatar(final WebDriver driver) {
        Actions action = new Actions(driver);
        for (int column = 0; column < COLUMN_NUMBER; column++) {
            for (int cell = 0; cell < COLUMN_NUMBER; cell++) {
                WebElement avatar = getAvatar(column, cell);
                if (avatar != null) {
                    action.moveToElement(avatar).build().perform();
                    break;
                }
            }
        }
    }

    public final Boolean isShowLeaveMessage(final WebDriver driver) {
        return driver.getPageSource().contains("Hover message");
    }

    public final Boolean isDisplayFullColumns(final WebDriver driver) {
        boolean isFull = true;
        waitForElement(driver, calendar, TIME_OUT_SECOND);
        List<WebElement> divs = calendar.findElements(By.tagName("div"));
        for (WebElement div : divs) {
            int columns = div.findElements(By.tagName("div")).size();
            if (columns != COLUMN_NUMBER) {
                isFull = false;
            }
        }
        return isFull;
    }

    public final String getHeaderTime() {
        return toolBox.findElement(By.tagName("span")).getText();
    }

    public final String getTableTime(final WebDriver driver) {
        return getColumnTime(driver, 0) + " - " + getColumnTime(driver, COLUMN_NUMBER - 1);
    }

    private String getColumnTime(final WebDriver driver, final Integer column) {
        waitForElement(driver, calenderHead, TIME_OUT_SECOND);
        return calenderHead.findElements(By.cssSelector(".calendar-cell.ng-star-inserted")).get(column).findElement(By.tagName("i")).getText();
    }

    private WebElement getItemInfor(final Integer column, final Integer position) {
        List<WebElement> columns = calendarBody.findElements(By.cssSelector(".calendar-cell.ng-star-inserted"));
        if (columns.size() != 0 && column < columns.size()) {
            List<WebElement> columnsItems = columns.get(column).findElements(By.cssSelector(".cell-content>div"));
            if (columnsItems.size() != 0 && position < columnsItems.size()) {
                return columnsItems.get(position).findElement(By.cssSelector(".cell-info"));
            }
        }
        return columns.get(0);
    }

    private WebElement getUserName(final Integer column, final Integer position) {
        if (isElementExisted(getItemInfor(column, position), USER_NAME)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("h4"));
        }
        return null;
    }

    private String getUserProfileLink(final Integer column, final Integer position) {
        if (isElementExisted(getItemInfor(column, position), PROFILE_LINK)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).getAttribute("href");
        }
        return "";
    }

    private WebElement getAvatar(final Integer column, final Integer position) {
        if (isElementExisted(getItemInfor(column, position), AVATAR)) {
            return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("img"));
        }
        return null;
    }

    private boolean isElementExisted(final WebElement parentElement, final Integer findTye) {
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
                default:
                    break;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
