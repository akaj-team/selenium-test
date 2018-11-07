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
import org.testng.xml.XmlTest;
import vn.asiantech.object.Account;

import java.util.HashMap;
import java.util.Map;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;
import static vn.asiantech.base.Constant.MAXIMUM_TIME_OUT;

public class DriverBase {

    private static ThreadLocal<DriverFactory> driverFactoryThread = new ThreadLocal<>();

    static synchronized void instantiateDriverObject(XmlTest xmlTest) {
        DriverFactory driverFactory = new DriverFactory(xmlTest);
        driverFactoryThread.set(driverFactory);
    }

    public static synchronized RemoteWebDriver getDriver() {
        if (driverFactoryThread.get() == null) {
            XmlTest xmlTest = new XmlTest();
            xmlTest.setParameters(defaultParam());
            instantiateDriverObject(xmlTest);
        }
        return driverFactoryThread.get().getDriver();
    }

    private static Map<String, String> defaultParam() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("browserName", Constant.BROWSER_CHROME);
        parameters.put("server", "");
        return parameters;
    }

    protected final Account getAccount() {
        return driverFactoryThread.get().getAccountCanUse();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            driverFactoryThread.get().getDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

    public static void closeDriverObjects() {
        driverFactoryThread.get().quitDriver();
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
