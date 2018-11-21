package vn.asiantech.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * @author at-anh.quach
 * AwardCategoryPage
 */
public class AwardCategoryPage extends BasePage<AwardCategoryPage> {

    private static final int POS_EDIT = 2;
    private static final int POS_DELETE = 2;
    private static final int POS_CATEGORY = 1;

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

    @FindBy(className = "ng-valid")
    private WebElement txaDescription;

    @FindBy(className = "help-block")
    private WebElement txtNameError;

    @FindBy(id = "award-dialog-wrapper")
    private WebElement dialog;

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
        tbCategory.findElements(By.tagName("tr")).get(POS_CATEGORY).findElements(By.tagName("td")).get(POS_EDIT).
                findElement(By.cssSelector(".update.ng-star-inserted")).click();
    }

    public final void clickDeleteButton() {
        tbCategory.findElements(By.tagName("tr")).get(POS_CATEGORY).findElements(By.tagName("td")).get(POS_DELETE).
                findElement(By.cssSelector(".delete.ng-star-inserted")).click();
    }

    public final void enterName(final String name) {
        inputName.sendKeys(name);
    }

    public final void enterDescription() {
        txaDescription.click();
        txaDescription.sendKeys("Fast etailing");
    }

    public final void clickCloseDialog() {
        dialog.findElement(By.cssSelector(".fa.fa-fw.fa-close")).click();
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
        return appError.findElement(By.cssSelector(".app-alert.ng-star-inserted")).isDisplayed();
    }

    public final void clickDeleteConfirm() {
        btnDeleteConfirm.click();
    }
}
