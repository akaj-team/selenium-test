package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.HomePage;

import static vn.asiantech.page.HomePage.TIME_OUT_SECOND_NORMAL;
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

        // Check Home page is started
        Given("^I am stayed in home page$", () -> {
            getDriver().get("http://portal-stg.asiantech.vn/homepage");
            new WebDriverWait(getDriver(), 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            waitForPageDisplayed(driver, URL_HOME_PAGE, By.cssSelector(".is-top.is-home"));
        });

        // Check color and data of tab on navigation header when clicked
        When("^I click on tab item \"([^\"]*)\"$", (String position) -> homePage.onClickTabInIBoxContent(position));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String activeColor) -> Assert.assertTrue(homePage.isColorTabInIBoxContentCorrect(activeColor, position)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\"$", (String position, String defaultColor) -> Assert.assertTrue(homePage.isColorOtherTabInIBoxContentCorrect(defaultColor, position)));
        And("^I Should see data when has or no$", () -> Assert.assertTrue(homePage.isInIBoxContentShowed()));

        // Check function search
        When("^I fill to search with value is \"([^\"]*)\"$", (String searchData) -> homePage.sendKeysSearch(searchData));
        Then("^I should see list feed is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.className("notification-container")).findElement(By.className("social-feed-box")).isDisplayed());
            Assert.assertFalse(homePage.isFeedListEmpty());
        });
        Then("^I should see message \"([^\"]*)\"$", (String message) -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.cssSelector(".text-center.ng-star-inserted")).findElement(By.tagName("h2")).isDisplayed());
            Assert.assertEquals(homePage.getEmptyTeamMessage(), message);
        });

        // Check color and data of tab on right sideBar when clicked
        When("^I click on tab item \"([^\"]*)\" in right sideBar$", (String position) -> homePage.onClickTabInRightSideBar(position));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String position, String activeColor) -> Assert.assertTrue(homePage.isColorTabInRightSideBarCorrect(position, activeColor)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String position, String defaultColor) -> Assert.assertTrue(homePage.isColorOtherTabInRightSideBarCorrect(position, defaultColor)));
        And("^I should see data Today's Event and Upcoming Event on right sideBar$", () -> Assert.assertTrue(homePage.isRightSideBarShowed()));

        // Click username on homeContent
        When("^I click on username in social-box$", () -> homePage.onClickUserName());

        // Click avatar on homeContent
        When("^I click on avatar in social-box$", () -> homePage.onClickAvatarInIBoxContent());

        // Click avatar on right sideBar
        When("^I click on avatar in right sideBar$", () -> homePage.onClickAvatarInRightSideBar());

        // Open successfully profile page
        Then("^Open successfully User Profile page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND_NORMAL).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });

        // Show effect when click on Flowers and Congrats
        When("^I click on button Flowers$", () -> homePage.onClickButtonReaction(0));
        When("^I click on button Congrats$", () -> homePage.onClickButtonReaction(1));
        Then("^I should see Flowers is displayed$", () -> Assert.assertTrue(homePage.isShowEffectForReaction()));
        Then("^I should see Congrats is displayed$", () -> Assert.assertTrue(homePage.isShowEffectForReaction()));

        // Check scrollview on homeContent
        When("^I scroll down homeContent$", () -> homePage.scrollIBoxContent(driver, true));
        Then("^I scroll up homeContent$", () -> homePage.scrollIBoxContent(driver, false));

        // Check scrollview on right sideBar
        When("^I scroll down right sideBar$", () -> homePage.scrollRightSideBar(driver, true));
        Then("^I scroll up right sideBar$", () -> homePage.scrollRightSideBar(driver, false));
    }
}
