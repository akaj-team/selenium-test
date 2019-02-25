package stepdefs.admin;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.admin.AccessControlPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/27/18.
 */
public class AccessControlDefinitions extends BaseDefinitions implements En {

    private WebDriver driver;
    private AccessControlPage accessControlPage;

    public AccessControlDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        accessControlPage = initPage(driver, AccessControlPage.class);

        And("^I stayed in Access Control page$", () -> {
            driver.get(Constant.ACCESS_CONTROL_PAGE_URL);
            waitForPageDisplayed(driver, Constant.ACCESS_CONTROL_PAGE_URL, By.cssSelector(".ibox-content.main-content"));
        });

        // Check color item in toolbox when I click on any item
        When("^I click on access control tab item \"([^\"]*)\"$", (String position) -> {
            waitVisibilityOfElement(driver, By.className("ui-buttonset-4"));
            accessControlPage.onClickItemInToolBox(position);
        });
        Then("^Color of access control tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String activeColor) -> {
            waitVisibilityOfElement(driver, By.className("ui-buttonset-4"));
            Assert.assertTrue(accessControlPage.isColorItemTabCorrect(position, activeColor));
        });
        And("^Color other access control tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String defaultColor) -> {
            waitVisibilityOfElement(driver, By.className("ui-buttonset-4"));
            Assert.assertTrue(accessControlPage.isColorOtherTabCorrect(position, defaultColor));
        });
        And("^Button Submit of access control is enable$", () -> {
            waitVisibilityOfElement(driver, By.id("btn-submit-permission"));
            Assert.assertTrue(accessControlPage.isButtonSubmitAbleClick());
        });

        // I click on tab Item, check view is displayed in this tab
        Then("^Has \"([^\"]*)\" drop down is displayed$", (String sum) -> {
            if (Integer.parseInt(sum) > 0) {
                new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toolbox-item.dropdown-md.ng-star-inserted")));
            }
            Assert.assertEquals(accessControlPage.getSumDropDown(), Integer.parseInt(sum));
        });
        And("^BodyTable is displayed$", () -> {
            waitVisibilityOfElement(driver, By.id("permission-list-wrapper"));
            Assert.assertTrue(accessControlPage.isBodyTableShown());
        });
        And("^Button Submit is \"([^\"]*)\"$", (String isEnable) -> Assert.assertEquals(accessControlPage.isButtonSubmitEnable(), Boolean.parseBoolean(isEnable)));

        // Open dropdown Role and click on any Role
        When("^I open dropDown Role$", () -> {
            waitVisibilityOfElement(driver, By.className("dropdown-md"));
            accessControlPage.onClickDropDownList(0);
            waitVisibilityOfElement(driver, By.className("ui-dropdown-open"));
        });
        And("^I click on any role$", () -> accessControlPage.onSelectItemInDropDown(0));

        // Open dropdown Team and click on any Team
        When("^I open DropDown Team$", () -> {
            waitVisibilityOfElement(driver, By.className("dropdown-md"));
            accessControlPage.onClickDropDownList(1);
            waitVisibilityOfElement(driver, By.className("ui-dropdown-open"));
        });
        And("^I click on any team$", () -> accessControlPage.onSelectItemInDropDown(2));

        // Open dropdown Group and click on any group
        When("^I open DropDown Group$", () -> {
            waitVisibilityOfElement(driver, By.className("dropdown-md"));
            accessControlPage.onClickDropDownList(1);
            waitVisibilityOfElement(driver, By.className("ui-dropdown-open"));
        });
        And("^I click on any group$", () -> accessControlPage.onSelectItemInDropDown(1));

        // Click on button Submit and show alert message
        When("^I click on Button Submit$", () -> {
            waitVisibilityOfElement(driver, By.id("btn-submit-permission"));
            accessControlPage.onCLickButtonSubmit();
        });

        Then("^I should see the alert message$", () -> {
            waitVisibilityOfElement(driver, By.className("app-alert"));
            Assert.assertTrue(accessControlPage.isAlertMessageShown());
        });
    }
}
