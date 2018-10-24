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
    private static final int MAX_LENGTH_NAME_TEAM = 45;
    @FindBy(css = ".wrapper.wrapper-content")
    private WebElement wrapper;

    private WebElement itemManager;

    @Override
    public final NewTeamFormPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final String generateNameTeam() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int randomLength = 1 + random.nextInt(MAX_LENGTH_NAME_TEAM - 1);
        for (int i = 0; i < randomLength; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(c);
        }
        return "Test " + stringBuilder;
    }

    public final void sendKeyInputName(final String searchData, final int whileSpaceBegging, final int whileSpaceEnd) {
        wrapper.findElement(By.name("name")).clear();
        wrapper.findElement(By.name("name")).sendKeys(setNumberWhileSpace(whileSpaceBegging) + searchData + setNumberWhileSpace(whileSpaceEnd));
    }

    public final Boolean isButtonSubmitEnable() {
        return wrapper.findElement(By.id("btn-submit-team")).isEnabled();
    }

    public final boolean isMessageErrorShown() {
        WebElement parentContainer = wrapper.findElement(By.xpath("//label[contains(text(),'Name')]/.."));
        return parentContainer.findElement(By.className("help-block")).isDisplayed();
    }

    public final void onClickButtonSubmit() {
        if (isElementPresented(wrapper.findElement(By.id("btn-submit-team")))) {
            wrapper.findElement(By.id("btn-submit-team")).click();
        }
    }

    public final void clickDropDownListManager() {
        WebElement parentContainer = wrapper.findElement(By.xpath("//label[contains(text(),'Manager')]/.."));
        parentContainer.click();
        itemManager = parentContainer.findElement(By.tagName("div")).findElement(By.className("ui-dropdown-panel"));
    }

    public final void selectManager() {
        List<WebElement> listManager = itemManager.findElement(By.className("ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(1).click();
    }

    private String setNumberWhileSpace(final int number) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < number; i++) {
            str.append(" ");
        }
        return str.toString();
    }
}
