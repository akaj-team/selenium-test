package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import static vn.asiantech.base.DriverBase.getDriver;

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

    @Override
    public final NewWikiPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final NewWikiPage enterTitle() {
        inputTitle.sendKeys("Pikalong");
        return this;
    }

    public final void enterContent() {
        String message = "Is a great team Is a great team Is a great team Is a great team Is a great team Is a great "
                + "team Is a great team Is a great team team Is a great team Is a great team";

        waitForElement(getDriver(), inputContent);
        getDriver().switchTo().frame(getDriver().findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));
        getDriver().findElement(By.tagName("body")).sendKeys(message);
        getDriver().switchTo().defaultContent();
    }

    public final boolean isSubmitEnable() {
        new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                driver -> btnSubmit.isEnabled());
        return btnSubmit.isEnabled();
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
}
