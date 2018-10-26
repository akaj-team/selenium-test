package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeaveOtherPage;

public class LeaveOtherDefinitions extends DriverBase implements En {
    private LeaveOtherPage leaveOtherPage;
    private WebDriver webDriver;
    private static final int TIME_OUT = 30;
    private String pathOrganisationEmployer;
    private String pathEmployerName;
    private int countItem;

    public LeaveOtherDefinitions() {
        try {
            webDriver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        And("^Open leave of other page$", () -> {
            webDriver.get("http://portal-stg.asiantech.vn/leave/tracking");
            leaveOtherPage = initPage(getDriver(), LeaveOtherPage.class);
            new WebDriverWait(webDriver, TIME_OUT).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals("http://portal-stg.asiantech.vn/leave/tracking", webDriver.getCurrentUrl());
        });
        And("^Wait for load data$", () -> leaveOtherPage.waitForLoadData(webDriver));

        When("^I click status item$", () -> leaveOtherPage.clickStatusItem());
        Then("^Should show status dropdown$", () -> Assert.assertTrue(leaveOtherPage.checkShowStatusDropdown()));
        When("^I click group by item$", () -> leaveOtherPage.clickGroupByItem());
        Then("^Should show group by dropdown$", () -> Assert.assertTrue(leaveOtherPage.checkShowGroupByItem()));
        When("^I click choose start date$", () -> leaveOtherPage.clickItemStartDate());
        Then("^Should show start date dialog$", () -> Assert.assertTrue(leaveOtherPage.checkShowStartDateDialog()));
        When("^I click end date$", () -> leaveOtherPage.clickItemEndDate());
        Then("^Should show end day dialog$", () -> Assert.assertTrue(leaveOtherPage.checkShowEndDateDialog()));

        Then("^I choose a start date$", () -> leaveOtherPage.chooseStartDate());
        Then("^I choose a end date$", () -> leaveOtherPage.chooseEndDate());
        When("^end date less than start date$", () -> Assert.assertTrue(leaveOtherPage.compareTwoDate()));
        Then("^show error message with text is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(leaveOtherPage.checkShowErrorMessage(message)));

        When("^leave of other no data$", () -> Assert.assertTrue(leaveOtherPage.checkDataEmpty()));
        Then("^show empty message with text is \"([^\"]*)\"$", (String message) -> Assert.assertTrue(leaveOtherPage.checkShowMessageEmpty(message)));

        Given("^Leave of other have data$", () -> {
            Assert.assertFalse(leaveOtherPage.checkDataEmpty());
            pathOrganisationEmployer = leaveOtherPage.getPathPageRedirect(0);
            pathEmployerName = leaveOtherPage.getPathPageRedirect(1);
            countItem = leaveOtherPage.countDataItem(webDriver);
        });
        When("^I click in employer id$", () -> leaveOtherPage.clickEmployerId());
        Then("^Should redirect to organisation employees$", () -> redirectPage(pathOrganisationEmployer));
        When("^I click in employees name$", () -> leaveOtherPage.clickEmployerName());
        Then("^Should redirect to profile detail$", () -> redirectPage(pathEmployerName));
        When("^I click in icon search$", () -> leaveOtherPage.clickSearchIcon());

        When("^Button next_to_end_page is shown$", () -> Assert.assertTrue(leaveOtherPage.checkButtonNextEndPageShown(countItem)));
        And("^I click button next_to_end_page$", () -> leaveOtherPage.clickButtonLast());
        Then("^Should show end page$", () -> Assert.assertEquals(getDriver().getCurrentUrl(), leaveOtherPage.getPathLastPage(countItem)));
    }

    private void redirectPage(final String path) {
        new WebDriverWait(getDriver(), TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}

