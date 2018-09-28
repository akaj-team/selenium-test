package vn.asiantech.page.teams;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    private static final String TYPE_VALID_HAS_WHITE_SPACE_AT_THE_BEGINNING = "whiteSpaceAtTheBeginning";
    private static final String TYPE_VALID_HAS_WHITE_SPACE_AT_THE_END = "whiteSpaceAtTheEnd";
    @FindBy(css = ".wrapper.wrapper-content")
    private WebElement wrapper;

    @FindBy(className = "ibox-content")
    private WebElement iboxContent;

    private WebElement itemManager;

    @Override
    public final NewTeamFormPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final StringBuilder generateNameTeam() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int randomLength = 1 + random.nextInt(MAX_LENGTH_NAME_TEAM - 1);
        for (int i = 0; i < randomLength; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(c);
        }
        return stringBuilder;
    }

    public final void sendKeyInputNameWithTypeValid(final String searchData, final String typeValid) {
        wrapper.findElement(By.name("name")).clear();
        if (typeValid != null) {
            switch (typeValid) {
                case TYPE_VALID_HAS_WHITE_SPACE_AT_THE_BEGINNING:
                    wrapper.findElement(By.name("name")).sendKeys(" " + searchData);
                    break;
                case TYPE_VALID_HAS_WHITE_SPACE_AT_THE_END:
                    wrapper.findElement(By.name("name")).sendKeys(searchData + " ");
                    break;
                default:
                    wrapper.findElement(By.name("name")).sendKeys(searchData);
            }
        } else {
            wrapper.findElement(By.name("name")).sendKeys(searchData);
        }
    }

    public final Boolean isBtnSubmitEnable() {
        try {
            return wrapper.findElement(By.name("submit")).isEnabled();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean isMessageErrorShown() {
        List<WebElement> formGroup = iboxContent.findElements(By.className("form-group"));
        try {
            return formGroup.get(0).findElement(By.className("help-block")).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void onClickBtnSubmit() {
        try {
            if (wrapper.findElement(By.name("submit")).isDisplayed()) {
                wrapper.findElement(By.name("submit")).click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public final void clickDropDownListManager() {
        WebElement dropDownListManager = iboxContent.findElements(By.className("form-group")).get(1).findElement(By.className("col-md-8"));
        dropDownListManager.click();
        itemManager = dropDownListManager.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
    }

    public final void selectManager() {
        List<WebElement> listManager = itemManager.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(1).click();
    }
}
