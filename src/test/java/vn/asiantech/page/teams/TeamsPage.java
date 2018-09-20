package vn.asiantech.page.teams;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/13/18.
 */
public class TeamsPage extends BasePage<TeamsPage> {
    public static final int TIME_OUT_SECONDS_10 = 10;
    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_MANAGER = 1;
    private static final int COLUMN_ACTION = 3;

    @FindBy(className = "filter-toolbox")
    private WebElement sectionToolBox;

    @FindBy(className = "ui-datatable-data")
    private WebElement tableBody;

    @FindBy(className = "title-action")
    private WebElement titleAction;

    @FindBy(className = "ui-overflow-hidden")
    private WebElement hiddenBody;

    @Override
    public TeamsPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/teams");
        return this;
    }

    public final void searchNameTeam(final String name) {
        WebElement search = sectionToolBox.findElement(By.name("search"));
        search.sendKeys(name);
    }

    public final boolean isTeamListEmpty() {
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        if (rows.get(0).getAttribute("class").contains("ui-datatable-emptymessage-row")) {
            return true;
        }
        return rows.size() == 0;
    }

    public final String showMessageEmptyTeam() {
        WebElement rowEmpty = tableBody.findElement(By.tagName("tr"));
        return rowEmpty.findElement(By.tagName("span")).getText();
    }

    public final String onClickAvatarTeam() {
        WebElement columnName = getColumnIndex(COLUMN_NAME, 0);
        columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(By.tagName("img")).click();
        return columnName.findElement(By.tagName("a")).getAttribute("href");
    }

    public final String onClickNameTeam() {
        WebElement columnName = getColumnIndex(COLUMN_NAME, 0);
        columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(By.tagName("span")).click();
        return columnName.findElement(By.tagName("a")).getAttribute("href");
    }

    public final String onClickNameManager() {
        WebElement columnName = getColumnIndex(COLUMN_MANAGER, 0);
        WebElement nameManager = columnName.findElement(By.tagName("a"));
        nameManager.click();
        return nameManager.getAttribute("href");
    }

    public final String onClickNewTeam() {
        WebElement newEmployee = titleAction.findElement(By.cssSelector(".btn.btn-sm.btn-default.btn-add"));
        newEmployee.click();
        String href;
        try {
            href = newEmployee.getAttribute("href");
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
            href = newEmployee.getAttribute("href");
        }
        return href;
    }

    public final String onClickUpdateTeam() {
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, 0);
        WebElement aUpdate = columnAction.findElement(By.tagName("a"));
        aUpdate.click();
        String href="";
        try {
            href = aUpdate.getAttribute("href");
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
        return href;
    }

    public final String onClickDeleteTeam(int position) {
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, position);
        columnAction.findElement(By.tagName("button")).click();
        WebElement columnName = getColumnIndex(COLUMN_NAME, position);
        return columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(By.tagName("span")).getText();
    }

    public final Boolean isDialogShowed() {
        try {
            return hiddenBody.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final String getNameTeamIsDeleted() {
        return hiddenBody.findElement(By.className("ui-dialog-content")).getText();
    }

    public final void onClickBtnCancelInDialogDelete() {
        hiddenBody.findElement(By.className("btn-cancel")).click();
    }

    public final void onClickBtnDeleteInDialogDelete() {
        hiddenBody.findElement(By.className("btn-submit")).click();
    }

    private WebElement getColumnIndex(final Integer column, final int position) {
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        return rows.get(position).findElements(By.tagName("td")).get(column);
    }
}
