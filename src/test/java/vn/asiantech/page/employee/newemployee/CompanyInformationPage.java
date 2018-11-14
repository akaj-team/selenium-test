package vn.asiantech.page.employee.newemployee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-hangtran
 */
public class CompanyInformationPage extends BasePage<CompanyInformationPage> {

    private static final int MONTH_INDEX = 3;

    @FindBy(id = "employee-form-wrapper")
    private WebElement formContainer;

    @FindBy(id = "available_for_project_assignment")
    private WebElement chbAssignment;

    @FindBy(name = "joined_company_day")
    private WebElement inputJoinDate;

    @FindBy(name = "employee_code")
    private WebElement inputEmpCode;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(className = "btn-submit")
    private WebElement btnDialogSubmit;

    @FindBy(className = "app-alert")
    private WebElement alertError;

    private WebElement dropDownEmpType;
    private WebElement dropDownPosition;
    private WebElement dropDownLineManager;
    private WebElement calendarForm;
    private boolean isCheckBoxChecked;

    private WebDriver driver;

    public CompanyInformationPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final CompanyInformationPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void clickAssignCheckbox() {
        isCheckBoxChecked = chbAssignment.isSelected();
        chbAssignment.click();
    }

    public final boolean isAssignmentCheckboxChecked() {
        return isCheckBoxChecked != chbAssignment.isSelected();
    }

    public final void clickJoinDateInput() {
        inputJoinDate.click();
    }

    public final boolean isCalendarFormShowed() {
        calendarForm = inputJoinDate.findElements(By.xpath("//div[contains(@class,'ui-datepicker')]")).get(1);
        return calendarForm.isDisplayed();
    }

    public final boolean isTimeCorrect() {
        String time = getMonth() + " " + getDate() + ", " + getYear();
        clickToDay();
        return inputJoinDate.findElement(By.tagName("input")).getAttribute("value").equals(time);
    }

    public final void selectEmpType(final String empType) {
        WebElement empTypeContainer = formContainer.findElement(By.xpath("//label[contains(text(),'Employee Type')]/.."));
        dropDownEmpType = empTypeContainer.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        dropDownEmpType.click();
        for (WebElement li : dropDownEmpType.findElements(By.tagName("li"))) {
            if (li.findElement(By.tagName("span")).getText().equals(empType)) {
                li.click();
                break;
            }
        }
    }

    public final boolean isEmployeeTypeDataCorrected(final String empType) {
        boolean isChbEmployee;
        if (empType.equals("Employee")) {
            isChbEmployee = formContainer.findElements(By.cssSelector(".checkbox.abc-checkbox.m-n")).get(1).isDisplayed();
        } else {
            isChbEmployee = true;
        }
        return dropDownEmpType.findElement(By.tagName("label")).getText().equals(empType) && isChbEmployee;
    }

    public final void fillEmpCode(final String code, final int aheadSpace, final int behindSpace) {
        inputEmpCode.sendKeys(getCodeWithSpace(code, aheadSpace, behindSpace));
    }

    public final boolean choosePosition() {
        List<WebElement> positions = dropDownPosition.findElements(By.tagName("li"));
        String positionSelected = positions.get(1).findElement(By.tagName("span")).getText();
        positions.get(1).click();
        return positionSelected.equals(dropDownPosition.findElement(By.tagName("label")).getText());
    }

    public final void clickPosition() {
        WebElement positionContainer = formContainer.findElement(By.xpath("//label[contains(text(),'Position')]/.."));
        dropDownPosition = positionContainer.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        dropDownPosition.click();
    }

    public final void clickLineManager() {
        WebElement lineManageContainer = formContainer.findElements(By.xpath("//label[contains(text(),'Line Manager')]/..")).get(1);
        dropDownLineManager = lineManageContainer.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        dropDownLineManager.click();
    }

    public final boolean chooseLineManager() {
        WebElement lineManagerName = dropDownLineManager.findElements(By.tagName("li")).get(0);
        String managerSelected = lineManagerName.findElement(By.tagName("span")).getText();
        lineManagerName.click();
        return managerSelected.equals(dropDownLineManager.findElement(By.tagName("label")).getText());
    }

    public final void clickButtonDialogSubmit() {
        btnDialogSubmit.click();
    }

    public final boolean isErrorAlertShowed() {
        waitForElement(driver, alertError);
        return alertError.isDisplayed();
    }

    public final void clearFocusEmpCode() {
        inputEmpCode.clear();
    }

    public final boolean isEmpCodeErrorDisplayed(final String error) {
        WebElement empCodeContainer = formContainer.findElement(By.xpath("//label[contains(text(),'Employee Code')]/.."));
        WebElement errorElement = empCodeContainer.findElement(By.cssSelector(".help-block"));
        return errorElement.isDisplayed() && errorElement.getText().equals(error);
    }

    public final boolean isEmpCodeInvalid() {
        return inputEmpCode.getAttribute("class").contains(" ng-invalid");
    }

    public final boolean isEmailCorrected(final String firstName, final String lastName) {
        String email = firstName + "." + lastName + "@asiantech.vn";
        return inputEmail.getAttribute("value").equals(email.toLowerCase());
    }

    public final void fillEmailInput(final String data) {
        inputEmail.sendKeys(data);
    }

    public final void clearFocusEmail() {
        inputEmail.clear();
    }

    public final boolean isEmailErrorDisplayed(final String error) {
        WebElement emailContainer = formContainer.findElement(By.xpath("//label[contains(text(),'Email')]/.."));
        WebElement errorElement = emailContainer.findElement(By.cssSelector(".help-block"));
        return errorElement.isDisplayed() && errorElement.getText().equals(error);
    }

    public final boolean isEmailInvalid() {
        return inputEmail.getAttribute("class").contains(" ng-invalid");
    }

    private String getMonth() {
        WebElement title = calendarForm.findElements(By.tagName("div")).get(1);
        WebElement monthContainer = title.findElement(By.tagName("select"));
        String monthIndex = monthContainer.getAttribute("value");
        return monthContainer.findElements(By.tagName("option")).get(Integer.parseInt(monthIndex)).getText().substring(0, MONTH_INDEX);
    }

    private String getYear() {
        WebElement title = calendarForm.findElements(By.tagName("div")).get(1);
        WebElement yearContainer = title.findElement(By.xpath("//span[contains(@class,'ui-datepicker-year')]"));
        return yearContainer.getText();
    }

    private String getDate() {
        WebElement toDay = inputJoinDate.findElement(By.tagName("tbody")).findElement(By.xpath("//td[contains(@class,'ui-datepicker-today')]"));
        String date = toDay.findElement(By.tagName("a")).getText().trim();
        if (date.length() == 1) {
            date = "0" + date;
        }
        return date;
    }

    private void clickToDay() {
        WebElement toDay = inputJoinDate.findElement(By.tagName("tbody")).findElement(By.xpath("//td[contains(@class,'ui-datepicker-today')]"));
        toDay.findElement(By.tagName("a")).click();
    }

    private String getCodeWithSpace(final String code, final int aheadSpace, final int behindSpace) {
        StringBuilder aheadSpaces = new StringBuilder();
        for (int i = 0; i < aheadSpace; i++) {
            aheadSpaces.append(" ");
        }
        StringBuilder behindSpaces = new StringBuilder();
        for (int i = 0; i < behindSpace; i++) {
            behindSpaces.append(" ");
        }
        return aheadSpaces.toString() + code + behindSpaces.toString();
    }
}
