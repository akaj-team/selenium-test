package stepdefs.wiki;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.wiki.WikiPage;

/**
 * @author at-anh.quach
 * WikiDefinitions
 */

public class WikiDefinitions extends BaseDefinitions implements En {
    private static final int NUMBER_HOMEPAGE = 168;
    private static final int NUMBER_AT_LIBRARY = 190;

    private WebDriver driver;
    private WikiPage wikiPage;

    public WikiDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        wikiPage = initPage(driver, WikiPage.class);

        When("^I click on name author$", () -> wikiPage.clickAuthor());

        Then("^Redirect employee detail page$", () -> displayPage("/organisation/employees/42", By.className("page-detail")));

        When("^I click on child page button$", () -> wikiPage.clickChildPageButton());

        Then("^Redirect new page page$", () -> displayPage("/wiki/" + NUMBER_HOMEPAGE + "/new", By.id("wiki-form-wrapper")));

        When("^I click on update button$", () -> wikiPage.clickUpdateButton());

        Then("^Redirect update wiki page$", () -> displayPage("/wiki/" + NUMBER_HOMEPAGE + "/update", By.id("wiki-form-wrapper")));

        When("^I click on child page title$", () -> wikiPage.clickTitle());

        Then("^Redirect wiki detail page$", () -> displayPage("/wiki/" + NUMBER_AT_LIBRARY, By.cssSelector(".wrapper.wrapper-content.wiki")));

        Then("^Can not click delete button$", () -> Assert.assertFalse(wikiPage.isEnableDeleteButton()));

        When("^I click on icon package Wiki$", () -> wikiPage.clickIconPackageWikiHome());

        And("^Wiki page displayed$", () -> displayPage("/wiki/" + NUMBER_HOMEPAGE, By.cssSelector(".wrapper.wrapper-content.wiki")));

        Then("^Categories disappeared$", () -> Assert.assertTrue(wikiPage.isRemoveCategories()));

        When("^I click on icon package Company Document$", () -> wikiPage.clickIconPackageCompanyDocument());

        Then("^Categories inside hide$", () -> Assert.assertFalse(wikiPage.isHideCategoryInside()));

        When("^I click on icon package Company Document again$", () -> wikiPage.clickIconPackageCompanyDocumentAgain());

        Then("^Categories inside show$", () -> Assert.assertTrue(wikiPage.isHideCategoryInside()));
    }

    private void displayPage(final String path, final By container) {
        driver.get(Constant.PORTAL_URL + path);
        waitForPageDisplayed(driver, Constant.PORTAL_URL + path, container);
    }
}
