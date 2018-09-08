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

        Given("^I am stayed in home page$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.hang.nguyen@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, 5).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
            homePage.waitForElementsVisibility(getDriver());
        });
        And("^Header navigation is displayed$", () -> Assert.assertTrue(homePage.checkHeaderIsShown()));

        //Check data when click on tab navigation header
        When("^I click on tab item \"([^\"]*)\"$", (String tabName) -> homePage.onClickTabItems(tabName));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\"$", (String tabName, String colorActive) -> Assert.assertTrue(homePage.checkColorForTabItems(colorActive, tabName)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\"$", (String tabName, String colorDefault) -> Assert.assertTrue(homePage.checkColorForOtherTabItems(colorDefault, tabName)));

        //Check data when click on item in right slideBar
        When("^I click on tab item \"([^\"]*)\" in right sideBar$", (String tabName) -> homePage.onClickTabItemsInRightSideBar(tabName));
        Then("^Color of tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String tabName, String colorActive) -> Assert.assertTrue(homePage.checkColorForTabItemsInRightSideBar(tabName, colorActive)));
        And("^Color other tab \"([^\"]*)\" is \"([^\"]*)\" in right sideBar$", (String tabName, String colorDefault) -> Assert.assertTrue(homePage.checkColorForOtherTabItemsOnRightSideBar(tabName, colorDefault)));

        And("^I Should see data when has or no$", () -> Assert.assertTrue(homePage.checkShowHaveOrNoDataInHomeContent()));
        And("^Title New Feeds is displayed$", () -> Assert.assertTrue(driver.findElement(By.className("m-b-xs")).isDisplayed()));
        And("^ToolBox Search is displayed$", () -> Assert.assertTrue(driver.findElement(By.className("toolbox-search")).isDisplayed()));
        When("^I enter in toolbox search with string value is \"([^\"]*)\"$", (String valueSearch) -> homePage.withValueSearch(valueSearch));
        Then("^I should see data corresponding$", () -> {

        });

        And("^I should see data Today's Event and Upcoming Event on right sideBar$", () -> Assert.assertTrue(homePage.checkShowHaveOrNoDataOnRightSideBar()));

        When("^I click on username in social-box$", () -> {
            homePage.onClickUserNameOnHomeContent();
            new WebDriverWait(driver, 10).until(
                    webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });
        When("^I click on avatar in social-box$", () -> {
            homePage.onClickAvatarOnHomeContent();
            new WebDriverWait(driver, 10).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });
        When("^I click on avatar in right sideBar$", () -> {
            homePage.onClickAvatarOnRightSideBar();
            new WebDriverWait(driver, 10).until(webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });
        Then("^I should see User profile is displayed$", () -> Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed()));
    }
}
