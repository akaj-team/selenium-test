package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class LeaveRequestPage extends BasePage<LeaveRequestPage> {
    @FindBy(css = ".ng-tns-c1-22.ui-dropdown.ui-widget.ui-state-default.ui-corner-all.ui-helper-clearfix")
    private WebElement typeOfLeave;

    @FindBy(css = ".ng-tns-c2-23.ui-calendar")
    private WebElement calendarFrom;

    @FindBy(css = ".ng-tns-c2-26.ui-calendar")
    private WebElement calendarTo;

    @FindBy(css = ".table.table-striped.table-vm.has-error")
    private WebElement tableDateRequest;

    private WebElement tableDateRequestData = tableDateRequest.findElement(By.tagName("tbody"));

    @FindBy(id = "cke_1_contents")
    private WebElement ckeContents;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement leaveBalance;

    @FindBy(css = ".btn.btn-primary.btn-submit")
    private WebElement submitButton;

    private WebElement contenteditable = ckeContents.findElement(By.cssSelector(".cke_editable.cke_editable_themed.cke_contents_ltr.cke_show_borders"));

    @Override
    public LeaveRequestPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/request");
        return this;
    }

    public boolean checkTextAnnualLeave(WebDriver driver, String annualLeave) {
        waitForElementDisplay(driver, leaveBalance, 10);
        return findLeaveBalance(0).getText().equals(annualLeave);
    }

    public boolean checkTextMarriageLeave(WebDriver driver, String marriageLeave) {
        waitForElementDisplay(driver, leaveBalance, 10);
        return findLeaveBalance(1).getText().equals(marriageLeave);
    }

    public boolean checkTextOvertimeLeave(WebDriver driver, String overTimeLeave) {
        waitForElementDisplay(driver, leaveBalance, 10);
        return findLeaveBalance(2).getText().equals(overTimeLeave);
    }

    public boolean checkTextPaternalLeave(WebDriver driver, String paternalLeave) {
        waitForElementDisplay(driver, leaveBalance, 10);
        return findLeaveBalance(3).getText().equals(paternalLeave);
    }

    public void withMessage() {
        String message = "Gui A Tien";
        contenteditable.findElement(By.tagName("p")).sendKeys(message);
    }

    public boolean isEnableSubmitButton() {
        return submitButton.isEnabled();
    }

    private WebElement findDataDate(int row, int col) {
        List<WebElement> rows = tableDateRequestData.findElements(By.tagName("tr"));
        WebElement item = tableDateRequest.findElement(By.cssSelector(".ui-radiobutton-icon.ui-clickable"));
        for (int i = 0; i < rows.size(); i++) {
            if (i == row) {
                List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
                for (int j = 0; j < columns.size(); j++) {
                    if (j == col) {
                        if (col != 0 && col != 4) {
                            WebElement divs = columns.get(j).findElement(By.tagName("p-radiobutton"))
                                    .findElement(By.cssSelector("ui-radiobutton ui-widget"));
                            item = divs.findElements(By.tagName("div")).get(1);
                        } else {
                            if (col == 4) {
                                item = columns.get(j).findElement(By.tagName("button"));
                            } else {
                                item = columns.get(j);
                            }
                        }
                        break;
                    }
                }
            }
        }
        return item;
    }

    private WebElement findLeaveBalance(int pos) {
        WebElement item = leaveBalance.findElement(By.tagName("dd"));
        List<WebElement> balances = leaveBalance.findElements(By.tagName("dd"));
        for (int i = 0; i < balances.size(); i++) {
            if (i == pos) {
                item = balances.get(i).findElement(By.tagName("span"));
                break;
            }
        }
        return item;
    }

}
