package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HomePage;

import static vn.asiantech.page.HomePage.TIME_OUT_IN_SECONDS_10;
import static vn.asiantech.page.HomePage.URL_HOME_PAGE;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 02/09.
 */
public class HomeDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private HomePage homePage;

    public HomeDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        homePage = initPage(getDriver(), HomePage.class);

//        Check Home page is started
        Given("^I am stayed in home page$", () -> {
            driver.get(URL_HOME_PAGE);
            new WebDriverWait(driver, TIME_OUT_IN_SECONDS_10).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            homePage.waitForElementsVisibility(getDriver());
            Assert.assertEquals(URL_HOME_PAGE, driver.getCurrentUrl());
        });

//        Check color and data of tab on navigation header when clicked
        When("^I click on tab item \"([^\"]*)\"$", (String position) -> homePage.onClickTabInIBoxContent(position));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String colorActive) -> Assert.assertTrue(homePage.checkColorTabInIBoxContent(colorActive, position)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String colorDefault) -> Assert.assertTrue(homePage.checkColorOtherTabInIBoxContent(colorDefault, position)));
        And("^I Should see data when has or no$", () -> Assert.assertTrue(homePage.checkShowHaveOrNoDataInIBoxContent()));

//        Check function search
        When("^I fill to search with value is \"([^\"]*)\"$", (String valueSearch) -> homePage.sendKeysSearch(valueSearch));

//        Check color and data of tab on right sideBar when clicked
        When("^I click on tab item \"([^\"]*)\" in right sideBar$", (String position) -> homePage.onClickTabInRightSideBar(position));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String position, String colorActive) -> Assert.assertTrue(homePage.checkColorTabInRightSideBar(position, colorActive)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String position, String colorDefault) -> Assert.assertTrue(homePage.checkColorOtherTabInRightSideBar(position, colorDefault)));
        And("^I should see data Today's Event and Upcoming Event on right sideBar$", () -> Assert.assertTrue(homePage.checkShowHaveOrNoDataOnRightSideBar()));

//        Check click username on homeContent
        When("^I click on username in social-box$", () -> homePage.onClickUserName());

//        Check click avatar on homeContent
        When("^I click on avatar in social-box$", () -> homePage.onClickAvatarInIBoxContent());

//        Check click avatar on right sideBar
        When("^I click on avatar in right sideBar$", () -> homePage.onClickAvatarInRightSideBar());
//        Open successfully profile page
        Then("^Open successfully User Profile page$", () -> {
            new WebDriverWait(driver, TIME_OUT_IN_SECONDS_10).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

//        Check click on Flowers and Congrats
        When("^I click on btn Flowers$", () -> homePage.onClickBtnReaction(0));
        When("^I click on btn Congrats$", () -> homePage.onClickBtnReaction(1));
        Then("^I should see Flowers is displayed$", () -> {
            if (driver.findElements(By.cssSelector("button.btn-reaction.congrafs-btn.active")).size() > 0) {
                Assert.assertTrue(homePage.isShowEffectForReaction());
            }
        });
        Then("^I should see Congrats is displayed$", () -> {
            if (driver.findElements(By.cssSelector("button.btn-reaction.congrafs-btn.active")).size() > 0) {
                Assert.assertTrue(homePage.isShowEffectForReaction());
            }
        });

//        Check scrollview on homeContent
        When("^I scroll down homeContent$", () -> homePage.scrollIBoxContent(driver, true));
        Then("^I scroll up homeContent$", () -> homePage.scrollIBoxContent(driver, false));

//        Check scrollview on right sideBar
        When("^I scroll down right sideBar$", () -> homePage.scrollRightSideBar(driver, true));
        Then("^I scroll up right sideBar$", () -> homePage.scrollRightSideBar(driver, false));
    }
}
