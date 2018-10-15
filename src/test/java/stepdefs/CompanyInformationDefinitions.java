package stepdefs;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.CompanyInformationPage;
import vn.asiantech.page.PersonalInformationPage;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

public class CompanyInformationDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private PersonalInformationPage personalInformationPage;
    private CompanyInformationPage companyInformationPage;

    public CompanyInformationDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^Display new employee page with company information tab$", () -> {
            personalInformationPage = initPage(getDriver(), PersonalInformationPage.class);
            companyInformationPage = initPage(getDriver(), CompanyInformationPage.class);
        });

        When("^Click the assign check box$", () -> companyInformationPage.clickAssignCheckbox());

        Then("^Checkbox is unchecked or checked$", () -> Assert.assertTrue(companyInformationPage.isAssignmentCheckboxChecked()));

        And("^Choose join date input$", () -> {
            companyInformationPage.clickJoinDateInput();
            if (companyInformationPage.isCalendarFormShowed()) {
                Assert.assertTrue(companyInformationPage.isTimeCorrect());
            }
        });

        And("^Select employee type$", () -> companyInformationPage.selectEmpType());

        And("^Fill employee code input with \"([^\"]*)\"$", (String code) -> companyInformationPage.fillEmpCode(code, 0, 0));

        And("^Select position$", () -> companyInformationPage.choosePosition());

        And("^Click submit button$", () -> {
            if (personalInformationPage.isSubmitClickable()) {
                personalInformationPage.clickButtonSubmit();
            }
        });

        And("^Select line manager$", () -> companyInformationPage.chooseLineManager(driver));

        And("^Click submit button of a dialog is showed$", () -> companyInformationPage.clickButtonDialogSubmit());

        Then("^A error alert is showed$", () -> Assert.assertTrue(companyInformationPage.isErrorAlertShowed(driver)));

        When("^Click to join date input$", () -> companyInformationPage.clickJoinDateInput());

        Then("^Calendar form of join date is showed$", () -> Assert.assertTrue(companyInformationPage.isCalendarFormShowed()));

        Then("^Data in join date input displays correctly$", () -> Assert.assertTrue(companyInformationPage.isTimeCorrect()));
    }
}