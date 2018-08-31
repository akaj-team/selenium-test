package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class NotificationMenuElement extends BasePage<NotificationMenuElement> {

    @FindBy(css = ".dropdown-menu.dropdown-alerts.size-lg.arrow-block.t-r")
    private WebElement notificationMenu;

    @FindBy(css = ".fa.fa-bell")
    private WebElement notificationMenuIcon;

    @FindBy(css = "div.text-center.link-block")
    private WebElement seeAllArea;

    @FindBy(css = "span.pull-right.link-primary.text-normal.m-l-sm")
    private WebElement reloadText;

    private WebElement notificationList;

    private String destinationPath;

    public void seeAll() {
        WebElement seeAllTag = seeAllArea.findElement(By.tagName("a"));
        setDestinationUrl(seeAllTag.getAttribute("href"));
        seeAllArea.click();
    }

    @Override
    public NotificationMenuElement navigateTo(WebDriver webDriver) {
        return this;
    }

    public void openNotification() {
        notificationMenuIcon.click();
    }

    public Boolean notificationElementIsDisplay() {
        return notificationMenu.isDisplayed();
    }

    public void notificationMenuItemClick() {
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        WebElement firstElement = notificationList.findElements(By.tagName("li")).get(0);
        setDestinationUrl(firstElement.findElement(By.cssSelector("a.msg-item")).getAttribute("href"));
        firstElement.click();
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void reload() {
        reloadText.click();
    }

    public void waitForNotificationReload(WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, 5);
    }

    public int getNotificationList(WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, 5);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        return notificationList.findElements(By.cssSelector("li.ng-star-inserted")).size();
    }

    public void scrollToEndOfList(WebDriver driver) {
        waitForElementDisplay(driver, notificationMenu, 5);
        notificationList = notificationMenu.findElements(By.tagName("li")).get(1).findElement(By.tagName("ul"));
        By findCondition = By.cssSelector("li.ng-star-inserted");
        int count = notificationList.findElements(findCondition).size();
        int allChildCount = notificationList.findElements(findCondition).size();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                notificationList.findElements(findCondition).get(count - 1));
        waitUntilCountChanges(driver, notificationList, findCondition, allChildCount);
    }

    private void setDestinationUrl(String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
