package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class CompanyInformationPage extends BasePage<CompanyInformationPage> {

    @FindBy(id = "available_for_project_assignment")
    private WebElement chbAssignment;

    @FindBy(id = "calendar-join-date")
    private WebElement inputJoinDate;

    @FindBy(id = "dropdown-employee-type")
    private WebElement dropDownEmpType;

    @FindBy(id = "input-employee-code")
    private WebElement inputEmpCode;

    @FindBy(id = "dropdown-position")
    private WebElement dropDownPosition;

    @FindBy(id = "btn-agree-dialog-confirm")
    private WebElement btnDialogSubmit;

    @FindBy(css = ".app-alert.ng-star-inserted")
    private WebElement alertError;
    private WebElement dropDownLineManager;
    private WebElement calendarForm;
    public static final int TIME_OUT_SECOND = 10;
    boolean isCheckBoxChecked = false;

    @Override
    public CompanyInformationPage navigateTo(WebDriver webDriver) {
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

    public void selectEmpType() {
        dropDownEmpType.findElement(By.tagName("div")).click();
        dropDownEmpType.findElements(By.tagName("li")).get(0).click();
    }

    public void fillEmpCode(String code, int aheadSpace, int behindSpase) {
        inputEmpCode.sendKeys(getCodeWithSpace(code, aheadSpace, behindSpase));
    }

    public void choosePosition() {
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
