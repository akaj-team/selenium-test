package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public static final String URL_HOME_PAGE = "http://portal-stg.asiantech.vn/homepage";
    public static final int TIME_OUT_IN_SECONDS_10 = 10;
    private static final int TIME_OUT_IN_SECONDS_20 = 20;

    @FindBy(className = "welcome-message")
    private WebElement txtWelcome;

    @FindBy(className = "fa-sign-out")
    private WebElement btnLogout;

    @FindBy(className = "wapper")
    private WebElement wapper;

    @FindBy(className = "sidebar-panel")
    private WebElement rightSideBar;

    @FindBy(css = ".text-center.ng-star-inserted")
    private WebElement homeContentNoData;

    @FindBy(className = "notification-container")
    private WebElement homeContentHasData;

    @FindBy(name = "search")
    private WebElement inputSearch;

    @Override
    public final HomePage navigateTo(final WebDriver webDriver) {
        webDriver.get(URL_HOME_PAGE);
        return this;
    }

    public final boolean hasWelcomeMessage() {
        return isElementPresented(txtWelcome);
    }

    public final boolean welcomeTestIsDisplayed() {
        return txtWelcome.isDisplayed();
    }

    public final void waitForWelcomeMessage(final WebDriver driver) {
        waitForElement(driver, txtWelcome, TIME_OUT_IN_SECONDS_10);
    }

    public final void logout() {
        btnLogout.click();
    }

    public final void waitForElementsVisibility(final WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS_20);
        wait.until(ExpectedConditions.visibilityOfAllElements(wapper, rightSideBar));
    }

    public final void onClickTabInIBoxContent(final String tabName) {
        if (getBtnTab(wapper, tabName) != null && isElementPresented(getBtnTab(wapper, tabName))) {
            getBtnTab(wapper, tabName).click();
        }
    }

    public final boolean checkColorTabInIBoxContent(final String color, final String tabName) {
        return getActualColor(getBtnTab(wapper, tabName)).equals(color);
    }

    public final boolean checkColorOtherTabInIBoxContent(final String color, final String position) {
        return checkColorOtherTab(wapper, color, position);
    }

    public final void onClickTabInRightSideBar(final String tabName) {
        if (getBtnTab(rightSideBar, tabName) != null && isElementPresented(getBtnTab(rightSideBar, tabName))) {
            getBtnTab(rightSideBar, tabName).click();
        }
    }

    public final boolean checkColorTabInRightSideBar(final String tabName, final String colorActive) {
        return getActualColor(getBtnTab(rightSideBar, tabName)).equals(colorActive);
    }

    public final boolean checkColorOtherTabInRightSideBar(final String position, final String colorDefault) {
        return checkColorOtherTab(rightSideBar, colorDefault, position);
    }

    public final boolean checkShowHaveOrNoDataInIBoxContent() {
        if (isElementPresented(homeContentHasData) && homeContentHasData.findElements(By.className("social-feed-box")).size() > 0) {
            return true;
        } else {
            return isElementPresented(homeContentNoData) && isElementPresented(homeContentNoData.findElement(By.tagName("h2")));
        }
    }

    public final void sendKeysSearch(final String valueSearch) {
        inputSearch.sendKeys(valueSearch);
    }

    public final boolean checkShowHaveOrNoDataOnRightSideBar() {
        List<WebElement> listEvent = rightSideBar.findElements(By.className("event-block"));
        //noinspection LoopStatementThatDoesntLoop
        for (WebElement event : listEvent) {
            if (event.findElement(By.tagName("h3")).isDisplayed() && event.findElements(By.className("feed-element")).size() > 0) {
                return true;
            } else {
                return event.findElement(By.tagName("h3")).isDisplayed() && isElementPresented(event.findElement(By.className("text-na")));
            }
        }
        return false;
    }

    public final void onClickUserName() {
        List<WebElement> listUserName = wapper.findElements(By.cssSelector("span.name"));
        listUserName.get(0).click();
    }

    public final void onClickAvatarInIBoxContent() {
        List<WebElement> listAvatar = homeContentHasData.findElements(By.cssSelector("img.img-circle.pull-left"));
        listAvatar.get(0).click();
    }

    public final void onClickAvatarInRightSideBar() {
        List<WebElement> listAvatar = rightSideBar.findElements(By.cssSelector("img.img-circle"));
        listAvatar.get(0).click();
    }

    public final void onClickBtnReaction(final int position) {
        List<WebElement> listBtnReaction = rightSideBar.findElements(By.className("event-block"));
        if (listBtnReaction.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).size() > 0) {
            listBtnReaction.get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn")).get(position).click();
        }
    }

    public final boolean isShowEffectForReaction() {
        return rightSideBar.findElement(By.cssSelector("button.btn-reaction.congrafs-btn.active")).isDisplayed();
    }

    public final void scrollIBoxContent(final WebDriver driver, final boolean isDown) {
        List<WebElement> listFeed = homeContentHasData.findElements(By.cssSelector("div.social-feed-box.ng-star-inserted"));
        scrollElementContent(listFeed, driver, isDown);
    }

    public final void scrollRightSideBar(final WebDriver driver, final boolean isDown) {
        List<WebElement> listFeed = rightSideBar.findElements(By.cssSelector("div.feed-element.ng-star-inserted"));
        scrollElementContent(listFeed, driver, isDown);
    }

    private WebElement getBtnTab(final WebElement elementContent, final String position) {
        List<WebElement> listBtnTab = elementContent.findElement(By.className("ui-buttonset-3")).findElements(By.className("ui-button-text-only"));
        return listBtnTab.get(Integer.parseInt(position));
    }

    private boolean checkColorOtherTab(final WebElement elementContent, final String color, final String position) {
        List<WebElement> listBtnTab = elementContent.findElement(By.className("ui-buttonset-3")).findElements(By.className("ui-button-text-only"));
        for (int i = 0; i < listBtnTab.size(); i++) {
            if (i != Integer.parseInt(position)) {
                return getActualColor(listBtnTab.get(i)).equals(color);
            }
        }
        return false;
    }

    private String getActualColor(final WebElement element) {
        String colorCss = element.findElement(By.tagName("span")).getCssValue("color");
        String[] hexValue = new String[0];
        if (colorCss.contains("rgba")) {
            hexValue = colorCss.replace("rgba(", "").replace(")", "").split(",");
        } else if (colorCss.contains("rgb")) {
            hexValue = colorCss.replace("rgb(", "").replace(")", "").split(",");
        }
        return String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
    }

    private void scrollElementContent(final List<WebElement> listElementContent, final WebDriver driver, final boolean isDown) {
        if (listElementContent.size() > 0) {
            if (isDown) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElementContent.get(listElementContent.size() - 1));
            } else {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElementContent.get(0));
            }
        }
    }
}
