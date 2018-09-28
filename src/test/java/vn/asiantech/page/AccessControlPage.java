package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/27/18.
 */
public class AccessControlPage extends BasePage<AccessControlPage> {
    private static final String URL_PAGE_ACCESS_CONTROL = "http://portal-stg.asiantech.vn/admin/acl";

    @FindBy(className = "wrapper-content")
    private WebElement wrapperContent;

    @FindBy(className = "ui-buttonset-4")
    private WebElement btnSetInToolBox;

    private By titleOfDropdown = By.cssSelector(".toolbox-item.m-l-sm.ng-star-inserted");
    private By dropDown = By.cssSelector(".toolbox-item.dropdown-md.ng-star-inserted");
    private WebElement itemSpinner;

    @Override
    public final AccessControlPage navigateTo(final WebDriver webDriver) {
        webDriver.get(URL_PAGE_ACCESS_CONTROL);
        return this;
    }

    public final void onClickItemInToolBox(final String position) {
        if (isElementPresented(btnSetInToolBox)) {
            getItemInToolBox(position).click();
        }
    }

    public final boolean isColorItemTabCorrect(final String position, final String activeColor) {
        return getActualColor(getItemInToolBox(position)).equals(activeColor);
    }

    public final boolean isEnableBtnSubmit() {
        WebElement btnSubmit = wrapperContent.findElement(By.name("submit"));
        return btnSubmit.isEnabled();
    }

    public final boolean isTitleDropDownShown() {
        return getSumTitleOfDropDown().size() > 0;
    }

    public final boolean isDropDownShown() {
        return getSumDropDown().size() > 0;
    }

    public final boolean isCorrectSumTitleWithDropDown() {
        return getSumTitleOfDropDown().size() == getSumDropDown().size();
    }

    public final List getSumTitleOfDropDown() {
        return wrapperContent.findElements(titleOfDropdown);
    }

    public final List getSumDropDown() {
        return wrapperContent.findElements(dropDown);
    }

    public final boolean isDisplayedTitleOfDropDown() {
        WebElement title = wrapperContent.findElement(titleOfDropdown);
        return title.isDisplayed();
    }

    public final boolean isDisplayedDropDown() {
        WebElement drop = wrapperContent.findElement(dropDown);
        return drop.isDisplayed();
    }

    public final void clickDropDownList(final int position) {
        WebElement dropDownList = wrapperContent.findElements(dropDown).get(position);
        dropDownList.click();
        itemSpinner = dropDownList.findElement(By.tagName("p-dropdown")).findElement(By.className("ng-trigger")).findElement(By.cssSelector(".ui-dropdown-items-wrapper"));
    }

    public final void selectItemInDropDown(final int position) {
        List<WebElement> listManager = itemSpinner.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(position).click();
    }

    private WebElement getItemInToolBox(final String position) {
        List<WebElement> listBtnTab = btnSetInToolBox.findElements(By.className("ui-button-text-only"));
        return listBtnTab.get(Integer.parseInt(position));
    }

    public final boolean isColorOtherTabCorrect(final String position, final String defaultColor) {
        List<WebElement> listBtnTab = btnSetInToolBox.findElements(By.className("ui-button-text-only"));
        for (int i = 0; i < listBtnTab.size(); i++) {
            if (i != Integer.parseInt(position)) {
                return getActualColor(listBtnTab.get(i)).equals(defaultColor);
            }
        }
        return false;
    }

    private String getActualColor(final WebElement element) {
        String colorCss = element.getCssValue("color");
        String[] hexValue = new String[0];
        if (colorCss.contains("rgba")) {
            hexValue = colorCss.replace("rgba(", "").replace(")", "").split(",");
        } else if (colorCss.contains("rgb")) {
            hexValue = colorCss.replace("rgb(", "").replace(")", "").split(",");
        }
        return String.format("#%01x%01x%01x", Integer.parseInt(hexValue[0].trim()), Integer.parseInt(hexValue[1].trim()), Integer.parseInt(hexValue[2].trim()));
    }
}
