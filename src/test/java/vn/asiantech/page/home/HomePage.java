package vn.asiantech.page.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.Constant.HOME_PAGE_URL;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 02/09.
 */
public class HomePage extends BasePage<HomePage> {

    @FindBy(className = "welcome-message")
    private WebElement txtWelcome;

    @FindBy(id = "btn-logout")
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

    private WebDriver driver;

    public HomePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final HomePage navigateTo(final WebDriver webDriver) {
        webDriver.get(HOME_PAGE_URL);
        return this;
    }

    public final boolean hasWelcomeMessage() {
        return isElementPresented(txtWelcome);
    }

    public final boolean welcomeTestIsDisplayed() {
        return txtWelcome.isDisplayed();
    }

    public final void waitForWelcomeMessage() {
        waitForElement(driver, txtWelcome);
    }

    public final void logout() {
        btnLogout.click();
    }

    public final void onClickTabInIBoxContent(final String position) {
        if (getButtonTab(wapper, position) != null && isElementPresented(getButtonTab(wapper, position))) {
            getButtonTab(wapper, position).click();
        }
    }

    public final boolean isColorTabInIBoxContentCorrect(final String color, final String position) {
        return getActualColor(getButtonTab(wapper, position)).equals(color);
    }

    public final boolean isColorOtherTabInIBoxContentCorrect(final String color, final String position) {
        return isColorOtherTabCorrect(wapper, color, position);
    }

    public final void onClickTabInRightSideBar(final String position) {
        if (getButtonTab(rightSideBar, position) != null && isElementPresented(getButtonTab(rightSideBar, position))) {
            getButtonTab(rightSideBar, position).click();
        }
    }

    public final boolean isColorTabInRightSideBarCorrect(final String position, final String activeColor) {
        return getActualColor(getButtonTab(rightSideBar, position)).equals(activeColor);
    }

    public final boolean isColorOtherTabInRightSideBarCorrect(final String position, final String defaultColor) {
        return isColorOtherTabCorrect(rightSideBar, defaultColor, position);
    }

    public final boolean isInIBoxContentShowed() {
        if (isElementPresented(homeContentHasData) && homeContentHasData.findElements(By.className("social-feed-box")).size() > 0) {
            return true;
        }
        return isElementPresented(homeContentNoData) && isElementPresented(homeContentNoData.findElement(By.tagName("h2")));
    }

    public final void sendKeysSearch(final String valueSearch) {
        waitForElement(driver, inputSearch);
        inputSearch.sendKeys(valueSearch);
    }

    public final String getEmptyTeamMessage() {
        WebElement txtEmpty = homeContentNoData.findElement(By.tagName("h2"));
        return txtEmpty.getText();
    }

    public final boolean isFeedListEmpty() {
        List<WebElement> listFeeds = homeContentHasData.findElements(By.className("social-feed-box"));
        return listFeeds.size() == 0;
    }

    public final boolean isRightSideBarShowed() {
        List<WebElement> listEvent = rightSideBar.findElements(By.className("event-block"));
        //noinspection LoopStatementThatDoesntLoop
        for (WebElement event : listEvent) {
            if (event.findElement(By.tagName("h3")).isDisplayed() && event.findElements(By.className("feed-element")).size() > 0) {
                return true;
            }
            return event.findElement(By.tagName("h3")).isDisplayed() && isElementPresented(event.findElement(By.className("text-na")));
        }
        return false;
    }

    public final void onClickUserName() {
        List<WebElement> listUserName = homeContentHasData.findElements(By.className("name"));
        listUserName.get(0).click();
    }

    public final void onClickAvatarInIBoxContent() {
        List<WebElement> listAvatar = homeContentHasData.findElements(By.cssSelector(".img-circle.pull-left"));
        listAvatar.get(0).click();
    }

    public final void onClickAvatarInRightSideBar() {
        List<WebElement> listAvatar = rightSideBar.findElements(By.className("img-circle"));
        listAvatar.get(0).click();
    }

    public final void onClickButtonReaction(final int position) {
        List<WebElement> listBtnReaction = rightSideBar.findElements(By.className("event-block")).get(0).findElements(By.cssSelector("button.btn-reaction.congrafs-btn"));
        if (listBtnReaction.size() > 0) {
            listBtnReaction.get(position).click();
        }
    }

    public final boolean isShowEffectForReaction() {
        By btnActive = By.cssSelector("button.btn-reaction.congrafs-btn.active");
        if (rightSideBar.findElements(btnActive).size() > 0) {
            return rightSideBar.findElement(btnActive).isDisplayed();
        }
        return true;
    }

    public final void scrollIBoxContent(final boolean isDown) {
        List<WebElement> listFeed = homeContentHasData.findElements(By.cssSelector("div.social-feed-box.ng-star-inserted"));
        scrollElementContent(listFeed, isDown);
    }

    public final void scrollRightSideBar(final boolean isDown) {
        List<WebElement> listFeed = rightSideBar.findElements(By.cssSelector("div.feed-element.ng-star-inserted"));
        scrollElementContent(listFeed, isDown);
    }

    private WebElement getButtonTab(final WebElement elementContent, final String position) {
        List<WebElement> listBtnTab = elementContent.findElement(By.className("ui-buttonset-3")).findElements(By.className("ui-button-text-only"));
        return listBtnTab.get(Integer.parseInt(position));
    }

    private boolean isColorOtherTabCorrect(final WebElement elementContent, final String color, final String position) {
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

    private void scrollElementContent(final List<WebElement> listElementContent, final boolean isDown) {
        if (listElementContent.size() > 0) {
            if (isDown) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElementContent.get(listElementContent.size() - 1));
            } else {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElementContent.get(0));
            }
        }
    }
}
