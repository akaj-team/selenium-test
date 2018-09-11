package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * NotificationMenuElement class
 *
 * @author at-vinh.huynh
 */
public class NotificationMenuElement extends BasePage<NotificationMenuElement> {

    @FindBy(css = ".dropdown-menu.dropdown-alerts.size-lg.arrow-block.t-r")
    private WebElement notificationMenu;

    @FindBy(css = ".fa.fa-bell")
    private WebElement notificationMenuIcon;

    @FindBy(css = "div.text-center.link-block")
    private WebElement seeAllButton;

    @FindBy(css = "span.pull-right.link-primary.text-normal.m-l-sm")
    private WebElement reloadText;

    @FindBy(css = "span.pull-right.link-primary.text-normal")
    private WebElement markAllAsReadText;

    private WebElement notificationList;
    private WebElement firstItemNotification;

    private String destinationPath;

    public final void seeAll() {
        WebElement seeAllTag = seeAllButton.findElement(By.tagName("a"));
        setDestinationUrl(seeAllTag.getAttribute("href"));
        seeAllButton.click();
    }

    @Override
    public final NotificationMenuElement navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void openNotification() {
        notificationMenuIcon.click();
    }

    public final Boolean notificationElementIsDisplay() {
        return notificationMenu.isDisplayed();
    }

    public final void notificationMenuItemClick(final WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, DEFAULT_TIME_OUT);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        firstItemNotification = notificationList.findElements(By.tagName("li")).get(0);
        waitForElementDisplay(driver, firstItemNotification, DEFAULT_TIME_OUT);
        setDestinationUrl(firstItemNotification.findElement(By.cssSelector("a.msg-item")).getAttribute("href"));
        firstItemNotification.click();
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void reload() {
        reloadText.click();
    }

    public final void waitForNotificationReload(final WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, DEFAULT_TIME_OUT / 2);
    }

    public final int getNotificationList(final WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, DEFAULT_TIME_OUT);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        return notificationList.findElements(By.cssSelector("li.ng-star-inserted")).size();
    }

    public final void scrollToEndOfList(final WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, DEFAULT_TIME_OUT);
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
