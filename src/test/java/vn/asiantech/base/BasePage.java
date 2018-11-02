package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static vn.asiantech.base.DriverBase.TIME_OUT_SECONDS_NORMAL;

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

    protected void waitForElement(WebDriver webDriver, WebElement element) {
        new WebDriverWait(webDriver, Constant.DEFAULT_TIME_OUT).until(
                driver -> isElementPresented(element));
    }

    protected final void waitForElementDisplay(final WebDriver webDriver, final WebElement element, final int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> element.isDisplayed());
    }

    protected final void waitUntilCountDifference(final WebDriver webDriver, final WebElement element, final By by, final int count) {
        new WebDriverWait(webDriver, TIME_OUT_SECONDS_NORMAL).until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = element.findElements(by).size();
            return elementCount != count;
        });
    }
}
