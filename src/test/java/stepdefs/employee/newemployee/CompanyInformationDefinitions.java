package stepdefs.employee.newemployee;

import cucumber.api.java8.En;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.BaseDefinitions;
import vn.asiantech.page.employee.newemployee.CompanyInformationPage;
import vn.asiantech.page.employee.newemployee.PersonalInformationPage;

/**
 * @author at-hangtran
 */
public class CompanyInformationDefinitions extends BaseDefinitions implements En {

    private WebDriver driver;
    private PersonalInformationPage personalInformationPage;
    private CompanyInformationPage companyInformationPage;
    private boolean isPositionSelected;
    private boolean isManagerSelected;

    public CompanyInformationDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        personalInformationPage = initPage(driver, PersonalInformationPage.class);
        companyInformationPage = initPage(driver, CompanyInformationPage.class);

        When("^Click the assign check box$", () -> companyInformationPage.clickAssignCheckbox());

        Then("^Checkbox is unchecked or checked$", () -> Assert.assertTrue(companyInformationPage.isAssignmentCheckboxChecked()));

        And("^Choose join date input$", () -> {
            companyInformationPage.clickJoinDateInput();
            if (companyInformationPage.isCalendarFormShowed()) {
                Assert.assertTrue(companyInformationPage.isTimeCorrect());
            }
        });

        And("^Choose employee type \"([^\"]*)\"$", (String empType) -> companyInformationPage.selectEmpType(empType));

        And("^Fill employee code input with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$", (String code, String aheadSpace, String behindSpace) ->
                companyInformationPage.fillEmpCode(code, Integer.valueOf(aheadSpace), Integer.valueOf(behindSpace)));

        And("^Select position$", () -> isPositionSelected = companyInformationPage.choosePosition());

        And("^Click submit button$", () -> {
            if (personalInformationPage.isSubmitClickable()) {
                personalInformationPage.clickButtonSubmit();
            }
        });

        And("^Select line manager$", () -> isManagerSelected = companyInformationPage.chooseLineManager());

        And("^Click submit button of a dialog is showed$", () -> companyInformationPage.clickButtonDialogSubmit());

        Then("^A error alert is showed$", () -> Assert.assertTrue(companyInformationPage.isErrorAlertShowed()));

        When("^Click to join date input$", () -> companyInformationPage.clickJoinDateInput());

        Then("^Calendar form of join date is showed$", () -> Assert.assertTrue(companyInformationPage.isCalendarFormShowed()));

        Then("^Data in join date input displays correctly$", () -> Assert.assertTrue(companyInformationPage.isTimeCorrect()));

        Then("^Data in Employee Type is \"([^\"]*)\"$", (String empType) -> Assert.assertTrue(companyInformationPage.isEmployeeTypeDataCorrected(empType)));

        And("^Click out of employee code input area$", () -> companyInformationPage.clearFocusEmpCode());

        Then("^Error message \"([^\"]*)\" of employee code is displayed$", (String error) -> Assert.assertTrue(companyInformationPage.isEmpCodeErrorDisplayed(error)));

        And("^A red border of employee code input is displayed$", () -> Assert.assertTrue(companyInformationPage.isEmpCodeInvalid()));

        Given("^Data in email input is correct with FirstName \"([^\"]*)\", LastName \"([^\"]*)\"$", (String firstName, String lastName) ->
                Assert.assertTrue(companyInformationPage.isEmailCorrected(firstName, lastName)));

        When("^Fill email input with \"([^\"]*)\"$", (String data) -> companyInformationPage.fillEmailInput(data));

        And("^Click out of email input area$", () -> companyInformationPage.clearFocusEmail());

        Then("^Error message \"([^\"]*)\" of email is displayed$", (String error) -> Assert.assertTrue(companyInformationPage.isEmailErrorDisplayed(error)));

        And("^A red border of email input is displayed$", () -> Assert.assertTrue(companyInformationPage.isEmailInvalid()));

        When("^Click on dropdown position$", () -> companyInformationPage.clickPosition());

        When("^Data of dropdown position is correct$", () -> Assert.assertTrue(isPositionSelected));

        When("^Click on line manager$", () -> companyInformationPage.clickLineManager());

        Then("^Data of dropdown line manager is correct$", () -> Assert.assertTrue(isManagerSelected));
    }
}
