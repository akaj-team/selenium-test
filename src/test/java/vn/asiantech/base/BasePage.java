package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T> {

    public abstract T navigateTo(WebDriver webDriver);

    protected boolean isElementPresented(WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    protected void waitForElementPresented(WebDriver webDriver, WebElement element, int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> isElementPresented(element));
    }

    protected void waitForElementDisplay(WebDriver webDriver, WebElement element, int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> element.isDisplayed());
    }

    protected void waitUntilCountChanges(WebDriver webDriver, WebElement element, By by, int count) {
        new WebDriverWait(webDriver, 5).until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = element.findElements(by).size();
            return elementCount > count;
        });
    }
}
