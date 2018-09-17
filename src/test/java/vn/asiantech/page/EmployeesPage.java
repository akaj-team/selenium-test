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
    private static final int NEXT_INDICATOR_CLICK = 0;
    private static final int BACK_INDICATOR_CLICK = 1;
    private static final int FIST_INDICATOR_CLICK = 2;
    private static final int LAST_INDICATOR_CLICK = 3;

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
    private int typeClick;

    @Override
    public EmployeesPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/employees");
        return this;
    }

    public String clickEmployeeName() {
        WebElement employee = getEmployeeInfor(0, 0);
        WebElement employeeName = employee.findElement(By.tagName("span")).findElement(By.tagName("span"));
        employeeName.click();
        return employee.getAttribute("href");
    }

    public String clickEmployeeCode() {
        WebElement employee = getEmployeeInfor(0, 1);
        employee.click();
        return employee.getAttribute("href");
    }

    public String clickEmployeeAvatar() {
        WebElement employee = getEmployeeInfor(0, 0);
        employee.findElement(By.tagName("span")).findElement(By.tagName("img")).click();
        return employee.getAttribute("href");
    }

    public String clickManagerName() {
        List<WebElement> cells = tableBody.findElements(By.tagName("tr"));
        WebElement manager = cells.get(0).findElements(By.tagName("td")).get(3).findElement(By.tagName("span")).findElement(By.tagName("span")).findElement(By.tagName("a"));
        manager.click();
        return manager.getAttribute("href");
    }

    public String clickTeamName() {
        WebElement team = getEmployeeInfor(0, 4);
        team.click();
        return team.getAttribute("href");
    }

    public String clickGroupName() {
        WebElement group = getEmployeeInfor(0, 5);
        group.click();
        return group.getAttribute("href");
    }

    public String clickEditButton() {
        WebElement editEmployee = getEmployeeInfor(0, 6);
        editEmployee.click();
        return editEmployee.getAttribute("href");
    }

    public void clickPromotionButton() {
        actionTitle.findElements(By.cssSelector(".btn.btn-sm.btn-default")).get(0).click();
    }

    public Boolean isAlertShowed(String title) {
        hiddenbody.isDisplayed();
        return hiddenbody.findElement(By.tagName("p-header")).getText().equals(title);
    }

    public void clickAwardCategory() {
        actionTitle.findElements(By.cssSelector(".btn.btn-sm.btn-default")).get(1).click();
    }

    public String clickNewEmployee() {
        WebElement newEmployee = actionTitle.findElement(By.cssSelector(".btn.btn-sm.btn-default.btn-add"));
        newEmployee.click();
        return newEmployee.getAttribute("href");
    }

    public Integer getCellSum() {
        return tableBody.findElements(By.tagName("tr")).size();
    }

    public Boolean isOneIndicatorActive() {
        String oneIndicator = indicatorPage.findElement(By.xpath("//a[text()='1']")).getAttribute("class");
        return oneIndicator.contains("ui-state-active");
    }

    public Boolean isFirstAndBackIndicatorClickable() {
        firstIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-first')]"));
        backIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-prev')]"));
        return !firstIndicator.getAttribute("class").contains("ui-state-disabled") && !backIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public Boolean isNextAndLastIndicatorClickable() {
        nextIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-next')]"));
        lastIndicator = bottomIndicator.findElement(By.xpath("//a[contains(@class,'ui-paginator-last')]"));
        return !nextIndicator.getAttribute("class").contains("ui-state-disabled") && !lastIndicator.getAttribute("class").contains("ui-state-disabled");
    }

    public void clickNextIndicator() {
        if (!isNextAndLastIndicatorClickable()) {
            typeClick = NEXT_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            nextIndicator.click();
        }
    }

    public void clickBackIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            typeClick = BACK_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            backIndicator.click();
        }
    }

    public void clickFirstIndicator() {
        if (isFirstAndBackIndicatorClickable()) {
            typeClick = FIST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            firstIndicator.click();
        }
    }

    public void clickLastIndicator() {
        if (isNextAndLastIndicatorClickable()) {
            typeClick = LAST_INDICATOR_CLICK;
            indicatorIndex = findIndicatorIndex();
            lastIndicator.click();
        }
    }

    public Boolean isIndicatorActive() {
        switch (typeClick) {
            case NEXT_INDICATOR_CLICK:
                return indicatorIndex + 1 == findIndicatorIndex();
            case BACK_INDICATOR_CLICK:
                return indicatorIndex - 1 == findIndicatorIndex();
            case FIST_INDICATOR_CLICK:
                return indicatorIndex == 1;
            default: {
                String content = leftContent.findElement(By.tagName("small")).getText();
                String firstSub = content.substring(7, content.length() - 7);
                Integer sumCell = Integer.valueOf(firstSub.split("of")[1].trim());
                return Integer.valueOf(indicatorPage.findElements(By.tagName("a")).get(4).getText()) * MAXIMUM_CELL >= sumCell;
            }
        }
    }

    public Boolean compareLeftContentWithPageIndicator() {
        String content = leftContent.findElement(By.tagName("small")).getText();
        String firstSub = content.substring(7, content.length() - 7);
        String secondSub = firstSub.split("of")[0];
        int topCell = Integer.parseInt(secondSub.split("-")[0].trim());
        int endCell = Integer.parseInt(secondSub.split("-")[1].trim());
        return findIndicatorIndex() * MAXIMUM_CELL == endCell && (findIndicatorIndex() - 1) * MAXIMUM_CELL + 1 == topCell;
    }

    public void searchEmployee(String name) {
        WebElement search = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(1).findElement(By.tagName("input"));
        search.sendKeys(name);
    }

    public Boolean isEmployeeListEmpty() {
        String emptyCell = tableBody.findElements(By.tagName("tr")).get(0).getAttribute("class");
        if (emptyCell.contains("ui-datatable-emptymessage-row")) {
            return true;
        }
        return tableBody.findElements(By.tagName("tr")).size() == 0;
    }

    public Boolean clickPositionView() {
        WebElement positionView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(3);
        positionView.click();
        positionList = positionView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return positionList.getAttribute("class").contains("ui-dropdown-open");
    }

    public Boolean isPositionSelected(String positionName) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            if (position.getAttribute("class").contains("ui-state-highlight")) {
                System.out.println(position.findElement(By.tagName("span")).getText());
                return position.findElement(By.tagName("span")).getText().equals(positionName);
            }
        }
        return false;
    }

    public Boolean isShowCorrectPositionList(String positionName) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            String positionItemName = position.findElement(By.tagName("span")).getText();
            if (!positionItemName.equals("All Position") && !positionItemName.contains(positionName)) {
                return false;
            }
        }
        return true;
    }

    public void searchPosition(String positionName, WebDriver driver) {
        WebElement searchPosition = positionList.findElement(By.cssSelector(".ui-dropdown-filter.ui-inputtext.ui-widget.ui-state-default.ui-corner-all"));
        waitForElement(driver, searchPosition, TIME_OUT_SECOND);
        searchPosition.sendKeys(positionName);
    }

    public Boolean isShowNoResultMessage(String message) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        return positions.size() == 1 && positions.get(0).getText().equals(message);
    }

    public void selectPosition(WebDriver driver) {
        List<WebElement> positions = positionList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement position : positions) {
            waitForElement(driver, position, TIME_OUT_SECOND);
            position.click();
            break;
        }
    }

    public Boolean clickType() {
        WebElement typeView = toolBox.findElements(By.xpath("//div[contains(@class,'toolbox-item')]")).get(5);
        typeView.click();
        typeList = typeView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return typeList.getAttribute("class").contains("ui-dropdown-open");
    }

    public String selectType() {
        String typeName = "";
        List<WebElement> types = typeList.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        for (WebElement type : types) {
            typeName = type.findElement(By.tagName("span")).getText();
            type.click();
            break;
        }
        return typeName;
    }

    public Boolean isTypeChoosed(String type) {
        return typeList.findElement(By.tagName("label")).getText().equals(type);
    }

    private WebElement getEmployeeInfor(Integer cellPosition, Integer columnPosition) {
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
