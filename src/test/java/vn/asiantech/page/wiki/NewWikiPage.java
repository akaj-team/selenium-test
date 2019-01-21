package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

/**
 * @author at-anh.quach
 * NewPage page
 */
public class NewWikiPage extends BasePage<NewWikiPage> {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "btn-submit-wiki")
    private WebElement btnSubmit;

    @FindBy(className = "help-block")
    private WebElement txtMessageError;

    @FindBy(css = ".cke_inner.cke_reset")
    private WebElement inputContent;

    @FindBy(id = "link-to-wiki")
    private WebElement txtWiki;

    @FindBy(id = "static-dialog-wrapper")
    private WebElement dlgConfirm;

    @FindBy(className = "btn-cancel")
    private WebElement btnStayDialogConfirm;

    @FindBy(css = ".btn.btn-sm.btn-warning.btn-submit.ng-star-inserted")
    private WebElement btnLeaveDialogConfirm;

    @FindBy(xpath = "//a[contains(@class,'fa-close')]")
    private WebElement btnCloseDialogConfirm;

    private WebDriver driver;

    public NewWikiPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final NewWikiPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final NewWikiPage enterTitle(final String title) {
        inputTitle.sendKeys(title);
        return this;
    }

    public final void enterContent() {
        waitForElement(driver, inputContent);
        driver.switchTo().frame(driver.findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));
        String message = "Is a great team Is a great team Is a great team Is a great team Is a great team Is a great "
                + "team Is a great team Is a great team team Is a great team Is a great team";
        driver.findElement(By.tagName("body")).sendKeys(message);
        driver.switchTo().defaultContent();
    }

    public final boolean isSubmitEnable() {
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                driver -> btnSubmit.isEnabled());
        return true;
    }

    public final boolean isSubmitDisable() {
        return !btnSubmit.isEnabled();
    }

    public final void clickSubmit() {
        btnSubmit.click();
    }

    public final void clearTitle() {
        inputTitle.clear();
        inputTitle.sendKeys("h");
        inputTitle.sendKeys(Keys.BACK_SPACE);
    }

    public final boolean isShowMessageError(final String text) {
        return txtMessageError.getText().equals(text);
    }

    public final void clickTxtWiki() {
        txtWiki.click();
    }

    public final boolean isDisplayDialog() {
        waitForElement(driver, dlgConfirm);
        return dlgConfirm.isDisplayed();
    }

    public final boolean isDismissDisplayDialog() {
        return !dlgConfirm.isDisplayed();
    }

    public final void clickCloseDialog() {
        btnCloseDialogConfirm.click();
    }

    public final void clickStayButtonDialog() {
        btnStayDialogConfirm.click();
    }

    public final void clickLeaveButtonDialog() {
        btnLeaveDialogConfirm.click();
    }

    public final boolean isKeepData() {
        return inputTitle.getAttribute("value").equals("Pikalong");
    }
}
