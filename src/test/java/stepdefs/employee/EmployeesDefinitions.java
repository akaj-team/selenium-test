package stepdefs.employee;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.employee.EmployeesPage;

import static vn.asiantech.base.Constant.EMPLOYEE_PAGE_URL;
import static vn.asiantech.page.employee.EmployeesPage.MAXIMUM_CELL;

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
    private boolean isShowPositionList;
    private boolean isShowTypeList;
    private boolean isShowStatusList;
    private String employeePosition;
    private String employeeType;
    private String employeeStatus;

    public EmployeesDefinitions() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        employeesPage = initPage(getDriver(), EmployeesPage.class);

        Given("Display employees page", () -> {
            driver.get(EMPLOYEE_PAGE_URL);
            waitForPageDisplayed(getDriver(), EMPLOYEE_PAGE_URL, By.cssSelector(".ibox-content.main-content"));
        });

        Given("^Click on status view$", () -> isShowStatusList = employeesPage.clickStatus());

        Given("^Click on employee type view$", () -> isShowTypeList = employeesPage.getClickType());

        When("^Click on employee name$", () -> employeeProfileUrl = employeesPage.clickAndGetEmployeeName());

        When("^Click on employee avatar$", () -> employeeProfileUrl = employeesPage.clickAndGetEmployeeAvatar());

        When("^Click on employee code$", () -> employeeProfileUrl = employeesPage.clickAndGetEmployeeCode());

        When("^Click on manager name$", () -> managerUrl = employeesPage.clickAndGetManagerName());

        When("^Click on team name$", () -> teamUrl = employeesPage.clickAndGetTeamName());

        When("^Click on group name$", () -> groupUrl = employeesPage.clickAndGetGroupName());

        When("^Click on edit action$", () -> editEmployeeUrl = employeesPage.clickEditButtonAndGetLink());

        When("^Click on promotion button$", () -> employeesPage.clickPromotionButton());

        When("^Click on award category button$", () -> employeesPage.clickAwardCategory());

        When("^Click on new employee button$", () -> newEmployeeUrl = employeesPage.clickNewEmployeeAndGetLink());

        When("^Click on next indicator$", () -> employeesPage.clickNextIndicator());

        When("^Click on back indicator$", () -> employeesPage.clickBackIndicator());

        When("^Click on fist indicator$", () -> employeesPage.clickFirstIndicator());

        When("^Click on last indicator$", () -> employeesPage.clickLastIndicator());

        When("^Click on position view$", () -> isShowPositionList = employeesPage.clickPositionView());

        When("^Enter search employee with \"([^\"]*)\"$", (String name) -> {
            employeesPage.searchEmployee(name);
            String currentUrl = EMPLOYEE_PAGE_URL + ";name_cont=" + name;
            driver.get(currentUrl);
            waitForPageDisplayed(getDriver(), currentUrl, By.cssSelector(".ui-datatable-data.ui-widget-content"));
        });

        When("^Search position with \"([^\"]*)\"$", (String position) -> {
            if (isShowPositionList) {
                employeePosition = position;
                employeesPage.searchWithEmployeePosition(position);
            }
        });

        When("^Select any item in that type list$", () -> {
            if (isShowTypeList) {
                employeeType = employeesPage.getSelectType();
            }
        });

        When("^Select any item in that status list$", () -> {
            if (isShowStatusList) {
                employeeStatus = employeesPage.selectStatus();
            }
        });

        When("^Select any item in the position list$", () -> {
            if (isShowPositionList) {
                employeesPage.selectPosition();
            }
        });

        Then("^Open successfully profile page of that employee$", () -> {
            if (!employeeProfileUrl.equals("")) {
                waitForPageDisplayed(getDriver(), employeeProfileUrl, By.className("section-top"));
            }
        });

        Then("^Open successfully profile page of that manager$", () -> {
            if (!managerUrl.equals("")) {
                waitForPageDisplayed(getDriver(), managerUrl, By.className("section-top"));
            }
        });

        Then("^Open successfully a team detail page$", () -> {
            if (!teamUrl.equals("")) {
                waitForPageDisplayed(getDriver(), teamUrl, By.className("section-top"));
            }
        });

        Then("^Open successfully a group detail page$", () -> {
            if (!groupUrl.equals("")) {
                waitForPageDisplayed(getDriver(), groupUrl, By.className("section-top"));
            }
        });

        Then("^Open successfully update employee page$", () -> waitForPageDisplayed(driver, editEmployeeUrl, By.id("employee-form-wrapper")));

        Then("^Open an import promotion dialog$", () -> Assert.assertTrue(employeesPage.isAlertShowed("Import Promotion")));

        Then("^Open an import award dialog$", () -> Assert.assertTrue(employeesPage.isAlertShowed("Import Award")));

        Then("^Open successfully new employee page$", () -> waitForPageDisplayed(driver, newEmployeeUrl, By.id("employee-form-wrapper")));

        Then("^Show maximum (\\d+) cells$", (Integer cell) -> Assert.assertTrue((employeesPage.getCellSum() >= 0 && employeesPage.getCellSum() <= MAXIMUM_CELL)));

        Then("^Page indicator 1 is active$", () -> Assert.assertTrue(employeesPage.isOneIndicatorActive()));

        Then("^Corresponding page indicator is active$", () -> Assert.assertTrue(employeesPage.isIndicatorActive()));

        Then("^Check compatibility sum of cells with page number$", () -> Assert.assertTrue(employeesPage.isLeftContentAndPageIndicatorCorrect()));

        Then("^Show an employee list$", () -> Assert.assertFalse(employeesPage.isEmployeeListEmpty()));

        Then("^Show an empty message$", () -> Assert.assertTrue(employeesPage.isEmployeeListEmpty()));

        Then("^Show a position list$", () -> Assert.assertTrue(isShowPositionList));

        Then("^Display \"([^\"]*)\" message$", (String message) -> Assert.assertTrue(employeesPage.isNoResultMessageShowed(message)));

        Then("^Display a corresponding employee list$", () -> Assert.assertFalse(employeesPage.isEmployeeListEmpty()));

        Then("^Employee type is chose$", () -> {
            if (!employeeType.equals("")) {
                Assert.assertTrue(employeesPage.isTypeChose(employeeType));
            }
        });

        Then("^A status item is chose$", () -> {
            if (!employeeStatus.equals("")) {
                Assert.assertTrue(employeesPage.isStatusChose(employeeStatus));
            }
        });

        Then("^Display a corresponding position list with that position$", () -> Assert.assertTrue(employeesPage.isShowCorrectPositionList(employeePosition)));

        And("^Fist indicator and back indicator are not clickable$", () -> Assert.assertFalse(employeesPage.isFirstAndBackIndicatorClickable()));

        And("^Item \"([^\"]*)\" is selected$", (String position) -> Assert.assertTrue(employeesPage.isPositionSelected(position)));

        And("^Last indicator and next indicator are not clickable$", () -> Assert.assertFalse(employeesPage.isNextAndLastIndicatorClickable()));

        Then("^The import promotion button is clickable$", () -> Assert.assertFalse(employeesPage.isImportButtonClickable("Import Promotion")));

        When("^Click on cancel button$", () -> employeesPage.clickCancelButton());

        Then("^The import promotion dialog is dismissed$", () -> Assert.assertFalse(employeesPage.isPromotionDialogDismissed()));

        Then("^The import award category dialog is dismissed$", () -> Assert.assertFalse(employeesPage.isPromotionDialogDismissed()));

        Then("^The import award button is clickable$", () -> Assert.assertFalse(employeesPage.isImportButtonClickable("Import Award")));

        And("^Select award category dropdown$", () -> employeesPage.selectAwardCategoryDropDown());

        Then("^Data in award category dropdown is correct$", () -> Assert.assertTrue(employeesPage.isDataAwardCategoryCorrect()));
    }
}
