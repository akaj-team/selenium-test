package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.PositionsPage;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author at-trungnguyen
 */
public class PositionsDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private PositionsPage positionsPage;

    public PositionsDefinitions() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Displayed positions page$", () -> {
            driver.get(Constant.POSITION_PAGE_URL);
            positionsPage = initPage(getDriver(), PositionsPage.class);
            waitForPageDisplayed(driver, Constant.POSITION_PAGE_URL, By.id("page-wrapper"));
        });

        Then("^I see header title \"([^\"]*)\" is display$", (String title) -> Assert.assertEquals(positionsPage.getTitle(driver), title));

        Then("^I see button new position and career path is display$", () -> {
            Assert.assertTrue(positionsPage.getButtonCareerPath(driver).isDisplayed());
            Assert.assertTrue(positionsPage.getButtonCareerPath(driver).isDisplayed());
        });

        When("^I click on new position button$", () -> positionsPage.getButtonNewPosition(driver).click());

        Then("^I move to new position page$", () -> waitForPageDisplayed(driver, Constant.POSITION_PAGE_URL + "/new", By.className("btn-submit")));

        When("^I click on career path button$", () -> positionsPage.getButtonCareerPath(driver).click());

        Then("^I move to career path page$", () -> waitForPageDisplayed(driver, Constant.POSITION_PAGE_URL + "/tree", By.className("panel-heading")));

        When("^I click on a row in table position$", () -> {
            WebElement webElement = positionsPage.getCellDataTable(driver, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I move to position detail page$", () -> waitForPageDisplayed(driver, positionsPage.getUrlOfCell(), By.className("section-top")));

        When("^I click on update button in a row$", () -> {
            WebElement webElement = positionsPage.getCellEditInTable(driver, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I move to update position page$", () -> waitForPageDisplayed(driver, positionsPage.getUpdatePositionUrl(), By.className("btn-submit")));

        When("^I click on delete button in a row$", () -> {
            WebElement webElement = positionsPage.getCellDeleteInTable(driver, 1);
            if (webElement != null) {
                webElement.click();
            }
        });

        Then("^I see dialog confirm delete position is display$", () -> Assert.assertTrue(positionsPage.isDialogConfirmDeleteDisplay(driver)));

        When("^I write \"([^\"]*)\" and press enter on search field$", (String text) -> {
            positionsPage.searchPosition(driver, text);
            String redirectUrl = Constant.POSITION_PAGE_URL + ";long_name_cont=" + text;
            waitForPageRedirected(driver, redirectUrl, By.cssSelector(".ui-datatable-data.ui-widget-content"));
        });

        Then("^The table show positions with long name are contains \"([^\"]*)\" value$", (String text) -> {
            waitVisibilityOfElement(driver, By.cssSelector(".ui-widget-content.ng-star-inserted"));
            waitVisibilityOfElement(driver, By.className("ui-cell-data"));
            waitVisibilityOfElement(driver, By.className("item-name"));
            List<String> names = positionsPage.getListLongNameInTable(driver);
            boolean isMatch = true;
            for (String name : names) {
                if (!Pattern.compile(Pattern.quote(text), Pattern.CASE_INSENSITIVE).matcher(name).find()) {
                    isMatch = false;
                }
            }
            Assert.assertTrue(isMatch);
        });

        And("^I should see empty message \"([^\"]*)\"$", (String message) -> {
            waitVisibilityOfElement(driver, By.className("ui-datatable-emptymessage-row"));
            Assert.assertEquals(message, positionsPage.showMessageEmptyTeam(driver));
        });
    }
}
