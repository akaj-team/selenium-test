//package stepdefs.team;
//
//import cucumber.api.java8.En;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import vn.asiantech.base.DriverBase;
//import vn.asiantech.page.team.TeamsPage;
//
///**
// * Copyright © 2018 Asian Tech Co., Ltd.
// * Created by at-vietphan on 9/18/18.
// */
//public class TeamsDefinitions extends DriverBase implements En {
//    private static final String URL_PAGE_TEAMS = "http://portal-stg.asiantech.vn/organisation/teams";
//    private WebDriver driver;
//    private TeamsPage teamsPage;
//    private String updateTeamUrl;
//    private String teamDetailUrl;
//    private String employeeDetailUrl;
//    private String newTeamUrl;
//    private String nameTeam;
//
//    public TeamsDefinitions() {
//        try {
//            driver = getDriver();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        teamsPage = initPage(getDriver(), TeamsPage.class);
//
//        And("^I am stayed in teams page$", () -> {
//            driver.get(URL_PAGE_TEAMS);
//            waitForPageDisplayed(driver, URL_PAGE_TEAMS, By.className("ibox-content"));
//        });
//        And("^I am stayed in dialog confirm delete team at position is (\\d+)$", (Integer position) -> {
//            driver.get(URL_PAGE_TEAMS);
//            waitForPageDisplayed(driver, URL_PAGE_TEAMS, By.className("ibox-content"));
//            nameTeam = teamsPage.onClickDeleteTeam(position);
//            Assert.assertTrue(teamsPage.isDeleteDialogShown());
//        });
//
//        //Search teams with enter name team
//        When("^Enter search with name is \"([^\"]*)\"$", (String searchData) -> {
//            teamsPage.searchNameTeam(searchData);
//            String redirectUrl = URL_PAGE_TEAMS + ";name_cont=" + searchData;
//            waitForPageRedirected(driver, redirectUrl, By.cssSelector(".ui-datatable-data.ui-widget-content"));
//        });
//        Then("^I should see list team$", () -> Assert.assertFalse(teamsPage.isTeamListEmpty()));
//        Then("^I should see list team is empty$", () -> Assert.assertTrue(teamsPage.isTeamListEmpty()));
//        And("^I should see alert message \"([^\"]*)\"$", (String message) -> Assert.assertEquals(message, teamsPage.showMessageEmptyTeam()));
//
//        // Open successfully profile when click on avatar or name team
//        When("^I click on avatar of team$", () -> {
//            waitVisibilityOfElement(driver, By.className("ui-datatable-data"));
//            teamDetailUrl = teamsPage.onClickAvatarTeam();
//        });
//        When("^I click on name team$", () -> {
//            waitVisibilityOfElement(driver, By.className("ui-datatable-data"));
//            teamDetailUrl = teamsPage.onClickNameTeam();
//        });
//        Then("^Team details is displayed$", () -> waitForPageRedirected(driver, teamDetailUrl, By.className("section-top")));
//
//        // Open successfully profile when click on username of manager
//        When("^I click on username of manager$", () -> {
//            waitVisibilityOfElement(driver, By.className("ui-datatable-data"));
//            employeeDetailUrl = teamsPage.onClickNameManager();
//        });
//        Then("^Manager profile is displayed$", () -> waitForPageRedirected(driver, employeeDetailUrl, By.className("section-top")));
//
//        // Open successfully new team page when click on button New Team
//        When("^I click on button New Team$", () -> {
//            waitVisibilityOfElement(driver, By.className("btn-create-team"));
//            newTeamUrl = teamsPage.onClickNewTeam();
//        });
//        Then("^Page is redirected to New Team page$", () -> waitForPageRedirected(driver, newTeamUrl, By.className("ibox-content")));
//
//        // Open successfully update team page when click on button Update
//        When("^I click on button update team at position is (\\d+)$", (Integer position) -> {
//            waitVisibilityOfElement(driver, By.className("ui-datatable-data"));
//            updateTeamUrl = teamsPage.onClickUpdateTeam(position);
//        });
//        Then("^Page is redirected to Update Team page$", () -> waitForPageRedirected(driver, updateTeamUrl, By.className("ibox-content")));
//
//        // Delete team function
//        When("^I click on button delete team at position is (\\d+)$", (Integer position) -> {
//            waitVisibilityOfElement(driver, By.className("ui-datatable-data"));
//            nameTeam = teamsPage.onClickDeleteTeam(position);
//        });
//        Then("^I should see dialog confirm delete team$", () -> Assert.assertTrue(teamsPage.isDeleteDialogShown()));
//        And("^Name team is correct with team is chose$", () -> Assert.assertEquals("Are you sure to delete " + nameTeam + " team?", teamsPage.getNameTeamIsDeleted()));
//        When("^I choose button cancel on dialog$", () -> teamsPage.onClickButtonCancelInDialogDelete());
//        When("^I choose button ok on dialog$", () -> teamsPage.onClickButtonDeleteInDialogDelete());
//        Then("^Dialog is dismiss$", () -> Assert.assertFalse(teamsPage.isDeleteDialogShown()));
//        Then("^Team is chose would be deleted$", () -> {
//            waitVisibilityOfElement(driver, By.className("app-alert"));
//            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
//        });
//    }
//}
