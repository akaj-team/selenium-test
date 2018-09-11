package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 02/09.
 */
public class HomePage extends BasePage<HomePage> {
    public static final int TIME_OUT_IN_SECONDS_5 = 5;
    public static final int TIME_OUT_IN_SECONDS_10 = 10;
    private static final int TIME_OUT_IN_SECONDS_20 = 20;
    private static final int TIME_SLEEP_MILLISECONDS_3000 = 3000;
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

    public final boolean hasWelcomeMessage() {
        return isElementPresented(welcomeText);
    }

    public final boolean welcomeTestIsDisplayed() {
        return welcomeText.isDisplayed();
    }

    public final void waitForWelcomeMessage(WebDriver driver) {
        waitForElement(driver, welcomeText, 5);
    }

    public final void logout() {
        logoutButton.click();
    }

    public final boolean checkHeaderIsShown() {
        return rightSideBar.isDisplayed() && homeContent.isDisplayed();
    }

    public final void waitForElementsVisibility(final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_20);
        wait.until(ExpectedConditions.visibilityOfAllElements(homeContent, rightSideBar));
    }

    public final void onClickTabItems(final String tabName) {
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = homeContentChild.findElements(By.className("ui-button-text-only"));
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            elementItemBtn = btnItems.get(ItemTabNavigation.AllNews.ordinal());
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            elementItemBtn = btnItems.get(ItemTabNavigation.Announcerment.ordinal());
        } else {
            elementItemBtn = btnItems.get(ItemTabNavigation.Notification.ordinal());
        }
        if (elementItemBtn != null && isElementPresented(elementItemBtn)) {
            elementItemBtn.click();
        }
    }

    public final boolean checkColorForTabItems(final String color, final String tabName) {
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = homeContentChild.findElements(By.className("ui-button-text-only"));
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            elementItemBtn = btnItems.get(ItemTabNavigation.AllNews.ordinal());
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            elementItemBtn = btnItems.get(ItemTabNavigation.Announcerment.ordinal());
        } else {
            elementItemBtn = btnItems.get(ItemTabNavigation.Notification.ordinal());
        }
        return getActualColor(elementItemBtn).equals(color);
    }

    public final boolean checkColorForOtherTabItems(final String color, final String tabName) {
        int position;
        if (tabName.equals(ItemTabNavigation.AllNews.name())) {
            position = ItemTabNavigation.AllNews.ordinal();
        } else if (tabName.equals(ItemTabNavigation.Announcerment.name())) {
            position = ItemTabNavigation.Announcerment.ordinal();
        } else {
            position = ItemTabNavigation.Notification.ordinal();
        }
        homeContentChild = homeContent.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = homeContentChild.findElements(By.className("ui-button-text-only"));
        for (int i = 0; i < btnItems.size(); i++) {
            if (i != position) {
                elementItemBtn = btnItems.get(i);
                return getActualColor(elementItemBtn).equals(color);
            }
        }
        return false;
    }

    public final void onClickTabItemsInRightSideBar(final String tabName) {
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = rightSideBarChild.findElements(By.className("ui-button-text-only"));
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.AllEvents.ordinal());
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.Birthday.ordinal());
        } else {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.Anniversary.ordinal());
        }
        if (elementItemBtn != null && isElementPresented(elementItemBtn)) {
            elementItemBtn.click();
        }
    }

    public final boolean checkColorForTabItemsInRightSideBar(final String tabName, final String colorActive) {
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = rightSideBarChild.findElements(By.className("ui-button-text-only"));
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.AllEvents.ordinal());
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.Birthday.ordinal());
        } else {
            elementItemBtn = btnItems.get(ItemTabOnRightSidebar.Anniversary.ordinal());
        }
        return getActualColor(elementItemBtn).equals(colorActive);
    }

    public final boolean checkColorForOtherTabItemsOnRightSideBar(final String tabName, final String colorDefault) {
        int position;
        if (tabName.equals(ItemTabOnRightSidebar.AllEvents.name())) {
            position = ItemTabOnRightSidebar.AllEvents.ordinal();
        } else if (tabName.equals(ItemTabOnRightSidebar.Birthday.name())) {
            position = ItemTabOnRightSidebar.Birthday.ordinal();
        } else {
            position = ItemTabOnRightSidebar.Anniversary.ordinal();
        }
        rightSideBarChild = rightSideBar.findElement(By.className("ui-buttonset-3"));
        List<WebElement> btnItems = rightSideBarChild.findElements(By.className("ui-button-text-only"));
        for (int i = 0; i < btnItems.size(); i++) {
            if (i != position) {
                return getActualColor(btnItems.get(i)).equals(colorDefault);
            }
        }
        return false;
    }

    public final boolean checkShowHaveOrNoDataInHomeContent() {
        By byFeedBox = By.className("social-feed-box");
        By byMessageNoData = By.tagName("h2");
        if (isPresentAndDisplayed(homeContentHasData) && homeContentHasData.findElements(byFeedBox).size() > 0) {
            return true;
        } else {
            return isPresentAndDisplayed(homeContentNoData) && isPresentAndDisplayed(homeContentNoData.findElement(byMessageNoData));
        }
    }

    public final void withValueSearch(final String valueSearch) {
        inputSearch.sendKeys(valueSearch);
    }

    public final boolean checkShowHaveOrNoDataOnRightSideBar() {
        List<WebElement> elements = rightSideBar.findElements(By.className("event-block"));
        //noinspection LoopStatementThatDoesntLoop
        for (WebElement element : elements) {
            WebElement title = element.findElement(By.tagName("h3"));
            List<WebElement> feeds = element.findElements(By.className("feed-element"));
            WebElement messageNoData = element.findElement(By.className("text-na"));
            if (title.isDisplayed() && feeds.size() > 0) {
                return true;
            } else {
                return title.isDisplayed() && isPresentAndDisplayed(messageNoData);
            }
        }
        return false;
    }

    public final void onClickUserNameOnHomeContent() {
        List<WebElement> elements = homeContent.findElements(By.cssSelector("span.name"));
        elements.get(0).click();
    }

    public final void onClickAvatarOnHomeContent() {
        List<WebElement> elements = homeContentHasData.findElements(By.cssSelector("img.img-circle.pull-left"));
        elements.get(0).click();
    }

    public final void onClickAvatarOnRightSideBar() {
        List<WebElement> elements = rightSideBar.findElements(By.cssSelector("img.img-circle"));
        elements.get(0).click();
    }

    public final void onClickBtnReactionFlowers() {
        By byEventBlock = By.className("event-block");
        List<WebElement> elements = rightSideBar.findElements(byEventBlock);
        if (elements.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).size() > 0) {
            elements.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).get(0).click();
        }
        try {
            Thread.sleep(TIME_SLEEP_MILLISECONDS_3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void onClickBtnReactionCongrats() {
        List<WebElement> elements = rightSideBar.findElements(By.className("event-block"));
        if (elements.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).size() > 0) {
            elements.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).get(1).click();
        }
        try {
            Thread.sleep(TIME_SLEEP_MILLISECONDS_3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void scrollDownHomeContent(final WebDriver driver) {
        By socialFeed = By.cssSelector("div.social-feed-box.ng-star-inserted");
        int count = homeContentHasData.findElements(socialFeed).size();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", homeContentHasData.findElements(socialFeed).get(count - 1));
    }

    public final void scrollUpHomeContent(final WebDriver driver) {
        By socialFeed = By.cssSelector("div.social-feed-box.ng-star-inserted");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", homeContentHasData.findElements(socialFeed).get(0));
    }

    public final void scrollDownRightSideBar(final WebDriver driver) {
        By feed = By.cssSelector("div.feed-element.ng-star-inserted");
        int count = rightSideBar.findElements(feed).size();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", rightSideBar.findElements(feed).get(count - 1));
    }

    public final void scrollUpRightSideBar(final WebDriver driver) {
        By feed = By.cssSelector("div.feed-element.ng-star-inserted");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", rightSideBar.findElements(feed).get(0));
    }

    private String getActualColor(final WebElement element) {
        String colorCss = element.findElement(By.tagName("span")).getCssValue("color");
        String[] hexValue = colorCss.replace("rgb(", "").replace(")", "").split(",");
        return String.format("#%01x%01x%01x",
                Integer.parseInt(hexValue[0].trim()),
                Integer.parseInt(hexValue[1].trim()),
                Integer.parseInt(hexValue[2].trim()));
    }

    private static boolean isPresentAndDisplayed(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * enum for ite tab on navigation on home content.
     */
    private enum ItemTabNavigation {
        AllNews,
        Announcerment,
        Notification
    }

    /**
     * enum for Item tab on right side bar.
     */
    private enum ItemTabOnRightSidebar {
        AllEvents,
        Birthday,
        Anniversary
    }
}
