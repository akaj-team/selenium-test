package stepdefs.teams;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.teams.NewTeamPage;

import static stepdefs.teams.TeamsDefinitions.URL_PAGE_TEAMS;
import static vn.asiantech.page.teams.TeamsPage.TIME_OUT_SECONDS_10;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/20/18.
 */
public class NewTeamDefinitions extends DriverBase implements En {
    private static final int INDEX_ID_IN_URL = 5;
    private WebDriver driver;
    private NewTeamPage mNewTeamPage;
    private Boolean isShowPositionList;

    public NewTeamDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNewTeamPage = initPage(getDriver(), NewTeamPage.class);
        When("^I open dropdown Manager$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p-dropdown")));
            isShowPositionList = mNewTeamPage.clickPositionView();
        });
        And("^I select a item in manager list$", () -> {
            if (isShowPositionList) {
                mNewTeamPage.selectManager();
            }
        });
        And("^I should see the created team success message$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("app-alert")));
            Assert.assertTrue(driver.findElement(By.className("app-alert")).isDisplayed());
        });
        Then("^Open successfully team detail page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECONDS_10).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            String id = driver.getCurrentUrl().split("/")[INDEX_ID_IN_URL];
            System.out.print(id);
            Assert.assertEquals(driver.getCurrentUrl(), URL_PAGE_TEAMS + "/" + id);
        });

    }
}
