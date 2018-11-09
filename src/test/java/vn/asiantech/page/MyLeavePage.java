package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.DriverBase.getDriver;

/**
 * @author at-anh.quach
 */

public class MyLeavePage extends BasePage<MyLeavePage> {
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

    private String sysId;

    @Override
    public final MyLeavePage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void clickMenuStatus() {
        waitForElement(getDriver(), inputStatus);
        inputStatus.click();
    }

    public final Boolean dropDownMenuStatus() {
        return menuStatus.isDisplayed();
    }

    public final void clickItemMenuStatus(final String status) {
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

    public final boolean checkTextStatusMenu(final String status) {
        waitForElement(getDriver(), inputStatus);
        return inputStatus.findElement(By.tagName("label")).getText().equals(status);
    }

    public final boolean checkTextStatus(final String status) {
        return findDataLeave(POS_STATUS).getAttribute("class").equals(getNameIconStatus(status));
    }

    public final boolean checkTextApprover(final String approver) {
        return findDataLeave(POS_APPROVER).getText().equals(approver);
    }

    public final boolean checkTextManager(final String manager) {
        return findDataLeave(POS_MANAGER).getText().equals(manager);
    }

    public final boolean checkTextAnnualLeave(final String annualLeave) {
        return findLeaveBalance(ANNUAL_LEAVE).getText().equals(annualLeave);
    }

    public final boolean checkTextMarriageLeave(final String marriageLeave) {
        return findLeaveBalance(MARRIAGE_LEAVE).getText().equals(marriageLeave);
    }

    public final boolean checkTextOvertimeLeave(final String overTimeLeave) {
        return findLeaveBalance(OVERTIME_LEAVE).getText().equals(overTimeLeave);
    }

    public final boolean checkTextPaternalLeave(final String paternalLeave) {
        return findLeaveBalance(PATERNAL_LEAVE).getText().equals(paternalLeave);
    }

    public final boolean checkMenuStatusDropDown() {
        return menuStatus.isDisplayed();
    }

    public final void clickSYSID() {
        findDataLeave(POS_ICON).click();
    }

    public final void clickNameManager() {
        waitForElement(getDriver(), inputStatus);
        findDataLeave(POS_MANAGER).click();
    }

    public final void clickIconSearch() {
        findDataLeave(POS_ICON).click();
    }

    public final void setSysId() {
        waitForElement(getDriver(), inputStatus);
        sysId = findDataLeave(POS_SYSID).getText();
    }

    public final String getSysId() {
        return sysId;
    }

    public final void clickBtnLeaveRequest() {
        waitForElement(getDriver(), inputStatus);
        btnLeaveRequest.click();
    }

    public final void hoverMouseToStatus() {
        waitForElement(getDriver(), tableLeave);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(findDataLeave(POS_STATUS)).build().perform();
    }

    public final boolean checkDisplayTipStatus(final String status) {
        return toolTip.isDisplayed() && toolTip.getText().equals(status);
    }

    private WebElement findDataLeave(final int col) {
        WebElement tableData = tableLeave.findElement(By.tagName("table"));
        List<WebElement> rows = tableData.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(POS_SYSID).findElements(By.tagName("td"));

        if (col != POS_STATUS) {
            return columns.get(col).findElement(By.tagName("span"));
        } else {
            return columns.get(POS_STATUS).findElement(By.className("ui-cell-data")).findElement(By.tagName("span"));
        }
    }

    private WebElement findLeaveBalance(final int pos) {
        List<WebElement> balances = leaveBalance.findElements(By.tagName("dd"));
        return balances.get(pos).findElement(By.tagName("span"));
    }

    private String getNameIconStatus(final String status) {
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
