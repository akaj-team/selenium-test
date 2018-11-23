package vn.asiantech.page.employee.editemployee;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;
import java.util.Random;

/**
 * @author at-hangtran
 */
public class EditEmployeePage extends BasePage<EditEmployeePage> {

    private static final int MAXIMUM_MIDDLE_NAME_WORD = 10;
    private static final int PROMOTION_POSITION_INDEX = 1;
    private static final int PROMOTION_EMPLOYEE_TYPE_INDEX = 2;
    private static final int PROMOTION_AWARD_NEW = 3;
    private static final String PROMOTION_POSITION = "Promotion - Position";
    private static final String PROMOTION_EMPLOYEE_TYPE = "Promotion - Employee Type";
    private static final String PROMOTION_AWARD = "Achievement - Award";

    @FindBy(id = "btn-next-tab")
    @CacheLookup
    private WebElement btnNext;

    @FindBy(id = "btn-deactivated-employee")
    private WebElement btnDeactive;

    @FindBy(id = "btn-submit-employee")
    private WebElement btnSubmit;

    @FindBy(css = "div[role=dialog]")
    private WebElement dialog;

    @FindBy(css = "div[formgroupname=timeline]")
    @CacheLookup
    private WebElement timeLineContainer;

    @FindBy(css = "tbody[formarrayname=relatives]")
    @CacheLookup
    private WebElement infoContactContainer;

    private WebDriver driver;

    public EditEmployeePage(final WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final EditEmployeePage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final boolean isDeactiveButtonClickable() {
        return btnDeactive.isEnabled();
    }

    public final void clickDeactiveButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnDeactive);
        btnDeactive.click();
    }

    public final boolean isSubmitButtonClickable() {
        return btnSubmit.isEnabled();
    }

    public final boolean isDeactiveButtonDisplayed() {
        return isElementPresented(btnDeactive);
    }

    public final boolean isSubmitButtonDisplayed() {
        return isElementPresented(btnSubmit);
    }

    public final boolean isNextButtonClickable() {
        return btnNext.isEnabled();
    }

    public final boolean isBackButtonClickable() {
        return driver.findElement(By.id("btn-prev-tab")).isEnabled();
    }

    public final void clickNextButton() {
        waitForElement(driver, btnNext);
        btnNext.click();
    }

    public final String getEmployeeName() {
        WebElement inputFirstName = driver.findElement(By.cssSelector("input[name=first_name]"));
        WebElement inputMiddleName = driver.findElement(By.cssSelector("input[name=middle_name]"));
        WebElement inputLastName = driver.findElement(By.cssSelector("input[name=surname]"));
        StringBuilder middleName = new StringBuilder(StringUtils.stripAccents(inputMiddleName.getAttribute("value")));
        StringBuilder middleNameWithSpace = new StringBuilder(" ");
        if (!middleName.toString().equals("")) {
            String[] subStrings = middleName.toString().split(" ", MAXIMUM_MIDDLE_NAME_WORD);
            for (String subString : subStrings) {
                middleNameWithSpace.append(subString).append(".");
            }
            return StringUtils.stripAccents(inputFirstName.getAttribute("value")) + " " + StringUtils.stripAccents(inputLastName.getAttribute("value")) + middleNameWithSpace;
        } else {
            return StringUtils.stripAccents(inputFirstName.getAttribute("value")) + " " + StringUtils.stripAccents(inputLastName.getAttribute("value"));
        }
    }

    public final String getEmployeeCode() {
        return driver.findElement(By.cssSelector("input[name=employee_code]")).getAttribute("value");
    }

    public final String getEmployeeEmail() {
        return driver.findElement(By.cssSelector("input[name=email]")).getAttribute("value");
    }

    public final String getEmployeeGroup() {
        return getDisplayGroupOrTeamName(By.cssSelector("p-multiselect[formcontrolname=group_ids]"));
    }

    public final String getEmployeeTeam() {
        return getDisplayGroupOrTeamName(By.cssSelector("p-multiselect[formcontrolname=team_ids]"));
    }

    public final String getEmployeeManager() {
        String managerName = driver.findElement(By.cssSelector("p-dropdown[formcontrolname=manager_id]"))
                .findElement(By.cssSelector(".ui-dropdown-label.ui-inputtext.ui-corner-all.ng-star-inserted")).getText();
        if (managerName.equals("Choose")) {
            return "Empty";
        } else {
            return managerName;
        }
    }

    public final boolean isDialogDisplayed() {
        waitForElement(driver, dialog);
        return dialog.isDisplayed();
    }

    public final String getDeactiveDialogTitle() {
        return dialog.findElement(By.tagName("p-header")).getText().trim();
    }

    public final void clickCloseButtonOfDeactiveDialog() {
        WebElement btnClose = dialog.findElement(By.cssSelector(".btn-default"));
        waitForElement(driver, btnClose);
        btnClose.click();
    }

    public final boolean isDeactiveDialogDismissed() {
        return !driver.findElement(By.tagName("body")).getAttribute("class").contains("ui-overflow-hidden");
    }

    public final void chooseDateToDeactivate() {
        WebElement dateTime = driver.findElement(By.cssSelector("p-calendar[name=deactivate_date]"));
        dateTime.click();
        dateTime.findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]")).click();
    }

    public final void clickDeactiveButtonOfDialog() {
        WebElement btnDialogDeactive = dialog.findElement(By.cssSelector(".btn-submit"));
        waitForElement(driver, btnDialogDeactive);
        btnDialogDeactive.click();
    }

    public final void clickCancelButtonOfDialog() {
        WebElement btnCancel = dialog.findElement(By.cssSelector(".btn-cancel"));
        waitForElement(driver, btnCancel);
        btnCancel.click();
    }

    public final boolean isTabActive(final String tabName, final boolean isCompanyInfoTab) {
        WebElement listTabContainer = driver.findElement(By.className("ui-tabview-nav"));
        WebElement tab;
        if (isCompanyInfoTab) {
            tab = listTabContainer.findElement(By.xpath("//span[contains(text(),'Company')]"));
        } else {
            tab = listTabContainer.findElement(By.xpath("//span[contains(text(),'" + tabName + "')]"));
        }
        tab.click();
        WebElement tabContainer = listTabContainer.findElement(By.xpath("//li[contains(@class,'ui-state-active')]"));
        return tabContainer.getText().toLowerCase().equals(tabName.toLowerCase());
    }

    public final void clickNewEventButton() {
        WebElement btnNewEvent = driver.findElement(By.cssSelector(".btn.btn-default.btn-add.btn-block.ng-star-inserted"));
        waitForElement(driver, btnNewEvent);
        btnNewEvent.click();
    }

    public final void chooseTypeOfEvent(final String event) {
        dialog.findElement(By.xpath("//div[contains(@class,'ui-dropdown ui-widget ui-state-default ui-corner-all ui-helper-clearfix')]")).click();
        int position = 0;
        switch (event) {
            case PROMOTION_POSITION:
                position = PROMOTION_POSITION_INDEX;
                break;
            case PROMOTION_EMPLOYEE_TYPE:
                position = PROMOTION_EMPLOYEE_TYPE_INDEX;
                break;
            case PROMOTION_AWARD:
                position = PROMOTION_AWARD_NEW;
            default:
                break;
        }
        WebElement typeListContainer = dialog.findElement(By.cssSelector(".ui-dropdown-items-wrapper"));
        waitUntilCountDifference(driver, typeListContainer, By.tagName("li"), 0);
        typeListContainer.findElements(By.tagName("li")).get(position).click();
    }

    public final EditEmployeePage choosePositionOfPromotion() {
        selectItemInDropDown();
        return this;
    }

    public final void chooseDateOfPromotion() {
        chooseDate();
    }

    public final void clickSubmitButtonEvent() {
        dialog.findElement(By.cssSelector(".btn-primary.btn-submit")).click();
    }

    public final boolean isEventAdded() {
        List<WebElement> events = timeLineContainer.findElement(By.cssSelector(".timeline-item.m-t-md")).findElements(By.tagName("div"));
        return events.size() > 1;
    }

    public final EditEmployeePage selectEmployeeType() {
        selectItemInDropDown();
        return this;
    }

    public final EditEmployeePage selectAward() {
        selectItemInDropDown();
        return this;
    }

    public final EditEmployeePage chooseDateAward() {
        chooseDate();
        return this;
    }

    public final void fillPraise() {
        dialog.findElement(By.className("none-resize")).sendKeys("He is deserved to review this award!");
    }

    public final void clickEditNewEvent() {
        List<WebElement> events = timeLineContainer.findElement(By.cssSelector(".timeline-item.m-t-md")).findElements(By.cssSelector(".row.ng-star-inserted"));
        if (events.size() > 1) {
            events.get(0).findElement(By.className("btn-edit")).click();
        }
    }

    public final void clickDeleteNewEvent() {
        List<WebElement> events = timeLineContainer.findElement(By.cssSelector(".timeline-item.m-t-md")).findElements(By.cssSelector(".row.ng-star-inserted"));
        if (events.size() > 1) {
            events.get(0).findElement(By.className("btn-delete")).click();
        }
    }

    public final boolean isConfirmDialogDisplayed() {
        WebElement confirmDialog = driver.findElement(By.id("static-dialog-wrapper"));
        waitForElement(driver, confirmDialog);
        return confirmDialog.isDisplayed();
    }

    public final void clickSubmitButton() {
        WebElement submitDialog = driver.findElement(By.id("static-dialog-wrapper"));
        submitDialog.findElement(By.className("btn-submit")).click();
    }

    public final void clickRelativeButton() {
        driver.findElement(By.xpath("//button[contains(text(),'Add Relative')]")).click();
    }

    public final EditEmployeePage fillInfoContact(final String info) {
        fillDataRelative(By.cssSelector("input[formcontrolname=title]"), info);
        return this;
    }

    public final void fillName(final String name) {
        fillDataRelative(By.cssSelector("input[formcontrolname=name]"), name);
    }

    public final void fillPhone(final String phone) {
        fillDataRelative(By.cssSelector("input[formcontrolname=phone]"), phone);
    }

    public final void clickSaveButton() {
        clickItemRelative(By.cssSelector(".btn.btn-primary.btn-md"));
    }

    public final void clickDeleteButton() {
        clickItemRelative(By.cssSelector(".btn.btn-md.btn-default.btn-remove"));
    }

    public final boolean isSaveButtonEnabled() {
        List<WebElement> relatives = infoContactContainer.findElements(By.xpath("//tr[contains(@class,'ng-dirty') or contains(@class,'ng-pristine')]"));
        if (relatives.size() > 1) {
            return relatives.get(relatives.size() - 1).findElement(By.cssSelector(".btn.btn-primary.btn-md")).isEnabled();
        }
        return false;
    }

    public final boolean isPhoneErrorMessageDisplayed() {
        List<WebElement> relatives = infoContactContainer.findElements(By.xpath("//tr[contains(@class,'ng-dirty') or contains(@class,'ng-pristine')]"));
        if (relatives.size() > 1) {
            return relatives.get(relatives.size() - 1).findElement(By.cssSelector("input[formcontrolname=phone]")).getAttribute("class").contains("ng-invalid");
        }
        return false;
    }

    private String getDisplayGroupOrTeamName(final By elementBy) {
        String name = driver.findElement(elementBy).findElement(By.className("ui-multiselect-label-container")).getAttribute("title");
        if (name.equals("Choose")) {
            return "Empty";
        } else {
            return name;
        }
    }

    private void selectItemInDropDown() {
        dialog.findElement(By.cssSelector(".ng-pristine.ng-valid.ng-star-inserted")).click();
        List<WebElement> positions = dialog.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElements(By.tagName("li"));
        if (positions.size() > 1) {
            positions.get(new Random().nextInt(positions.size() - 1) + 1).click();
        }
    }

    private void chooseDate() {
        WebElement dayTimeContainer = dialog.findElement(By.cssSelector(".ui-calendar"));
        dayTimeContainer.click();
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                it -> dayTimeContainer.findElement(By.xpath("..")).getAttribute("class").contains("ui-inputwrapper-focus"));
        dayTimeContainer.findElement(By.tagName("table")).findElement(By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]")).click();
    }

    private void fillDataRelative(final By elementBy, final String content) {
        List<WebElement> relatives = infoContactContainer.findElements(By.xpath("//tr[contains(@class,'ng-dirty') or contains(@class,'ng-pristine')]"));
        if (relatives.size() > 1) {
            relatives.get(relatives.size() - 1).findElement(elementBy).sendKeys(content);
        }
    }

    private void clickItemRelative(final By elementBy) {
        List<WebElement> relatives = infoContactContainer.findElements(By.xpath("//tr[contains(@class,'ng-dirty') or contains(@class,'ng-pristine')]"));
        if (relatives.size() > 1) {
            relatives.get(relatives.size() - 1).findElement(elementBy).click();
        }
    }
}
