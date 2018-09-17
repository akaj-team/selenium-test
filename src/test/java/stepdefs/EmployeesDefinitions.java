package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.EmployeesPage;

import static vn.asiantech.page.EmployeesPage.*;

/**
 * @author at-hangtran
 */
public class EmployeesDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private EmployeesPage employeesPage;
    private String employeeProfileUrl;
    private String managerUrl;
    private String teamUrl;
    private String groupUrl;
    private String editEmployeeUrl;
    private String newEmployeeUrl;
    private Boolean isShowPositionList;
    private Boolean isShowTypeList;
    private String positionName;
    private String typeName;

    public EmployeesDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("Display employees page", () -> {
            driver.get("http://portal-stg.asiantech.vn/organisation/employees");
            employeesPage = initPage(getDriver(), EmployeesPage.class);
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals("http://portal-stg.asiantech.vn/organisation/employees", driver.getCurrentUrl());
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ibox-content.main-content")));
        });

        When("^Click on employee name$", () -> {
            employeeProfileUrl = employeesPage.clickEmployeeName();
        });

        When("^Click on employee avatar$", () -> {
            employeeProfileUrl = employeesPage.clickEmployeeAvatar();
        });

        When("^Click on employee code$", () -> {
            employeeProfileUrl = employeesPage.clickEmployeeCode();
        });

        Then("^Open successfully profile page of that employee$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(employeeProfileUrl, driver.getCurrentUrl());
        });


        When("^Click on manager name$", () -> {
            managerUrl = employeesPage.clickManagerName();
        });

        Then("^Open successfully profile page of that manager$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(managerUrl, driver.getCurrentUrl());
        });

        When("^Click on team name$", () -> {
            teamUrl = employeesPage.clickTeamName();
        });

        Then("^Open successfully a team detail page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(teamUrl, driver.getCurrentUrl());
        });

        When("^Click on group name$", () -> {
            groupUrl = employeesPage.clickGroupName();
        });

        Then("^Open successfully a group detail page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.className("section-top")));
            Assert.assertEquals(groupUrl, driver.getCurrentUrl());
        });

        When("^Click on edit action edit$", () -> {
            editEmployeeUrl = employeesPage.clickEditButton();
        });

        Then("^Open successfully update employee page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.urlToBe(editEmployeeUrl));
        });

        When("^Click on promotion button$", () -> {
            employeesPage.clickPromotionButton();
        });

        Then("^Open an import promotion dialog$", () -> {
            Assert.assertTrue(employeesPage.isAlertShowed("Import Promotion"));
        });

        When("^Click on award category button$", () -> {
            employeesPage.clickAwardCategory();
        });

        Then("^Open an import award dialog$", () -> {
            Assert.assertTrue(employeesPage.isAlertShowed("Import Award"));
        });

        When("^Click on new employee button$", () -> {
            newEmployeeUrl = employeesPage.clickNewEmployee();
        });

        Then("^Open successfully new employee page$", () -> {
            new WebDriverWait(driver, TIME_OUT_SECOND).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.urlToBe(newEmployeeUrl));
        });

        Then("^Show maximum (\\d+) cells$", (Integer cell) -> {
            Assert.assertTrue((employeesPage.getCellSum() >= 0 && employeesPage.getCellSum() <= 50));
        });

        Then("^Page indicator (\\d+) is active$", (Integer arg0) -> {
            Assert.assertTrue(employeesPage.isOneIndicatorActive());
        });

        And("^Fist indicator and back indicator are not clickable$", () -> {
            Assert.assertFalse(employeesPage.isFirstAndBackIndicatorClickable());
        });

        When("^Click on next indicator$", () -> {
            employeesPage.clickNextIndicator();
        });

        When("^Click on back indicator$", () -> {
            employeesPage.clickBackIndicator();
        });

        Then("^Page indicator corresponding is active$", () -> {
            Assert.assertTrue(employeesPage.isIndicatorActive());
        });

        Then("^Check compatibility sum of cells with page number$", () -> {
            Assert.assertTrue(employeesPage.compareLeftContentWithPageIndicator());
        });

        When("^Click on fist indicator$", () -> {
            employeesPage.clickFirstIndicator();
        });

        When("^Click on last indicator$", () -> {
            employeesPage.clickLastIndicator();
        });

        And("^Last indicator and next indicator are not clickable$", () -> {
            Assert.assertFalse(employeesPage.isNextAndLastIndicatorClickable());
        });

        When("^Enter search employee with \"([^\"]*)\"$", (String name) -> {
            employeesPage.searchEmployee(name);
            String currentUrl = "http://portal-stg.asiantech.vn/organisation/employees;name_cont=" + name;
            driver.get(currentUrl);
            new WebDriverWait(driver, 10).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Assert.assertEquals(currentUrl, driver.getCurrentUrl());
            new WebDriverWait(driver, TIME_OUT_SECOND).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-datatable-data.ui-widget-content")));
        });

        Then("^Show an employee list$", () -> {
            Assert.assertFalse(employeesPage.isEmployeeListEmpty());
        });

        Then("^Show an empty message$", () -> {
            Assert.assertTrue(employeesPage.isEmployeeListEmpty());
        });

        When("^Click on position view$", () -> {
            isShowPositionList = employeesPage.clickPositionView();
        });

        Then("^Show a position list$", () -> {
            Assert.assertTrue(isShowPositionList);
        });

        And("^Item \"([^\"]*)\" is selected$", (String position) -> {
            Assert.assertTrue(employeesPage.isPositionSelected(position));
        });

        When("^Search position with \"([^\"]*)\"$", (String position) -> {
            if (isShowPositionList) {
                positionName = position;
                employeesPage.searchPosition(position, driver);
            }
        });

        Then("^Display a position list corresponding with that position$", () -> {
            Assert.assertTrue(employeesPage.isShowCorrectPositionList(positionName));
        });

        Then("^Display \"([^\"]*)\" message$", (String message) -> {
            Assert.assertTrue(employeesPage.isShowNoResultMessage(message));
        });

        When("^Select any item in the position list$", () -> {
            if (isShowPositionList) {
                employeesPage.selectPosition(driver);
            }
        });

        Then("^Display an employee list corresponding$", () -> {
            Assert.assertFalse(employeesPage.isEmployeeListEmpty());
        });

        Given("^Click on employee type view$", () -> {
            isShowTypeList = employeesPage.clickType();
        });

        When("^Select any item in that list$", () -> {
            if (isShowTypeList) {
                typeName = employeesPage.selectType();
            }
        });

        Then("^Employee type is choosed$", () -> {
            Assert.assertTrue(employeesPage.isTypeChoosed(typeName));
        });
    }
}