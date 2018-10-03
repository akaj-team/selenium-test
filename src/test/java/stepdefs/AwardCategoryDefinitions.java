package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.AwardCategoryPage;

import java.util.List;

public class AwardCategoryDefinitions extends DriverBase implements En {
    private static final int TIMEOUTINSECONDS = 5;

    private AwardCategoryPage awardCategotyPage;
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;
    private int countCurrentLine = 0;

    public AwardCategoryDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^I am logged in as an team member$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            awardCategotyPage = initPage(getDriver(), AwardCategoryPage.class);
            new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                //Not logged in
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.thien.dang2@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
        });

        And("^Award Category page displayed$", this::displayPage);

        When("^Check count line in table", () -> this.setCountCurrentLine(awardCategotyPage.getCountLine()));

        When("^I click new award button$", () -> awardCategotyPage.clickNewAward());

        Then("^Show dialog new award$", () -> Assert.assertTrue(awardCategotyPage.isDisplayDialog()));

        When("^I click edit button$", () -> awardCategotyPage.clickEditButton());

        Then("^Show dialog edit$", () -> Assert.assertTrue(awardCategotyPage.isDisplayDialog()));

        Given("^New dialog is showed$", () -> awardCategotyPage.clickNewAward());

        When("^I input name on Name box$", () -> awardCategotyPage.enterName("Best project" + countCurrentLine));

        Then("^Submit change from disable to enable$", () -> Assert.assertTrue(awardCategotyPage.isEnableSubmitButton()));

        Given("^New dialog is showed and entered name$", () -> {
            awardCategotyPage.clickNewAward();
            awardCategotyPage.enterName("Best project" + System.currentTimeMillis());
        });

        When("^I click submit button$", () -> awardCategotyPage.clickSubmit());

        Then("^Table add one line$", () -> Assert.assertTrue(awardCategotyPage.checkAddLine(countCurrentLine + 1)));

        And("^With description empty$", () -> Assert.assertTrue(awardCategotyPage.isDescriptionEmpty(countCurrentLine)));

        When("^I enter description$", () -> awardCategotyPage.enterDescription());

        Given("^Dialog edit is showed$", () -> {
//            awardCategotyPage.isNotEmptyName() &&
            Assert.assertTrue(awardCategotyPage.isDisplayDialog());
        });

        When("^I clear name box$", () -> awardCategotyPage.clearText("name"));

        Then("^Display message \"([^\"]*)\"$", (String message) -> Assert.assertTrue(awardCategotyPage.isShowError()));

        And("^Disable submit button$", () -> Assert.assertFalse(awardCategotyPage.isEnableSubmitButton()));

        But("^I click close button$", () -> awardCategotyPage.clickCloseDialog());

        Then("^No line is added to table$", () -> Assert.assertTrue(awardCategotyPage.checkAddLine(countCurrentLine)));

        And("^Disappear dialog$", () -> Assert.assertFalse(awardCategotyPage.isDisplayDialog()));

        When("^I click cancel button$", () -> awardCategotyPage.clickCancelButton());

        Then("^Check name was exist$", () -> Assert.assertTrue(awardCategotyPage.isNameExist("Best project")));
    }

    private void displayPage() {
        driver.get("http://portal-stg.asiantech.vn" + "/admin/award-category");
        new WebDriverWait(driver, TIMEOUTINSECONDS).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        new WebDriverWait(driver, TIMEOUTINSECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.id("page-wrapper")));
        Assert.assertEquals("http://portal-stg.asiantech.vn" + "/admin/award-category", driver.getCurrentUrl());
    }

    private void setCountCurrentLine(int count) {
        this.countCurrentLine = count;
    }
}
