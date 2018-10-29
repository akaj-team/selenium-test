package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.ProjectPage;

/**
 * @author at-vinhhuynh
 */
public class ProjectsDefinitions extends DriverBase implements En {

    public static final String PROJECT_PAGE_URL = "http://portal-stg.asiantech.vn/project-management/projects";
    private WebDriver driver;
    private ProjectPage projectPage;

    public ProjectsDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        projectPage = initPage(getDriver(), ProjectPage.class);

        Given("^I access to projects page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            projectPage.navigateTo(driver);
            waitForPageDisplayed(getDriver(), PROJECT_PAGE_URL, null);
        });

        Then("^List project should display$", () -> Assert.assertTrue(projectPage.getProjectCount(driver) != 0));

        When("^I search with \"([^\"]*)\"$", (String key) -> projectPage.searchWith(driver, key));

        Then("^Should search with correct key is \"([^\"]*)\"$", (String key) -> new WebDriverWait(getDriver(), TIME_OUT_SECONDS_NORMAL).until(
                webDriver -> !webDriver.getCurrentUrl().equals(PROJECT_PAGE_URL + ";status_eq=in_progress;name_cont=" + key)));

        And("^Project name should be \"([^\"]*)\"$", (String projectName) -> Assert.assertEquals(projectName, projectPage.getProjectName(driver)));

        When("^I click on project name$", () -> projectPage.projectNameClick(driver));

        Then("^should go to correct detail page$", () -> {
            new WebDriverWait(getDriver(), TIME_OUT_SECONDS_NORMAL).until(
                    webDriver -> webDriver.getCurrentUrl().equals(projectPage.getProjectUrl()));
            Assert.assertEquals(projectPage.getProjectUrl(), driver.getCurrentUrl());
        });

        When("^I click status$", () -> projectPage.statusFilterClick(driver));

        Then("^List status should display$", () -> Assert.assertTrue(projectPage.filterListDisplayed(driver)));

        When("^I click on item of filter list$", () -> projectPage.statusFilterClick(driver).filterItemClick(driver));

        Then("^Filter tex should display correct$", () -> Assert.assertEquals(projectPage.getCurrentStatusFilter(), projectPage.getStatusDisplayed(driver)));

        When("^I click on project avatar$", () -> projectPage.projectAvatarClick(driver));

        When("^I click on project code$", () -> projectPage.projectCodeClick(driver));

        When("^I click on table filter item$", () -> projectPage.currentTableOptionItemClick(driver));

        Then("^Table of field should show$", () -> Assert.assertTrue(projectPage.tableFilterDisplayed(driver)));

        Given("^Table of field is displayed$", () -> {
            projectPage.currentTableOptionItemClick(driver);
            Assert.assertTrue(projectPage.tableFilterDisplayed(driver));
        });
    }
}
