package vn.asiantech.page.leave;

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
    private static final int ANNUAL_LEAVE = 0;
    private static final int MARRIAGE_LEAVE = 1;
    private static final int OVERTIME_LEAVE = 2;
    private static final int PATERNAL_LEAVE = 3;
    private static final int NONE_PAID = 2;

    private static final int POS_AFTERNOON = 1;
    private static final int POS_MORNING = 0;
    private static final int POS_ALL_DAY = 2;
    private static final int POS_TIME_FROM = 0;
    private static final int POS_TIME_TO = 1;

    @FindBy(className = "fa-caret-down")
    private WebElement inputTypeOfLeave;

    @FindBy(css = ".input-daterange.input-group")
    private WebElement inputDateRange;

    @FindBy(xpath = "//table[contains(@class,'ui-datepicker-calendar')]")
    private WebElement calendarTime;

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

    @Override

    public final LeaveRequestPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final boolean checkTextAnnualLeave(final String annualLeave) {
        waitForElement(getDriver(), tableLeaveBalance);
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

    public final void clickMenuTypeOfLeave() {
        inputTypeOfLeave.click();
    }

    public final boolean isMenuDropDown() {
        return menuTypeOfLeave.isDisplayed();
    }

    public final void clickItemMenuType(final String status) {
        waitForElement(getDriver(), menuTypeOfLeave);
        List<WebElement> items = menuTypeOfLeave.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        WebElement itemStatus = items.get(ANNUAL_LEAVE);

        if (status.equals("Marriage Leave")) {
            itemStatus = items.get(MARRIAGE_LEAVE);
        }
        if (status.equals("None Paid")) {
            itemStatus = items.get(NONE_PAID);
        }
        if (status.equals("Overtime Leave")) {
            itemStatus = items.get(OVERTIME_LEAVE + 1);
        }
        if (status.equals("Paternal Leave")) {
            itemStatus = items.get(PATERNAL_LEAVE + 1);
        }
        itemStatus.click();
    }

    public final void withMessage() {
        String message = "Gui A Tien";
        waitForElement(getDriver(), inputMessage);

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
        inputDateRange.findElements(By.tagName("p-calendar")).get(POS_TIME_FROM).click();
    }

    public final void clickTimeToBox() {
        inputDateRange.findElements(By.tagName("p-calendar")).get(POS_TIME_TO).click();
    }

    public final void chooseTime() {
        java.util.Date date = new java.util.Date();
        findDayLeave(date.getDate() + "");
    }

    public final boolean isCalendarShow() {
        return calendarTime.isDisplayed();
    }

    public final boolean isDateRequestShow() {
        waitForElement(getDriver(), tableDateRequest);
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
        findRadioButtonDateRequest(POS_AFTERNOON).click();
        return findRadioButtonDateRequest(POS_AFTERNOON).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public final boolean chooseMorning() {
        findRadioButtonDateRequest(POS_MORNING).click();
        return findRadioButtonDateRequest(POS_MORNING).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
    }

    public final boolean chooseAllDay() {
        findRadioButtonDateRequest(POS_ALL_DAY).click();
        return findRadioButtonDateRequest(POS_ALL_DAY).findElement(By.xpath("//div[contains(@class,'ui-state-active')]")).isDisplayed();
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

    public final void enterFullInfo() {
        setNonePaidInTypeOfLeave("None Paid");
        clickTimeFromBox();
        chooseTime();
        clickTimeToBox();
        chooseTime();
        withMessage();
    }

    public final void clickSubmit() {
        btnSubmit.click();
    }

    public final boolean isShowDialog() {
        return dialog.isDisplayed();
    }

    public final void showDialog() {
        enterFullInfo();
        clickSubmit();
    }

    public final void clickSubmitButtonInDialog() {
        WebElement submitButton = dialog.findElement(By.className("btn-submit"));
        submitButton.click();
    }

    public final void clickCancelButtonInDialog() {
        WebElement cancelButton = dialog.findElement(By.className("btn-cancel"));
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

    private WebElement findLeaveBalance(final int pos) {
        List<WebElement> balances = tableLeaveBalance.findElements(By.tagName("dd"));
        return balances.get(pos).findElement(By.tagName("span"));
    }

    private void findDayLeave(final String day) {
        WebElement data;
        WebElement item = null;
        data = calendarTime.findElement(By.tagName("tbody"));
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

    private WebElement findRadioButtonDateRequest(final int col) {
        waitForElement(getDriver(), tableDateRequest);
        WebElement data = tableDateRequest.findElement(By.tagName("tbody"));
        List<WebElement> rows = data.findElements(By.tagName("tr"));
        List<WebElement> columns = rows.get(0).findElements(By.cssSelector(".ui-radiobutton.ui-widget"));
        return columns.get(col);
    }
}
