//package stepdefs;
//
//import cucumber.api.java8.En;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import vn.asiantech.base.DriverBase;
//import vn.asiantech.page.LeavePlannerPage;
//
///**
// * @author at-hangtran
// */
//public class LeavePlannerDefinitions extends DriverBase implements En {
//
//    private static final String URL = "http://portal-stg.asiantech.vn/leave/planning";
//    private LeavePlannerPage leavePlanerPage;
//    private WebDriver driver;
//    private String profileLink;
//
//    public LeavePlannerDefinitions() {
//        try {
//            driver = getDriver();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Given("^Display leave planner page$", () -> {
//            driver.get(URL);
//            leavePlanerPage = initPage(driver, LeavePlannerPage.class);
//            waitForPageDisplayed(getDriver(), URL, By.id("page-wrapper"));
//        });
//
//        Then("^Can not click this week button$", () -> Assert.assertFalse(false, leavePlanerPage.getClickable(driver).toString()));
//
//        When("^Click on back button$", () -> leavePlanerPage.clickBackButton(driver));
//
//        Then("^Can click this week button$", () -> Assert.assertTrue(true, leavePlanerPage.getClickable(driver).toString()));
//
//        When("^Click on next button$", () -> leavePlanerPage.clickNextButton(driver));
//
//        When("^Click username$", () -> profileLink = leavePlanerPage.clickUserName());
//
//        Then("^Open successfully profile page of that user$", () -> {
//            waitForPageDisplayed(getDriver(), profileLink, By.className("section-top"));
//        });
//
//        When("^Move to avatar user$", () -> leavePlanerPage.moveToAvatar(driver));
//
//        Then("^Display leave message$", () -> Assert.assertTrue(true, leavePlanerPage.isShowLeaveMessage(driver).toString()));
//
//        Then("^Display full seven columns$", () -> Assert.assertTrue(true, leavePlanerPage.isDisplayFullColumns(driver).toString()));
//
//        Then("^Display time correctly$", () -> Assert.assertEquals(leavePlanerPage.getTableTime(driver), leavePlanerPage.getHeaderTime()));
//    }
//}
