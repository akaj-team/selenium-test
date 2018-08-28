package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class NotificationMenuElement extends BasePage<NotificationMenuElement> {

    @FindBy(css = ".dropdown-menu.dropdown-alerts.size-lg.arrow-block.t-r")
    private WebElement notificationMenu;

    @FindBy(className = "dropdown")
    private WebElement notificationMenuIcon;

    @FindBy(css = ".text-center.link-block")
    private WebElement seeAllArea;

    @Override
    public NotificationMenuElement navigateTo(WebDriver webDriver) {
        return this;
    }

    public NotificationMenuElement seeAll() {
        seeAllArea.findElements(By.xpath(".//*")).get(0).click();
        return this;
    }

    public void navigateToHomePage(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn");
    }

    public void openNotification() {
        notificationMenuIcon.click();
    }

    public Boolean notificationElementIsDisplay() {
        return notificationMenu.getRect().width != 0 && notificationMenu.getRect().height != 0;
    }
}
