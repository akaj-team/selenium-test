package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class AwardCategoryPage extends BasePage<AwardCategoryPage> {

    @FindBy(className = "ui-datatable-scrollable-table-wrapper")
    private WebElement tbCategory;

    @FindBy(id = "btn-award-create")
    private WebElement btnNewAward;

    @FindBy(css = ".btn.btn-sm.btn-primary.btn-submit")
    private WebElement btnSubmit;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel")
    private WebElement btnCancel;

    @FindBy(name = "name")
    private WebElement inputName;

    @FindBy(css = ".form-control.ng-untouched.ng-pristine.ng-valid")
    private WebElement txaDescription;

    @FindBy(className = "help-block")
    private WebElement txtNameError;

    @FindBy(xpath = "//div[contains(@class, 'ui-dialog-titlebar')]")
    private WebElement headerDialog;

    @Override
    public final AwardCategoryPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final int getCountLine() {
        return tbCategory.findElements(By.tagName("tr")).size();
    }

    public final boolean checkAddLine(int count) {
        return getCountLine() == count;
    }

    public final boolean isNameExist(String name) {
        List<WebElement> list = tbCategory.findElements(By.tagName("tr"));
        for (WebElement aList : list) {
            if (name.equals(aList.findElements(By.tagName("td")).get(0).getText())) {
                return true;
            }
        }
        return false;
    }

    public final void clickNewAward() {
        btnNewAward.click();
    }

    public final boolean isDisplayDialog() {
        return btnSubmit.isDisplayed();
    }

    public final void clickEditButton() {
        tbCategory.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(2).
                findElement(By.cssSelector(".update.ng-star-inserted")).click();
    }

    public final void enterName(String name) {
        inputName.sendKeys(name);
    }

    public final void enterDescription() {
        txaDescription.click();
        txaDescription.sendKeys("Fast Retailing");
    }

    public final void clickCloseDialog() {
        headerDialog.findElement(By.cssSelector(".fa.fa-fw.fa-close")).click();
    }

    public final boolean isEnableSubmitButton() {
        return btnSubmit.isEnabled();
    }

    public final void clickSubmit() {
        btnSubmit.click();
    }

    public final boolean isDescriptionEmpty(Integer count) {
        return tbCategory.findElements(By.tagName("tr")).get(count - 1).findElements(By.tagName("td")).get(1).getText().equals("Empty");
    }

    public final boolean isNotEmptyName() {
        return inputName.getText().length() != 0;
    }

    public final void clearText(String type) {
        if (type.equals("name")) {
            inputName.clear();
            inputName.sendKeys("h");
            inputName.sendKeys(Keys.BACK_SPACE);
        } else {
            txaDescription.clear();
        }
    }

    public final boolean isShowError() {
        return txtNameError.isDisplayed();
    }

    public final void clickCancelButton() {
        btnCancel.click();
    }
}
