package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.DriverBase.getDriver;

/**
 * @author at-anh-quach
 */
public class LeaveRequestPage extends BasePage<LeaveRequestPage> {
    private static final int TIMEOUTINSECONDS = 5;

    @FindBy(xpath = "//label[contains(@class,'ui-inputtext')]")
    private WebElement inputTypeOfLeave;

    @FindBy(css = ".input-daterange.input-group")
    private WebElement inputDateRange;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar')]")
    private WebElement calendarTimeFrom;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar')]")
    private WebElement calendarTimeTo;

    @FindBy(css = ".table.table-striped.table-vm.has-error")
    private WebElement tableDateRequest;

    @FindBy(id = "cke_1_contents")
    private WebElement inputMessage;

    @FindBy(id = "leave-balance-remain-wrapper")
    private WebElement tableLeaveBalance;

    @FindBy(id = "btn-submit-leave")
    private WebElement btnSubmit;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement menuTypeOfLeave;

    @FindBy(css = ".text-center.m-t-xs.ng-star-inserted")
    private WebElement messageTypeOfLeave;

    @FindBy(id = "static-dialog-wrapper")
    private WebElement dialog;

    @FindBy(id = "btn-link-to-my-leave")
    private WebElement btnMyLeave;


    @Override

    public final LeaveRequestPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/request");
        return this;
    }

    public final boolean checkTextAnnualLeave(final String annualLeave) {
        waitForElementDisplay(getDriver(), tableLeaveBalance, TIMEOUTINSECONDS);
        return findLeaveBalance(0).getText().equals(annualLeave);
    }

    public final boolean checkTextMarriageLeave(final String marriageLeave) {
        return findLeaveBalance(1).getText().equals(marriageLeave);
    }

    public final boolean checkTextOvertimeLeave(final String overTimeLeave) {
        return findLeaveBalance(2).getText().equals(overTimeLeave);
    }

    public final boolean checkTextPaternalLeave(final String paternalLeave) {
        return findLeaveBalance(3).getText().equals(paternalLeave);
    }

    public final void clickMenuTypeOfLeave() {
        inputTypeOfLeave.click();
    }

    public final boolean isMenuDropDown() {
        return menuTypeOfLeave.isDisplayed();
    }

    public final void clickItemMenuType(final String status) {
        waitForElementDisplay(getDriver(), menuTypeOfLeave, TIMEOUTINSECONDS);

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

    public final void withMessage() {
        String message = "Gui A Tien";

        waitForElementDisplay(getDriver(), inputMessage, TIMEOUTINSECONDS);

        getDriver().switchTo().frame(getDriver().findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));

        getDriver().findElement(By.tagName("body")).sendKeys(message);

        getDriver().switchTo().defaultContent();
    }

    public final boolean isEnableSubmitButton() {
        return btnSubmit.isEnabled();
    }

    public final boolean isShowMessage(final String mess) {
        return messageTypeOfLeave.isDisplayed() && messageTypeOfLeave.getText().equals(mess);
    }

    public final void clickTimeFromBox() {
        inputDateRange.findElements(By.tagName("p-calendar")).get(0).click();
    }

    public final void clickTimeToBox() {
        inputDateRange.findElements(By.tagName("p-calendar")).get(1).click();
    }

    public final void chooseTime(final String type) {
        java.util.Date date = new java.util.Date();
        findDayLeave(type, date.getDate() + "");
    }

    public final boolean isCalendarShow(final String type) {
        if (type.equals("timeFrom")) {
            return calendarTimeFrom.isDisplayed();
        } else {
            return calendarTimeTo.isDisplayed();
        }
    }

    public final boolean isDateRequestShow() {
        waitForElementDisplay(getDriver(), tableDateRequest, TIMEOUTINSECONDS);
        return true;
    }

    public final void setNonePaidInTypeOfLeave(final String status) {
        clickMenuTypeOfLeave();
        clickItemMenuType(status);
    }

    public final boolean isRemoveDateRequest() {
        try {
            tableDateRequest.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public final boolean chooseAfternoon() {
        findRadioButtonDateRequest(1).click();
        return findRadioButtonDateRequest(1).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public final boolean chooseMorning() {
        findRadioButtonDateRequest(0).click();
        return findRadioButtonDateRequest(0).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public final boolean chooseAllday() {
        findRadioButtonDateRequest(2).click();
        return findRadioButtonDateRequest(2).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public final boolean checkDateInDateRequest(final int row, final String date) {
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        return rows.get(row).findElement(By.className("text-left")).getText().equals(date);
    }

    public final void clickRemoveButton(final int row) {
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        rows.get(row).findElement(By.className("delete")).click();
    }

    public final void enterFullInfor() {
        setNonePaidInTypeOfLeave("None Paid");
        clickTimeFromBox();
        chooseTime("timeFrom");
        clickTimeToBox();
        chooseTime("timeTo");
        withMessage();
    }

    public final void clickSubmit() {
        btnSubmit.click();
    }

    public final boolean isShowDialog() {
        return dialog.isDisplayed();
    }

    public final void showDialog() {
        enterFullInfor();
        clickSubmit();
    }

    public final void clickSubmitButtonInDialog() {
        WebElement submitButton = dialog.findElement(By.xpath("//button[contains(@class,'btn-submit')]"));
        submitButton.click();
    }

    public final void clickCancelButtonInDialog() {
        WebElement cancelButton = dialog.findElement(By.xpath("//button[contains(@class,'btn-cancel')]"));
        cancelButton.click();
    }

    public final boolean isDialogDisappeared() {
        try {
            dialog.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public final void redirectLeaveDetail() {
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

    private WebElement findRadioButtonDateRequest(int col) {
        waitForElementDisplay(getDriver(), tableDateRequest, TIMEOUTINSECONDS);
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));

        List<WebElement> rows = data.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.cssSelector(".ui-radiobutton.ui-widget"));

        return columns.get(col);
    }
}
