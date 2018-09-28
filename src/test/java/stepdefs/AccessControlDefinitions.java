package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    }
}
