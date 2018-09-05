package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.List;

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
        WebElement itemHome = getItemMenuInPosition(1);
        String color = itemHome.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
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

    public void clickMyTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        myTimeSheet.click();
    }

    public boolean checkMyTimeSheetColor(String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        String color = myTimeSheet.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickTimeSheetOfOthers() {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        myTimeSheetOfOthers.click();
    }

    public boolean checkTimeSheetOfOtherColor(String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(2);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        String color = myTimeSheetOfOthers.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickHomeItem() {
        WebElement itemHome = getItemMenuInPosition(1);
        itemHome.click();
    }

    public boolean checkItemLeaveClose() {
        WebElement itemLeave = getItemMenuInPosition(3);
        return itemLeave.findElement(By.tagName("ul")).getRect().height == 0;
    }

    public void clickItemLeave() {
        WebElement itemLeave = getItemMenuInPosition(3);
        itemLeave.click();
    }

    public boolean checkColorItemLeaveIsWhite(String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(3);
        String color = itemLeave.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickMyLeave() {
        WebElement itemLeave = getItemMenuInPosition(3);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        myLeave.click();
    }

    public boolean checkColorMyLeave(String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(3);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        String color = myLeave.findElement(By.tagName("a")).getCssValue("color");
        String[] hexValue = color.replace("rgb(", "").replace(")", "").split(",");
        String actualColor = String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
        return actualColor.equals(whiteColor);
    }

    public void clickLeavePlanner() {
        WebElement itemLeave = getItemMenuInPosition(3);
        WebElement leavePlanner = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        leavePlanner.click();
    }

    public void clickLeaveOfOther() {
        WebElement itemLeave = getItemMenuInPosition(3);
        WebElement leaveOfOther = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
        leaveOfOther.click();
    }

    public void clickLeaveBalance() {
        WebElement itemLeave = getItemMenuInPosition(3);
        WebElement leaveBalance = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(3);
        leaveBalance.click();
    }

    public boolean checkItemOrganisationClose() {
        WebElement itemOrganisation = getItemMenuInPosition(4);
        return itemOrganisation.findElement(By.tagName("ul")).getRect().height == 0;
    }

    public void clickItemOrganisation() {
        WebElement itemOrganisation = getItemMenuInPosition(4);
        itemOrganisation.click();
    }

    public void clickChildItemOrganisation(String childPosition) {
        WebElement itemOrganisation = getItemMenuInPosition(4);
        WebElement childItemOrganisation = getChildItemMenuInPosition(itemOrganisation, Integer.valueOf(childPosition));
        childItemOrganisation.click();
    }

    public boolean checkCountMyTeamIsZero() {
        WebElement itemOrganisation = getItemMenuInPosition(4);
        WebElement itemMyTeam = getChildItemMenuInPosition(itemOrganisation, 2);
        return itemMyTeam.findElements(By.tagName("li")).size() == 0;
    }

    public void clickItemProjectManagement() {
        WebElement itemProjectManagement = getItemMenuInPosition(5);
        itemProjectManagement.click();
    }

    public void clickChildItemProjectManagement(String childPosition) {
        WebElement itemProjectManagement = getItemMenuInPosition(5);
        WebElement childItemProject = getChildItemMenuInPosition(itemProjectManagement, Integer.valueOf(childPosition));
        childItemProject.click();
    }

    public void clickItemWiki() {
        WebElement itemWiki = getItemMenuInPosition(6);
        itemWiki.click();
    }

    public void clickItemAdministration() {
        WebElement itemAdministration = getItemMenuInPosition(7);
        itemAdministration.click();
    }

    public void clickChildItemAdministration(String childPosition) {
        WebElement itemAdministration = getItemMenuInPosition(7);
        WebElement childItemAdministration = getChildItemMenuInPosition(itemAdministration, Integer.valueOf(childPosition));
        childItemAdministration.click();
    }

    public void clickItemDevice() {
        WebElement itemDevice = getItemMenuInPosition(8);
        itemDevice.click();
    }

    public void clickChildItemDevice(String childPosition) {
        WebElement itemDevice = getItemMenuInPosition(8);
        WebElement childItemDevice = getChildItemMenuInPosition(itemDevice, Integer.valueOf(childPosition));
        childItemDevice.click();
    }

    public void clickItemTools() {
        WebElement itemTools = getItemMenuInPosition(9);
        itemTools.click();
    }

    public void clickChildItemTools(String childPosition) {
        WebElement itemTools = getItemMenuInPosition(9);
        WebElement childItemTools = getChildItemMenuInPosition(itemTools, Integer.valueOf(childPosition));
        childItemTools.click();
    }

    public void clickItemCareer() {
        WebElement itemCareer = getItemMenuInPosition(10);
        itemCareer.click();
    }

    public void clickChildItemCareer(String childPosition) {
        WebElement itemCareer = getItemMenuInPosition(10);
        WebElement childItemCareer = getChildItemMenuInPosition(itemCareer, Integer.valueOf(childPosition));
        childItemCareer.click();
    }

    private WebElement getItemMenuInPosition(int position) {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = sideMenu.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(position);
    }

    private WebElement getChildItemMenuInPosition(WebElement itemMenu, int childPosition) {
        List<WebElement> itemChildMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> itemChild = itemMenu.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (int i = 0; i < itemChild.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemChildMenus.add(itemChild.get(i));
            if (itemChild.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = itemChild.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemChildMenus.get(childPosition);
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
