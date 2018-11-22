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
            Assert.assertEquals(employee.getName(), editEmployeesPage.getEmployeeName());
            editEmployeesPage.clickNextButton();
            Assert.assertTrue(editEmployeesPage.isCompanyInfoTabActive());
            Assert.assertEquals(employee.getCode(), editEmployeesPage.getEmployeeCode());
            Assert.assertEquals(employee.getEmail(), editEmployeesPage.getEmployeeEmail());
            Assert.assertEquals(employee.getGroup(), editEmployeesPage.getEmployeeGroup());
            Assert.assertEquals(employee.getTeam(), editEmployeesPage.getEmployeeTeam());
        });

        And("^Submit button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isSubmitButtonClickable()));

        And("^Next button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isNextButtonClickable()));

        And("^Back button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isBackButtonClickable()));

        When("^Click deactive button$", () -> editEmployeesPage.clickDeactiveButton());

        Then("^A notification dialog is display$", () -> Assert.assertTrue(editEmployeesPage.isDialogDisplayed()));

        When("^Click close button$", () -> {
            if (editEmployeesPage.getDeactiveDialogTitle().equals("Notification")) {
                editEmployeesPage.clickCloseButtonOfDeactiveDialog();
            }
        });

        Then("^This deactive dialog dismissed$", () -> Assert.assertTrue(editEmployeesPage.isDeactiveDialogDismissed()));


        When("^Choose day to deactivate this employee$", () -> {
            if (editEmployeesPage.getDeactiveDialogTitle().contains(employee.getName())) {
                editEmployeesPage.chooseDateToDeactivate();
            }
        });

        Then("^Click deactive button of dialog button$", () -> editEmployeesPage.clickDeactiveButtonOfDialog());

        Then("^Deactivate successfully this employee$", () -> Assert.assertTrue(editEmployeesPage.isDeactiveDialogDismissed()));

        Then("^Click cancel button of dialog button$", () -> editEmployeesPage.clickCancelButtonOfDialog());

        When("^Click next button two times$", () -> {
            editEmployeesPage.clickNextButton();
            editEmployeesPage.clickNextButton();
        });

        Then("^Timeline tab is active$", () -> Assert.assertTrue(editEmployeesPage.isTimelineTabActive()));

        And("^Submit button is not displayed$", () -> Assert.assertFalse(editEmployeesPage.isSubmitButtonDisplayed()));

        And("^Deactive button is not displayed$", () -> Assert.assertFalse(editEmployeesPage.isDeactiveButtonDisplayed()));

        Then("^Click new event button$", () -> editEmployeesPage.clickNewEventButton());

        And("^A new event dialog displayed$", () -> Assert.assertTrue(editEmployeesPage.isDialogDisplayed()));

        And("^Select type of event \"([^\"]*)\"$", (String event) -> editEmployeesPage.chooseTypeOfEvent(event));

        When("^Select position of promotion$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        When("^Select date of promotion$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        When("^Click submit button of new event dialog$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}
