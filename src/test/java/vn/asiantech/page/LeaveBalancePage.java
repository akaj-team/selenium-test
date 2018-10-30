package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import static vn.asiantech.base.DriverBase.TIME_OUT_SECONDS_NORMAL;

/**
 * @author at-vinhhuynh
 */
public class LeaveBalancePage extends BasePage<LeaveBalancePage> {

    @FindBy(css = "label.ui-multiselect-label.ui-corner-all")
    private WebElement allTeamsView;

    @FindBy(css = "div.ui-multiselect-panel.ui-widget.ui-widget-content.ui-corner-all.ui-shadow")
    private WebElement filterElement;


    @FindBy(css = "tbody.ui-datatable-data.ui-widget-content")
    private WebElement leaveBalanceList;

    @FindBy(css = "input.form-control.ng-untouched.ng-pristine.ng-valid")
    private WebElement inputViewSearchEmpty;

    @FindBy(css = "input.form-control.ng-valid.ng-dirty.ng-touched")
    private WebElement inputViewSearch;

    @FindBy(css = "div.directional-toolbox")
    private WebElement elementYearContainer;

    @FindBy(css = "span.ui-paginator-pages")
    private WebElement pageinatorElement;

    private String currentProfileUrl;

    private String currentLeaveHistoryUrl;

    @Override
    public final LeaveBalancePage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.LEAVE_BALANCE_PAGE_URL);
        return this;
    }

    public final LeaveBalancePage openFilter(final WebDriver driver) {
        waitForElementDisplay(driver, allTeamsView, TIME_OUT_SECONDS_NORMAL);
        allTeamsView.click();
        return this;
    }

    public final boolean filterElementDisplayed() {
        return filterElement.isDisplayed();
    }

    public final int getLeaveBalanceListCount(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, TIME_OUT_SECONDS_NORMAL);
        return leaveBalanceList.findElements(By.tagName("tr")).size();
    }

    public final LeaveBalancePage avatarClick(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, TIME_OUT_SECONDS_NORMAL);

        WebElement imgAvatar = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-major.has-avatar.ng-star-inserted"))
                .get(0).findElement(By.tagName("a")).findElement(By.cssSelector("img.img-circle.avatar-sm"));

        String profilePath = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-major.has-avatar.ng-star-inserted"))
                .get(0).findElement(By.tagName("a")).getAttribute("href");

        setCurrentProfileUrl(profilePath);
        imgAvatar.click();
        return this;
    }

    public final LeaveBalancePage userNameClick(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, TIME_OUT_SECONDS_NORMAL);

        WebElement tvUserName = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-major.has-avatar.ng-star-inserted"))
                .get(0).findElement(By.tagName("a")).findElement(By.cssSelector("span.info-grouping-text.truncate"));

        String profilePath = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.className("has-avatar"))
                .get(0).findElement(By.tagName("a")).getAttribute("href");

        setCurrentProfileUrl(profilePath);
        tvUserName.click();
        return this;
    }

    public final LeaveBalancePage sysIdClick(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, TIME_OUT_SECONDS_NORMAL);

        WebElement tvSysId = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-60.ng-star-inserted"))
                .get(0).findElement(By.cssSelector("span.ui-cell-data.ng-star-inserted"))
                .findElement(By.tagName("a"));

        String historyPath = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-60.ng-star-inserted"))
                .get(0).findElement(By.className("ui-cell-data"))
                .findElement(By.tagName("a")).getAttribute("href");
        setCurrentLeaveHistoryUrl(historyPath);
        tvSysId.click();
        return this;
    }

    public final String getCurrentProfileUrl() {
        return currentProfileUrl;
    }

    private LeaveBalancePage setCurrentProfileUrl(final String currentProfileUrl) {
        this.currentProfileUrl = currentProfileUrl;
        return this;
    }

    public final LeaveBalancePage openLeaveHistory(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, TIME_OUT_SECONDS_NORMAL);
        WebElement imgLeaveHistory = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-action.text-right.ng-star-inserted"))
                .get(0).findElement(By.tagName("a"));
        setCurrentLeaveHistoryUrl(imgLeaveHistory.getAttribute("href"));
        imgLeaveHistory.click();
        return this;
    }

    public final String getCurrentLeaveHistoryUrl() {
        return currentLeaveHistoryUrl;
    }

    private LeaveBalancePage setCurrentLeaveHistoryUrl(final String currentLeaveHistoryUrl) {
        this.currentLeaveHistoryUrl = currentLeaveHistoryUrl;
        return this;
    }

    public final LeaveBalancePage searchWithKey(final WebDriver driver, final String key) {
        waitForElementDisplay(driver, inputViewSearchEmpty, TIME_OUT_SECONDS_NORMAL);
        inputViewSearchEmpty.sendKeys(key);
        return this;
    }

    public final String getCurrentEmployeeName(final WebDriver driver) {
        int currentCountLeaveList = leaveBalanceList.findElements(By.tagName("tr")).size();
        waitUntilCountDifference(driver, leaveBalanceList, By.tagName("tr"), currentCountLeaveList);
        WebElement nameElement = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-major.has-avatar.ng-star-inserted"))
                .get(0).findElement(By.tagName("a")).findElement(By.cssSelector("strong"));
        return nameElement.getText();
    }

    public final LeaveBalancePage filterItemClick(final int position) {
        WebElement filterList = filterElement.findElement(By.cssSelector("ul.ui-multiselect-items.ui-multiselect-list.ui-widget-content.ui-widget.ui-corner-all.ui-helper-reset"));
        filterList.findElements(By.tagName("li")).get(position).click();
        return this;
    }

    public final String getFilterLabel() {
        return allTeamsView.getText();
    }

    public final String getYear(final WebDriver driver) {
        waitForElementDisplay(driver, elementYearContainer, TIME_OUT_SECONDS_NORMAL);
        WebElement tvYear = elementYearContainer.findElement(By.cssSelector("span.content"));
        return tvYear.getText();
    }

    public final LeaveBalancePage previous(final WebDriver driver) {
        waitForElementDisplay(driver, elementYearContainer, TIME_OUT_SECONDS_NORMAL);
        WebElement btnPrevious = elementYearContainer.findElement(By.cssSelector("button.btn.control-item.prev"));
        btnPrevious.click();
        return this;
    }

    public final LeaveBalancePage gotoPage(final WebDriver driver, final int page) {
        waitForElementDisplay(driver, pageinatorElement, TIME_OUT_SECONDS_NORMAL);
        pageinatorElement.findElements(By.tagName("a")).get(page).click();
        return this;
    }
}
