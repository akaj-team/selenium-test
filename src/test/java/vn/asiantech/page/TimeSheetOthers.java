package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class TimeSheetOthers extends BasePage<TimeSheetOthers> {
    @FindBy(css = ".btn.btn-sm.btn-white")
    private List<WebElement> btnThisWeek;
    @FindBy(css = ".btn.control-item.prev")
    private WebElement btnBack;
    @FindBy(css = ".btn.control-item.next")
    private WebElement btnNext;
    @FindBy(className = "ui-button-text-only")
    private WebElement defaultGroupBy;
    @FindBy(xpath = "//div[contains(@class, 'ui-multiselect-label-container') and contains(@title, 'All Subordinator')]")
    private WebElement dropDownSubordinator;
    @FindBy(xpath = "//div[contains(@class, 'ui-multiselect-label-container') and contains(@title, 'All Status')]")
    private WebElement dropDownStatus;
    @FindBy(className = "ui-multiselect-items-wrapper")
    private WebElement CheckBoxGroupStatus;
    @FindBy(xpath = "//div[contains(@class, 'ui-state-default') and contains(@class ,'ui-state-active')]")
    private WebElement selectedTab;
    @FindBy(className = "label-normal")
    private WebElement labelSubmitted;
    @FindBy(className = "label-success")
    private WebElement labelApproved;
    @FindBy(className = "label-danger")
    private WebElement labelRejected;
    @FindBy(tagName = "h2")
    private WebElement timeSheetTile;
    @FindBy(id = "page-wrapper")
    private WebElement pageWrapper;


    @Override
    public TimeSheetOthers navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/timesheet/approval");
        return this;
    }

    public WebElement getThisWeekButton(WebDriver driver) {
        waitForListElement(driver, btnThisWeek, 10);
        return btnThisWeek.get(0);
    }

    public WebElement getNextButton(WebDriver driver) {
        waitForElement(driver, btnNext, 10);
        return btnNext;
    }

    public WebElement getPreButton(WebDriver driver) {
        waitForElement(driver, btnBack, 10);
        return btnBack;
    }

    public WebElement getDefaultGroupBy(WebDriver driver) {
        waitForElement(driver, defaultGroupBy, 10);
        return defaultGroupBy;
    }

    public WebElement getDropDownStatus(WebDriver driver) {
        waitForElement(driver, dropDownStatus, 10);
        return dropDownStatus;
    }

    public WebElement getCheckBoxGroupStatus() {
        return CheckBoxGroupStatus;
    }

    public TimeSheetOthers clickThisWeek() {
        btnThisWeek.get(0).click();
        return this;
    }

    public TimeSheetOthers clickNext() {
        btnNext.click();
        return this;
    }

    public TimeSheetOthers clickPre() {
        btnBack.click();
        return this;
    }

    public WebElement getSelectedTab(WebDriver driver) {
        waitForElement(driver, selectedTab, 10);
        return selectedTab;
    }

    public WebElement getLabelSubmitted(WebDriver driver) {
        waitForElement(driver, labelSubmitted, 10);
        return labelSubmitted;
    }

    public WebElement getDropDownSubordinator(WebDriver driver) {
        waitForElement(driver, dropDownSubordinator, 10);
        return dropDownSubordinator;
    }

    public WebElement getLabelApproved(WebDriver driver) {
        waitForElement(driver, labelApproved, 10);
        return labelApproved;
    }

    public WebElement getLabelRejected(WebDriver driver) {
        waitForElement(driver, labelRejected, 10);
        return labelRejected;
    }

    public WebElement getTimeSheetTile(WebDriver driver) {
        waitForElement(driver, timeSheetTile, 10);
        return timeSheetTile;
    }
}
