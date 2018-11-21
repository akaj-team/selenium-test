package vn.asiantech.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 11/21/18.
 */
public class AwardCategoryPage extends BasePage<AwardCategoryPage> {

    private static final int POS_ROW = 1;
    private static final int POS_ACTION = 2;

    @FindBy(id = "award-list-wrapper")
    private WebElement tbCategory;

    @FindBy(id = "btn-create-award")
    private WebElement btnNewAward;

    @FindBy(id = "btn-submit-award")
    private WebElement btnSubmit;

    @FindBy(className = "btn-cancel")
    private WebElement btnCancel;

    @FindBy(name = "name")
    private WebElement inputName;

    @FindBy(xpath = "//textarea[contains(@name, 'description') and contains(@formcontrolname, 'description')]")
    private WebElement txaDescription;

    @FindBy(className = "help-block")
    private WebElement txtNameError;

    @FindBy(id = "award-dialog-wrapper")
    private WebElement dialogAward;

    @FindBy(tagName = "app-error")
    private WebElement appError;

    @FindBy(className = "btn-warning")
    private WebElement btnDeleteConfirm;

    @Override
    public final AwardCategoryPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void clickNewAward() {
        btnNewAward.click();
    }

    public final boolean isDisplayDialog() {
        return btnSubmit.isDisplayed();
    }

    public final void clickEditButton() {
        tbCategory.findElements(By.tagName("tr")).get(POS_ROW).findElements(By.tagName("td")).get(POS_ACTION).
                findElement(By.cssSelector(".update.ng-star-inserted")).click();
    }

    public final void clickDeleteButton() {
        tbCategory.findElements(By.tagName("tr")).get(POS_ROW).findElements(By.tagName("td")).get(POS_ACTION).
                findElement(By.cssSelector(".delete.ng-star-inserted")).click();
    }

    public final void enterName(final String name) {
        inputName.sendKeys(name);
    }

    public final void enterNameWithWhileSpace(final String name, final int whileSpaceBeginning, final int whileSpaceEnd) {
        inputName.sendKeys(setNumberWhileSpace(whileSpaceBeginning) + name + setNumberWhileSpace(whileSpaceEnd));
    }

    public final void enterDescription() {
        txaDescription.click();
        txaDescription.sendKeys("Fast Retailing");
    }

    public final void clickCloseDialog() {
        dialogAward.findElement(By.cssSelector(".fa.fa-fw.fa-close")).click();
    }

    public final boolean isEnableSubmitButton() {
        return btnSubmit.isEnabled();
    }

    public final void clickSubmit() {
        btnSubmit.click();
    }

    public final void clearText() {
        inputName.clear();
        inputName.sendKeys("h");
        inputName.sendKeys(Keys.BACK_SPACE);
    }

    public final boolean isShowError() {
        return txtNameError.isDisplayed();
    }

    public final void clickCancelButton() {
        btnCancel.click();
    }

    public final boolean isDisplayAlertSuccess() {
        return appError.findElement(By.xpath("//div[contains(@class,'alert-success')]")).isDisplayed();
    }

    public final boolean isDisplayAlertDanger() {
        return appError.findElement(By.cssSelector(".alert.alert-danger.alert-dismissible")).isDisplayed();
    }

    public final boolean isDisplayAlertDeleteSuccess() {
        return appError.findElement(By.className("app-alert")).isDisplayed();
    }

    public final void clickDeleteConfirm() {
        btnDeleteConfirm.click();
    }

    private String setNumberWhileSpace(final int number) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < number; i++) {
            str.append(" ");
        }
        return str.toString();
    }
}
