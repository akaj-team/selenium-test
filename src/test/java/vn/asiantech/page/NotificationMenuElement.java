package vn.asiantech.page;

import org.openqa.selenium.By;
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
        WebElement firstItem = notificationList.findElements(By.tagName("li")).get(0);
        setDestinationUrl(firstItem.findElement(By.tagName("a")).getAttribute("href"));
        firstItem.click();
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    private void setDestinationUrl(String destinationPath) {
        this.destinationPath = destinationPath;
    }
}
