package stepdefs.employee.newemployee;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.employee.newemployee.PersonalInformationPage;

import static vn.asiantech.base.Constant.NEW_EMPLOYEE_PAGE_URL;

/**
 * @author at-hangtran
 */
public class PersonalInformationDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private PersonalInformationPage personalInformationPage;
    private boolean isCalendarFormShowed;

    public PersonalInformationDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        personalInformationPage = initPage(getDriver(), PersonalInformationPage.class);

        Given("^Display new employee page$", () -> {
            driver.get(NEW_EMPLOYEE_PAGE_URL);
            waitForPageDisplayed(getDriver(), NEW_EMPLOYEE_PAGE_URL, By.id("employee-form-wrapper"));
        });

        Then("^Personal information tab is active$", () -> Assert.assertTrue(personalInformationPage.isPersonalInformation()));

        Then("^Next button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isNextButtonClickable()));

        Then("^Back button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isBackButtonClickable()));

        Then("^Submit button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isSubmitClickable()));

        When("^Fill First Name input with \"([^\"]*)\"$", (String firstName) -> personalInformationPage.fillFirstName(firstName, 0, 0));

        And("^Fill Middle Name input with \"([^\"]*)\"$", (String middleName) -> personalInformationPage.fillMiddleName(middleName));

        And("^Fill Last Name input with \"([^\"]*)\"$", (String lastName) -> personalInformationPage.fillLastName(lastName, 0, 0));

        And("^Choose Gender check box$", () -> personalInformationPage.chooseGender());

        And("^Choose nationality$", () -> personalInformationPage.chooseNationality());

        Then("^Next button is clickable$", () -> Assert.assertTrue(personalInformationPage.isNextButtonClickable()));

        When("^Fill First Name input with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$", (String firstName, String aheadSpace, String behindSpace)
                -> personalInformationPage.fillFirstName(firstName, Integer.valueOf(aheadSpace), Integer.valueOf(behindSpace)));

        Then("^Error message of first name is displayed$", () -> Assert.assertTrue(personalInformationPage.isFirstNameErrorMessageDisplayed()));

        And("^Click out of middle name input area$", () -> personalInformationPage.clearFocusMiddleName());

        Then("^A red border of middle name is displayed$", () -> Assert.assertTrue(personalInformationPage.isMiddleNameInvalid()));

        When("^Fill Last Name input with a \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$", (String lastName, String aheadSpace, String behindSpace)
                -> personalInformationPage.fillLastName(lastName, Integer.valueOf(aheadSpace), Integer.valueOf(behindSpace)));

        Then("^Error message of last name is displayed$", () -> Assert.assertTrue(personalInformationPage.isLastNameErrorMessageDisplayed()));

        And("^A red border of last name is displayed$", () -> Assert.assertTrue(personalInformationPage.isLastNameInvalid()));

        When("^Click to calendar input$", () -> personalInformationPage.clickCalendar());

        Then("^Calendar form is showed$", () -> {
            isCalendarFormShowed = personalInformationPage.isCalendarFormShowed();
            Assert.assertTrue(personalInformationPage.isCalendarFormShowed());
        });

        Then("^Data in calendar input displays correctly$", () -> {
            if (isCalendarFormShowed) {
                Assert.assertTrue(personalInformationPage.isTimeCorrect());
            }
        });

        Then("^Error message \"([^\"]*)\" of mobile is displayed$", (String errorMessage) -> Assert.assertTrue(personalInformationPage.isMobileErrorMessageShowed(errorMessage)));

        And("^A red border of mobile input is displayed$", () -> Assert.assertTrue(personalInformationPage.isMobileInvalid()));

        When("^Fill mobile input with \"([^\"]*)\"$", (String mobile) -> personalInformationPage.fillMobileInput(mobile));

        Then("^Click out of mobile input area$", () -> personalInformationPage.clearFocusMobile());

        When("^Fill telephone input with \"([^\"]*)\"$", (String telephone) -> personalInformationPage.fillTelephoneInput(telephone));

        Then("^Click out of telephone input area$", () -> personalInformationPage.clearFocusTelephone());

        Then("^A red border of telephone input is displayed$", () -> Assert.assertTrue(personalInformationPage.isTelephoneInvalid()));

        Then("^Error message \"([^\"]*)\" of telephone is displayed$", (String errorMessage) -> Assert.assertTrue(personalInformationPage.isTelephoneErrorMessageShowed(errorMessage)));

        Then("^Open successfully company information tab with FirstName \"([^\"]*)\", LastName \"([^\"]*)\"$", (String fistName, String lastName) -> {
            personalInformationPage.fillFirstName(fistName, 0, 0);
            personalInformationPage.fillLastName(lastName, 0, 0);
            personalInformationPage.chooseGender();
            personalInformationPage.chooseNationality();
            personalInformationPage.clickNextButton();
        });
    }
}
