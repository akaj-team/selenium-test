package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;

public class HomePage extends BasePage<HomePage> {
    @FindBy(className = "welcome-message")
    private WebElement welcomeText;
    @FindBy(className = "fa-sign-out")
    private WebElement logoutButton;
    @FindBy(className = "wapper")
    private WebElement homeContent;
    @FindBy(className = "sidebar-panel")
    private WebElement rightSideBar;
    private WebElement homeContentChild;
    private WebElement rightSideBarChild;
    private WebElement elementItemBtn;
    @FindBy(css = ".text-center.ng-star-inserted")
    private WebElement homeContentNoData;
    @FindBy(className = "notification-container")
    private WebElement homeContentHasData;
    @FindBy(name = "search")
    private WebElement inputSearch;

    @Override
    public HomePage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/homepage");
        return this;
    }

    public boolean hasWelcomeMessage() {
        return isElementPresented(welcomeText);
    }

    public boolean welcomeTestIsDisplayed() {
        return welcomeText.isDisplayed();
    }

    public boolean hasLogoutButton() {
        return isElementPresented(logoutButton);
    }

    public void waitForWelcomeMessage(WebDriver driver) {
        waitForElement(driver, welcomeText, 5);
    }

    public void logout() {
        logoutButton.click();
    }

    public boolean checkHeaderIsShown() {
        return rightSideBar.isDisplayed() && homeContent.isDisplayed();
    }

    public void waitForElementsVisibility(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(homeContent, rightSideBar));
    }

    public void onClickTabItems(String tabName) {
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.AllNews.ordinal());
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.Announcerment.ordinal());
        } else {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.Notification.ordinal());
        }
        if (elementItemBtn != null && isElementPresented(elementItemBtn)) {
            elementItemBtn.click();
        }
    }

    public boolean checkColorForTabItems(String color, String tabName) {
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.AllNews.ordinal());
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.Announcerment.ordinal());
        } else {
            elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(ItemTabNavigation.Notification.ordinal());
        }
        return getActualColor(elementItemBtn).equals(color);
    }

    public boolean checkColorForOtherTabItems(String color, String tabName) {
        int position;
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            position = ItemTabNavigation.AllNews.ordinal();
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            position = ItemTabNavigation.Announcerment.ordinal();
        } else {
            position = ItemTabNavigation.Notification.ordinal();
        }
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        for (int i = 0; i < homeContentChild.findElements(By.className("ui-button-text-only")).size(); i++) {
            if (i != position) {
                elementItemBtn = homeContentChild.findElements(By.className("ui-button-text-only")).get(i);
                return getActualColor(elementItemBtn).equals(color);
            }
        }
        return false;
    }

    public void onClickTabItemsInRightSideBar(String tabName) {
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.AllEvents.ordinal());
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.Birthday.ordinal());
        } else {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.Anniversary.ordinal());
        }
        if (elementItemBtn != null && isElementPresented(elementItemBtn)) {
            elementItemBtn.click();
        }
    }

    public boolean checkColorForTabItemsInRightSideBar(String tabName, String colorActive) {
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.AllEvents.ordinal());
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.Birthday.ordinal());
        } else {
            elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(ItemTabOnRightSidebar.Anniversary.ordinal());
        }
        return getActualColor(elementItemBtn).equals(colorActive);
    }

    public boolean checkColorForOtherTabItemsOnRightSideBar(String tabName, String colorDefault) {
        int position;
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            position = ItemTabOnRightSidebar.AllEvents.ordinal();
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            position = ItemTabOnRightSidebar.Birthday.ordinal();
        } else {
            position = ItemTabOnRightSidebar.Anniversary.ordinal();
        }
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        for (int i = 0; i < rightSideBarChild.findElements(By.className("ui-button-text-only")).size(); i++) {
            if (i != position) {
                elementItemBtn = rightSideBarChild.findElements(By.className("ui-button-text-only")).get(i);
                return getActualColor(elementItemBtn).equals(colorDefault);
            }
        }
        return false;
    }

    public boolean checkShowHaveOrNoDataInHomeContent() {
        if (isPresentAndDisplayed(homeContentHasData) && homeContentHasData.findElements(By.className("social-feed-box")).size() > 0) {
            return true;
        } else {
            return isPresentAndDisplayed(homeContentNoData) && isPresentAndDisplayed(homeContentNoData.findElement(By.tagName("h2")));
        }
    }

    public void withValueSearch(String valueSearch) {
        inputSearch.sendKeys(valueSearch);
    }

    public boolean checkShowHaveOrNoDataOnRightSideBar() {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) rightSideBar.findElements(By.className("event-block"));
        //noinspection LoopStatementThatDoesntLoop
        for (WebElement element : elements) {
            if (element.findElement(By.tagName("h3")).isDisplayed() && element.findElements(By.className("feed-element")).size() > 0) {
                return true;
            } else
                return element.findElement(By.tagName("h3")).isDisplayed() && isPresentAndDisplayed(element.findElement(By.className("text-na")));
        }
        return false;
    }

    public void onClickUserNameOnHomeContent() {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) homeContent.findElements(By.cssSelector("span.name"));
        elements.get(0).click();
    }

    public void onClickAvatarOnHomeContent() {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) homeContentHasData.findElements(By.cssSelector("img.img-circle.pull-left"));
        elements.get(0).click();
    }

    public void onClickAvatarOnRightSideBar() {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) rightSideBar.findElements(By.cssSelector("img.img-circle"));
        elements.get(0).click();
    }

    private String getActualColor(WebElement element) {
        String colorCss = element.findElement(By.tagName("span")).getCssValue("color");
        String[] hexValue = colorCss.replace("rgb(", "").replace(")", "").split(",");
        return String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
    }

    private static boolean isPresentAndDisplayed(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public enum ItemTabNavigation {
        AllNews,
        Announcerment,
        Notification
    }

    public enum ItemTabOnRightSidebar {
        AllEvents,
        Birthday,
        Anniversary
    }
}
