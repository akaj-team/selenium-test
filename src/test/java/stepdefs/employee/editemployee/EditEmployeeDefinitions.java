package stepdefs.employee.editemployee;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.object.Employee;
import vn.asiantech.page.employee.EmployeesPage;
import vn.asiantech.page.employee.editemployee.EditEmployeePage;

/**
 * @author at-hangtran
 */
public class EditEmployeeDefinitions extends BaseDefinitions implements En {

    private WebDriver driver;
    private Employee employee;
    private String editEmployeeUrl;
    private String employeeProfileUrl;

    public EditEmployeeDefinitions() {

        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmployeesPage employeesPage = initPage(driver, EmployeesPage.class);
        EditEmployeePage editEmployeesPage = initPage(driver, EditEmployeePage.class);

        When("^Click on edit action of an employee", () -> {
            employee = employeesPage.getEditEmployee();
            employeeProfileUrl = employeesPage.getEmployeeProfileLink();
            editEmployeeUrl = employeesPage.clickEditButtonAndGetLink();
        });

        Then("^Open successfully edit employee page$", () -> waitForPageDisplayed(driver, editEmployeeUrl, By.id("employee-form-wrapper")));

        And("^Next button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isNextButtonClickable()));

        And("^Submit button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isSubmitButtonClickable()));

        And("^Deactivate button at edit employee page is clickable$", () -> Assert.assertTrue(editEmployeesPage.isDeactivateButtonClickable()));

        And("^Information of that employee displays correctly$", () -> {
            Assert.assertEquals(employee.getName(), editEmployeesPage.getEmployeeName());
            editEmployeesPage.clickNextButton();
            Assert.assertTrue(editEmployeesPage.isTabActive("Company's information", true));
            Assert.assertEquals(employee.getCode(), editEmployeesPage.getEmployeeCode());
            Assert.assertEquals(employee.getEmail(), editEmployeesPage.getEmployeeEmail());
            Assert.assertEquals(employee.getGroup(), editEmployeesPage.getEmployeeGroup());
            Assert.assertEquals(employee.getTeam(), editEmployeesPage.getEmployeeTeam());
            Assert.assertTrue(editEmployeesPage.getEmployeeManager().contains(employee.getManager()));
        });

        And("^Submit button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isSubmitButtonClickable()));

        And("^Next button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isNextButtonClickable()));

        And("^Back button at edit employee page is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isBackButtonClickable()));

        When("^Click deactivate button$", editEmployeesPage::clickDeactivateButton);

        Then("^A notification dialog is display$", () -> Assert.assertTrue(editEmployeesPage.isDialogDisplayed()));

        When("^Click close button$", editEmployeesPage::clickCloseButtonOfDeactivateDialog);

        Then("^This deactivate dialog dismissed$", () -> Assert.assertTrue(editEmployeesPage.isDeactivateDialogDismissed()));

        When("^Choose day to deactivate this employee$", () -> {
            if (editEmployeesPage.getDeactivateDialogTitle().contains(employee.getName())) {
                editEmployeesPage.chooseDateToDeactivate();
            }
        });

        And("^Click deactivate button of dialog button$", () -> {
            if (editEmployeesPage.getDeactivateDialogTitle().contains(employee.getName())) {
                editEmployeesPage.clickDeactivateButtonOfDialog();
            }
        });

        Then("^Deactivate this employee$", () -> {
            if (editEmployeesPage.getDeactivateDialogTitle().contains(employee.getName())) {
                Assert.assertTrue(editEmployeesPage.isDeactivateDialogDismissed());
            }
        });

        Then("^Click cancel button of dialog button$", () -> {
            if (editEmployeesPage.getDeactivateDialogTitle().contains(employee.getName())) {
                editEmployeesPage.clickCancelButtonOfDialog();
            }
        });

        Given("^Click \"([^\"]*)\" tab and this tab is active$", (String tabName) -> Assert.assertTrue(editEmployeesPage.isTabActive(tabName, false)));

        And("^Submit button is not displayed$", () -> Assert.assertFalse(editEmployeesPage.isSubmitButtonDisplayed()));

        And("^Deactivate button is not displayed$", () -> Assert.assertFalse(editEmployeesPage.isDeactivateButtonDisplayed()));

        When("^Click new event button$", editEmployeesPage::clickNewEventButton);

        Then("^A new event dialog displayed$", () -> Assert.assertTrue(editEmployeesPage.isDialogDisplayed()));

        When("^Select type of event \"([^\"]*)\"$", editEmployeesPage::chooseTypeOfEvent);

        And("^Select position and date of promotion$", () -> editEmployeesPage.choosePositionOfPromotion().chooseDateOfPromotion());

        And("^Click submit button of new event dialog$", editEmployeesPage::clickSubmitButtonEvent);

        Then("^This event is added correctly$", () -> {
            waitVisibilityOfElement(driver, By.className("app-alert"));
            Assert.assertTrue(editEmployeesPage.isEventAdded());
        });

        And("^Select employee type and date of promotion$", () -> editEmployeesPage.selectEmployeeType().chooseDateOfPromotion());

        And("^Select award type, date and fill praise for this award$", () -> editEmployeesPage.selectAward().chooseDateAward().fillPraise());

        When("^Click edit button of a new event$", editEmployeesPage::clickEditNewEvent);

        Then("^Edit successfully this new event$", () -> waitVisibilityOfElement(driver, By.className("app-alert")));

        When("^Click delete button of a new event$", editEmployeesPage::clickDeleteNewEvent);

        Then("^A confirm dialog displayed$", () -> Assert.assertTrue(editEmployeesPage.isConfirmDialogDisplayed()));

        When("^Click delete button of this dialog$", editEmployeesPage::clickSubmitButton);

        Then("^Delete successfully this new event$", () -> waitVisibilityOfElement(driver, By.className("app-alert")));

        When("^Click add relative button$", editEmployeesPage::clickRelativeButton);

        And("^Fill information contact with \"([^\"]*)\" and name of relative with \"([^\"]*)\"$", (String info, String name) -> editEmployeesPage.fillInfoContact(info).fillName(name));

        And("^Fill phone of relative with valid data \"([^\"]*)\"$", editEmployeesPage::fillPhone);

        And("^Click save button$", editEmployeesPage::clickSaveButton);

        Then("^A relative is created successfully$", () -> waitVisibilityOfElement(driver, By.className("app-alert")));

        And("^Fill phone of relative with invalid data \"([^\"]*)\"$", editEmployeesPage::fillPhone);

        Then("^A message error is displayed$", () -> Assert.assertTrue(editEmployeesPage.isPhoneErrorMessageDisplayed()));

        And("^Save button is not clickable$", () -> Assert.assertFalse(editEmployeesPage.isSaveButtonEnabled()));

        When("^Click delete button of a relative$", editEmployeesPage::clickDeleteButton);

        When("^Click remove button of this dialog$", editEmployeesPage::clickSubmitButton);

        Then("^Delete successfully this relative$", () -> waitVisibilityOfElement(driver, By.className("app-alert")));

        When("^Click submit button in this dialog$", editEmployeesPage::clickSubmitButton);

        Then("^Open successfully profile of this employee$", () -> waitForPageDisplayed(driver, employeeProfileUrl, By.className("section-top")));
    }
}
