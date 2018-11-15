package stepdefs.project;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.project.ProjectPage;

/**
 * @author at-vinhhuynh
 */
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
            waitForPageDisplayed(getDriver(), Constant.PROJECT_PAGE_URL, null);
        });

        Then("^List project should display$", () -> Assert.assertTrue(projectPage.getProjectCount() != 0));

        When("^I search with \"([^\"]*)\"$", (String key) -> projectPage.searchWith(key));

        Then("^Should search with correct key is \"([^\"]*)\"$", (String key) -> new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                webDriver -> !webDriver.getCurrentUrl().equals(Constant.PROJECT_PAGE_URL + ";status_eq=in_progress;name_cont=" + key)));

        And("^Project name should be \"([^\"]*)\"$", (String projectName) -> Assert.assertEquals(projectName, projectPage.getProjectName()));

        When("^I click on project name$", () -> projectPage.projectNameClick());

        Then("^should go to correct detail page$", () -> {
            new WebDriverWait(getDriver(), Constant.DEFAULT_TIME_OUT).until(
                    webDriver -> webDriver.getCurrentUrl().equals(projectPage.getProjectUrl()));
            Assert.assertEquals(projectPage.getProjectUrl(), driver.getCurrentUrl());
        });

        When("^I click status$", () -> projectPage.statusFilterClick());

        Then("^List status should display$", () -> Assert.assertTrue(projectPage.filterListDisplayed()));

        When("^I click on item of filter list$", () -> projectPage.statusFilterClick().filterItemClick());

        Then("^Filter tex should display correct$", () -> Assert.assertEquals(projectPage.getCurrentStatusFilter(), projectPage.getStatusDisplayed()));

        When("^I click on project avatar$", () -> projectPage.projectAvatarClick());

        When("^I click on project code$", () -> projectPage.projectCodeClick());

        When("^I click on table filter item$", () -> projectPage.currentTableOptionItemClick());

        Then("^Table of field should show$", () -> Assert.assertTrue(projectPage.tableFilterDisplayed()));

        Given("^Table of field is displayed$", () -> {
            projectPage.currentTableOptionItemClick();
            Assert.assertTrue(projectPage.tableFilterDisplayed());
        });
    }
}
