package vn.asiantech.page.team;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;
import java.util.Random;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/21/18.
 */
public class NewTeamFormPage extends BasePage<NewTeamFormPage> {

    private static final int MAX_LENGTH_NAME_TEAM = 50;
    private static final int MAX_LENGTH_EMAIL = 30;
    private static final String NAME_DEFAULT = "123Test ";
    private static final String EMAIL_DEFAULT = "@asiantech.vn";

    @FindBy(css = ".wrapper.wrapper-content")
    private WebElement wrapper;

    @FindBy(name = "name")
    private WebElement inputName;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "submit")
    private WebElement btnSubmit;

    private WebElement itemManager;
    private WebDriver driver;

    public NewTeamFormPage(final WebDriver driver) {
        this.driver = driver;
    }

    public NewTeamFormPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public final NewTeamFormPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void sendKeyInputName(final String name, final int whileSpaceBegging, final int whileSpaceEnd) {
        waitForElement(driver, inputName);
        inputName.clear();
        inputName.sendKeys(setNumberWhileSpace(whileSpaceBegging) + name + setNumberWhileSpace(whileSpaceEnd));
    }

    public final String generateNameTeam() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int randomLength = 1 + random.nextInt(MAX_LENGTH_NAME_TEAM - NAME_DEFAULT.length() - 1);
        for (int i = 0; i < randomLength; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(c);
        }
        return NAME_DEFAULT + stringBuilder;
    }

    public final boolean isMessageErrorValidateNameShown() {
        WebElement parentContainer = wrapper.findElement(By.xpath("//label[contains(text(),'Name')]/.."));
        return parentContainer.findElement(By.className("help-block")).isDisplayed();
    }

    public final void clickDropDownListManager() {
        waitForElement(driver, wrapper);
        WebElement parentContainer = wrapper.findElement(By.xpath("//label[contains(text(),'Manager')]/.."));
        parentContainer.click();
        itemManager = parentContainer.findElement(By.tagName("div")).findElement(By.className("ui-dropdown-panel"));
    }

    public final void selectManager() {
        List<WebElement> listManager = itemManager.findElement(By.className("ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(1).click();
    }

    public final void sendKeyInputEmail(final String email, final int whileSpaceBegging, final int whileSpaceEnd) {
        waitForElement(driver, inputEmail);
        inputEmail.clear();
        inputEmail.sendKeys(setNumberWhileSpace(whileSpaceBegging) + email + setNumberWhileSpace(whileSpaceEnd));
    }

    public final String generateEmail() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int randomLength = 1 + random.nextInt(MAX_LENGTH_EMAIL - EMAIL_DEFAULT.length() - 1);
        for (int i = 0; i < randomLength; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(c);
        }
        return stringBuilder + EMAIL_DEFAULT;
    }

    public final boolean isMessageErrorValidateEmailShown() {
        WebElement parentContainer = wrapper.findElement(By.xpath("//label[contains(text(),'Email')]/.."));
        return parentContainer.findElement(By.className("help-block")).isDisplayed();
    }

    public final void onClickButtonSubmit() {
        waitForElement(driver, btnSubmit);
        if (isElementPresented(btnSubmit)) {
            btnSubmit.click();
        }
    }

    public final boolean isButtonSubmitEnable() {
        waitForElement(driver, btnSubmit);
        return btnSubmit.isEnabled();
    }

    private String setNumberWhileSpace(final int number) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < number; i++) {
            str.append(" ");
        }
        return str.toString();
    }
}
