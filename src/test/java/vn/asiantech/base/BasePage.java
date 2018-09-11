package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage<T> {

    public abstract T navigateTo(WebDriver webDriver);

    protected final boolean isElementPresented(final WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    protected final void waitForElementPresented(final WebDriver webDriver, final WebElement element, final int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> isElementPresented(element));
    }

    protected final void waitForElementDisplay(final WebDriver webDriver, final WebElement element, final int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> element.isDisplayed());
    }

    protected final void waitUntilCountChanges(final WebDriver webDriver, final WebElement element, final By by, final int count) {
        new WebDriverWait(webDriver, Constant.DEFAULT_TIME_OUT).until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = element.findElements(by).size();
            return elementCount > count;
        });
    }
}
