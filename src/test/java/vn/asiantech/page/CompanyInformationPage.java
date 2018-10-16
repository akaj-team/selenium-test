package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class CompanyInformationPage extends BasePage<CompanyInformationPage> {
    private static final int TIME_OUT_SECOND = 10;

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

    @FindBy(css = ".btn.btn-sm.btn-primary.btn-submit.ng-star-inserted")
    private WebElement btnDialogSubmit;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private WebElement alertError;
    private WebElement dropDownEmpType;
    private WebElement dropDownPosition;
    private WebElement dropDownLineManager;
    private WebElement calendarForm;
    private boolean isCheckBoxChecked = false;

    @Override
    public final CompanyInformationPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public void clickAssignCheckbox() {
        isCheckBoxChecked = chbAssignment.isSelected();
        chbAssignment.click();
    }

    public boolean isAssignmentCheckboxChecked() {
        return isCheckBoxChecked != chbAssignment.isSelected();
    }

    public void clickJoinDateInput() {
        inputJoinDate.click();
    }

    public boolean isCalendarFormShowed() {
        calendarForm = inputJoinDate.findElements(By.xpath("//div[contains(@class,'ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-shadow ng-trigger ng-trigger-overlayState')]")).get(1);
        return calendarForm.isDisplayed();
    }

    public Boolean isTimeCorrect() {
        String time = getMonth() + " " + getDate() + ", " + getYear();
        clickToDay();
        return inputJoinDate.findElement(By.tagName("input")).getAttribute("value").equals(time);
    }

    public void selectEmpType(String empType) {
        List<WebElement> formGroups = formContainer.findElements(By.cssSelector(".form-group"));
        for (WebElement formGroup : formGroups) {
            if (formGroup.findElement(By.tagName("label")).getText().equals("Employee Type")) {
                dropDownEmpType = formGroup.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
                dropDownEmpType.click();
                break;
            }
        }
        for (WebElement li : dropDownEmpType.findElements(By.tagName("li"))) {
            if (li.findElement(By.tagName("span")).getText().equals(empType)) {
                li.click();
                break;
            }
        }
    }

    public boolean isEmployeeTypeDataCorrected(String empType) {
        boolean isChbEmployee = false;
        if (empType.equals("Employee")) {
            isChbEmployee = formContainer.findElements(By.cssSelector(".checkbox.abc-checkbox.m-n")).get(1).isDisplayed();
        } else {
            isChbEmployee = true;
        }
        return dropDownEmpType.findElement(By.tagName("label")).getText().equals(empType) && isChbEmployee;
    }

    public void fillEmpCode(String code, int aheadSpace, int behindSpace) {
        inputEmpCode.sendKeys(getCodeWithSpace(code, aheadSpace, behindSpace));
    }

    public void choosePosition() {
        List<WebElement> formGroups = formContainer.findElements(By.cssSelector(".form-group"));
        for (WebElement formGroup : formGroups) {
            if (formGroup.findElement(By.tagName("label")).getText().equals("Position")) {
                dropDownPosition = formGroup.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
                break;
            }
        }
        dropDownPosition.click();
        dropDownPosition.findElements(By.tagName("li")).get(1).click();
    }

    public void chooseLineManager(WebDriver driver) throws InterruptedException {
        for (int i = 0; i < driver.findElements(By.cssSelector(".form-group")).size(); i++) {
            if (driver.findElements(By.cssSelector(".form-group")).get(i).findElement(By.tagName("label")).getText().equals("Line Manager")) {
                dropDownLineManager = driver.findElements(By.cssSelector(".form-group")).get(i).findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
                break;
            }
        }
        System.out.println(dropDownLineManager.getAttribute("class"));
        dropDownLineManager.click();
        Thread.sleep(1000);
        dropDownLineManager.findElements(By.tagName("li")).get(0).click();
    }

    public void clickButtonDialogSubmit() {
        btnDialogSubmit.click();
    }

    public boolean isErrorAlertShowed(WebDriver driver) {
        waitForElement(driver, alertError, TIME_OUT_SECOND);
        return alertError.isDisplayed();
    }

    public void clearFocusEmpCode() {
        inputEmpCode.clear();
    }

    public boolean isEmpCodeErrorDisplayed(String error) {
        List<WebElement> formGroups = formContainer.findElements(By.cssSelector(".form-group"));
        for (WebElement formGroup : formGroups) {
            if (formGroup.findElement(By.tagName("label")).getText().equals("Employee Code")) {
                WebElement errorElement = formGroup.findElement(By.cssSelector(".help-block"));
                return errorElement.isDisplayed() && errorElement.getText().equals(error);
            }
        }
        return false;
    }

    public boolean isEmpCodeInvalid() {
        return inputEmpCode.getAttribute("class").contains(" ng-invalid");
    }

    public boolean isEmailCorrected(String firstName, String lastName) {
        String email = firstName + "." + lastName + "@asiantech.vn";
        return inputEmail.getAttribute("value").equals(email.toLowerCase());
    }

    public void fillEmailInput(String data) {
        inputEmail.sendKeys(data);
    }

    public void clearFocusEmail() {
        inputEmail.clear();
    }

    public boolean isEmailErrorDisplayed(String error) {
        List<WebElement> formGroups = formContainer.findElements(By.cssSelector(".form-group"));
        for (WebElement formGroup : formGroups) {
            if (formGroup.findElement(By.tagName("label")).getText().equals("Email")) {
                WebElement errorElement = formGroup.findElement(By.cssSelector(".help-block"));
                return errorElement.isDisplayed() && errorElement.getText().equals(error);
            }
        }
        return false;
    }

    public boolean isEmailInvalid() {
        return inputEmail.getAttribute("class").contains(" ng-invalid");
    }

    private String getMonth() {
        WebElement title = calendarForm.findElements(By.tagName("div")).get(1);
        WebElement monthContainer = title.findElement(By.tagName("select"));
        String monthIndex = monthContainer.getAttribute("value");
        return monthContainer.findElements(By.tagName("option")).get(Integer.parseInt(monthIndex)).getText().substring(0, 3);
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

    private String getCodeWithSpace(String code, int aheadSpace, int behindSpace) {
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
