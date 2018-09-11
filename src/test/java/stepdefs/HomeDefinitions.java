package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HomePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 02/09.
 */
public class HomeDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private HomePage homePage;
    private WebElement usernameInput;
    private WebElement passwordInput;

    public HomeDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        homePage = initPage(getDriver(), HomePage.class);

//        Check Home page is started
        Given("^I am stayed in home page$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            new WebDriverWait(driver, HomePage.TIME_OUT_IN_SECONDS_10).until(webDriver ->
                    ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.hang.nguyen@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, HomePage.TIME_OUT_IN_SECONDS_5).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
            homePage.waitForElementsVisibility(getDriver());
        });
        And("^Header navigation is displayed$", () ->
                Assert.assertTrue(homePage.checkHeaderIsShown()));

//        Check status button and data when click on tab navigation header
        When("^I click on tab item \"([^\"]*)\"$", (String tabName) ->
                homePage.onClickTabItems(tabName));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\"$", (String tabName, String colorActive) ->
                Assert.assertTrue(homePage.checkColorForTabItems(colorActive, tabName)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\"$", (String tabName, String colorDefault) ->
                Assert.assertTrue(homePage.checkColorForOtherTabItems(colorDefault, tabName)));
        And("^I Should see data when has or no$", () ->
                Assert.assertTrue(homePage.checkShowHaveOrNoDataInHomeContent()));

//        Check function search
        And("^ToolBox Search is displayed$", () ->
                Assert.assertTrue(driver.findElement(By.className("toolbox-search")).isDisplayed()));
        When("^I enter in toolbox search with string value is \"([^\"]*)\"$", (String valueSearch) ->
                homePage.withValueSearch(valueSearch));

//        Check status button and data when click on item in right sideBar
        When("^I click on tab item \"([^\"]*)\" in right sideBar$", (String tabName) ->
                homePage.onClickTabItemsInRightSideBar(tabName));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String tabName, String colorActive) ->
                Assert.assertTrue(homePage.checkColorForTabItemsInRightSideBar(tabName, colorActive)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String tabName, String colorDefault) ->
                Assert.assertTrue(homePage.checkColorForOtherTabItemsOnRightSideBar(tabName, colorDefault)));
        And("^Title New Feeds is displayed$", () ->
                Assert.assertTrue(driver.findElement(By.className("m-b-xs")).isDisplayed()));
        And("^I should see data Today's Event and Upcoming Event on right sideBar$", () ->
                Assert.assertTrue(homePage.checkShowHaveOrNoDataOnRightSideBar()));

//        Check click username on homeContent and open successfully profile page
        When("^I click on username in social-box$", () -> {
            homePage.onClickUserNameOnHomeContent();
            new WebDriverWait(driver, HomePage.TIME_OUT_IN_SECONDS_10).until(
                    webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });

//        Check click avatar on homeContent and open successfully profile page
        When("^I click on avatar in social-box$", () -> {
            homePage.onClickAvatarOnHomeContent();
            new WebDriverWait(driver, HomePage.TIME_OUT_IN_SECONDS_10).until(
                    webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });

//        Check click avatar on right sideBar and open successfully profile page
        When("^I click on avatar in right sideBar$", () -> {
            homePage.onClickAvatarOnRightSideBar();
            new WebDriverWait(driver, HomePage.TIME_OUT_IN_SECONDS_10).until(
                    webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });
        Then("^I should see User profile is displayed$", () ->
                Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed()));

//        Check click on Flowers and Congrats
        When("^I click on btn Flowers$", () -> homePage.onClickBtnReactionFlowers());
        When("^I click on btn Congrats$", () -> homePage.onClickBtnReactionCongrats());
        Then("^I should see Flowers is displayed$", () -> {
            if (driver.findElements(By.cssSelector("button.btn-reaction.congrafs-btn.active")).size() > 0) {
                Assert.assertTrue(driver.findElement(By.cssSelector("button.btn-reaction.congrafs-btn.active")).isDisplayed());
            }
        });
        Then("^I should see Congrats is displayed$", () -> {
            if (driver.findElements(By.cssSelector("button.btn-reaction.congrafs-btn.active")).size() > 0) {
                Assert.assertTrue(driver.findElement(By.cssSelector("button.btn-reaction.congrafs-btn.active")).isDisplayed());
            }
        });

//        Check scrollview on homeContent
        When("^I scroll down homeContent$", () -> homePage.scrollDownHomeContent(driver));
        Then("^I scroll up homeContent$", () -> homePage.scrollUpHomeContent(driver));

//        Check scrollview on right sideBar
        When("^I scroll down right sideBar$", () -> homePage.scrollDownRightSideBar(driver));
        Then("^I scroll up right sideBar$", () -> homePage.scrollUpRightSideBar(driver));
    }
}
