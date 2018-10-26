package vn.asiantech.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    protected final void waitForElement(final WebDriver webDriver, final WebElement element, final int timeOutInSecond) {
        new WebDriverWait(webDriver, timeOutInSecond).until(
                driver -> isElementPresented(element));
    }
}
