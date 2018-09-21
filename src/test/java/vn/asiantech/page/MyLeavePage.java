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

public class MyLeavePage extends BasePage<MyLeavePage> {

    @FindBy(css = ".toolbox-item.dropdown-md")
    private WebElement inputStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement menuStatus;

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement tableLeave;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement leaveBalance;

    @FindBy(css = ".btn.btn-default.btn-sm.btn-add")
    private WebElement btnLeaveRequest;

    @FindBy(css = ".ui-tooltip-text.ui-shadow.ui-corner-all")
    private WebElement toolTip;

    private String sysid;

    @Override
    public MyLeavePage navigateTo(WebDriver webDriver) {
        return this;
    }

    public void clickMenuStatus(WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        inputStatus.click();
    }

    public Boolean dropDownMenuStatus() {
        return menuStatus.isDisplayed();
    }

    public void clickItemMenuStatus(String status) {
        WebElement itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        if (status.equals("Pending")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        }
        if (status.equals("Approved")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
        }
        if (status.equals("Rejected")) {
            itemStatus = menuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(3);
        }
        itemStatus.click();
    }

    public boolean checkTextStatusMenu(String status, WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        return inputStatus.findElement(By.tagName("label")).getText().equals(status);
    }

    public boolean checkTextSYSID(String sysid) {
        return findDataLeave(0, 0).getText().equals(sysid);
    }

    public boolean checkTextTypeOfLeave(String type) {
        return findDataLeave(0, 1).getText().equals(type);
    }

    public boolean checkTextStatus(String status) {
        return findDataLeave(0, 2).getAttribute("class").equals(getNameIconStatus(status));
    }

    public boolean checkTextApprover(String approver) {
        return findDataLeave(0, 5).getText().equals(approver);
    }

    public boolean checkTextManager(String manager) {
        return findDataLeave(0, 6).getText().equals(manager);
    }

    public boolean checkTextAnnualLeave(String annualLeave) {
        return findLeaveBalance(0).getText().equals(annualLeave);
    }

    public boolean checkTextMarriageLeave(String marriageLeave) {
        return findLeaveBalance(1).getText().equals(marriageLeave);
    }

    public boolean checkTextOvertimeLeave(String overTimeLeave) {
        return findLeaveBalance(2).getText().equals(overTimeLeave);
    }

    public boolean checkTextPaternalLeave(String paternalLeave) {
        return findLeaveBalance(3).getText().equals(paternalLeave);
    }

    public boolean checkMenuStatusDropDown() {
        return menuStatus.isDisplayed();
    }

    public boolean checkNoRecordsFound() {
        return findDataLeave(0, 0).getText().equals("No records found");
    }

    public void clickSYSID(WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        sysid = findDataLeave(0, 0).getText();
        findDataLeave(0, 0).click();
    }

    public void clickNameManager(WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        findDataLeave(0, 6).click();
    }

    public void clickIconSearch(WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        sysid = findDataLeave(0, 0).getText();
        findDataLeave(0, 7).click();
    }

    public void clickBtnLeaveRequest(WebDriver driver) {
        waitForElementDisplay(driver, inputStatus, 10);
        btnLeaveRequest.click();
    }

    public void hoverMouseToStatus(WebDriver driver) {
        waitForElementDisplay(driver, tableLeave, 10);
        Actions builder = new Actions(getDriver());
        builder.moveToElement(findDataLeave(0, 2)).build().perform();
    }

    public boolean checkDisplayTipStatus(String status) {
        System.out.println(toolTip.getText());
        return toolTip.isDisplayed() && toolTip.getText().equals(status);
    }

    public void displayLeaveDetailPage(WebDriver driver) {
        driver.get("http://portal-stg.asiantech.vn/leave/" + sysid);
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
        Assert.assertEquals("http://portal-stg.asiantech.vn/leave/" + sysid, driver.getCurrentUrl());
    }

    private WebElement findDataLeave(int row, int col) {
        WebElement tableData = tableLeave.findElement(By.tagName("table"));
        List<WebElement> rows = tableData.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(row).findElements(By.tagName("td"));

        if (col != 2) {
            return columns.get(col).findElement(By.tagName("span"));
        } else {
            return columns.get(2).findElement(By.className("ui-cell-data")).findElement(By.tagName("span"));
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
