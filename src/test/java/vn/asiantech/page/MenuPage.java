package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.DriverBase.getDriver;

public class MenuPage extends BasePage<MenuPage> {
    @FindBy(id = "side-menu")
    private WebElement sideMenu;
    @FindBy(className = "font-bold")
    private WebElement accountName;

    @Override
    public MenuPage navigateTo(WebDriver webDriver) {
        return this;
    }

    public boolean checkShowAccountNameShown() {
        return accountName.isDisplayed();
    }

    public boolean checkTextAccountName(String inputName) {
        return accountName.getText().equals(inputName);
    }

    public boolean checkColorItemMenuIsWhite(String whiteColor) {
        WebElement itemHome = getItemMenuInPosition(1);
        String color = itemHome.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public boolean checkItemTimeSheetClose() {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        return itemTimeSheet.findElement(By.tagName("ul")).getRect().height == 0;
    }

    public void clickItemMenu() {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        itemTimeSheet.click();
    }

    public void hoverMouseToAccountName() {
        WebElement itemProfile = getItemMenuInPosition(0);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(itemProfile.findElement(By.className("dropdown-toggle")).findElement(By.className("font-bold"))).build().perform();
    }

    public boolean checkColorAccountNameWhenHover(String colorHover) {
        WebElement itemProfile = getItemMenuInPosition(0);
        if (itemProfile.findElement(By.className("dropdown-toggle")).getCssValue("cursor").equals("pointer")) {
            String color = itemProfile.findElement(By.className("dropdown-toggle")).getCssValue("color");
            String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
            String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
            return actualColor.equals(colorHover);
        }
        return false;
    }

    private WebElement getItemMenuInPosition(int position) {
        List<WebElement> itemTimeSheet = sideMenu.findElements(By.tagName("li"));
        return itemTimeSheet.get(position);
    }
}
