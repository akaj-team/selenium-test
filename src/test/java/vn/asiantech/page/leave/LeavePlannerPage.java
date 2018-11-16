package vn.asiantech.page.leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.Constant.LEAVE_PLANNER_PAGE_URL;

/**
 * @author at-hangtran
 */
public class LeavePlannerPage extends BasePage<LeavePlannerPage> {

    private static final int USER_NAME = 1;
    private static final int PROFILE_LINK = 2;
    private static final int AVATAR = 3;
    private static final int COLUMN_NUMBER = 7;

    @FindBy(id = "btn-this-week")
    private WebElement btnThisWeek;

    @FindBy(id = "btn-prev-week")
    private WebElement btnBack;

    @FindBy(id = "btn-next-week")
    private WebElement btnNext;

    @FindBy(css = ".calendar-grid")
    private WebElement calendar;

    @FindBy(css = ".calendar-body")
    private WebElement calendarBody;

    @FindBy(css = ".calendar-head")
    private WebElement calenderHead;

    @FindBy(css = ".directional-toolbox")
    private WebElement toolBox;

    private WebDriver driver;

    public LeavePlannerPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final LeavePlannerPage navigateTo(final WebDriver webDriver) {
        webDriver.get(LEAVE_PLANNER_PAGE_URL);
        return this;
    }

    public final boolean getClickable() {
        waitForElement(driver, btnThisWeek);
        return btnThisWeek.isEnabled();
    }

    public final void clickBackButton() {
        waitForElement(driver, btnThisWeek);
        btnBack.click();
    }

    public final void clickNextButton() {
        waitForElement(driver, btnThisWeek);
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

    public final void moveToAvatar() {
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

    public final Boolean isShowLeaveMessage() {
        return driver.getPageSource().contains("Hover message");
    }

    public final Boolean isDisplayFullColumns() {
        boolean isFull = true;
        waitForElement(driver, calendar);
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

    public final String getTableTime() {
        return getColumnTime(0) + " - " + getColumnTime(COLUMN_NUMBER - 1);
    }

    private String getColumnTime(final Integer column) {
        waitForElement(driver, calenderHead);
        return calenderHead.findElements(By.cssSelector(".calendar-cell.ng-star-inserted")).get(column).findElement(By.tagName("i")).getText();
    }

    private WebElement getItemInfo(final Integer column, final Integer position) {
        waitForElement(driver, calendarBody);
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
        if (isElementExisted(getItemInfo(column, position), USER_NAME)) {
            return getItemInfo(column, position).findElement(By.tagName("a")).findElement(By.tagName("h4"));
        }
        return null;
    }

    private String getUserProfileLink(final Integer column, final Integer position) {
        if (isElementExisted(getItemInfo(column, position), PROFILE_LINK)) {
            return getItemInfo(column, position).findElement(By.tagName("a")).getAttribute("href");
        }
        return "";
    }

    private WebElement getAvatar(final Integer column, final Integer position) {
        if (isElementExisted(getItemInfo(column, position), AVATAR)) {
            return getItemInfo(column, position).findElement(By.tagName("a")).findElement(By.tagName("img"));
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
