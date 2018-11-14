package vn.asiantech.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/27/18.
 */
public class AccessControlPage extends BasePage<AccessControlPage> {

    @FindBy(className = "wrapper-content")
    private WebElement wrapperContent;

    @FindBy(className = "ui-buttonset-4")
    private WebElement listItemInToolBox;

    @FindBy(id = "btn-submit-permission")
    private WebElement btnSubmit;

    @FindBy(id = "permission-list-wrapper")
    private WebElement dataTable;

    @FindBy(className = "app-alert")
    private WebElement appAlert;

    private By dropDown = By.className("dropdown-md");
    private WebElement itemSpinner;

    public AccessControlPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public final AccessControlPage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.ACCESS_CONTROL_PAGE_URL);
        return this;
    }

    public final void onClickItemInToolBox(final String position) {
        if (isElementPresented(listItemInToolBox)) {
            getItemInToolBox(position).click();
        }
    }

    public final boolean isColorItemTabCorrect(final String position, final String activeColor) {
        return getActualColor(getItemInToolBox(position)).equals(activeColor);
    }

    public final boolean isEnableBtnSubmit() {
        return btnSubmit.isEnabled();
    }

    public final void onCLickButtonSubmit() {
        btnSubmit.click();
    }

    public final boolean isAlertMessageShown() {
        return isElementPresented(appAlert);
    }

    public final boolean isBodyTableShown() {
        return isElementPresented(dataTable);
    }

    public final int getSumDropDown() {
        return wrapperContent.findElements(dropDown).size();
    }

    public final void onClickDropDownList(final int position) {
        WebElement dropDownList = wrapperContent.findElements(dropDown).get(position);
        dropDownList.click();
        itemSpinner = dropDownList.findElement(By.tagName("p-dropdown")).findElement(By.className("ng-trigger")).findElement(By.cssSelector(".ui-dropdown-items-wrapper"));
    }

    public final void onSelectItemInDropDown(final int position) {
        List<WebElement> listManager = itemSpinner.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        listManager.get(position).click();
    }

    public final boolean isColorOtherTabCorrect(final String position, final String defaultColor) {
        List<WebElement> listBtnTab = listItemInToolBox.findElements(By.className("ui-button-text-only"));
        for (int i = 0; i < listBtnTab.size(); i++) {
            if (i != Integer.parseInt(position)) {
                return getActualColor(listBtnTab.get(i)).equals(defaultColor);
            }
        }
        return false;
    }

    private WebElement getItemInToolBox(final String position) {
        List<WebElement> listBtnTab = listItemInToolBox.findElements(By.className("ui-button-text-only"));
        return listBtnTab.get(Integer.parseInt(position));
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
