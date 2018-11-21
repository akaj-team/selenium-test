package vn.asiantech.page.employee.editemployee;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

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


    public boolean isSubmitButtonClickable() {
        return btnSubmit.isEnabled();
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
        StringBuilder middleName = new StringBuilder(inputMiddleName.getAttribute("value"));
        StringBuilder middleNameWithSpace = new StringBuilder(" ");
        if (!middleName.toString().equals("")) {
            String[] subStrings = middleName.toString().split(" ");
            for (String subString : subStrings) {
                middleNameWithSpace.append(subString).append(".");
            }
        }
        return inputFirstName.getAttribute("value") + " " + inputLastName.getAttribute("value") + middleNameWithSpace;
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
        String team = teamContainer.findElement(By.xpath("//div[contains(@class,'ui-multiselect-label-container')]")).getAttribute("title");
        if (team.equals("Choose")) {
            return "Empty";
        } else {
            return team;
        }
    }
}
