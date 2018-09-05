package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class MyLeavePage extends BasePage<MyLeavePage> {
    @FindBy(id = "side-menu")
    private WebElement sideMenu;

    @FindBy(css = ".toolbox-item.dropdown-md")
    private WebElement menuStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement itemMenuStatus;

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement data;

    @FindBy(css = ".dl-horizontal.m-t-xs.ng-star-inserted")
    private WebElement leaveBalance;

    @Override
    public MyLeavePage navigateTo(WebDriver webDriver) {
        return this;
    }


    public void clickItemLeave() {
        WebElement itemLeave = getItemMenuInPosition();
        itemLeave.click();
    }

    public void clickMyLeave() {
        WebElement itemLeave = getItemMenuInPosition();
        WebElement myLeave = itemLeave.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        myLeave.click();
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

    public boolean checkTextStatusMenu(String status) {
        return menuStatus.findElement(By.tagName("label")).getText().equals(status);
    }

    public boolean checkTextSYSID(String sysid) {
        return findDataLeave(0, 0).getText().equals(sysid);
    }

    public boolean checkTextTypeOfLeave(String type) {
        return findDataLeave(0, 1).getText().equals(type);
    }

    public boolean checkTextStatus(String status) {
        String nameIcon = "icon default ng-star-inserted";
        if (status.equals("Approve")) {
            nameIcon = "icon approve ng-star-inserted";

        }
        System.out.println(findDataLeave(0, 2).getAttribute("class"));
        return findDataLeave(0, 2).getAttribute("class").equals(nameIcon);
    }

    public boolean checkTextDateRequest(String date) {
        return findDataLeave(0, 3).getText().equals(date);
    }

    public boolean checkTextQuantity(String quatity) {
        return findDataLeave(0, 4).getText().equals(quatity);
    }

    public boolean checkTextApprover(String approver) {
        return findDataLeave(0, 5).getText().equals(approver);
    }

    public boolean checkTextManager(String manager) {
        return findDataLeave(0, 6).getText().equals(manager);
    }

    public boolean checkTextAnnualLeave(String annualLeave) {
        return findLeaveBalance(0).getText().equals(annualLeave);
    }

    public boolean checkTextMarriageLeave(String marriageLeave) {
        return findLeaveBalance(1).getText().equals(marriageLeave);
    }

    public boolean checkTextOvertimeLeave(String overTimeLeave) {
        return findLeaveBalance(2).getText().equals(overTimeLeave);
    }

    public boolean checkTextPaternalLeave(String paternalLeave) {
        return findLeaveBalance(3).getText().equals(paternalLeave);
    }

    public boolean checkLeaveMenuDropDown() {
        WebElement itemLeave = getItemMenuInPosition();
        return itemLeave.findElement(By.tagName("ul")).getRect().height == 0;
    }

    private WebElement findDataLeave(int row, int col) {
        WebElement tableData = data.findElement(By.tagName("table"));
        WebElement item = tableData.findElement(By.tagName("colgroup"));
        List<WebElement> rows = tableData.findElements(By.tagName("tr"));

        for (int i = 0; i < rows.size(); i++) {
            if (i == row) {
                List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
                for (int j = 0; j < columns.size(); j++) {
                    if (j == col) {
                        item = columns.get(j).findElement(By.tagName("span"));
                        if (i == 2) {
                            List<WebElement> items = columns.get(2).findElements(By.tagName("span"));
                            item = items.get(1);
                        }
                        break;
                    }
                }
            }
        }
        return item;
    }

    private WebElement findLeaveBalance(int pos) {
        WebElement item = leaveBalance.findElement(By.tagName("dt"));
        List<WebElement> balances = leaveBalance.findElements(By.tagName("dd"));
        for (int i = 0; i < balances.size(); i++) {
            if (i == pos) {
                item = balances.get(i).findElement(By.tagName("span"));
                break;
            }
        }
        return item;
    }

    private WebElement getItemMenuInPosition() {
        List<WebElement> itemMenus = new ArrayList<>();
        int countChildItem;
        List<WebElement> items = sideMenu.findElements(By.tagName("li"));
        for (int i = 0; i < items.size(); i = i + countChildItem + 1) {
            countChildItem = 0;
            itemMenus.add(items.get(i));
            if (items.get(i).findElements(By.tagName("li")).size() > 0) {
                countChildItem = items.get(i).findElements(By.tagName("li")).size();
            }
        }
        return itemMenus.get(3);
    }

}
