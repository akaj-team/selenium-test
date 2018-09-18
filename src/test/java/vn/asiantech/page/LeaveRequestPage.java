package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.Calendar;
import java.util.List;

public class LeaveRequestPage extends BasePage<LeaveRequestPage> {

    @FindBy(xpath = "//p-dropdown[contains(@class,'ui-inputwrapper-filled')]")
    private WebElement typeOfLeaveInput;

    @FindBy(css = ".ng-tns-c2-2.ui-calendar")
    private WebElement timeFromInput;

    @FindBy(css = ".ng-tns-c2-5.ui-calendar")
    private WebElement timeToInput;

    @FindBy(xpath = "//table[contains(@class,'ng-tns-c2-2')]")
    private WebElement timeFromCalendar;

    @FindBy(xpath = "//table[contains(@class,'ng-tns-c2-5')]")
    private WebElement timeToCalendar;

    @FindBy(css = ".table.table-striped.table-vm.has-error")
    private WebElement dateRequestTable;

    @FindBy(id = "cke_1_contents")
    private WebElement messageInput;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement leaveBalanceTable;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private WebElement submitButton;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement typeOfLeaveMenu;

    @FindBy(css = ".text-center.m-t-xs.ng-star-inserted")
    private WebElement messageTypeOfLeave;

    @FindBy(tagName = "p-dialog")
    private WebElement dialog;

    @Override

    public LeaveRequestPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/request");
        return this;
    }

    public boolean checkTextAnnualLeave(WebDriver driver, String annualLeave) {
        waitForElementDisplay(driver, leaveBalanceTable, 10);
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

    public void clickMenuTypeOfLeave() {
        typeOfLeaveInput.click();
    }

    public boolean isMenuDropDown() {
        return typeOfLeaveMenu.isDisplayed();
    }

    public void clickItemMenuType(WebDriver driver, String status) {
        waitForElementDisplay(driver, typeOfLeaveMenu, 10);

        WebElement itemStatus = typeOfLeaveMenu.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        if (status.equals("Marriage Leave")) {
            itemStatus = typeOfLeaveMenu.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        }
        if (status.equals("None Paid")) {
            itemStatus = typeOfLeaveMenu.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
        }
        if (status.equals("Overtime Leave")) {
            itemStatus = typeOfLeaveMenu.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(3);
        }
        if (status.equals("Paternal Leave")) {
            itemStatus = typeOfLeaveMenu.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(4);
        }
        itemStatus.click();
    }

    public void withMessage(WebDriver driver) {
        String message = "Gui A Tien";

        waitForElementDisplay(driver, messageInput, 10);

        driver.switchTo().frame(driver.findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));

        driver.findElement(By.tagName("body")).sendKeys(message);

        driver.switchTo().defaultContent();
    }

    public boolean isEnableSubmitButton() {
        return submitButton.isEnabled();
    }

    public boolean isShowMessage(String mess) {
        return messageTypeOfLeave.isDisplayed() && messageTypeOfLeave.getText().equals(mess);
    }

    public void clickTimeFromBox() {
        timeFromInput.click();
    }

    public void clickTimeToBox() {
        timeToInput.click();
    }

    public void chooseTime(String type) {
        findDayLeave(type);
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
    }

    public boolean isCalendarShow(String type) {
        if (type.equals("timeFrom")) {
            return timeFromCalendar.isDisplayed();
        } else {
            return timeToCalendar.isDisplayed();
        }
    }

    public boolean isDateRequestShow(WebDriver driver) {
        waitForElementDisplay(driver, dateRequestTable, 10);
        return true;
    }

    public void setNonePaidInTypeOfLeave(WebDriver driver, String status) {
        clickMenuTypeOfLeave();
        clickItemMenuType(driver, status);
    }

    public boolean isRemoveDateRequest() {
        try {
            dateRequestTable.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean chooseAfternoon(WebDriver driver) {
        findRadioButtonDateRequest(driver, 1).click();
        return findRadioButtonDateRequest(driver, 1).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public boolean chooseMorning(WebDriver driver) {
        findRadioButtonDateRequest(driver, 0).click();
        return findRadioButtonDateRequest(driver, 0).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public boolean chooseAllday(WebDriver driver) {
        findRadioButtonDateRequest(driver, 2).click();
        return findRadioButtonDateRequest(driver, 2).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    private WebElement findRadioButtonDateRequest(WebDriver driver, int col) {
        waitForElementDisplay(driver, dateRequestTable, 10);
        WebElement data = dateRequestTable.findElement(By.tagName("tbody"));

        List<WebElement> rows = data.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.cssSelector(".ui-radiobutton.ui-widget"));

        return columns.get(col);
    }

    public boolean checkDateInDateRequest(int row, String date) {
        WebElement data = dateRequestTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        return rows.get(row).findElement(By.className("text-left")).getText().equals(date);
    }

    public void clickRemoveButton(int row) {
        WebElement data = dateRequestTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        rows.get(row).findElement(By.className("delete")).click();
    }

    public void enterFullInfor(WebDriver driver) {
        setNonePaidInTypeOfLeave(driver, "None Paid");
        clickTimeFromBox();
        chooseTime("timeFrom");
        clickTimeToBox();
        chooseTime("timeTo");
        withMessage(driver);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public boolean isShowDialog() {
        return dialog.isDisplayed();
    }

    public void showDialog(WebDriver driver) {
        enterFullInfor(driver);
        clickSubmit();
    }

    public void clickSubmitButtonInDialog() {
        WebElement submitButton = dialog.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
        submitButton.click();
    }

    public void clickCancelButtonInDialog() {
        WebElement cancelButton = dialog.findElement(By.xpath("//button[contains(@class,'btn-cancel')]"));
        cancelButton.click();
    }

    public boolean isDialogDisappeared() {
        try {
            dialog.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void display(WebDriver driver) {
        WebElement title = driver.findElement(By.className("col-sm-8"));
        System.out.println(title.getText());
    }

    private WebElement findLeaveBalance(int pos) {
        List<WebElement> balances = leaveBalanceTable.findElements(By.tagName("dd"));
        return balances.get(pos).findElement(By.tagName("span"));
    }

    private void findDayLeave(String type) {
        WebElement data;
        if (type.equals("timeTo")) {
            data = timeToCalendar.findElement(By.tagName("tbody"));
        } else {
            data = timeFromCalendar.findElement(By.tagName("tbody"));
        }

        List<WebElement> rows = data.findElements(By.tagName("tr"));
        List<WebElement> cols = rows.get(3).findElements(By.tagName("td"));

        cols.get(1).findElement(By.tagName("a")).click();
    }
}
