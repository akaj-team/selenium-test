package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

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

    private String currentProfileUrl;

    private String currentLeaveHistoryUrl;

    @Override
    public final LeaveBalancePage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/balance");
        return this;
    }

    public final LeaveBalancePage openFilter(final WebDriver driver) {
        waitForElementDisplay(driver, allTeamsView, 5);
        allTeamsView.click();
        return this;
    }

    public final boolean filterElementDisplayed() {
        return filterElement.isDisplayed();
    }

    public final int getFirstPageLeaveBalanceList(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, 5);
        return leaveBalanceList.findElements(By.tagName("tr")).size();
    }

    public final LeaveBalancePage openProfile(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, 5);

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

    public final String getCurrentProfileUrl() {
        return currentProfileUrl;
    }

    private LeaveBalancePage setCurrentProfileUrl(final String currentProfileUrl) {
        this.currentProfileUrl = currentProfileUrl;
        return this;
    }

    public final LeaveBalancePage openLeaveHistory(final WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, 5);
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

    public final LeaveBalancePage searchWithKey(WebDriver driver, String key) {
        waitForElementDisplay(driver, inputViewSearchEmpty, 5);
        inputViewSearchEmpty.sendKeys(key);
        return this;
    }

    public final String getCurrentEmployeeName(WebDriver driver) {
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

    public  String getFilterLabel() {
        return allTeamsView.getText();
    }
}
