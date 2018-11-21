package vn.asiantech.page.timesheet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;

public class TimeSheetOthers extends BasePage<TimeSheetOthers> {

    @FindBy(className = "toolbox-content")
    private WebElement toolBoxContent;
    @FindBy(tagName = "h2")
    private WebElement title;
    @FindBy(id = "btn-this-week")
    private WebElement btnThisWeek;
    @FindBy(id = "btn-prev-week")
    private WebElement btnPrevWeek;
    @FindBy(id = "btn-next-week")
    private WebElement btnNextWeek;
    @FindBy(css = ".toolbox-item.dropdown-md")
    private WebElement dropDownSubordinator;
    @FindBy(css = ".toolbox-item.dropdown-sm")
    private WebElement dropDownStatus;
    @FindBy(xpath = "//div[contains(@class, 'ui-state-default') and contains(@class ,'ui-state-active')]")
    private WebElement selectedTab;
    @FindBy(className = "label-normal")
    private WebElement labelSubmitted;
    @FindBy(className = "label-success")
    private WebElement labelApproved;
    @FindBy(className = "label-danger")
    private WebElement labelRejected;
    @FindBy(css = ".table.table-vm")
    private WebElement table;

    private WebDriver driver;

    public TimeSheetOthers(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final TimeSheetOthers navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.TIME_SHEET_OTHER_URL);
        return this;
    }

    final public WebElement getTitle() {
        return title;
    }

    public final String getNameSelectedTab() {
        waitForElement(driver, selectedTab);
        return selectedTab.getText();
    }

    public final boolean isDisplayedLabbelBottom() {
        waitForElement(driver, labelSubmitted);
        waitForElement(driver, labelApproved);
        waitForElement(driver, labelRejected);
        return labelSubmitted.isDisplayed() && labelApproved.isDisplayed() && labelRejected.isDisplayed();

    }

    public final void onClickButtonPrevWeek() {
        waitForElement(driver, btnPrevWeek);
        btnPrevWeek.click();
    }

    public final void onClickButtonNextWeek() {
        waitForElement(driver, btnNextWeek);
        btnNextWeek.click();
    }

    public final boolean isEnableButtonNextWeek() {
        waitForElement(driver, btnNextWeek);
        return btnNextWeek.isEnabled();
    }

    public final boolean isEnableButtonPrevWeek() {
        waitForElement(driver, btnPrevWeek);
        return btnPrevWeek.isEnabled();
    }

    public final boolean isEnableButtonThisWeek() {
        waitForElement(driver, btnThisWeek);
        return btnThisWeek.isEnabled();
    }

    public final void onClickDropDownStatus() {
        waitForElement(driver, dropDownStatus);
        dropDownStatus.click();
    }

    public final String getValueDropDownStatus() {
        waitForElement(driver, dropDownStatus);
        return dropDownStatus.findElement(By.className("ui-multiselect-label-container")).getText();
    }

    public final void onClickDropDownSubordinator() {
        waitForElement(driver, dropDownSubordinator);
        dropDownSubordinator.click();
    }

    public final String getValueDropDownSubordinator() {
        waitForElement(driver, dropDownSubordinator);
        return dropDownSubordinator.findElement(By.className("ui-multiselect-label-container")).getText();
    }

    public final List<WebElement> getListStatusOption() {
        return getListOptionOnDropdown(dropDownStatus);
    }

    public final List<WebElement> getListSubordinatorOption() {
        return getListOptionOnDropdown(dropDownSubordinator);
    }

    public final void onClickButtonInToolBox(final int position) {
        getListButtonToolbox().get(position).click();
    }

    public final String onClickInfoUser(final int position) {
        waitForElement(driver, table);
        WebElement element = table.findElements(By.cssSelector(".info-grouping.ng-star-inserted")).get(position);
        return onClickLinkAndReturnHref(element);
    }

    public final void onClickHasTask(final int position) {
        waitForElement(driver, table);
        table.findElements(By.cssSelector(".has-task.ng-star-inserted")).get(position).click();
    }

    public final boolean isShowPopupHasTask() {
        return driver.findElement(By.className("ui-overlaypanel")).getAttribute("style").contains("block");
    }

    private String onClickLinkAndReturnHref(final WebElement element) {
        String href = element.getAttribute("href");
        element.click();
        return href;
    }

    private List<WebElement> getListButtonToolbox() {
        waitForElement(driver, toolBoxContent);
        List<WebElement> listBtn = toolBoxContent.findElements(By.className("ui-button-text-only"));
        waitForListElement(driver, listBtn);
        return listBtn;
    }

    private List<WebElement> getListOptionOnDropdown(WebElement element) {
        List<WebElement> listCheckbox = element.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        waitForListElement(driver, listCheckbox);
        return listCheckbox;
    }
}
