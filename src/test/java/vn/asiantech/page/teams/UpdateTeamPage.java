package vn.asiantech.page.teams;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/20/18.
 */
public class UpdateTeamPage extends BasePage<UpdateTeamPage> {

    @FindBy(css = ".wrapper.wrapper-content")
    private WebElement wrapper;

    @FindBy(className = "ibox-content")
    private WebElement iboxContent;

    @FindBy(className = "title-action")
    private WebElement titleAction;

    @Override
    public final UpdateTeamPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void sendKeyInputName(final String name) {
        wrapper.findElement(By.name("name")).clear();
        wrapper.findElement(By.name("name")).sendKeys(name);
    }

    public final Boolean isBtnSubmitEnable() {
        try {
            return wrapper.findElement(By.name("submit")).isEnabled();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final boolean isShowMessageError() {
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

    public final void onClickBtnTeams() {
        titleAction.findElement(By.tagName("a")).click();
    }
}
