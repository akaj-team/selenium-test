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
import vn.asiantech.core.DriverFactory;
import vn.asiantech.core.PageFactory;
import vn.asiantech.object.Account;

import java.lang.reflect.Field;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;

public class BaseDefinitions {

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        try {
            DriverFactory.instance.getDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

    private static void initElements(final WebDriver driver, final Object page) {
        initElements(new DefaultElementLocatorFactory(driver), page);
    }

    private static void initElements(final ElementLocatorFactory factory, final Object page) {
        initElements(new DefaultFieldDecorator(factory), page);
    }

    private static void initElements(final FieldDecorator decorator, final Object page) {
        for (Class proxyIn = page.getClass(); proxyIn != Object.class; proxyIn = proxyIn.getSuperclass()) {
            proxyFields(decorator, page, proxyIn);
        }
    }

    private static void proxyFields(final FieldDecorator decorator, final Object page, final Class<?> proxyIn) {
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

    protected final RemoteWebDriver getDriver() {
        return DriverFactory.instance.getDriver();
    }

    protected final Account getAccount() {
        return DriverFactory.instance.getAccountCanUse();
    }

    protected <T> T initPage(WebDriver driver, Class<T> clazz) {
        T page = new PageFactory<>(driver, clazz).create();
        initElements(driver, page);
        return page;
    }

    protected final void waitForPageDisplayed(final WebDriver driver, final String url, final By containerElement) {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        if (containerElement != null) {
            waitVisibilityOfElement(driver, containerElement);
        }
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    protected final void waitRedirectToPage(final String destinationUrl) {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.getCurrentUrl().equals(destinationUrl));
    }

    protected final void waitForPageRedirected(final WebDriver driver, final String url, final By containerElement) {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.urlToBe(url));
        waitVisibilityOfElement(driver, containerElement);
    }

    protected final void waitVisibilityOfElement(final WebDriver driver, final By element) {
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
