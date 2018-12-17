package vn.asiantech.page.team;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.base.Constant.TEAM_PAGE_URL;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/13/18.
 */
public class TeamsPage extends BasePage<TeamsPage> {
    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_MANAGER = 1;
    private static final int COLUMN_ACTION = 4;

    @FindBy(name = "search")
    private WebElement inputSearch;

    @FindBy(className = "ui-datatable-data")
    private WebElement tbBody;

    @FindBy(className = "ui-overflow-hidden")
    private WebElement hiddenBody;

    @FindBy(id = "btn-create-team")
    private WebElement btnNewTeam;

    @FindBy(className = "app-alert")
    private WebElement alertMessage;

    private WebDriver driver;

    public TeamsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final TeamsPage navigateTo(final WebDriver webDriver) {
        webDriver.get(TEAM_PAGE_URL);
        return this;
    }

    public final void searchNameTeam(final String name) {
        waitForElement(driver, inputSearch);
        inputSearch.sendKeys(name);
        inputSearch.sendKeys(Keys.ENTER);
    }

    public final boolean isTeamListEmpty() {
        try {
            return tbBody.findElement(By.tagName("tr")).getAttribute("class").contains("ui-datatable-emptymessage-row");
        } catch (StaleElementReferenceException e) {
            return tbBody.findElement(By.tagName("tr")).getAttribute("class").contains("ui-datatable-emptymessage-row");
        }
    }

    public final String showMessageEmptyTeam() {
        WebElement rowEmpty = tbBody.findElement(By.tagName("tr"));
        return rowEmpty.findElement(By.tagName("span")).getText();
    }

    public final String onClickAvatarTeam() {
        waitForElement(driver, tbBody);
        return getViewContainerOfColumnName(By.className("avatar-sm"));
    }

    public final String onClickNameTeam() {
        waitForElement(driver, tbBody);
        return getViewContainerOfColumnName(By.className("info-grouping-text"));
    }

    public final String onClickNameManager() {
        waitForElement(driver, tbBody);
        WebElement columnName = getColumnIndex(COLUMN_MANAGER, 0);
        WebElement nameManager = columnName.findElement(By.tagName("a"));
        return onClickLinkAndReturnHref(nameManager);
    }

    public final String onClickNewTeam() {
        waitForElement(driver, btnNewTeam);
        return onClickLinkAndReturnHref(btnNewTeam);
    }

    public final String onClickUpdateTeam(final int position) {
        waitForElement(driver, tbBody);
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, position);
        WebElement updateTeamElement = columnAction.findElement(By.className("update"));
        return onClickLinkAndReturnHref(updateTeamElement);
    }

    public final String onClickDeleteTeam(final int position) {
        waitForElement(driver, tbBody);
        WebElement columnAction = getColumnIndex(COLUMN_ACTION, position);
        columnAction.findElement(By.className("delete")).click();
        WebElement columnName = getColumnIndex(COLUMN_NAME, position);
        return columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(By.tagName("span")).getText();
    }

    public final boolean isDeleteDialogShown() {
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

    public final boolean isShowAlertDeleteSuccess() {
        waitForElement(driver, alertMessage);
        return alertMessage.isDisplayed();
    }

    private String onClickLinkAndReturnHref(final WebElement element) {
        String href = element.getAttribute("href");
        element.click();
        return href;
    }

    private String getViewContainerOfColumnName(final By tagName) {
        WebElement columnName = getColumnIndex(COLUMN_NAME, 0);
        String teamPageUrl = columnName.findElement(By.tagName("a")).getAttribute("href");
        columnName.findElement(By.tagName("a")).findElement(By.tagName("span")).findElement(tagName).click();
        return teamPageUrl;
    }

    private WebElement getColumnIndex(final int column, final int position) {
        List<WebElement> rows = tbBody.findElements(By.tagName("tr"));
        return rows.get(position).findElements(By.tagName("td")).get(column);
    }
}
