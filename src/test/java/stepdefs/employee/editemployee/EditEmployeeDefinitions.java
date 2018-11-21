package stepdefs.employee.editemployee;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.object.Employee;
import vn.asiantech.page.employee.EmployeesPage;
import vn.asiantech.page.employee.editemployee.EditEmployeePage;
import vn.asiantech.page.employee.newemployee.PersonalInformationPage;

/**
 * @author at-hangtran
 */
public class EditEmployeeDefinitions extends DriverBase implements En {
    private WebDriver driver;
    private EmployeesPage employeesPage;
    private EditEmployeePage editEmployeesPage;
    private PersonalInformationPage personalInformationPage;
    private Employee employee;
    private String editEmployeeUrl;

    public EditEmployeeDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        employeesPage = initPage(getDriver(), EmployeesPage.class);
        editEmployeesPage = initPage(getDriver(), EditEmployeePage.class);
        personalInformationPage = initPage(getDriver(), PersonalInformationPage.class);

        When("^Click on edit action of an employee", () -> {
            employee = employeesPage.getEditEmployee();
            editEmployeeUrl = employeesPage.clickEditButtonAndGetLink();
        });

        Then("^Open successfully edit employee page$", () -> waitForPageDisplayed(driver, editEmployeeUrl, By.id("employee-form-wrapper")));

        Given("^Next button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isNextButtonClickable()));

        Given("^Submit button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isSubmitButtonClickable()));

        Given("^Deactive button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isDeactiveButtonClickable()));

        Given("^Information of that employee displays correctly$", () -> {
            Assert.assertEquals(employee.name, editEmployeesPage.getEmployeeName());
            editEmployeesPage.clickNextButton();
            Assert.assertTrue(editEmployeesPage.isCompanyInfoTabActive());
            Assert.assertEquals(employee.code, editEmployeesPage.getEmployeeCode());
            Assert.assertEquals(employee.email, editEmployeesPage.getEmployeeEmail());
            Assert.assertEquals(employee.group, editEmployeesPage.getEmployeeGroup());
            Assert.assertEquals(employee.team, editEmployeesPage.getEmployeeTeam());
        });

        And("^Submit button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isDeactiveButtonClickable()));

        And("^Next button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isNextButtonClickable()));

        And("^Back button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isBackButtonClickable()));
    }
}
