package vn.asiantech.page.employee.editemployee;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * @author at-hangtran
 */
public class EditEmployeePage extends BasePage<EditEmployeePage> {

    @FindBy(id = "btn-next-tab")
    private WebElement btnNext;

    @FindBy(id = "btn-prev-tab")
    private WebElement btnBack;

    @FindBy(id = "btn-deactivated-employee")
    private WebElement btnDeactive;

    @FindBy(id = "btn-submit-employee")
    private WebElement btnSubmit;

    @FindBy(css = "input[name=first_name]")
    private WebElement inputFirstName;

    @FindBy(css = "input[name=middle_name]")
    private WebElement inputMiddleName;

    @FindBy(css = "input[name=surname]")
    private WebElement inputLastName;

    @FindBy(css = "input[name=employee_code]")
    private WebElement inputCode;

    @FindBy(css = "input[name=email]")
    private WebElement inputEmail;

    @FindBy(css = "p-dropdown[formcontrolname=manager_id]")
    private WebElement managerContainer;

    @FindBy(css = "p-multiselect[formcontrolname=team_ids]")
    private WebElement teamContainer;

    @FindBy(css = "p-multiselect[formcontrolname=group_ids]")
    private WebElement groupContainer;

    @FindBy(css = "ul[role=tablist]")
    private WebElement companyInfoTab;

    @FindBy(css = "div[role=dialog]")
    private WebElement dialog;

    @FindBy(css = "p-calendar[name=deactivate_date]")
    private WebElement dateTime;

    @FindBy(className = "ui-tabview-nav")
    private WebElement listTabContainer;

    @FindBy(css = ".btn.btn-default.btn-add.btn-block.ng-star-inserted")
    private WebElement btnNewEvent;

    private WebDriver driver;

    public EditEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public EditEmployeePage navigateTo(WebDriver webDriver) {
        return null;
    }

    public boolean isDeactiveButtonClickable() {
        return btnDeactive.isEnabled();
    }

    public void clickDeactiveButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnDeactive);
        btnDeactive.click();
    }

    public boolean isSubmitButtonClickable() {
        return btnSubmit.isEnabled();
    }

    public boolean isDeactiveButtonDisplayed() {
        try {
            btnDeactive.isDisplayed();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isSubmitButtonDisplayed() {
        try {
            btnSubmit.isDisplayed();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean isNextButtonClickable() {
        return btnNext.isEnabled();
    }

    public boolean isBackButtonClickable() {
        return btnBack.isEnabled();
    }

    public void clickNextButton() {
        btnNext.click();
    }

    public String getEmployeeName() {
        StringBuilder middleName = new StringBuilder(StringUtils.stripAccents(inputMiddleName.getAttribute("value")));
        StringBuilder middleNameWithSpace = new StringBuilder(" ");
        if (!middleName.toString().equals("")) {
            String[] subStrings = middleName.toString().split(" ", 10);
            for (String subString : subStrings) {
                middleNameWithSpace.append(subString).append(".");
            }
            return StringUtils.stripAccents(inputFirstName.getAttribute("value")) + " " + StringUtils.stripAccents(inputLastName.getAttribute("value")) + middleNameWithSpace;
        } else {
            return StringUtils.stripAccents(inputFirstName.getAttribute("value")) + " " + StringUtils.stripAccents(inputLastName.getAttribute("value"));
        }
    }

    public boolean isCompanyInfoTabActive() {
        return companyInfoTab.findElements(By.tagName("li")).get(1).getAttribute("class").contains("ui-tabview-selected");
    }

    public String getEmployeeCode() {
        return inputCode.getAttribute("value");
    }

    public String getEmployeeEmail() {
        return inputEmail.getAttribute("value");
    }

    public String getEmployeeGroup() {
        String group = groupContainer.findElement(By.xpath("//div[contains(@class,'ui-multiselect-label-container')]")).getAttribute("title");
        if (group.equals("Choose")) {
            return "Empty";
        } else {
            return group;
        }
    }

    public String getEmployeeTeam() {
        String team = teamContainer.findElements(By.xpath("//div[contains(@class,'ui-multiselect-label-container')]")).get(1).getAttribute("title");
        if (team.equals("Choose")) {
            return "Empty";
        } else {
            return team;
        }
    }

    public boolean isDialogDisplayed() {
        waitForElement(driver, dialog);
        return dialog.isDisplayed();
    }

    public String getDeactiveDialogTitle() {
        return dialog.findElement(By.tagName("p-header")).getText().trim();
    }

    public void clickCloseButtonOfDeactiveDialog() {
        WebElement btnClose = dialog.findElement(By.cssSelector(".btn-default"));
        waitForElement(driver, btnClose);
        btnClose.click();
    }

    public boolean isDeactiveDialogDismissed() {
        return !driver.findElement(By.tagName("body")).getAttribute("class").contains("ui-overflow-hidden");
    }

    public void chooseDateToDeactivate() {
        dateTime.click();
        dateTime.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]")).click();
    }

    public void clickDeactiveButtonOfDialog() {
        WebElement btnDeactive = dialog.findElement(By.cssSelector(".btn-submit"));
        waitForElement(driver, btnDeactive);
        btnDeactive.click();
    }

    public void clickCancelButtonOfDialog() {
        WebElement btnCancel = dialog.findElement(By.cssSelector(".btn-cancel"));
        waitForElement(driver, btnCancel);
        btnCancel.click();
    }

    public boolean isTimelineTabActive() {
        WebElement tabPersonalInfo = listTabContainer.findElement(By.xpath("//li[contains(@class,'ui-state-active')]"));
        return tabPersonalInfo.findElement(By.tagName("span")).getText().equals("Timeline");
    }

    public void clickNewEventButton() {
        waitForElement(driver, btnNewEvent);
        btnNewEvent.click();
    }

    public void chooseTypeOfEvent(String event) {
        dialog.findElement(By.cssSelector("input[role=listbox]")).click();
        int position = 0;
        switch (event) {
            case "Promotion - Position":
                position = 1;
                break;
            case "Promotion - Employee Type":
                position = 2;
                break;
            case "Achievement - Award":
                position = 3;
        }
        WebElement typeListContainer = dialog.findElement(By.cssSelector(".ui-dropdown-items-wrapper"));
        waitForElement(driver, typeListContainer);
        waitUntilCountDifference(driver, typeListContainer, By.tagName("li"), 4);
        typeListContainer.findElements(By.tagName("li")).get(position).click();
    }
}
