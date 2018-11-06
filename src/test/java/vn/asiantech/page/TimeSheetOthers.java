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
    @FindBy(css = ".ui-multiselect-item.ui-corner-all.ng-star-inserted")
    private List<WebElement> listStatus;
    @FindBy(css = ".ui-button.ui-widget.ui-state-default.ui-button-text-only.ng-star-inserted")
    private WebElement tabNotSelected;
    @FindBy(css = ".ui-chkbox-box.ui-widget.ui-corner-all.ui-state-default")
    private List<WebElement> listCheckBox;


    @Override
    public final TimeSheetOthers navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/timesheet/approval");
        return this;
    }

    public final WebElement getThisWeekButton(final WebDriver driver) {
        waitForListElement(driver, btnThisWeek);
        return btnThisWeek.get(0);
    }

    public final WebElement getNextButton(final WebDriver driver) {
        waitForElement(driver, btnNext);
        return btnNext;
    }

    public final WebElement getPreButton(final WebDriver driver) {
        waitForElement(driver, btnBack);
        return btnBack;
    }

    public final WebElement getDefaultGroupBy(final WebDriver driver) {
        waitForElement(driver, defaultGroupBy);
        return defaultGroupBy;
    }

    public final WebElement getDropDownStatus(final WebDriver driver) {
        waitForElement(driver, dropDownStatus);
        return dropDownStatus;
    }

    public final TimeSheetOthers clickThisWeek() {
        btnThisWeek.get(0).click();
        return this;
    }

    public final TimeSheetOthers clickNext() {
        btnNext.click();
        return this;
    }

    public final TimeSheetOthers clickPre() {
        btnBack.click();
        return this;
    }

    public final WebElement getSelectedTab(final WebDriver driver) {
        waitForElement(driver, selectedTab);
        return selectedTab;
    }

    public final WebElement getLabelSubmitted(final WebDriver driver) {
        waitForElement(driver, labelSubmitted);
        return labelSubmitted;
    }

    public final WebElement getDropDownSubordinator(final WebDriver driver) {
        waitForElement(driver, dropDownSubordinator);
        return dropDownSubordinator;
    }

    public final WebElement getLabelApproved(final WebDriver driver) {
        waitForElement(driver, labelApproved);
        return labelApproved;
    }

    public final WebElement getLabelRejected(final WebDriver driver) {
        waitForElement(driver, labelRejected);
        return labelRejected;
    }

    public final WebElement getTimeSheetTile(final WebDriver driver) {
        waitForElement(driver, timeSheetTile);
        return timeSheetTile;
    }

    public final List<WebElement> getListStatus(final WebDriver driver) {
        waitForListElement(driver, listStatus);
        return listStatus;
    }

    public final WebElement getTabNotSelected(final WebDriver driver) {
        waitForElement(driver, tabNotSelected);
        return tabNotSelected;
    }

    public final WebElement findCheckboxPending(final WebDriver driver) {
        waitForListElement(driver, listStatus);
        return listStatus.get(0);
    }

    public final List<WebElement> getListCheckBox(final WebDriver driver) {
        waitForListElement(driver, listCheckBox);
        return listCheckBox;
    }
}
