package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class MyLeavePage extends BasePage<MyLeavePage> {
    @FindBy(css = ".ng-tns-c1-2.ui-dropdown.ui-widget.ui-state-default.ui-corner-all.ui-helper-clearfix")
    private WebElement menuStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement itemMenuStatus;

    @FindBy(className = "col-50 ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement SYSID;

    @FindBy(className = "col-100 ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement typeOfLeave;

    @FindBy(className = "col-50 text-center cell-status ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement status;

    @FindBy(className = "col-150 text-right ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement dateRequest;

    @FindBy(className = "col-70 text-center ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement quantity;

    @FindBy(className = "col-100 ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement approver;

    @FindBy(className = "col-100 ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement manager;

    @FindBy(className = "col-action text-right ui-state-default ui-unselectable-text ng-star-inserted")
    private WebElement action;

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement tableData;

    @Override
    public MyLeavePage navigateTo(WebDriver webDriver) {
        return this;
    }

    public void clickMenuStatus() {
        menuStatus.click();
    }

    public Boolean dropDownMenuStatus() {
        return itemMenuStatus.isDisplayed();
    }

    public void clickItemMenuStatus(String status) {
        WebElement itemStatus = itemMenuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        switch (status) {
            case "Pending":
                itemStatus = itemMenuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
            case "Approved":
                itemStatus = itemMenuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(2);
            case "Rejected":
                itemStatus = itemMenuStatus.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(3);

        }
        itemStatus.click();
    }
}
