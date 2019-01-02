package vn.asiantech.page.group;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-phuongdang
 */
public class NewGroupPage extends BasePage<NewGroupPage> {

    private static final int WAITED_ELEMENT = 500;
    @FindBy(css = "input[formcontrolname=name]")
    private WebElement inputNameGroup;
    @FindBy(css = "input[formcontrolname=email]")
    private WebElement inputEmailGroup;
    @FindBy(css = "input[formcontrolname=url]")
    private WebElement inputGroupFolder;
    @FindBy(css = ".wrapper.wrapper-content")
    private WebElement wrapperContent;
    @FindBy(id = "group-form-wrapper")
    private WebElement formMainGroupPage;
    @FindBy(id = "btn-submit-group")
    private WebElement btnSubmit;

    private WebElement itemLeader;
    private WebDriver driver;

    public NewGroupPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final NewGroupPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void validateInputNameOfGroup(final String nameGroup, final int firstSpace, final int lastSpace) {
        waitForElement(driver, inputNameGroup);
        sendKey(inputNameGroup, nameGroup, firstSpace, lastSpace);
    }

    public final boolean isShowMessageError(final String message, final int pos) {
        String errorMessage = getMessageError(pos);
        return errorMessage.equals(message);
    }

    public final void inputNameGroup(final String nameGroup) {
        waitForElement(driver, inputNameGroup);
        inputNameGroup.clear();
        inputNameGroup.sendKeys(nameGroup);
    }

    public final boolean isMessageErrorHide(final int pos) {
        WebElement errorMessageElement = formMainGroupPage.findElement(By.className("col-md-8"))
                .findElements(By.className("form-group")).get(pos).findElement(By.className("help-block"));
        return errorMessageElement.isDisplayed();
    }

    public final void openDropDownLeader() {
        try {
            Thread.sleep(WAITED_ELEMENT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement parentContainer = wrapperContent.findElement(By.xpath("//label[contains(text(),'Leader')]/.."));
        parentContainer.click();
        itemLeader = parentContainer.findElement(By.tagName("div")).findElement(By.className("ui-dropdown-panel"));
    }

    public final void selectedItemDropdown() {
        List<WebElement> listManager = itemLeader.findElement(By.className("ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(1).click();
    }

    public final boolean isDisplayButtonSubmit() {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isEnabled();
    }

    public final void validateInputEmailOfGroup(final String nameGroup, final int firstSpace, final int lastSpace) {
        waitForElement(driver, inputEmailGroup);
        sendKey(inputEmailGroup, nameGroup, firstSpace, lastSpace);
    }

    public final void validateInputGroupFolder(final String groupFolder, final int firstSpace, final int lastSpace) {
        waitForElement(driver, inputGroupFolder);
        sendKey(inputGroupFolder, groupFolder, firstSpace, lastSpace);
    }

    public final boolean isMessageErrorShowing() {
        WebElement errorMessageElement = formMainGroupPage.findElement(By.className("col-md-12"))
                .findElements(By.className("form-group")).get(0).findElement(By.className("help-block"));
        return errorMessageElement.isEnabled();
    }

    public final void clickButtonSubmitOnNewGroup() {
        waitForElement(driver, btnSubmit);
        btnSubmit.click();
    }

    private void sendKey(final WebElement element, final String textInput, final int firstSpace, final int lastSpace) {
        if (textInput.isEmpty()) {
            element.sendKeys(Keys.SPACE);
            element.sendKeys(Keys.BACK_SPACE);
            return;
        }
        for (int i = 0; i < firstSpace; i++) {
            element.sendKeys(Keys.SPACE);
        }
        element.sendKeys(textInput);
        for (int i = 0; i < lastSpace; i++) {
            element.sendKeys(Keys.SPACE);
        }
    }

    private String getMessageError(final int pos) {
        WebElement errorMessageElement = formMainGroupPage.findElement(By.className("col-md-8"))
                .findElements(By.className("form-group")).get(pos).findElement(By.className("help-block"));
        waitForElement(driver, errorMessageElement);
        return errorMessageElement.getText();
    }
}
