package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class LeaveRequestPage extends BasePage<LeaveRequestPage> {

    @FindBy(xpath = "//p-dropdown[contains(@class,'ui-inputwrapper-filled')]")
    private WebElement inputTypeOfLeave;

    @FindBy(css = ".ng-tns-c2-2.ui-calendar")
    private WebElement inputTimeFrom;

    @FindBy(css = ".ng-tns-c2-5.ui-calendar")
    private WebElement inputTimeTo;

    @FindBy(xpath = "//table[contains(@class,'ng-tns-c2-2')]")
    private WebElement calendarTimeFrom;

    @FindBy(xpath = "//table[contains(@class,'ng-tns-c2-5')]")
    private WebElement calendarTimeTo;

    @FindBy(css = ".table.table-striped.table-vm.has-error")
    private WebElement tableDateRequest;

    @FindBy(id = "cke_1_contents")
    private WebElement inputMessage;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement tableLeaveBalance;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private WebElement btnSubmit;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement menuTypeOfLeave;

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
        waitForElementDisplay(driver, tableLeaveBalance, 10);
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
        inputTypeOfLeave.click();
    }

    public boolean isMenuDropDown() {
        return menuTypeOfLeave.isDisplayed();
    }

    public void clickItemMenuType(WebDriver driver, String status) {
        waitForElementDisplay(driver, menuTypeOfLeave, 10);

        WebElement itemStatus = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        if (status.equals("Marriage Leave")) {
            itemStatus = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        }
        if (status.equals("None Paid")) {
            itemStatus = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
        }
        if (status.equals("Overtime Leave")) {
            itemStatus = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(3);
        }
        if (status.equals("Paternal Leave")) {
            itemStatus = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(4);
        }
        itemStatus.click();
    }

    public void withMessage(WebDriver driver) {
        String message = "Gui A Tien";

        waitForElementDisplay(driver, inputMessage, 10);

        driver.switchTo().frame(driver.findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));

        driver.findElement(By.tagName("body")).sendKeys(message);

        driver.switchTo().defaultContent();
    }

    public boolean isEnableSubmitButton() {
        return btnSubmit.isEnabled();
    }

    public boolean isShowMessage(String mess) {
        return messageTypeOfLeave.isDisplayed() && messageTypeOfLeave.getText().equals(mess);
    }

    public void clickTimeFromBox() {
        inputTimeFrom.click();
    }

    public void clickTimeToBox() {
        inputTimeTo.click();
    }

    public void chooseTime(String type) {
        java.util.Date date = new java.util.Date();
        findDayLeave(type, date.getDate() + "");
    }

    public boolean isCalendarShow(String type) {
        if (type.equals("timeFrom")) {
            return calendarTimeFrom.isDisplayed();
        } else {
            return calendarTimeTo.isDisplayed();
        }
    }

    public boolean isDateRequestShow(WebDriver driver) {
        waitForElementDisplay(driver, tableDateRequest, 10);
        return true;
    }

    public void setNonePaidInTypeOfLeave(WebDriver driver, String status) {
        clickMenuTypeOfLeave();
        clickItemMenuType(driver, status);
    }

    public boolean isRemoveDateRequest() {
        try {
            tableDateRequest.isDisplayed();
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
        waitForElementDisplay(driver, tableDateRequest, 10);
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));

        List<WebElement> rows = data.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.cssSelector(".ui-radiobutton.ui-widget"));

        return columns.get(col);
    }

    public boolean checkDateInDateRequest(int row, String date) {
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        return rows.get(row).findElement(By.className("text-left")).getText().equals(date);
    }

    public void clickRemoveButton(int row) {
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));
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
        btnSubmit.click();
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

    public void redirectLeaveDetail() {
        // To-do later
    }

    private WebElement findLeaveBalance(int pos) {
        List<WebElement> balances = tableLeaveBalance.findElements(By.tagName("dd"));
        return balances.get(pos).findElement(By.tagName("span"));
    }

    private void findDayLeave(String type, String day) {
        WebElement data;
        WebElement item = null;
        if (type.equals("timeTo")) {
            data = calendarTimeTo.findElement(By.tagName("tbody"));
        } else {
            data = calendarTimeFrom.findElement(By.tagName("tbody"));
        }

        List<WebElement> rows = data.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                if (col.findElement(By.tagName("a")).getText().equals(day)) {
                    item = col.findElement(By.tagName("a"));
                    break;
                }
            }
            if (item != null) {
                break;
            }
        }
        if (item != null) {
            item.click();
        }
    }
}
