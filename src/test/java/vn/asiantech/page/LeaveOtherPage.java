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
    @FindBy(className = "dropdown-lg")
    private WebElement itemTime;
    @FindBy(id = "page-wrapper")
    private WebElement mainBody;
    @FindBy(css = ".ui-datatable.ui-widget.ui-datatable-scrollable")
    private WebElement tableData;

    private static final String FORMAT_SLASH_MARK = "MMM dd, yyyy";

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
        itemTime.findElements(By.tagName("p-calendar")).get(0).click();
    }

    public void clickItemEndDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(1).click();
    }

    public boolean checkShowStartDateDialog() {
        return itemTime.findElements(By.tagName("p-calendar")).get(0).findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public boolean checkShowEndDateDialog() {
        return itemTime.findElements(By.tagName("p-calendar")).get(1).findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public void chooseStartDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(0).findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(5).click();
    }

    public void chooseEndDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(1).findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(4).findElements(By.tagName("td")).get(3).click();
    }

    public boolean compareTwoDate() {
        Date startDate = convertStringToDate(itemTime.findElements(By.tagName("p-calendar")).get(0).findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getAttribute("value"));
        Date endDate = convertStringToDate(itemTime.findElements(By.tagName("p-calendar")).get(1).findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getAttribute("value"));
        return startDate != null && endDate != null && startDate.compareTo(endDate) > 0;
    }

    public boolean checkShowErrorMessage(String message) {
        WebElement errorMessage = mainBody.findElement(By.tagName("app-error")).findElement(By.cssSelector(".app-alert.ng-star-inserted"));
        return errorMessage.isDisplayed() && errorMessage.getText().trim().contains(message);
    }

    public boolean checkDataEmpty() {
        return tableData.findElement(By.tagName("p-paginator")).findElements(By.tagName("div")).size() == 0;
    }

    public boolean checkShowMessageEmpty(String message) {
        return tableData.findElement(By.className("ui-datatable-scrollable-table-wrapper")).findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElement(By.tagName("td")).findElement(By.tagName("span")).getText().trim().equals(message);
    }

    public void clickEmployerId() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(0);
        WebElement employerId = itemFirst.findElements(By.tagName("td")).get(0);
        employerId.findElement(By.className("ng-star-inserted")).click();
    }

    public String getPathPageRedirect(Integer position) {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(0);
        WebElement employerId = itemFirst.findElements(By.tagName("td")).get(position);
        return employerId.findElement(By.tagName("span")).findElement(By.tagName("a")).getAttribute("href");
    }

    public void clickEmployerName() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(0);
        WebElement employerName = itemFirst.findElements(By.tagName("td")).get(1);
        employerName.findElement(By.className("ng-star-inserted")).findElement(By.className("info-grouping")).click();
    }

    public void clickSearchIcon() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(0);
        WebElement searchIcon = itemFirst.findElements(By.tagName("td")).get(8);
        searchIcon.findElement(By.cssSelector(".view.ng-star-inserted")).click();
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
