package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.DriverBase.getDriver;

/**
 * MyLeavePage
 *
 * @author at-anh.quach
 */

public class MyLeavePage extends BasePage<MyLeavePage> {
    private static final int TIME_OUT_IN_SECONDS = 5;
    private static final int ANNUAL_LEAVE = 0;
    private static final int MARRIAGE_LEAVE = 1;
    private static final int OVERTIME_LEAVE = 2;
    private static final int PATERNAL_LEAVE = 3;
    private static final int ALL_STATUS = 0;
    private static final int PENDING = 1;
    private static final int APPROVED = 2;
    private static final int REJECTED = 3;
    private static final int POS_MANAGER = 6;
    private static final int POS_ICON = 7;
    private static final int POS_APPROVER = 5;
    private static final int POS_SYSID = 0;
    private static final int POS_STATUS = 2;

    @FindBy(id = "status-filter-wrapper")
    private WebElement inputStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement menuStatus;

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement tableLeave;

    @FindBy(id = "leave-balance-remain-wrapper")
    private WebElement leaveBalance;

    @FindBy(id = "btn-request-leave")
    private WebElement btnLeaveRequest;

    @FindBy(css = ".ui-tooltip-text.ui-shadow.ui-corner-all")
    private WebElement toolTip;

    private String sysid;

    @Override
    public MyLeavePage navigateTo(WebDriver webDriver) {
        return this;
    }

    public void clickMenuStatus() {
        waitForElementDisplay(getDriver(), inputStatus);
        inputStatus.click();
    }

    public Boolean dropDownMenuStatus() {
        return menuStatus.isDisplayed();
    }

    public void clickItemMenuStatus(String status) {
        WebElement itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(ALL_STATUS);
        if (status.equals("Pending")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(PENDING);
        }
        if (status.equals("Approved")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(APPROVED);
        }
        if (status.equals("Rejected")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(REJECTED);
        }
        itemStatus.click();
    }

    public boolean checkTextStatusMenu(String status) {
        waitForElementDisplay(getDriver(), inputStatus);
        return inputStatus.findElement(By.tagName("label")).getText().equals(status);
    }

    public boolean checkTextStatus(String status) {
        return findDataLeave(POS_STATUS).getAttribute("class").equals(getNameIconStatus(status));
    }

    public boolean checkTextApprover(String approver) {
        return findDataLeave(POS_APPROVER).getText().equals(approver);
    }

    public boolean checkTextManager(String manager) {
        return findDataLeave(POS_MANAGER).getText().equals(manager);
    }

    public boolean checkTextAnnualLeave(String annualLeave) {
        return findLeaveBalance(ANNUAL_LEAVE).getText().equals(annualLeave);
    }

    public boolean checkTextMarriageLeave(String marriageLeave) {
        return findLeaveBalance(MARRIAGE_LEAVE).getText().equals(marriageLeave);
    }

    public boolean checkTextOvertimeLeave(String overTimeLeave) {
        return findLeaveBalance(OVERTIME_LEAVE).getText().equals(overTimeLeave);
    }

    public boolean checkTextPaternalLeave(String paternalLeave) {
        return findLeaveBalance(PATERNAL_LEAVE).getText().equals(paternalLeave);
    }

    public boolean checkMenuStatusDropDown() {
        return menuStatus.isDisplayed();
    }

    public boolean checkNoRecordsFound() {
        return findDataLeave(POS_SYSID).getText().equals("No records found");
    }

    public void clickSYSID() {
        waitForElementDisplay(getDriver(), inputStatus);
        sysid = findDataLeave(POS_SYSID).getText();
        findDataLeave(POS_ICON).click();
    }

    public void clickNameManager() {
        waitForElementDisplay(getDriver(), inputStatus);
        findDataLeave(POS_MANAGER).click();
    }

    public void clickIconSearch() {
        waitForElementDisplay(getDriver(), inputStatus);
        sysid = findDataLeave(POS_SYSID).getText();
        findDataLeave(POS_ICON).click();
    }

    public void clickBtnLeaveRequest() {
        waitForElementDisplay(getDriver(), inputStatus);
        btnLeaveRequest.click();
    }

    public void hoverMouseToStatus() {
        waitForElementDisplay(getDriver(), tableLeave);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(findDataLeave(POS_STATUS)).build().perform();
    }

    public boolean checkDisplayTipStatus(String status) {
        System.out.println(toolTip.getText());
        return toolTip.isDisplayed() && toolTip.getText().equals(status);
    }

    public void displayLeaveDetailPage(WebDriver driver) {
        driver.get("http://portal-stg.asiantech.vn/leave/" + sysid);
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
        Assert.assertEquals("http://portal-stg.asiantech.vn/leave/" + sysid, driver.getCurrentUrl());
    }

    private WebElement findDataLeave(int col) {
        WebElement tableData = tableLeave.findElement(By.tagName("table"));
        List<WebElement> rows = tableData.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(POS_SYSID).findElements(By.tagName("td"));

        if (col != POS_STATUS) {
            return columns.get(col).findElement(By.tagName("span"));
        } else {
            return columns.get(POS_STATUS).findElement(By.className("ui-cell-data")).findElement(By.tagName("span"));
        }
    }

    private WebElement findLeaveBalance(int pos) {
        List<WebElement> balances = leaveBalance.findElements(By.tagName("dd"));
        return balances.get(pos).findElement(By.tagName("span"));
    }

    private String getNameIconStatus(String status) {
        String nameIcon = "icon default ng-star-inserted";
        if (status.equals("Approved")) {
            nameIcon = "icon approved ng-star-inserted";
        }
        if (status.equals("Rejected")) {
            nameIcon = "icon rejected ng-star-inserted";
        }

        return nameIcon;
    }
}
