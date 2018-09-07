package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.Constant;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.TimeSheetPage;

import java.util.List;

public class TimeSheetDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private WebElement usernameInput;
    private WebElement passwordInput;

    private TimeSheetPage myTimeSheet;

    public TimeSheetDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        myTimeSheet = initPage(getDriver(), TimeSheetPage.class);
        Given("^I logged in with a employee account$", () -> {
            driver.get("http://portal-stg.asiantech.vn");
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            String url = driver.getCurrentUrl();
            if (url.endsWith("/auth/login")) {
                //Not logged in
                List<WebElement> formInputs = driver.findElements(By.className("form-control"));
                usernameInput = formInputs.get(0);
                passwordInput = formInputs.get(1);
                usernameInput.sendKeys("stg.tri.pham@asiantech.vn");
                passwordInput.sendKeys("Abc123@@");
                driver.findElement(By.className("btn-primary")).click();
                new WebDriverWait(driver, 5).until(
                        webDriver -> webDriver.findElement(By.className("welcome-message")).isDisplayed());
                Assert.assertTrue(driver.findElement(By.className("welcome-message")).isDisplayed());
            } else {
                Assert.assertTrue(true);
            }
        });

        When("^I click on Timesheet in menu$", () -> myTimeSheet.clickItemTimeSheet());
        Then("^Menu Timesheet drop down$", () -> Assert.assertFalse(myTimeSheet.checkLeaveMenuDropDown()));
        When("^I click on item Timesheet$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        When("^I click on item My Timesheet$", () -> myTimeSheet.clickMyTimeSheet());
        Then("^My Timesheet page is displayed \"([^\"]*)\"$", this::redirectPageWhenClickChildItem);
        And("^Title content is \"([^\"]*)\"$", (String content) -> Assert.assertTrue(myTimeSheet.checkTextStatusMenu(content)));
        And("^Display full record timesheet", () -> {
            Assert.assertTrue(true, myTimeSheet.isDisplayFullColumns(driver).toString());
        });
        And("^Disable button this week and can not click", () -> {
            Assert.assertTrue(true, myTimeSheet.getClickable(driver, "btnThisWeek").toString());
        });
        And("^Disable button submit and can not click", () -> {
            Assert.assertTrue(true, myTimeSheet.getClickable(driver, "btnSubmit").toString());
        });

        Given("^I open my timesheet page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            getDriver().get(Constant.TIME_SHEET_PAGE_URL);
        });
        When("^Click on back button$", () -> myTimeSheet.clickBackButton(driver));
        When("^Click on next button$", () -> myTimeSheet.clickNextButton(driver));
        Then("^Can click this week button$", () -> {
            Assert.assertTrue(true, myTimeSheet.getClickable(driver, "btnThisWeek").toString());
        });

        When("^Move to columns timesheet$", () -> {
            myTimeSheet.moveRowTimeSheet(driver);
        });
        Then("^Display button add new timesheet$", () -> {
            Assert.assertTrue(true, myTimeSheet.getAddTimeSheetClickable(driver).toString());
        });

        When("^Display dialog create timesheet after click button add timesheet$", () -> myTimeSheet.getAddTimeSheetsClickable());
        When("^Click on button timesheet$", () -> myTimeSheet.clickAddTimeSheets(driver));
        When("^Click first button add new timesheet$", () -> myTimeSheet.clickFirstItemAddTimeSheet(driver));
        Then("^Display dialog timesheet$", () -> {
            Assert.assertTrue(true, myTimeSheet.isTimeSheetShowing(driver).toString());
        });

    }

    private void redirectPageWhenClickChildItem(String path) {
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed()
        );
        String url = getDriver().getCurrentUrl();
        Assert.assertEquals(path, url.substring(url.length() - path.length()));
    }
}
