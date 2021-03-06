package vn.asiantech.page.notice;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

/**
 * Notification Menu Element class
 *
 * @author at-vinh.huynh
 */
public class NotificationMenuElement extends BasePage<NotificationMenuElement> {

    @FindBy(css = ".dropdown-menu.dropdown-alerts.size-lg.arrow-block.t-r")
    private WebElement notificationMenu;

    @FindBy(css = ".fa.fa-bell")
    private WebElement notificationMenuIcon;

    @FindBy(id = "link-to-homepage")
    private WebElement seeAllButton;

    @FindBy(id = "btn-reload-notification")
    private WebElement reloadText;

    @FindBy(id = "btn-read-all-notification")
    private WebElement markAllAsReadText;

    private WebElement notificationList;

    private String destinationPath;
    private WebDriver driver;

    public NotificationMenuElement(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final NotificationMenuElement navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void openNotification() {
        notificationMenuIcon.click();
    }

    public final void seeAll() {
        setDestinationUrl(seeAllButton.getAttribute("href"));
        seeAllButton.click();
    }

    public final boolean notificationElementIsDisplay() {
        return notificationMenu.isDisplayed();
    }

    public final void notificationMenuItemClick() {
        waitForElementDisplay(driver, notificationMenu, Constant.DEFAULT_TIME_OUT);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        WebElement firstItemNotification = notificationList.findElements(By.tagName("li")).get(0);
        waitForElementDisplay(driver, firstItemNotification, Constant.DEFAULT_TIME_OUT);
        setDestinationUrl(firstItemNotification.findElement(By.cssSelector("a.msg-item")).getAttribute("href"));
        firstItemNotification.click();
    }

    public final String getDestinationPath() {
        return destinationPath;
    }

    public final void reload() {
        reloadText.click();
    }

    public final void waitForNotificationReload() {
        waitForElementDisplay(driver, notificationMenu, Constant.DEFAULT_TIME_OUT / 2);
    }

    public final int getNotificationList() {
        waitForElementDisplay(driver, notificationMenu, Constant.DEFAULT_TIME_OUT);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        return notificationList.findElements(By.cssSelector("li.ng-star-inserted")).size();
    }

    public final void scrollToEndOfList() {
        waitForElementDisplay(driver, notificationMenu, Constant.DEFAULT_TIME_OUT);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        By findCondition = By.cssSelector("li.ng-star-inserted");
        int count = notificationList.findElements(findCondition).size();
        int allChildCount = notificationList.findElements(findCondition).size();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                notificationList.findElements(findCondition).get(count - 1));
        waitUntilCountChanges(driver, notificationList, findCondition, allChildCount);
    }

    public final void markAllAsRead() {
        markAllAsReadText.click();
    }

    private void setDestinationUrl(final String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
