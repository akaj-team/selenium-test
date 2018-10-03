package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.AccessControlPage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/27/18.
 */
public class AccessControlDefinitions extends DriverBase implements En {
    private static final String URL_ACCESS_CONTROL_PAGE = "http://portal-stg.asiantech.vn/admin/acl";
    private static final int TIME_OUT_SECOND_NORMAL = 10;
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
            driver.get(URL_ACCESS_CONTROL_PAGE);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.cssSelector(".ibox-content.main-content")));
            Assert.assertEquals(URL_ACCESS_CONTROL_PAGE, driver.getCurrentUrl());
        });

        // Check color item in toolbox when I click on any item
        When("^I click on tab item \"([^\"]*)\"$", (String position) -> accessControlPage.onClickItemInToolBox(position));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String activeColor) -> Assert.assertTrue(accessControlPage.isColorItemTabCorrect(position, activeColor)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String defaultColor) -> Assert.assertTrue(accessControlPage.isColorOtherTabCorrect(position, defaultColor)));
        And("^Button Submit is enable$", () -> Assert.assertTrue(accessControlPage.isEnableBtnSubmit()));
        And("^Button Submit is unable$", () -> Assert.assertFalse(accessControlPage.isEnableBtnSubmit()));

        // I click on tab Item, check view is displayed in this tab
        Then("^Has \"([^\"]*)\" drop down is displayed$", (String sum) -> {
            if (Integer.parseInt(sum) > 0) {
                new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toolbox-item.dropdown-md.ng-star-inserted")));
            }
            Assert.assertEquals(accessControlPage.getSumDropDown(), Integer.parseInt(sum));
            System.out.print(accessControlPage.getSumDropDown() + " " + Integer.parseInt(sum));
        });
        And("^BodyTable is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("table-striped")));
            Assert.assertTrue(accessControlPage.isBodyTableShown(driver));
        });
        And("^Button Submit is \"([^\"]*)\"$", (String isEnable) -> {
            Assert.assertEquals(accessControlPage.isEnableBtnSubmit(), Boolean.parseBoolean(isEnable));
            System.out.print(accessControlPage.isEnableBtnSubmit() + " " + Boolean.parseBoolean(isEnable));

        });

        // Open dropdown Role and click on any Role
        When("^I open dropDown Role$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            accessControlPage.onClickDropDownList(0);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.className("ui-dropdown-open")));
        });
        And("^I click on any role$", () -> {
            accessControlPage.onSelectItemInDropDown(0);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL);
        });

        // Open dropdown Team and click on any Team
        When("^I open DropDown Team$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            accessControlPage.onClickDropDownList(1);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.className("ui-dropdown-open")));
        });
        And("^I click on any team$", () -> {
            accessControlPage.onSelectItemInDropDown(2);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL);
        });

        // Open dropdown Group and click on any group
        When("^I open DropDown Group$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            accessControlPage.onClickDropDownList(1);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.className("ui-dropdown-open")));
        });
        And("^I click on any group$", () -> {
            accessControlPage.onSelectItemInDropDown(1);
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL);
        });

        // Click on button Submit and show alert message
        When("^I click on Button Submit$", () -> accessControlPage.onCLickBtnSubmit());
        Then("^I should see the alert message$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(accessControlPage.isAlertMessageShown(driver));
        });
    }
}
