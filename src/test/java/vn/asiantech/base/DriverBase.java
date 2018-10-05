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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;

    public static void instantiateDriverObject() {
        driverFactoryThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreadPool.add(driverFactory);
            return driverFactory;
        });
    }

    public static RemoteWebDriver getDriver() {
        if (driverFactoryThread == null) {
            instantiateDriverObject();
        }
        return driverFactoryThread.get().getDriver();
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

    protected void waitForPageDisplayed(WebDriver driver, String url, By containerElement) {
        new WebDriverWait(driver, TIME_OUT_SECOND).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(containerElement));
        Assert.assertEquals(url, driver.getCurrentUrl());
    }
}