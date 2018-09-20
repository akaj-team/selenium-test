package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LeaveOtherPage {
    @FindBy(className = "dropdown-sm")
    private WebElement statusItem;
    @FindBy(className = "dropdown-md")
    private WebElement groupByItem;
    @FindBy(name = "start")
    private WebElement itemDateStart;
    @FindBy(name = "end")
    private WebElement itemDateEnd;
    @FindBy(id = "page-wrapper")
    private WebElement mainBody;

    private static final String FORMAT_SLASH_MARK = "yyyy/MM/dd";

    public LeaveOtherPage() {
        //no-up
    }

    public void clickStatusItem() {
        statusItem.click();
    }

    public boolean checkShowStatusDropdown() {
        return statusItem.findElement(By.cssSelector(".ui-dropdown-panel.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-panelState")).isDisplayed();
    }

    public void clickGroupByItem() {
        groupByItem.click();
    }

    public boolean checkShowGroupByItem() {
        return groupByItem.findElement(By.cssSelector(".ui-dropdown-panel.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-panelState")).isDisplayed();
    }

    public void clickItemStartDate() {
        itemDateStart.click();
    }

    public void clickItemEndDate() {
        itemDateEnd.click();
    }

    public boolean checkShowStartDateDialog() {
        return itemDateStart.findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public boolean checkShowEndDateDialog() {
        return itemDateEnd.findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public void chooseStartDate() {
        itemDateStart.findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(5).click();
    }

    public void chooseEndDate() {
        itemDateEnd.findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(3).click();
    }

    public boolean compareTwoDate() {
        Date startDate = convertStringToDate(itemDateStart.findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getText());
        Date endDate = convertStringToDate(itemDateEnd.findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getText());
        return startDate != null && endDate != null && startDate.compareTo(endDate) > 0;
    }

    public boolean checkShowErrorMessage(String message) {
        WebElement errorMessage = mainBody.findElement(By.tagName("app-error")).findElement(By.cssSelector(".app-alert.ng-star-inserted"));
        return errorMessage.isDisplayed() && errorMessage.getText().trim().equals(message);
    }

    private Date convertStringToDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SLASH_MARK, Locale.getDefault());
        try {
            return sdf.parse(inputDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
