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

    public Boolean getClickable() {
        return btnThisWeek.isEnabled();
    }

    public void clickBackButton() {
        btnBack.click();
    }

    public void clickNextButton() {
        btnNext.click();
    }

    public String clickUserName() {
        String link = "";
        for (int column = 0; column < 7; column++) {
            for (int cell = 0; cell < 7; cell++) {
                link = getUserProfileLink(column, cell);
                if (!link.equals("")) {
                    getUserName(column, cell).click();
                    return link;
                }
            }
        }
        return getUserProfileLink(1, 1);
    }

    public void moveToAvatar(WebDriver driver) {
        WebElement avatar = null;
        Actions action = new Actions(driver);
        for (int column = 0; column < 7; column++) {
            for (int cell = 0; cell < 7; cell++) {
                avatar = getAvatar(column, cell);
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

    public Boolean isDisplayFullColumns() {
        boolean isFull = true;
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

    public String getTableTime() {
        return getColumnTime(0) + " - " + getColumnTime(6);
    }

    private String getColumnTime(Integer column) {
        return calenderHead.findElements(By.cssSelector(".calendar-cell.ng-star-inserted")).get(column).findElement(By.tagName("i")).getText();
    }

    private WebElement getItemInfor(Integer column, Integer position) {
        List<WebElement> columns = calendarBody.findElements(By.cssSelector(".calendar-cell.ng-star-inserted"));
        if (column < columns.size()) {
            List<WebElement> columnsItems = columns.get(column).findElement(By.cssSelector(".cell-content")).findElements(By.xpath("//div[contains(@class, 'cell-item')]"));
            if (position < columnsItems.size()) {
                return columnsItems.get(position).findElement(By.cssSelector(".cell-info"));
            }
        }
        return calendarBody;
    }

    private WebElement getUserName(Integer column, Integer position) {
        return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("h4"));
    }

    private String getUserProfileLink(Integer column, Integer position) {
        return getItemInfor(column, position).findElement(By.tagName("a")).getAttribute("href");
    }

    private WebElement getAvatar(Integer column, Integer position) {
        return getItemInfor(column, position).findElement(By.tagName("a")).findElement(By.tagName("img"));
    }
}
