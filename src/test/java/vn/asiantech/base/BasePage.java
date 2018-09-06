package vn.asiantech.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    public void waitForElement(WebDriver webDriver, WebElement element, int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> isElementPresented(element));
    }

    public void waitForListElement(WebDriver webDriver, List<WebElement> elements, int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> elements.size() != 0);
    }

    public void waitForElementDisplay(WebDriver webDriver, WebElement element, int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> element.isDisplayed());
    }
}
