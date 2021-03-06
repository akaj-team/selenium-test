package vn.asiantech.page.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.object.Employee;

import java.util.List;

import static vn.asiantech.base.Constant.EMPLOYEE_PAGE_URL;

/**
 * @author at-hangtran
 */
public class EmployeesPage extends BasePage<EmployeesPage> {

    public static final int MAXIMUM_CELL = 50;
    private static final int SPLIT_STRING_INDEX = 7;
    private static final int LAST_INDICATOR_INDEX = 4;
    private static final int NEXT_INDICATOR_CLICK = 0;
    private static final int BACK_INDICATOR_CLICK = 1;
    private static final int FIST_INDICATOR_CLICK = 2;
    private static final int LAST_INDICATOR_CLICK = 3;
    private static final int EMPLOYEE_NAME_COLUMN_INDEX = 0;
    private static final int EMPLOYEE_CODE_COLUMN_INDEX = 1;
    private static final int EMPLOYEE_EMAIL_COLUMN_INDEX = 2;
    private static final int EMPLOYEE_MANAGER_COLUMN_INDEX = 3;
    private static final int EMPLOYEE_TEAM_COLUMN_INDEX = 4;
    private static final int EMPLOYEE_GROUP_COLUMN_INDEX = 5;
    private static final int EMPLOYEE_ACTION_COLUMN_INDEX = 6;
    private static final int EMPLOYEE_TYPE_INDEX = 5;
    private static final int EMPLOYEE_STATUS_INDEX = 7;
    private static final int MINIMUM_EMPLOYEE_TEAM_DISPLAY = 6;
    private static final int EMPLOYEE_TEAM_JUMP = 2;
    private static final int EMPLOYEE_TEAM_MOD = 2;

    @FindBy(className = "ui-datatable-data")
    @CacheLookup
    private WebElement tblBody;

    @FindBy(className = "ui-overflow-hidden")
    private WebElement hiddenBody;

    @FindBy(className = "ui-paginator-pages")
    private WebElement indicatorPage;

    @FindBy(className = "ui-paginator-bottom")
    private WebElement bottomIndicator;

    @FindBy(className = "ui-paginator-left-content")
    private WebElement leftContentContainer;

    @FindBy(css = ".toolbox-content")
    @CacheLookup
    private WebElement toolBox;

    @FindBy(id = "employee-filter-wrapper")
    @CacheLookup
    private WebElement inputSearchEmployeeContainer;

    @FindBy(id = "position-filter-wrapper")
    @CacheLookup
    private WebElement selectPositionContainer;

    @FindBy(id = "btn-import-promotion")
    @CacheLookup
    private WebElement btnPromotion;

    @FindBy(id = "btn-import-award")
    @CacheLookup
    private WebElement btnAwardCategory;

    @FindBy(id = "btn-create-employee")
    @CacheLookup
    private WebElement btnNewEmployee;

    private int indicatorIndex = 0;
    private WebElement firstIndicator;
    private WebElement backIndicator;
    private WebElement nextIndicator;
    private WebElement lastIndicator;
    private WebElement typeList;
    private WebElement positionList;
    private WebElement statusList;
    private WebElement dialog;
    private int clickType;
    private WebDriver driver;

    public EmployeesPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final EmployeesPage navigateTo(final WebDriver webDriver) {
        webDriver.get(EMPLOYEE_PAGE_URL);
        return this;
    }

    public final String clickAndGetEmployeeName() {
        WebElement employee = getEmployeeInformation(EMPLOYEE_NAME_COLUMN_INDEX);
        assert employee != null;
        WebElement employeeName = employee.findElement(By.tagName("span")).findElement(By.tagName("span"));
        String employeeUrlProfile = employee.getAttribute("href");
        employeeName.click();
        return employeeUrlProfile;
    }

    public final String getEmployeeProfileLink() {
        WebElement employee = getEmployeeInformation(EMPLOYEE_NAME_COLUMN_INDEX);
        assert employee != null;
        return employee.getAttribute("href");
    }

    public final String clickAndGetEmployeeCode() {
        WebElement employee = getEmployeeInformation(EMPLOYEE_CODE_COLUMN_INDEX);
        assert employee != null;
        String employeeProfileLink = employee.getAttribute("href");
        employee.click();
        return employeeProfileLink;
    }

    public final String clickAndGetEmployeeAvatar() {
        WebElement employee = getEmployeeInformation(EMPLOYEE_NAME_COLUMN_INDEX);
        assert employee != null;
        WebElement avatar = employee.findElement(By.tagName("span")).findElement(By.tagName("img"));
        String employeeUrlProfile = employee.getAttribute("href");
        avatar.click();
        return employeeUrlProfile;
    }

    public final String clickAndGetManagerName() {
        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        WebElement manager = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_MANAGER_COLUMN_INDEX).findElement(By.tagName("span")).findElement(By.tagName("span")).findElement(By.tagName("a"));
        String managerUrl = manager.getAttribute("href");
        manager.click();
        return managerUrl;
    }

    public final String clickAndGetTeamName() {
        WebElement team = getEmployeeInformation(EMPLOYEE_TEAM_COLUMN_INDEX);
        assert team != null;
        String teamUrl = team.getAttribute("href");
        team.click();
        return teamUrl;
    }

    public final String clickAndGetGroupName() {
        WebElement group = getEmployeeInformation(EMPLOYEE_GROUP_COLUMN_INDEX);
        assert group != null;
        String groupUrl = group.getAttribute("href");
        group.click();
        return groupUrl;
    }

    public final String clickEditButtonAndGetLink() {
        WebElement editEmployee = getEmployeeInformation(EMPLOYEE_ACTION_COLUMN_INDEX);
        assert editEmployee != null;
        String updateEmployeeUrl = editEmployee.getAttribute("href");
        editEmployee.click();
        return updateEmployeeUrl;
    }

    public final Employee getEditEmployee() {
        WebElement nameColumn = getEmployeeInformation(EMPLOYEE_NAME_COLUMN_INDEX);
        assert nameColumn != null;
        String employeeName = nameColumn.findElement(By.tagName("strong")).getText();

        WebElement codeColumn = getEmployeeInformation(EMPLOYEE_CODE_COLUMN_INDEX);
        assert codeColumn != null;
        String employeeCode = codeColumn.getText();

        WebElement emailColumn = getEmployeeInformation(EMPLOYEE_EMAIL_COLUMN_INDEX);
        assert emailColumn != null;
        String employeeEmail = emailColumn.getText();

        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        String employeeManager = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_MANAGER_COLUMN_INDEX).findElement(By.tagName("a")).getText();

        return new Employee(employeeName, employeeCode, employeeEmail, employeeManager, getMoreTeamOrGroup(true), getMoreTeamOrGroup(false));
    }

    public final void clickPromotionButton() {
        btnPromotion.click();
    }

    public final boolean isAlertShowed(final String title) {
        return hiddenBody.findElement(By.tagName("p-header")).getText().equals(title) && hiddenBody.isDisplayed();
    }

    public final void clickAwardCategory() {
        btnAwardCategory.click();
    }

    public final String clickNewEmployeeAndGetLink() {
        String newEmployeeUrl = btnNewEmployee.getAttribute("href");
        btnNewEmployee.click();
        return newEmployeeUrl;
    }

    public final int getCellSum() {
        return tblBody.findElements(By.tagName("tr")).size();
    }

    public final boolean isOneIndicatorActive() {
        String oneIndicator = indicatorPage.findElement(By.xpath("//a[text()='1']")).getAttribute("class");
        return oneIndicator.contains("ui-state-active");
    }

    public final boolean isFirstAndBackIndicatorClickable() {
        firstIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-first')]"));
        backIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-prev')]"));
        return !firstIndicator.getAttribute("class").contains("ui-state-disabled") && !backIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public final boolean isNextAndLastIndicatorClickable() {
        nextIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-next')]"));
        lastIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-last')]"));
        return !nextIndicator.getAttribute("class").contains("ui-state-disabled") && !lastIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public final void clickNextIndicator() {
        if (!isNextAndLastIndicatorClickable()) {
            clickType = NEXT_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            nextIndicator.click();
        }
    }

    public final void clickBackIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            clickType = BACK_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            backIndicator.click();
        }
    }

    public final void clickFirstIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            clickType = FIST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            firstIndicator.click();
        }
    }

    public final void clickLastIndicator() {
        if (isNextAndLastIndicatorClickable()) {
            clickType = LAST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            lastIndicator.click();
        }
    }

    public final boolean isIndicatorActive() {
        String content = leftContentContainer.findElement(By.tagName("small")).getText();
        String firstSub = content.substring(SPLIT_STRING_INDEX, content.length() - SPLIT_STRING_INDEX);
        int sumCell = Integer.valueOf(firstSub.split("of")[1].trim());
        switch (clickType) {
            case NEXT_INDICATOR_CLICK:
                return indicatorIndex + 1 == findIndicatorIndex();
            case BACK_INDICATOR_CLICK:
                return indicatorIndex - 1 == findIndicatorIndex();
            case FIST_INDICATOR_CLICK:
                return indicatorIndex == 1;
            default:
                return Integer.valueOf(indicatorPage.findElements(By.tagName("a")).get(LAST_INDICATOR_INDEX).getText()) * MAXIMUM_CELL >= sumCell;
        }
    }

    public final boolean isLeftContentAndPageIndicatorCorrect() {
        String content = leftContentContainer.findElement(By.tagName("small")).getText();
        String firstSub = content.substring(SPLIT_STRING_INDEX, content.length() - SPLIT_STRING_INDEX);
        String secondSub = firstSub.split("of")[0];
        int topCell = Integer.parseInt(secondSub.split("-")[0].trim());
        int endCell = Integer.parseInt(secondSub.split("-")[1].trim());
        return findIndicatorIndex() * MAXIMUM_CELL == endCell && (findIndicatorIndex() - 1) * MAXIMUM_CELL + 1 == topCell;
    }

    public final void searchEmployee(final String name) {
        inputSearchEmployeeContainer.findElement(By.tagName("input")).sendKeys(name);
    }

    public final boolean isEmployeeListEmpty() {
        String emptyCell = tblBody.findElements(By.tagName("tr")).get(0).getAttribute("class");
        return emptyCell.contains("ui-datatable-emptymessage-row");
    }

    public final boolean clickPositionView() {
        selectPositionContainer.findElement(By.tagName("div")).click();
        positionList = selectPositionContainer.findElement(By.className("ui-dropdown-panel"));
        return selectPositionContainer.findElement(By.tagName("div")).getAttribute("class").contains("ui-dropdown-open");
    }

    public final boolean isPositionSelected(final String positionName) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            if (position.getAttribute("class").contains("ui-state-highlight")) {
                return position.findElement(By.tagName("span")).getText().equals(positionName);
            }
        }
        return false;
    }

    public final boolean isShowCorrectPositionList(final String positionName) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            String positionItemName = position.findElement(By.tagName("span")).getText();
            if (!positionItemName.equals("All Position") && !positionItemName.contains(positionName)) {
                return false;
            }
        }
        return true;
    }

    public final void searchWithEmployeePosition(final String positionName) {
        WebElement searchPosition = positionList.findElement(By.className("ui-dropdown-filter"));
        waitForElement(driver, searchPosition);
        searchPosition.sendKeys(positionName);
    }

    public final boolean isNoResultMessageShowed(final String message) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        return positions.size() == 1 && positions.get(0).getText().equals(message);
    }

    public final void selectPosition() {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        positions.get(0).click();
    }

    public final boolean getClickType() {
        WebElement typeView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_TYPE_INDEX);
        typeView.click();
        typeList = typeView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return typeList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final String getSelectType() {
        List<WebElement> types = typeList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        String type = types.get(0).findElement(By.tagName("span")).getText();
        types.get(0).click();
        return type;
    }

    public final boolean isTypeChose(final String type) {
        return typeList.findElement(By.tagName("label")).getText().equals(type);
    }

    public final boolean clickStatus() {
        WebElement statusView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_STATUS_INDEX);
        statusView.click();
        statusList = statusView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return statusList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final String selectStatus() {
        List<WebElement> statuses = statusList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        String status = statuses.get(0).findElement(By.tagName("span")).getText();
        statuses.get(0).click();
        return status;
    }

    public final boolean isStatusChose(final String status) {
        return statusList.findElement(By.tagName("label")).getText().equals(status);
    }

    public final boolean isImportButtonClickable(final String dialogName) {
        this.dialog = hiddenBody.findElement(By.xpath("//p-header[contains(text(),'" + dialogName + "')]/../../.."));
        WebElement importButton = this.dialog.findElement(By.className("btn-submit"));
        return importButton.isEnabled();
    }

    public final void clickCancelButton() {
        dialog.findElement(By.className("btn-cancel")).click();
    }

    public final boolean isPromotionDialogDismissed() {
        return dialog.isDisplayed();
    }

    public final String selectAwardCategoryDropDown() {
        hiddenBody.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div")).click();
        WebElement awardItem = hiddenBody.findElements(By.tagName("li")).get(0);
        String award = awardItem.findElement(By.tagName("span")).getText();
        awardItem.click();
        return award;
    }

    public final boolean isDataAwardCategoryCorrect() {
        return hiddenBody.findElement(By.tagName("p-dropdown")).findElement(By.tagName("label")).getText().equals(selectAwardCategoryDropDown());
    }

    private WebElement getEmployeeInformation(final int columnPosition) {
        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        for (WebElement cell : cells) {
            if (isElementFound(cell, columnPosition)) {
                return cell.findElements(By.tagName("td")).get(columnPosition).findElement(By.tagName("span")).findElement(By.tagName("a"));
            }
        }
        return null;
    }

    private int findIndicatorIndex() {
        List<WebElement> indicators = bottomIndicator.findElements(By.tagName("a"));
        for (WebElement indicator : indicators) {
            if (indicator.getAttribute("class").contains("ui-state-active")) {
                return Integer.valueOf(indicator.getText());
            }
        }
        return 0;
    }

    private boolean isElementFound(final WebElement cell, final int column) {
        try {
            cell.findElements(By.tagName("td")).get(column).findElement(By.tagName("span")).findElement(By.tagName("a"));
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    private String getMoreTeamOrGroup(final boolean isTeam) {
        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        WebElement column;
        if (isTeam) {
            column = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_TEAM_COLUMN_INDEX);
        } else {
            column = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_GROUP_COLUMN_INDEX);
        }
        StringBuilder employeeTeamOrGroup;
        try {
            employeeTeamOrGroup = new StringBuilder(column.findElement(By.tagName("a")).getText());
            try {
                column.findElement(By.tagName("i")).click();
                WebElement teamContainer = driver.findElement(By.id("org-panel-wrapper"));
                waitForElement(driver, teamContainer);
                int teamCount = teamContainer.findElements(By.cssSelector("span[class='ng-star-inserted']")).size();
                if (teamCount < MINIMUM_EMPLOYEE_TEAM_DISPLAY) {
                    employeeTeamOrGroup = new StringBuilder();
                    for (int i = 0; i < teamCount; i = i + EMPLOYEE_TEAM_JUMP) {
                        if (i != teamCount - 1) {
                            employeeTeamOrGroup.append(teamContainer.findElements(By.tagName("span")).get(i).findElement(By.tagName("span")).getText()).append(", ");
                        } else {
                            employeeTeamOrGroup.append(teamContainer.findElements(By.tagName("span")).get(i).findElement(By.tagName("span")).getText());
                        }
                    }
                } else {
                    employeeTeamOrGroup = new StringBuilder(teamCount / EMPLOYEE_TEAM_MOD + 1 + " items selected");
                }
            } catch (NoSuchElementException exception) {
                // no-opp
            }

        } catch (NoSuchElementException exception) {
            employeeTeamOrGroup = new StringBuilder(column.findElement(By.cssSelector(".text-na")).getText());
        }
        return employeeTeamOrGroup.toString();
    }
}
