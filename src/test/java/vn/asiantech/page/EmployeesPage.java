package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-hangtran
 */
public class EmployeesPage extends BasePage<EmployeesPage> {

    public static final int TIME_OUT_SECOND = 10;
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
    private static final int EMPLOYEE_POSITION_INDEX = 3;
    private static final int EMPLOYEE_TYPE_INDEX = 5;
    private static final int EMPLOYEE_STATUS_INDEX = 7;

    @FindBy(css = ".ui-datatable-data.ui-widget-content")
    private WebElement tableBody;
    @FindBy(css = ".title-action")
    private WebElement actionTitle;

    @FindBy(css = ".is-top.ui-overflow-hidden")
    private WebElement hiddenbody;

    @FindBy(css = ".ui-paginator-pages")
    private WebElement indicatorPage;

    @FindBy(css = ".ui-paginator-bottom.ui-paginator.ui-widget.ui-widget-header.ui-unselectable-text.ui-helper-clearfix.ng-star-inserted")
    private WebElement bottomIndicator;

    @FindBy(css = ".ui-paginator-left-content.ng-star-inserted")
    private WebElement leftContent;

    @FindBy(css = ".toolbox-content")
    private WebElement toolBox;

    private int indicatorIndex = 0;
    private WebElement firstIndicator;
    private WebElement backIndicator;
    private WebElement nextIndicator;
    private WebElement lastIndicator;
    private WebElement typeList;
    private WebElement positionList;
    private WebElement statusList;
    private int typeClick;

    @Override
    public final EmployeesPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/employees");
        return this;
    }

    public final String clickEmployeeName() {
        WebElement employee = getEmployeeInfor(0, EMPLOYEE_NAME_COLUMN_INDEX);
        WebElement employeeName = employee.findElement(By.tagName("span")).findElement(By.tagName("span"));
        employeeName.click();
        return employee.getAttribute("href");
    }

    public final String clickEmployeeCode() {
        WebElement employee = getEmployeeInfor(0, EMPLOYEE_CODE_COLUMN_INDEX);
        System.out.println(employee.getAttribute("class"));
        employee.click();
        return employee.getAttribute("href");
    }

    public final String clickEmployeeAvatar() {
        WebElement employee = getEmployeeInfor(0, EMPLOYEE_NAME_COLUMN_INDEX);
        employee.findElement(By.tagName("span")).findElement(By.tagName("img")).click();
        return employee.getAttribute("href");
    }

    public final String clickManagerName() {
        List<WebElement> cells = tableBody.findElements(By.tagName("tr"));
        WebElement manager = cells.get(0).findElements(By.tagName("td")).get(EMPLOYEE_MANAGER_COLUMN_INDEX).findElement(By.tagName("span")).findElement(By.tagName("span")).findElement(By.tagName("a"));
        manager.click();
        return manager.getAttribute("href");
    }

    public final String clickTeamName() {
        WebElement team = getEmployeeInfor(0, EMPLOYEE_TEAM_COLUMN_INDEX);
        team.click();
        return team.getAttribute("href");
    }

    public final String clickGroupName() {
        WebElement group = getEmployeeInfor(0, EMPLOYEE_GROUP_COLUMN_INDEX);
        group.click();
        return group.getAttribute("href");
    }

    public final String clickEditButton() {
        WebElement editEmployee = getEmployeeInfor(0, EMPLOYEE_ACTION_COLUMN_INDEX);
        editEmployee.click();
        return editEmployee.getAttribute("href");
    }

    public final void clickPromotionButton() {
        actionTitle.findElements(By.xpath(".btn.btn-sm.btn-default")).get(0).click();
    }

    public final Boolean isAlertShowed(final String title) {
        hiddenbody.isDisplayed();
        return hiddenbody.findElement(By.tagName("p-header")).getText().equals(title);
    }

    public final void clickAwardCategory() {
        actionTitle.findElements(By.cssSelector(".btn.btn-sm.btn-default")).get(1).click();
    }

    public final String clickNewEmployee() {
        WebElement newEmployee = actionTitle.findElement(By.cssSelector(".btn.btn-sm.btn-default.btn-add"));
        newEmployee.click();
        return newEmployee.getAttribute("href");
    }

    public final Integer getCellSum() {
        return tableBody.findElements(By.tagName("tr")).size();
    }

    public final Boolean isOneIndicatorActive() {
        String oneIndicator = indicatorPage.findElement(By.xpath("//a[text()='1']")).getAttribute("class");
        return oneIndicator.contains("ui-state-active");
    }

    public final Boolean isFirstAndBackIndicatorClickable() {
        firstIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-first')]"));
        backIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-prev')]"));
        return !firstIndicator.getAttribute("class").contains("ui-state-disabled") && !backIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public final Boolean isNextAndLastIndicatorClickable() {
        nextIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-next')]"));
        lastIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-last')]"));
        return !nextIndicator.getAttribute("class").contains("ui-state-disabled") && !lastIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public final void clickNextIndicator() {
        if (!isNextAndLastIndicatorClickable()) {
            typeClick = NEXT_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            nextIndicator.click();
        }
    }

    public final void clickBackIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            typeClick = BACK_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            backIndicator.click();
        }
    }

    public final void clickFirstIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            typeClick = FIST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            firstIndicator.click();
        }
    }

    public final void clickLastIndicator() {
        if (isNextAndLastIndicatorClickable()) {
            typeClick = LAST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            lastIndicator.click();
        }
    }

    public final Boolean isIndicatorActive() {
        switch (typeClick) {
            case NEXT_INDICATOR_CLICK:
                return indicatorIndex + 1 == findIndicatorIndex();
            case BACK_INDICATOR_CLICK:
                return indicatorIndex - 1 == findIndicatorIndex();
            case FIST_INDICATOR_CLICK:
                return indicatorIndex == 1;
            default: {
                String content = leftContent.findElement(By.tagName("small")).getText();
                String firstSub = content.substring(SPLIT_STRING_INDEX, content.length() - SPLIT_STRING_INDEX);
                Integer sumCell = Integer.valueOf(firstSub.split("of")[1].trim());
                return Integer.valueOf(indicatorPage.findElements(By.tagName("a")).get(LAST_INDICATOR_INDEX).getText()) * MAXIMUM_CELL >= sumCell;
            }
        }
    }

    public final Boolean compareLeftContentWithPageIndicator() {
        String content = leftContent.findElement(By.tagName("small")).getText();
        String firstSub = content.substring(SPLIT_STRING_INDEX, content.length() - SPLIT_STRING_INDEX);
        String secondSub = firstSub.split("of")[0];
        int topCell = Integer.parseInt(secondSub.split("-")[0].trim());
        int endCell = Integer.parseInt(secondSub.split("-")[1].trim());
        return findIndicatorIndex() * MAXIMUM_CELL == endCell && (findIndicatorIndex() - 1) * MAXIMUM_CELL + 1 == topCell;
    }

    public final void searchEmployee(final String name) {
        WebElement search = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(1).findElement(By.tagName("input"));
        search.sendKeys(name);
    }

    public final Boolean isEmployeeListEmpty() {
        String emptyCell = tableBody.findElements(By.tagName("tr")).get(0).getAttribute("class");
        if (emptyCell.contains("ui-datatable-emptymessage-row")) {
            return true;
        }
        return tableBody.findElements(By.tagName("tr")).size() == 0;
    }

    public final Boolean clickPositionView() {
        WebElement positionView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_POSITION_INDEX);
        positionView.click();
        positionList = positionView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return positionList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final Boolean isPositionSelected(final String positionName) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            if (position.getAttribute("class").contains("ui-state-highlight")) {
                System.out.println(position.findElement(By.tagName("span")).getText());
                return position.findElement(By.tagName("span")).getText().equals(positionName);
            }
        }
        return false;
    }

    public final Boolean isShowCorrectPositionList(final String positionName) {
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

    public final Boolean isShowNoResultMessage(final String message) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        return positions.size() == 1 && positions.get(0).getText().equals(message);
    }

    public final void selectPosition(final WebDriver driver) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            waitForElement(driver, position, TIME_OUT_SECOND);
            position.click();
            break;
        }
    }

    public final Boolean clickType() {
        WebElement typeView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_TYPE_INDEX);
        typeView.click();
        typeList = typeView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return typeList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final String selectType() {
        String typeName = "";
        List<WebElement> types = typeList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement type : types) {
            typeName = type.findElement(By.tagName("span")).getText();
            type.click();
            break;
        }
        return typeName;
    }

    public final Boolean isTypeChoosed(final String type) {
        return typeList.findElement(By.tagName("label")).getText().equals(type);
    }

    public final Boolean clickStatus() {
        WebElement statusView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(EMPLOYEE_STATUS_INDEX);
        statusView.click();
        statusList = statusView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return statusList.getAttribute("class").contains("ui-dropdown-open");
    }

    public final String selectStatus() {
        String statusName = "";
        List<WebElement> statuses = statusList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement status : statuses) {
            statusName = status.findElement(By.tagName("span")).getText();
            status.click();
            break;
        }
        return statusName;
    }

    public final Boolean isStatusChoosed(final String status) {
        return statusList.findElement(By.tagName("label")).getText().equals(status);
    }

    private WebElement getEmployeeInfor(final Integer cellPosition, final Integer columnPosition) {
        List<WebElement> cells = tableBody.findElements(By.tagName("tr"));
        return cells.get(cellPosition).findElements(By.tagName("td")).get(columnPosition).findElement(By.tagName("span")).findElement(By.tagName("a"));
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
