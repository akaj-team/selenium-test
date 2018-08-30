package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import static vn.asiantech.base.DriverBase.getDriver;

public class MenuPage extends BasePage<MenuPage> {
    @FindBy(id = "side-menu")
    private WebElement sideMenu;
    @FindBy(className = "font-bold")
    private WebElement accountName;

    public MenuPage() {
        //no-up
    }

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

    public boolean checkColorItemHomeIsWhite(String whiteColor) {
        WebElement itemHome = getItemMenuInPosition(2);
        String color = itemHome.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public boolean checkItemTimeSheetClose() {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        return itemTimeSheet.findElement(By.tagName("ul")).getRect().height == 0;
    }

    public void clickItemMenu() {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        itemTimeSheet.click();
    }

    public void hoverMouseToAccountName() {
        WebElement itemProfile = getItemMenuInPosition(1);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(itemProfile.findElement(By.className("dropdown-toggle")).findElement(By.className("font-bold"))).build().perform();
    }

    public boolean checkColorAccountNameWhenHover(String colorHover) {
        WebElement itemProfile = getItemMenuInPosition(1);
        if (itemProfile.findElement(By.className("dropdown-toggle")).getCssValue("cursor").equals("pointer")) {
            String color = itemProfile.findElement(By.className("dropdown-toggle")).getCssValue("color");
            String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
            String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
            return actualColor.equals(colorHover);
        }
        return false;
    }

    public void clickMyTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        myTimeSheet.click();
    }

    public boolean checkMyTimeSheetColor(String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        String color = myTimeSheet.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickTimeSheetOfOthers() {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        myTimeSheetOfOthers.click();
    }

    public boolean checkTimeSheetOfOtherColor(String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(3);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        String color = myTimeSheetOfOthers.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickHomeItem() {
        WebElement itemHome = getItemMenuInPosition(2);
        itemHome.click();
    }

    public boolean checkItemLeaveClose() {
        WebElement itemLeave = getItemMenuInPosition(4);
        return itemLeave.findElement(By.tagName("ul")).getRect().height == 0;
    }

    public void clickItemLeave() {
        WebElement itemLeave = getItemMenuInPosition(4);
        itemLeave.click();
    }

    public boolean checkColorItemLeaveIsWhite(String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(4);
        String color = itemLeave.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickMyLeave() {
        WebElement itemLeave = getItemMenuInPosition(4);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        myLeave.click();
    }

    public boolean checkColorMyLeave(String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(4);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        String color = myLeave.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    private WebElement getItemMenuInPosition(int position) {
        return sideMenu.findElement(By.xpath("/html/body/app-root/app-feature/div[1]/app-sidebar/nav/div/ul/li[" + position + "]"));
    }

    private String getColorString(String color) {
        if (color.contains("rgba")) {
            return color;
        } else if (color.contains("rgb")) {
            String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
            return Color.fromString(String.format("#%02x%02x%02x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()))).asRgba();
        }
        return Color.fromString(color).asRgba();
    }
}
