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

/**
 * @author at-hanhnguyen
 */
public class MenuPage extends BasePage<MenuPage> {

    private static final int MENU_ACCOUNT_NAME_POS = 0;
    private static final int MENU_HOME_POS = 1;
    private static final int MENU_TIME_SHEET_POS = 2;
    private static final int MENU_LEAVE_POS = 3;
    private static final int MENU_ORGANISATION_POS = 4;
    private static final int MENU_PROJECT_MANAGEMENT_POS = 5;
    private static final int MENU_WIKI_POS = 6;
    private static final int MENU_ADMINISTRATION_POS = 7;
    private static final int MENU_DEVICE_POS = 8;
    private static final int MENU_TOOL_POS = 9;
    private static final int MENU_CAREER_POS = 10;

    private static final int CHILD_MENU_MY_TIME_SHEET_POS = 0;
    private static final int CHILD_MENU_TIME_SHEET_OTHER_POS = 1;
    private static final int CHILD_MENU_MY_LEAVE_POS = 0;
    private static final int CHILD_MENU_LEAVE_PLANNER_POS = 1;
    private static final int CHILD_MENU_LEAVE_OTHER_POS = 2;
    private static final int CHILD_MENU_LEAVE_BALANCE_POS = 3;
    private static final int CHILD_MENU_ORGANISATION_MY_TEAM_POS = 2;

    private static final int TIME_SLEEP = 200;
    private static final int TIME_SLEEP_WIKI = 500;

    @FindBy(id = "side-menu")
    private WebElement sideMenu;
    @FindBy(className = "font-bold")
    private WebElement accountName;

    public MenuPage() {
        //no-up
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

    public final boolean checkColorItemHomeIsWhite(final String whiteColor) {
        WebElement itemHome = getItemMenuInPosition(MENU_HOME_POS);
        String color = itemHome.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final boolean checkItemTimeSheetClose() {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        return itemTimeSheet.findElement(By.tagName("ul")).isDisplayed();
    }

    public final void clickItemMenu() {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        itemTimeSheet.click();
    }

    public final void hoverMouseToAccountName() {
        WebElement itemProfile = getItemMenuInPosition(MENU_ACCOUNT_NAME_POS);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(itemProfile.findElement(By.id("link-to-my-profile")).findElement(By.className("font-bold"))).build().perform();
    }

    public final void clickMyTimeSheet() {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_MY_TIME_SHEET_POS);
        myTimeSheet.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkMyTimeSheetColor(final String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        WebElement myTimeSheet = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_MY_TIME_SHEET_POS);
        String color = myTimeSheet.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final void clickTimeSheetOfOthers() {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_TIME_SHEET_OTHER_POS);
        myTimeSheetOfOthers.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkTimeSheetOfOtherColor(final String whiteColor) {
        WebElement itemTimeSheet = getItemMenuInPosition(MENU_TIME_SHEET_POS);
        WebElement myTimeSheetOfOthers = itemTimeSheet.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_TIME_SHEET_OTHER_POS);
        String color = myTimeSheetOfOthers.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final void clickHomeItem() {
        WebElement itemHome = getItemMenuInPosition(MENU_HOME_POS);
        itemHome.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkItemLeaveClose() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        return itemLeave.findElement(By.tagName("ul")).isDisplayed();
    }

    public final void clickItemLeave() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        itemLeave.click();
    }

    public final boolean checkColorItemLeaveIsWhite(final String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        String color = itemLeave.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final void clickMyLeave() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_MY_LEAVE_POS);
        myLeave.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkColorMyLeave(final String whiteColor) {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_MY_LEAVE_POS);
        String color = myLeave.findElement(By.tagName("a")).getCssValue("color");
        String actualColor = getColorString(color);
        return Color.fromString(whiteColor).asRgba().equals(actualColor);
    }

    public final void clickLeavePlanner() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        WebElement leavePlanner = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_LEAVE_PLANNER_POS);
        leavePlanner.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickLeaveOfOther() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        WebElement leaveOfOther = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_LEAVE_OTHER_POS);
        leaveOfOther.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickLeaveBalance() {
        WebElement itemLeave = getItemMenuInPosition(MENU_LEAVE_POS);
        WebElement leaveBalance = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(CHILD_MENU_LEAVE_BALANCE_POS);
        leaveBalance.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkItemOrganisationClose() {
        WebElement itemOrganisation = getItemMenuInPosition(MENU_ORGANISATION_POS);
        return itemOrganisation.findElement(By.tagName("ul")).isDisplayed();
    }

    public final void clickItemOrganisation() {
        WebElement itemOrganisation = getItemMenuInPosition(MENU_ORGANISATION_POS);
        itemOrganisation.click();
    }

    public final void clickChildItemOrganisation(final String childPosition) {
        WebElement itemOrganisation = getItemMenuInPosition(MENU_ORGANISATION_POS);
        WebElement childItemOrganisation = getChildItemMenuInPosition(itemOrganisation, Integer.valueOf(childPosition));
        childItemOrganisation.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final boolean checkCountMyTeamIsZero() {
        WebElement itemOrganisation = getItemMenuInPosition(MENU_ORGANISATION_POS);
        WebElement itemMyTeam = getChildItemMenuInPosition(itemOrganisation, CHILD_MENU_ORGANISATION_MY_TEAM_POS);
        return itemMyTeam.findElements(By.tagName("li")).size() == 0;
    }

    public final void clickItemProjectManagement() {
        WebElement itemProjectManagement = getItemMenuInPosition(MENU_PROJECT_MANAGEMENT_POS);
        itemProjectManagement.click();
    }

    public final void clickChildItemProjectManagement(final String childPosition) {
        WebElement itemProjectManagement = getItemMenuInPosition(MENU_PROJECT_MANAGEMENT_POS);
        WebElement childItemProject = getChildItemMenuInPosition(itemProjectManagement, Integer.valueOf(childPosition));
        childItemProject.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickItemWiki() {
        WebElement itemWiki = getItemMenuInPosition(MENU_WIKI_POS);
        itemWiki.click();
        try {
            Thread.sleep(TIME_SLEEP_WIKI);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickItemAdministration() {
        WebElement itemAdministration = getItemMenuInPosition(MENU_ADMINISTRATION_POS);
        itemAdministration.click();
    }

    public final void clickChildItemAdministration(final String childPosition) {
        WebElement itemAdministration = getItemMenuInPosition(MENU_ADMINISTRATION_POS);
        WebElement childItemAdministration = getChildItemMenuInPosition(itemAdministration, Integer.valueOf(childPosition));
        childItemAdministration.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickItemDevice() {
        WebElement itemDevice = getItemMenuInPosition(MENU_DEVICE_POS);
        itemDevice.click();
    }

    public final void clickChildItemDevice(final String childPosition) {
        WebElement itemDevice = getItemMenuInPosition(MENU_DEVICE_POS);
        WebElement childItemDevice = getChildItemMenuInPosition(itemDevice, Integer.valueOf(childPosition));
        childItemDevice.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickItemTools() {
        WebElement itemTools = getItemMenuInPosition(MENU_TOOL_POS);
        itemTools.click();
    }

    public final void clickChildItemTools(final String childPosition) {
        WebElement itemTools = getItemMenuInPosition(MENU_TOOL_POS);
        WebElement childItemTools = getChildItemMenuInPosition(itemTools, Integer.valueOf(childPosition));
        childItemTools.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickItemCareer() {
        WebElement itemCareer = getItemMenuInPosition(MENU_CAREER_POS);
        itemCareer.click();
    }

    public final void clickChildItemCareer(final String childPosition) {
        WebElement itemCareer = getItemMenuInPosition(MENU_CAREER_POS);
        WebElement childItemCareer = getChildItemMenuInPosition(itemCareer, Integer.valueOf(childPosition));
        childItemCareer.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement getItemMenuInPosition(final int position) {
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

    private WebElement getChildItemMenuInPosition(final WebElement itemMenu, final int childPosition) {
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
