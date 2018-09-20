package stepdefs.teams;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.teams.UpdateTeamPage;

import static stepdefs.teams.TeamsDefinitions.URL_PAGE_TEAMS;
import static stepdefs.teams.TeamsDefinitions.urlUpdateTeam;
import static vn.asiantech.page.teams.TeamsPage.TIME_OUT_SECONDS_10;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/20/18.
 */
public class UpdateTeamDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private UpdateTeamPage mUpdateTeamPage;

    public UpdateTeamDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUpdateTeamPage = initPage(getDriver(), UpdateTeamPage.class);

        // Validate UpdateTeamPage
        Then("^Button submit is unable$", () -> Assert.assertFalse(mUpdateTeamPage.isBtnSubmitEnable()));
        When("^I fill in inputName with \"([^\"]*)\"$", (String name) -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
            mUpdateTeamPage.sendKeyInputName(name);
        });
        Then("^Button submit is enable$", () -> Assert.assertTrue((mUpdateTeamPage.isBtnSubmitEnable())));
        And("^Message error is displayed$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(webDriver -> webDriver.findElement(By.className("form-group")).findElement(By.className("help-block")).isDisplayed());
            Assert.assertTrue(mUpdateTeamPage.isShowMessageError());
        });
        And("^Message error is hidden$", () -> Assert.assertFalse(mUpdateTeamPage.isShowMessageError()));

        // Click button Submit, redirect to Team Detail page
        When("^I click on button Submit$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
            mUpdateTeamPage.onClickBtnSubmit();
        });
        Then("^Open successfully team detail page of that team$", () -> {
            String getUrl = urlUpdateTeam.replace("/update", "");
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(driver.getCurrentUrl(), getUrl);
        });
        And("^I should see the update success message$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });

        // Open successfully teams page when click on button Team in title
        When("^I click on button Team in title$", () -> mUpdateTeamPage.onClickBtnTeams());
        Then("^Open successfully team page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.urlToBe(URL_PAGE_TEAMS));
            Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE_TEAMS);
        });
    }
}
