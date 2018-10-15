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

/**
 * @author at-hangtran
 */
public class PersonalInformationDefinitions extends DriverBase implements En {

    private static final String CURRENT_URL = "http://portal-stg.asiantech.vn/organisation/employees/new";

    private WebDriver driver;
    private PersonalInformationPage personalInformationPage;
    private CompanyInformationPage companyInformationPage;
    private boolean isCalendarFormShowed;

    public PersonalInformationDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Given("^Display new employee page$", () -> {
            driver.get(CURRENT_URL);
            personalInformationPage = initPage(getDriver(), PersonalInformationPage.class);
            companyInformationPage = initPage(getDriver(), CompanyInformationPage.class);
            waitForPageDisplayed(getDriver(),CURRENT_URL,By.cssSelector(".ui-tabview-panel.ui-widget-content.ng-star-inserted"));
        });

        Then("^Personal information tab is active$", () -> Assert.assertTrue(personalInformationPage.isPersonalInformation()));

        Then("^Next button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isNextButtonClickable()));

        Then("^Back button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isBackButtonClickable()));

        Then("^Submit button is not clickable$", () -> Assert.assertFalse(personalInformationPage.isSubmitClickable()));

        When("^Fill First Name input with \"([^\"]*)\"$", (String firstName) -> personalInformationPage.fillFirstName(firstName, 0, 0));

        And("^Fill Middle Name input with \"([^\"]*)\"$", (String middleName) -> personalInformationPage.fillMiddleName(middleName));

        And("^Fill Last Name input with \"([^\"]*)\"$", (String lastName) -> personalInformationPage.fillLastName(lastName, 0, 0));

        And("^Choose Gender check box$", () -> personalInformationPage.chooseGender());

        And("^Choose nationality$", () -> personalInformationPage.chooseNationality(driver));

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

        When("^Click to calendar input$", () -> personalInformationPage.clickCalendar(driver));

        Then("^Calendar form is showed$", () -> {
            isCalendarFormShowed = personalInformationPage.isCalendarFormShowed();
            Assert.assertTrue(personalInformationPage.isCalendarFormShowed());
        });

        Then("^Data in calendar input displays correctly$", () -> {
            if (isCalendarFormShowed) {
                Thread.sleep(1000);
                Assert.assertTrue(personalInformationPage.isTimeCorrect());
            }
        });

        Then("^Error message \"([^\"]*)\" of mobile is displayed$", (String errorMessage) -> Assert.assertTrue(personalInformationPage.isMobileErrorMessageShowed(errorMessage, driver)));

        And("^A red border of mobile input is displayed$", () -> Assert.assertTrue(personalInformationPage.isMobileInvalid()));

        When("^Fill mobile input with \"([^\"]*)\"$", (String mobile) -> personalInformationPage.fillMobileInput(mobile, driver));

        Then("^Click out of mobile input area$", () -> personalInformationPage.clearFocusMobile());

        When("^Fill telephone input with \"([^\"]*)\"$", (String telephone) -> personalInformationPage.fillTelephoneInput(telephone, driver));

        Then("^Click out of telephone input area$", () -> personalInformationPage.clearFocusTelephone());

        Then("^A red border of telephone input is displayed$", () -> Assert.assertTrue(personalInformationPage.isTelephoneInvalid()));

        Then("^Error message \"([^\"]*)\" of telephone is displayed$", (String errorMessage) -> Assert.assertTrue(personalInformationPage.isTelephoneErrorMessageShowed(errorMessage, driver)));

        Then("^Open successfully company information tab$", () -> {
            personalInformationPage.fillFirstName("Hien", 0, 0);
            personalInformationPage.fillMiddleName("Thu");
            personalInformationPage.fillLastName("Hoa", 0, 0);
            personalInformationPage.chooseGender();
            personalInformationPage.chooseNationality(driver);
            personalInformationPage.clickNextButton(driver);
        });
    }
}
