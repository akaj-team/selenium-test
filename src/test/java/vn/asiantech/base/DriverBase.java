package vn.asiantech.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;
import static vn.asiantech.base.Constant.MAXIMUM_TIME_OUT;

public class DriverBase {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;

    static void instantiateDriverObject() {
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
        T page = new PageFactory<>(driver, clazz).create();
        initElements(driver, page);
        return page;
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

    private static void initElements(WebDriver driver, Object page) {
        initElements(new DefaultElementLocatorFactory(driver), page);
    }

    private static void initElements(ElementLocatorFactory factory, Object page) {
        initElements(new DefaultFieldDecorator(factory), page);
    }

    private static void initElements(FieldDecorator decorator, Object page) {
        for (Class proxyIn = page.getClass(); proxyIn != Object.class; proxyIn = proxyIn.getSuperclass()) {
            proxyFields(decorator, page, proxyIn);
        }

    }

    private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException var10) {
                    throw new RuntimeException(var10);
                }
            }
        }
    }
}
