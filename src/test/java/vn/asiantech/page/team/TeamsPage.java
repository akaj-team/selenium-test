package vn.asiantech.page.team;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/13/18.
 */
public class TeamsPage extends BasePage<TeamsPage> {
    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_MANAGER = 1;
    private static final int COLUMN_ACTION = 3;
    private static final String URL_TEAMS_PAGE = "http://portal-stg.asiantech.vn/organisation/teams";

    @FindBy(id = "team-filter-wrapper")
    private WebElement sectionToolBox;

    @FindBy(className = "ui-datatable-data")
    private WebElement tbBody;

    @FindBy(className = "title-action")
    private WebElement titleAction;

    @FindBy(className = "ui-overflow-hidden")
    private WebElement hiddenBody;

    @Override
    public final TeamsPage navigateTo(final WebDriver webDriver) {
        webDriver.get(URL_TEAMS_PAGE);
        return this;
    }

    public final void searchNameTeam(final String name) {
        WebElement search = sectionToolBox.findElement(By.name("search"));
        search.sendKeys(name);
    }

    public final boolean isTeamListEmpty() {
        List<WebElement> rows = tbBody.findElements(By.tagName("tr"));
        return rows.get(0).getAttribute("class").contains("ui-datatable-emptymessage-row");
    }

    public final String showMessageEmptyTeam() {
        WebElement rowEmpty = tbBody.findElement(By.tagName("tr"));
        return rowEmpty.findElement(By.tagName("span")).getText();
    }

    public final String onClickAvatarTeam() {
        return getViewContainerOfColumnName(By.className("avatar-sm"));
    }

    public final String onClickNameTeam() {
        return getViewContainerOfColumnName(By.className("info-grouping-text"));
    }

    public final String onClickNameManager() {
        WebElement columnName = getColumnIndex(COLUMN_MANAGER, 0);
        WebElement nameManager = columnName.findElement(By.tagName("a"));
        return onClickLinkAndReturnHref(nameManager);
    }

    public final String onClickNewTeam() {
        WebElement newEmployee = titleAction.findElement(By.id("btn-create-team"));
        return onClickLinkAndReturnHref(newEmployee);
    }

    public final String onClickUpdateTeam(final int position) {
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, position);
        WebElement updateTeamElement = columnAction.findElement(By.className("update"));
        return onClickLinkAndReturnHref(updateTeamElement);
    }

    public final String onClickDeleteTeam(final int position) {
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, position);
        columnAction.findElement(By.className("delete")).click();
        WebElement columnName = getColumnIndex(COLUMN_NAME, position);
        return columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(By.tagName("span")).getText();
    }

    public final Boolean isDeleteDialogShown() {
        return isElementPresented(hiddenBody);
    }

    public final String getNameTeamIsDeleted() {
        return hiddenBody.findElement(By.className("ui-dialog-content")).getText();
    }

    public final void onClickButtonCancelInDialogDelete() {
        hiddenBody.findElement(By.className("btn-cancel")).click();
    }

    public final void onClickButtonDeleteInDialogDelete() {
        hiddenBody.findElement(By.className("btn-submit")).click();
    }

    private String onClickLinkAndReturnHref(WebElement element) {
        String href = element.getAttribute("href");
        element.click();
        return href;
    }

    private String getViewContainerOfColumnName(final By tagName) {
        WebElement columnName = getColumnIndex(COLUMN_NAME, 0);
        columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(tagName).click();
        return columnName.findElement(By.tagName("a")).getAttribute("href");
    }

    private WebElement getColumnIndex(final Integer column, final int position) {
        List<WebElement> rows = tbBody.findElements(By.tagName("tr"));
        return rows.get(position).findElements(By.tagName("td")).get(column);
    }
}
