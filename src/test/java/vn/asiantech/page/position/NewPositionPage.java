package vn.asiantech.page.position;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author hanhnguyenm.
 */
public class NewPositionPage extends BasePage<NewPositionPage> {

    private static final int POS_LIST_POSITION = 2;
    private static final int POS_FIRST_POSITION_IN_LIST = 0;
    private static final int POS_NAME_POSITION = 0;
    private static final int POS_FIRST_CHAR_IN_STRING = 0;
    private static final int TIME_SLEEP = 300;

    private WebDriver driver;

    @FindBy(id = "btn-link-to-position-list")
    private WebElement btnPosition;
    @FindBy(xpath = "//input[contains(@formcontrolname,'long_name')]")
    private WebElement edtLongName;
    @FindBy(xpath = "//input[contains(@formcontrolname,'short_name')]")
    private WebElement edtShortName;
    @FindBy(id = "position-form-wrapper")
    private WebElement formMainPositionPage;
    @FindBy(id = "btn-submit-position")
    private WebElement btnSubmit;
    @FindBy(css = ".app-alert.ng-star-inserted")
    private WebElement alertError;
    @FindBy(className = "ui-dialog")
    private WebElement alertConfirmation;

    public NewPositionPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final NewPositionPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    public final void clickOnPositionPage() {
        btnPosition.click();
    }

    public final void inputLongName(final String longNameInput, final int firstSpace, final int lastSpace) {
        sendKey(edtLongName, longNameInput, firstSpace, lastSpace);
    }

    public final void inputShortName(final String shortNameInput, final int firstSpace, final int lastSpace) {
        sendKey(edtShortName, shortNameInput, firstSpace, lastSpace);
    }

    public final void inputCorrectLongName(final String longName) {
        edtLongName.sendKeys(longName);
    }

    public final boolean isMessageError(final String message, final int pos) {
        String errorMessage = getMessageError(pos);
        return errorMessage.equals(message);
    }

    public final boolean isShortNameText(final String shortName) {
        return edtShortName.getAttribute("value").equals(shortName);
    }

    public final boolean isShortNameEmpty() {
        return edtShortName.getAttribute("value").isEmpty();
    }

    public final boolean isShortNameHintText(final String hintText) {
        return edtShortName.getAttribute("placeholder").equals(hintText);
    }

    public final boolean isMessageErrorHide(final int pos) {
        WebElement errorMessageElement = formMainPositionPage.findElement(By.className("col-md-12")).findElements(By.className("form-group")).get(pos).findElement(By.className("help-block"));
        return errorMessageElement.isDisplayed();
    }

    public final void clickFirstItemPosition() {
        WebElement firstListPosition = formMainPositionPage.findElement(By.className("col-md-12")).findElements(By.className("form-group")).get(POS_LIST_POSITION).findElement(By.className("ui-listbox-list")).findElements(By.tagName("li")).get(POS_FIRST_POSITION_IN_LIST);
        firstListPosition.click();
    }

    public final boolean isButtonSubmitDisplay() {
        return btnSubmit.isDisplayed();
    }

    public final void clickButtonSubmit() {
        btnSubmit.click();
    }

    public final boolean isShownMessageErrorWhenSubmit() {
        return alertError.isDisplayed();
    }

    public final boolean isShownAlertConfirmation() {
        try {
            Thread.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return alertConfirmation.isDisplayed();
    }

    public final void clickButtonStay() {
        alertConfirmation.findElement(By.className("btn-cancel")).click();
    }

    public final void clickButtonLeave() {
        alertConfirmation.findElement(By.className("btn-submit")).click();
    }

    public final boolean isPositionExits() {
        List<WebElement> listPositions = formMainPositionPage.findElement(By.className("col-md-12")).findElements(By.className("form-group")).get(POS_LIST_POSITION).findElement(By.className("ui-listbox-list")).findElements(By.tagName("li"));
        for (WebElement listPosition : listPositions) {
            String namePosition = listPosition.findElements(By.tagName("span")).get(POS_NAME_POSITION).getText();
            String shortNamePosition = "";
            String[] tmpShortName = listPosition.findElements(By.tagName("span")).get(POS_NAME_POSITION).getText().split(" ");
            for (String aTmpShortName : tmpShortName) {
                shortNamePosition += String.valueOf(aTmpShortName.charAt(POS_FIRST_CHAR_IN_STRING));
            }
            if (namePosition.equals(edtLongName.getAttribute("value")) || shortNamePosition.equals(edtShortName.getAttribute("value"))) {
                return true;
            }
        }
        return false;
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
        WebElement errorMessageElement = formMainPositionPage.findElement(By.className("col-md-12")).findElements(By.className("form-group")).get(pos).findElement(By.className("help-block"));
        waitForElement(driver, errorMessageElement);
        return errorMessageElement.getText();
    }
}
