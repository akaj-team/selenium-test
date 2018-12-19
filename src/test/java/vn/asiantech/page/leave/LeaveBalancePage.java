package vn.asiantech.page.leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

/**
 * @author at-vinhhuynh
 */
public class LeaveBalancePage extends BasePage<LeaveBalancePage> {

    @FindBy(id = "team-filter-wrapper")
    private WebElement allTeamsView;

    @FindBy(className = "ui-multiselect-panel")
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
    private WebElement pagePaginatorElement;

    private String currentProfileUrl;

    private String currentLeaveHistoryUrl;

    private WebDriver driver;

    public LeaveBalancePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final LeaveBalancePage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.LEAVE_BALANCE_PAGE_URL);
        return this;
    }

    public final LeaveBalancePage openFilter() {
        waitForElementDisplay(driver, allTeamsView, Constant.DEFAULT_TIME_OUT);
        allTeamsView.click();
        return this;
    }

    public final boolean filterElementDisplayed() {
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-multiselect-panel")));
        return filterElement.isDisplayed();
    }

    public final int getLeaveBalanceListCount() {
        waitForElementDisplay(driver, leaveBalanceList, Constant.DEFAULT_TIME_OUT);
        return leaveBalanceList.findElements(By.tagName("tr")).size();
    }

    public final LeaveBalancePage avatarClick() {
        waitForElementDisplay(driver, leaveBalanceList, Constant.DEFAULT_TIME_OUT);

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

    public final LeaveBalancePage userNameClick() {
        waitForElementDisplay(driver, leaveBalanceList, Constant.DEFAULT_TIME_OUT);

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

    public final LeaveBalancePage sysIdClick() {
        waitForElementDisplay(driver, leaveBalanceList, Constant.DEFAULT_TIME_OUT);

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

    public final LeaveBalancePage openLeaveHistory() {
        waitForElementDisplay(driver, leaveBalanceList, Constant.DEFAULT_TIME_OUT);
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

    public final LeaveBalancePage searchWithKey(final String key) {
        waitForElementDisplay(driver, inputViewSearchEmpty, Constant.DEFAULT_TIME_OUT);
        inputViewSearchEmpty.sendKeys(key);
        return this;
    }

    public final String getCurrentEmployeeName() {
        int currentCountLeaveList = leaveBalanceList.findElements(By.tagName("tr")).size();
        waitUntilCountDifference(driver, leaveBalanceList, By.tagName("tr"), currentCountLeaveList);
        WebElement nameElement = leaveBalanceList.findElements(By.tagName("tr")).get(0)
                .findElements(By.cssSelector("td.col-major.has-avatar.ng-star-inserted"))
                .get(0).findElement(By.tagName("a")).findElement(By.cssSelector("strong"));
        return nameElement.getText();
    }

    public final String filterItemClick(final int position) {
        WebElement filterList = filterElement.findElement(By.className("ui-multiselect-list"));
        WebElement firstItem = filterList.findElements(By.tagName("li")).get(position);
        firstItem.click();
        return firstItem.findElement(By.tagName("label")).getText();
    }

    public final String getFilterLabel() {
        return allTeamsView.findElement(By.tagName("label")).getText();
    }

    public final String getYear() {
        waitForElementDisplay(driver, elementYearContainer, Constant.DEFAULT_TIME_OUT);
        WebElement tvYear = elementYearContainer.findElement(By.cssSelector("span.content"));
        return tvYear.getText();
    }

    public final LeaveBalancePage previous() {
        waitForElementDisplay(driver, elementYearContainer, Constant.DEFAULT_TIME_OUT);
        WebElement btnPrevious = elementYearContainer.findElement(By.cssSelector("button.btn.control-item.prev"));
        btnPrevious.click();
        return this;
    }

    public final LeaveBalancePage gotoPage(final int page) {
        waitForElementDisplay(driver, pagePaginatorElement, Constant.DEFAULT_TIME_OUT);
        pagePaginatorElement.findElements(By.tagName("a")).get(page).click();
        return this;
    }
}
