package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author at-hanhnguyen
 */
public class LeaveOtherPage extends BasePage<LeaveOtherPage> {

    @FindBy(css = ".ibox-content.main-content")
    private WebElement iboxContent;
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
    @FindBy(css = ".ui-widget-content.ui-datatable-emptymessage-row.ng-star-inserted")
    private WebElement trEmptyMessage;
    @FindBy(css = ".ui-paginator-last.ui-paginator-element.ui-state-default.ui-corner-all")
    private WebElement btnLast;

    private static final String FORMAT_SLASH_MARK = "MMM dd, yyyy";
    private static final int TIME_SLEEP = 300; // millisecond
    private static final int TIME_SLEEP_CLICK = 1000; // millisecond

    private static final int CALENDAR_START_POS = 0;
    private static final int CALENDAR_END_POS = 1;
    private static final int LINE_OF_CALENDAR_POS = 3;
    private static final int CALENDAR_START_ITEM_CLICK_POS = 5;
    private static final int CALENDAR_END_ITEM_CLICK_POS = 3;

    private static final int FIRST_ITEM_POS = 0;
    private static final int EMPLOYER_ID_POS = 0;
    private static final int EMPLOYER_NAME_POS = 1;
    private static final int ICON_SEARCH_POS = 8;

    private static final int MAX_ITEM_ON_A_PAGE = 50;
    private static final int MAX_PAGE_COUNT_ON_BOTTOM = 5;

    @Override
    public final LeaveOtherPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void waitForLoadData(final WebDriver driver) {
        waitForElement(driver, iboxContent);
    }

    public final void clickStatusItem() {
        statusItem.click();
    }

    public final boolean checkShowStatusDropdown() {
        return statusItem.findElement(By.cssSelector(".ui-dropdown-panel.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-panelState")).isDisplayed();
    }

    public final void clickGroupByItem() {
        groupByItem.click();
    }

    public final boolean checkShowGroupByItem() {
        return groupByItem.findElement(By.cssSelector(".ui-dropdown-panel.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-panelState")).isDisplayed();
    }

    public final void clickItemStartDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_START_POS).click();
    }

    public final void clickItemEndDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_END_POS).click();
    }

    public final boolean checkShowStartDateDialog() {
        return itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_START_POS).findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public final boolean checkShowEndDateDialog() {
        return itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_END_POS).findElement(By.cssSelector(".ui-datepicker.ui-widget.ui-widget-content.ui-helper-clearfix.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-overlayState")).isDisplayed();
    }

    public final void chooseStartDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_START_POS).findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(LINE_OF_CALENDAR_POS).findElements(By.tagName("td")).get(CALENDAR_START_ITEM_CLICK_POS).click();
    }

    public final void chooseEndDate() {
        itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_END_POS).findElement(By.cssSelector(".ui-datepicker-calendar.ng-star-inserted")).findElement(By.tagName("tbody")).findElements(By.tagName("tr")).get(LINE_OF_CALENDAR_POS).findElements(By.tagName("td")).get(CALENDAR_END_ITEM_CLICK_POS).click();
    }

    public final boolean compareTwoDate() {
        Date startDate = convertStringToDate(itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_START_POS).findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getAttribute("value"));
        Date endDate = convertStringToDate(itemTime.findElements(By.tagName("p-calendar")).get(CALENDAR_END_POS).findElement(By.cssSelector(".ui-inputtext.ui-widget.ui-state-default.ui-corner-all.ng-star-inserted")).getAttribute("value"));
        return startDate != null && endDate != null && startDate.compareTo(endDate) > 0;
    }

    public final boolean checkShowErrorMessage(final String message) {
        WebElement errorMessage = mainBody.findElement(By.tagName("app-error")).findElement(By.cssSelector(".app-alert.ng-star-inserted"));
        return errorMessage.isDisplayed() && errorMessage.getText().trim().contains(message);
    }

    public final boolean checkDataEmpty() {
        return isElementPresented(trEmptyMessage);
    }

    public final void clickEmployerId() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(FIRST_ITEM_POS);
        WebElement employerId = itemFirst.findElements(By.tagName("td")).get(EMPLOYER_ID_POS);
        employerId.findElement(By.className("ng-star-inserted")).click();
        try {
            Thread.sleep(TIME_SLEEP_CLICK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final String getPathPageRedirect(final Integer position) {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(FIRST_ITEM_POS);
        WebElement employerId = itemFirst.findElements(By.tagName("td")).get(position);
        return employerId.findElement(By.tagName("span")).findElement(By.tagName("a")).getAttribute("href");
    }

    public final void clickEmployerName() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(FIRST_ITEM_POS);
        WebElement employerName = itemFirst.findElements(By.tagName("td")).get(EMPLOYER_NAME_POS);
        employerName.findElement(By.className("ng-star-inserted")).findElement(By.className("info-grouping")).click();
        try {
            Thread.sleep(TIME_SLEEP_CLICK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void clickSearchIcon() {
        WebElement itemFirst = tableData.findElement(By.cssSelector(".ui-datatable-data.ui-widget-content")).findElements(By.tagName("tr")).get(FIRST_ITEM_POS);
        WebElement searchIcon = itemFirst.findElements(By.tagName("td")).get(ICON_SEARCH_POS);
        searchIcon.findElement(By.cssSelector(".view.ng-star-inserted")).click();
        try {
            Thread.sleep(TIME_SLEEP_CLICK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final int countDataItem(final WebDriver driver) {
        String lineCountPage = driver.findElement(By.cssSelector(".show-entry.ng-star-inserted")).getText();
        String countPage = lineCountPage.substring(lineCountPage.indexOf("of") + 2, lineCountPage.indexOf("entries"));
        return Integer.valueOf(countPage.trim());
    }

    public final boolean checkButtonNextEndPageShown(final int countItem) {
        return countItem / MAX_ITEM_ON_A_PAGE > MAX_PAGE_COUNT_ON_BOTTOM || (countItem / MAX_ITEM_ON_A_PAGE == MAX_PAGE_COUNT_ON_BOTTOM && countItem % MAX_ITEM_ON_A_PAGE > 0);
    }

    public final void clickButtonLast() {
        btnLast.click();
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final String getPathLastPage(final int countItem) {
        int numberPage = countItem / MAX_ITEM_ON_A_PAGE;
        if (countItem % MAX_ITEM_ON_A_PAGE > 0) {
            numberPage = numberPage + 1;
        }
        return Constant.LEAVE_TRACKING_PAGE_URL + ";page=" + numberPage;
    }

    private Date convertStringToDate(final String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SLASH_MARK, Locale.getDefault());
        try {
            return sdf.parse(inputDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
