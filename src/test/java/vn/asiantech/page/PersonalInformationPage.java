package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

import static vn.asiantech.page.LeavePlannerPage.TIME_OUT_SECOND;

public class PersonalInformationPage extends BasePage<PersonalInformationPage> {

    @FindBy(css = ".ui-tabview-nav.ui-helper-reset.ui-helper-clearfix.ui-widget-header.ui-corner-all.ng-star-inserted")
    private WebElement listTabContainer;

    @FindBy(className = "btn-group")
    private WebElement btnGroup;

    @FindBy(id = "btn-submit-employee")
    private WebElement btnSubmit;

    @FindBy(css = ".col-md-6.col-sm-8")
    private WebElement nameContainer;

    @FindBy(id = "gender-male")
    private WebElement chkMale;

    @FindBy(name = "phone")
    private WebElement inputMobile;

    @FindBy(name = "telephone")
    private WebElement inputTelephone;

    @FindBy(css = ".col-md-4.calendar-fullwidth")
    private WebElement inputCalendar;

    @FindBy(id = "personal-info-tab-wrapper")
    private WebElement formContainer;

    @FindBy(id = "btn-next-tab")
    private WebElement btnTabNext;

    @FindBy(id = "btn-prev-tab")
    private WebElement btnTabBack;

    private WebElement inputMiddleName;
    private WebElement inputLastName;
    private WebElement calendarForm;

    @Override
    public final PersonalInformationPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public boolean isPersonalInformation() {
        WebElement tabPersonalInfor = listTabContainer.findElement(By.xpath("//li[contains(@class,'ui-state-active')]"));
        return tabPersonalInfor.findElement(By.tagName("span")).getText().equals("Personal Infomation");
    }

    public final boolean isNextButtonClickable() {
        return btnTabNext.isEnabled();
    }

    public final void clickNextButton(final WebDriver driver) {
        for (WebElement button : btnGroup.findElements(By.tagName("button"))) {
            waitForElement(driver, button, TIME_OUT_SECOND);
            if (button.findElement(By.tagName("i")).getAttribute("class").equals("fa fa-chevron-right") && button.isEnabled()) {
                button.click();
                break;
            }
        }
    }

    public final boolean isBackButtonClickable() {
        return btnTabBack.isEnabled();
    }

    public final boolean isSubmitClickable() {
        return btnSubmit.isEnabled();
    }

    public final void clickButtonSubmit() {
        btnSubmit.click();
    }

    public final void fillFirstName(final String firstName, final int aheadSpace, final int behindSpace) {
        WebElement inputFirstName = nameContainer.findElement(By.xpath("//input[contains(@name,'first_name')]"));
        if (aheadSpace != 0 && behindSpace != 0) {
            inputFirstName.sendKeys(getNameWithSpace(firstName, aheadSpace, behindSpace));
        } else {
            inputFirstName.sendKeys(firstName);
        }
    }

    public final void fillMiddleName(final String middleName) {
        inputMiddleName = nameContainer.findElement(By.xpath("//input[contains(@name,'middle_name')]"));
        inputMiddleName.sendKeys(middleName);
    }

    public final void fillLastName(final String lastName, final int aheadSpace, final int behindSpace) {
        inputLastName = nameContainer.findElement(By.xpath("//input[contains(@name,'surname')]"));
        if (aheadSpace != 0 && behindSpace != 0) {
            inputLastName.sendKeys(getNameWithSpace(lastName, aheadSpace, behindSpace));
        } else {
            inputLastName.sendKeys(lastName);
        }
    }

    public final void chooseGender() {
        chkMale.click();
    }

    public final void chooseNationality(final WebDriver driver) {
        List<WebElement> forms = driver.findElements(By.cssSelector(".form-group"));
        WebElement nationity = null;
        for (WebElement form : forms) {
            if (form.findElement(By.tagName("label")).getText().equals("Nationality")) {
                nationity = form.findElement(By.xpath("//div[contains(@class,'ui-dropdown ui-widget ui-state-default ui-corner-all ui-helper-clearfix')]"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nationity);
                nationity.click();
                break;
            }
        }
        assert nationity != null;
        WebElement nationContainer = nationity.findElement(By.cssSelector(".ui-dropdown-items-wrapper"));
        waitForElement(driver, nationContainer, 10);
        List<WebElement> nations = nationContainer.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        if (nations.size() > 0) {
            nations.get(0).findElement(By.tagName("span")).click();
        }
    }

    public final boolean isFirstNameErrorMessageDisplayed() {
        List<WebElement> formGroups = formContainer.findElement(By.cssSelector(".col-md-6.col-sm-8")).findElements(By.cssSelector(".form-group"));
        return formGroups.get(0).findElement(By.tagName("span")).isDisplayed();
    }

    public final void clearFocusMiddleName() {
        inputMiddleName.clear();
    }

    public final boolean isMiddleNameInvalid() {
        return inputMiddleName.getAttribute("class").contains(" ng-invalid");
    }

    public final boolean isLastNameErrorMessageDisplayed() {
        List<WebElement> formGroups = formContainer.findElement(By.cssSelector(".col-md-6.col-sm-8")).findElements(By.cssSelector(".form-group"));
        return formGroups.get(2).findElement(By.tagName("span")).isDisplayed();
    }

    public final boolean isLastNameInvalid() {
        return inputLastName.getAttribute("class").contains(" ng-invalid");
    }

    public final void clickCalendar(final WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputCalendar);
        inputCalendar.click();
    }

    public final boolean isCalendarFormShowed() {
        calendarForm = inputCalendar.findElement(By.xpath("//div[contains(@class,'ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-shadow ng-trigger ng-trigger-overlayState')]"));
        return calendarForm.isDisplayed();
    }

    public final boolean isTimeCorrect() {
        String time = getMonth() + " " + getDate() + ", " + getYear();
        clickToDay();
        return inputCalendar.findElement(By.tagName("input")).getAttribute("value").equals(time);
    }

    public final void fillMobileInput(final String phone, final WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputMobile);
        inputMobile.sendKeys(phone);
    }

    public final boolean isMobileErrorMessageShowed(final String message, final WebDriver driver) {
        List<WebElement> forms = driver.findElements(By.cssSelector(".form-group"));
        for (WebElement form : forms) {
            if (isFindElement(form, "phone")) {
                WebElement errorSms = form.findElements(By.cssSelector(".help-block")).get(0);
                return errorSms.isDisplayed() && errorSms.getText().equals(message);
            }
        }
        return false;
    }

    public final boolean isMobileInvalid() {
        return inputMobile.getAttribute("class").contains(" ng-invalid");
    }

    public final void clearFocusMobile() {
        inputMobile.clear();
    }

    public final void fillTelephoneInput(final String telephone, final WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputMobile);
        inputTelephone.sendKeys(telephone);
    }

    public final boolean isTelephoneErrorMessageShowed(final String message, final WebDriver driver) {
        List<WebElement> forms = driver.findElements(By.cssSelector(".form-group"));
        for (WebElement form : forms) {
            if (isFindElement(form, "telephone")) {
                WebElement errorSms = form.findElements(By.cssSelector(".help-block")).get(1);
                return errorSms.isDisplayed() && errorSms.getText().equals(message);
            }
        }
        return false;
    }

    public final boolean isTelephoneInvalid() {
        return inputTelephone.getAttribute("class").contains(" ng-invalid");
    }

    public final void clearFocusTelephone() {
        inputTelephone.clear();
    }

    private boolean isFindElement(final WebElement webElement, final String name) {
        try {
            webElement.findElement(By.name(name));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    private String getMonth() {
        WebElement title = calendarForm.findElements(By.tagName("div")).get(1);
        WebElement monthContainer = title.findElement(By.xpath("//select[contains(@class,'ui-datepicker-month')]"));
        String monthIndex = monthContainer.getAttribute("value");
        return monthContainer.findElements(By.tagName("option")).get(Integer.parseInt(monthIndex)).getText().substring(0, 3);
    }

    private String getYear() {
        WebElement title = calendarForm.findElements(By.tagName("div")).get(1);
        WebElement yearContainer = title.findElement(By.xpath("//select[contains(@class,'ui-datepicker-year')]"));
        return yearContainer.getAttribute("value");
    }

    private String getDate() {
        WebElement toDay = inputCalendar.findElement(By.tagName("tbody")).findElement(By.xpath("//td[contains(@class,'ui-datepicker-today')]"));
        String date = toDay.findElement(By.tagName("a")).getText().trim();
        if (date.length() == 1) {
            date = "0" + date;
        }
        return date;
    }

    private void clickToDay() {
        WebElement toDay = inputCalendar.findElement(By.tagName("tbody")).findElement(By.xpath("//td[contains(@class,'ui-datepicker-today')]"));
        toDay.findElement(By.tagName("a")).click();
    }

    private String getNameWithSpace(final String name, final int aheadSpace, final int behindSpace) {
        StringBuilder aheadSpaces = new StringBuilder();
        for (int i = 0; i < aheadSpace; i++) {
            aheadSpaces.append(" ");
        }
        StringBuilder behindSpaces = new StringBuilder();
        for (int i = 0; i < behindSpace; i++) {
            behindSpaces.append(" ");
        }
        return aheadSpaces.toString() + name + behindSpaces.toString();
    }
}
