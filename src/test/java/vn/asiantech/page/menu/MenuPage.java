package vn.asiantech.page.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * @author at-hanhnguyen
 */
public class MenuPage extends BasePage<MenuPage> {

    private static final int TIME_SLEEP = 1000;

    @FindBy(className = "font-bold")
    private WebElement accountName;
    private WebDriver driver;

    public MenuPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final MenuPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final boolean checkShowAccountNameShown() {
        return accountName.isDisplayed();
    }

    public final boolean checkTextAccountName(final String inputName) {
        return accountName.getText().equals(inputName);
    }

    public final void clickParentItem(final String title) {
        WebElement parentItem = driver.findElement(By.xpath("//span[contains(text(),'" + title + "')]"));
        WebElement container = driver.findElement(By.xpath("//span[contains(text(),'" + title + "')]/../.."));
        if (!container.getAttribute("class").contains("active") && !title.equals("Home")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parentItem);
            parentItem.click();
        }
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkColorParentItemIsWhite(final String title, final String whiteColor) {
        WebElement item = driver.findElement(By.xpath("//span[contains(text(),'" + title + "')]/.."));
        String color = item.getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final void clickChildItem(final String title) {
        WebElement childItem = driver.findElement(By.xpath("//a[contains(text(),'" + title + "')]/.."));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", childItem);
        childItem.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkColorChildItemIsWhite(final String title, final String whiteColor) {
        WebElement item = driver.findElement(By.xpath("//a[contains(text(),'" + title + "')]"));
        String color = item.getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final boolean checkMenuItemClose(final String title) {
        WebElement menuItem = driver.findElement(By.xpath("//span[contains(text(),'" + title + "')]/../.."));
        return menuItem.getAttribute("class").equals("");
    }

    private String getColorString(final String color) {
        if (color.contains("rgba")) {
            return color;
        } else if (color.contains("rgb")) {
            String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
            return Color.fromString(String.format("#%02x%02x%02x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()))).asRgba();
        }
        return Color.fromString(color).asRgba();
    }
}
