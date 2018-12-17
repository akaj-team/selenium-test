package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePage<T> {

    public BasePage(final WebDriver driver) {
        //no-up
    }

    public abstract T navigateTo(WebDriver webDriver);

    protected final boolean isElementPresented(final WebElement element) {
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

    protected final void waitUntilCountDifference(final WebDriver webDriver, final WebElement element, final By by, final int count) {
        new WebDriverWait(webDriver, Constant.DEFAULT_TIME_OUT).until((ExpectedCondition<Boolean>) driver -> {
            int elementCount = element.findElements(by).size();
            return elementCount != count;
        });
    }

    protected final void waitForListElement(final WebDriver webDriver, final List<WebElement> elements) {
        new WebDriverWait(webDriver, Constant.DEFAULT_TIME_OUT).until(
                driver -> elements.size() != 0);
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
