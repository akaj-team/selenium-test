package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import vn.asiantech.object.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;
import static vn.asiantech.base.Constant.MAXIMUM_TIME_OUT;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread = new ThreadLocal<>();

    static synchronized void instantiateDriverObject(final String browserName) {
        DriverFactory driverFactory = new DriverFactory(browserName);
        webDriverThreadPool.add(driverFactory);
        driverFactoryThread.set(driverFactory);
    }

    public static synchronized RemoteWebDriver getDriver() {
        if (driverFactoryThread.get() == null) {
            instantiateDriverObject(Constant.BROWSER_CHROME);
        }
        return driverFactoryThread.get().getDriver();
    }

    protected final Account getAccount() {
        return driverFactoryThread.get().getAccountCanUse();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            driverFactoryThread.get().getStoredDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }

    protected <T> T initPage(WebDriver driver, Class<T> clazz) {
        return PageFactory.initElements(driver, clazz);
    }

    protected final void waitForPageDisplayed(final WebDriver driver, final String url, final By containerElement) {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        waitVisibilityOfElement(driver, containerElement);
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    protected final void waitForPageRedirected(final WebDriver driver, final String url, final By containerElement) {
        new WebDriverWait(driver, MAXIMUM_TIME_OUT).until(ExpectedConditions.urlToBe(url));
        waitVisibilityOfElement(driver, containerElement);
    }

    protected final void waitVisibilityOfElement(final WebDriver driver, final By element) {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
