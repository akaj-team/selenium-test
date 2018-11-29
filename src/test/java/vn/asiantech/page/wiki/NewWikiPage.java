package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import static vn.asiantech.base.DriverBase.getDriver;

/**
 * @author at-anh.quach
 * NewPage page
 */

public class NewWikiPage extends BasePage<NewWikiPage> {

    @FindBy(name = "title")
    WebElement inputTitle;

    @FindBy(id = "btn-submit-wiki")
    WebElement btnSubmit;

    @FindBy(className = "help-block")
    WebElement txtMessageError;

    @FindBy(css = ".cke_inner.cke_reset")
    WebElement inputContent;

    @Override
    public NewWikiPage navigateTo(WebDriver webDriver) {
        return this;
    }

    public NewWikiPage enterTitle() {
        inputTitle.sendKeys("Pikalong");
        return this;
    }

    public void enterContent() {
        String message = "Is a great team Is a great team Is a great team Is a great team Is a great team Is a great " +
                "team Is a great team Is a great team team Is a great team Is a great team";

        waitForElement(getDriver(), inputContent);
        getDriver().switchTo().frame(getDriver().findElement(By.cssSelector(".cke_wysiwyg_frame.cke_reset")));
        getDriver().findElement(By.tagName("body")).sendKeys(message);
        getDriver().switchTo().defaultContent();
    }

    public boolean isSubmitEnable() {
        return btnSubmit.isEnabled();
    }

    public void clickSubmit() {
        btnSubmit.click();
    }

    public void clearTitle() {
        inputTitle.clear();
        inputTitle.sendKeys("h");
        inputTitle.sendKeys(Keys.BACK_SPACE);
    }

    public boolean isShowMessageError(String text) {
        return txtMessageError.getText().equals(text);
    }
}
