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


    @Override
    public LeaveBalancePage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/leave/balance");
        return this;
    }

    public void openFilter(WebDriver driver) {
        waitForElementDisplay(driver, allTeamsView, 5);
        allTeamsView.click();
    }

    public boolean filterElementDisplayed() {
        return filterElement.isDisplayed();
    }

    public int getFirstPageLeaveBalanceList(WebDriver driver) {
        waitForElementDisplay(driver, leaveBalanceList, 5);
        return leaveBalanceList.findElements(By.tagName("tr")).size();
    }
}
