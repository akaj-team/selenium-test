package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.ProjectPage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

public class ProjectsDefinitions extends DriverBase implements En {

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
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(ProjectPage.PROJECT_PAGE_URL, driver.getCurrentUrl());
        });

        Then("^List project should display$", () -> Assert.assertTrue(ProjectPage.DEFAULT_PAGE_COUNT >= projectPage.getProjectCount(driver)));

        When("^I search with \"([^\"]*)\"$", (String arg0) -> projectPage.searchWith(driver, arg0));

        Then("^Should search with correct key is \"([^\"]*)\"$", (String arg0) -> new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
                webDriver -> !webDriver.getCurrentUrl().equals("http://portal-stg.asiantech.vn/project-management/projects;status_eq=in_progress;name_cont=" + arg0)));

        And("^Project name should be \"([^\"]*)\"$", (String arg0) -> Assert.assertEquals(arg0, projectPage.getProjectName(driver)));

        When("^I click on project name$", () -> projectPage.projectNameClick(driver));

        Then("^should go to correct detail page$", () -> {
            new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).until(
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

        When("^I click on end date item of table filter item$", () -> projectPage.tableOptionItemClick(driver));

        Then("^End date should display as current choose$", () -> Assert.assertEquals(projectPage.getCurrentTableOption(), projectPage.tableOptionDisplayed()));
    }
}
