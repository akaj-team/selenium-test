package vn.asiantech.page.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-hangtran
 */
public class EmployeesPage extends BasePage<EmployeesPage> {

    public static final int TIME_OUT_SECOND = 10;
    public static final String EMPLOYEE_URL = "http://portal-stg.asiantech.vn/organisation/employees";
    public static final int MAXIMUM_CELL = 50;
    private static final int SPLIT_STRING_INDEX = 7;
    private static final int LAST_INDICATOR_INDEX = 4;
    private static final int NEXT_INDICATOR_CLICK = 0;
    private static final int BACK_INDICATOR_CLICK = 1;
    private static final int FIST_INDICATOR_CLICK = 2;
    private static final int LAST_INDICATOR_CLICK = 3;
    private static final int EMPLOYEE_NAME_COLUMN_INDEX = 0;
    private static final int EMPLOYEE_CODE_COLUMN_INDEX = 1;
    private static final int EMPLOYEE_MANAGER_COLUMN_INDEX = 3;
    private static final int EMPLOYEE_TEAM_COLUMN_INDEX = 4;
    private static final int EMPLOYEE_GROUP_COLUMN_INDEX = 5;
    private static final int EMPLOYEE_ACTION_COLUMN_INDEX = 6;
    private static final int EMPLOYEE_TYPE_INDEX = 5;
    private static final int EMPLOYEE_STATUS_INDEX = 7;

    @FindBy(css = ".ui-datatable-data.ui-widget-content")
    @CacheLookup
    private WebElement tblBody;

    @FindBy(css = ".is-top.ui-overflow-hidden")
    private WebElement hiddenBody;

    @FindBy(css = ".ui-paginator-pages")
    private WebElement indicatorPage;

    @FindBy(css = ".ui-paginator-bottom.ui-paginator.ui-widget.ui-widget-header.ui-unselectable-text.ui-helper-clearfix.ng-star-inserted")
    private WebElement bottomIndicator;

    @FindBy(id = "text-show-entry")
    private WebElement leftContent;

    @FindBy(css = ".toolbox-content")
    @CacheLookup
    private WebElement toolBox;

    @FindBy(id = "input-filter")
    @CacheLookup
    private WebElement inputSearchEmployee;

    @FindBy(id = "dropdown-filter-position")
    @CacheLookup
    private WebElement selectPositionElement;

    @FindBy(id = "btn-promote-employee")
    @CacheLookup
    private WebElement btnPromotion;

    @FindBy(id = "btn-award-employee")
    @CacheLookup
    private WebElement btnAwardCategory;

    @FindBy(id = "link-to-employee-create")
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
    private int clickType;

    @Override
    public final EmployeesPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/employees");
        return this;
    }

    public final String clickAndGetEmployeeName() {
        WebElement employee = getEmployeeInfor(EMPLOYEE_NAME_COLUMN_INDEX);
        WebElement employeeName = employee.findElement(By.tagName("span")).findElement(By.tagName("span"));
        employeeName.click();
        return employee.getAttribute("href");
    }

    public final String clickAndGetEmployeeCode() {
        WebElement employee = getEmployeeInfor(EMPLOYEE_CODE_COLUMN_INDEX);
        try {
            employee.click();
        } catch (StaleElementReferenceException exception) {
            System.out.println(exception.toString());
        }
        return employee.getAttribute("href");
    }

    public final String clickAndGetEmployeeAvatar() {
        WebElement employee = getEmployeeInfor(EMPLOYEE_NAME_COLUMN_INDEX);
        try {
            WebElement avatar = employee.findElement(By.tagName("span")).findElement(By.tagName("img"));
            avatar.click();
        } catch (StaleElementReferenceException exception) {
            System.out.println(exception.toString());
        }
        return employee.getAttribute("href");
    }

    public final String clickAndGetManagerName() {
        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        WebElement manager = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_MANAGER_COLUMN_INDEX).findElement(By.tagName("span")).findElement(By.tagName("span")).findElement(By.tagName("a"));
        manager.click();
        return manager.getAttribute("href");
    }

    public final String clickAndGetTeamName() {
        WebElement team = getEmployeeInfor(EMPLOYEE_TEAM_COLUMN_INDEX);
        team.click();
        return team.getAttribute("href");
    }

    public final String clickAndGetGroupName() {
        WebElement group = getEmployeeInfor(EMPLOYEE_GROUP_COLUMN_INDEX);
        group.click();
        return group.getAttribute("href");
    }

    public final String clickEditButtonAndGetLink() {
        WebElement editEmployee = getEmployeeInfor(EMPLOYEE_ACTION_COLUMN_INDEX);
        editEmployee.click();
        return editEmployee.getAttribute("href");
    }

    public final void clickPromotionButton() {
        btnPromotion.click();
    }

    public final boolean isAlertShowed(final String title) {
        hiddenBody.isDisplayed();
        return hiddenBody.findElement(By.tagName("p-header")).getText().equals(title);
    }

    public final void clickAwardCategory() {
        btnAwardCategory.click();
    }

    public final String clickNewEmployeeAndGetLink() {
        btnNewEmployee.click();
        return btnNewEmployee.getAttribute("href");
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
        String content = leftContent.getText();
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
        String content = leftContent.getText();
        String firstSub = content.substring(SPLIT_STRING_INDEX, content.length() - SPLIT_STRING_INDEX);
        String secondSub = firstSub.split("of")[0];
        int topCell = Integer.parseInt(secondSub.split("-")[0].trim());
        int endCell = Integer.parseInt(secondSub.split("-")[1].trim());
        return findIndicatorIndex() * MAXIMUM_CELL == endCell && (findIndicatorIndex() - 1) * MAXIMUM_CELL + 1 == topCell;
    }

    public final void searchEmployee(final String name) {
        inputSearchEmployee.sendKeys(name);
    }

    public final boolean isEmployeeListEmpty() {
        String emptyCell = tblBody.findElements(By.tagName("tr")).get(0).getAttribute("class");
        if (emptyCell.contains("ui-datatable-emptymessage-row")) {
            return true;
        }
        return tblBody.findElements(By.tagName("tr")).size() == 0;
    }

    public final boolean clickPositionView() {
        selectPositionElement.click();
        positionList = selectPositionElement.findElement(By.tagName("div"));
        return positionList.getAttribute("class").contains("ui-dropdown-open");
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

    public final void searchPosition(final String positionName, final WebDriver driver) {
        WebElement searchPosition = positionList.findElement(By.cssSelector(".ui-dropdown-filter.ui-inputtext.ui-widget.ui-state-default.ui-corner-all"));
        waitForElement(driver, searchPosition, TIME_OUT_SECOND);
        searchPosition.sendKeys(positionName);
    }

    public final boolean isNoResultMessageShowed(final String message) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        return positions.size() == 1 && positions.get(0).getText().equals(message);
    }

    public final void selectPosition() {
        try {
            List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            positions.get(0).click();
        } catch (StaleElementReferenceException exception) {
            System.out.println(exception.toString());
        }
    }

    public final boolean getClickType() {
        WebElement typeView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_TYPE_INDEX);
        typeView.click();
        typeList = typeView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return typeList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final String getSelectType() {
        try {
            List<WebElement> types = typeList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            types.get(0).click();
            return types.get(0).findElement(By.tagName("span")).getText();
        } catch (StaleElementReferenceException exception) {
            System.out.println(exception.toString());
        }
        return "";
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
        try {
            List<WebElement> statuses = statusList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
            statuses.get(0).click();
            return statuses.get(0).findElement(By.tagName("span")).getText();
        } catch (StaleElementReferenceException exception) {
            System.out.println(exception.toString());
        }
        return "";
    }

    public final boolean isStatusChose(final String status) {
        return statusList.findElement(By.tagName("label")).getText().equals(status);
    }

    private WebElement getEmployeeInfor(final int columnPosition) {
        List<WebElement> cells = tblBody.findElements(By.tagName("tr"));
        return cells.get(0).findElements(By.tagName("td")).get(columnPosition).findElement(By.tagName("span")).findElement(By.tagName("a"));
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
}
