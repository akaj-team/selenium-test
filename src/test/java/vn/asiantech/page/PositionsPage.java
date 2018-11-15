package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author at-trungnguyen
 */
public class PositionsPage extends BasePage<PositionsPage> {

    private static final int COLUMN_SHORT_NAME = 0;
    private static final int COLUMN_LONG_NAME = 1;
    private static final int COLUMN_ACTION = 3;

    @FindBy(tagName = "h2")
    private WebElement title;
    @FindBy(id = "btn-create-position")
    private WebElement btnNewPosition;
    @FindBy(id = "btn-link-to-career-path")
    private WebElement btnCareerPath;
    @FindBy(css = "input[name=search]")
    private WebElement inputSearch;
    @FindBy(className = "ui-datatable")
    private WebElement dataTable;
    @FindBy(className = "ui-dialog")
    private WebElement dialogConfirmDelete;

    private String positionDetailUrl;
    private String updatePositionUrl;
    private WebDriver driver;

    public PositionsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final PositionsPage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.POSITION_PAGE_URL);
        return this;
    }

    public final WebElement getTitle() {
        return title;
    }

    public final String getUrlOfCell() {
        return positionDetailUrl;
    }

    public final WebElement getButtonNewPosition() {
        waitForElement(driver, btnNewPosition);
        return btnNewPosition;
    }

    public final WebElement getButtonCareerPath() {
        waitForElement(driver, btnCareerPath);
        return btnCareerPath;
    }

    public final void searchPosition(final String text) {
        waitForElement(driver, inputSearch);
        inputSearch.sendKeys(text);
        inputSearch.sendKeys(Keys.RETURN);
    }

    public final WebElement getCellDataTable(final int row) {
        WebElement item = Objects.requireNonNull(getListCellInRow(row)).get(COLUMN_SHORT_NAME).findElement(By.className("item-name"));
        positionDetailUrl = item.getAttribute("href");
        return item;
    }

    public final void onClickCellEditInTable(final int row) {
        WebElement item = Objects.requireNonNull(getListCellInRow(row)).get(COLUMN_ACTION).findElement(By.className("update"));
        updatePositionUrl = item.getAttribute("href");
        item.click();
    }

    public final void onCLickCellDeleteInTable(final int row) {
        Objects.requireNonNull(getListCellInRow(row)).get(COLUMN_ACTION).findElement(By.className("delete")).click();
    }

    public final boolean isMatcherFindName(final String text) {
        List<String> names = getListLongNameInTable();
        boolean isMatch = true;
        for (String name : names) {
            if (!Pattern.compile(Pattern.quote(text), Pattern.CASE_INSENSITIVE).matcher(name).find()) {
                isMatch = false;
            }
        }
        return isMatch;
    }

    public final String showMessageEmptyTeam() {
        WebElement rowEmpty = getDataTable().findElement(By.className("ui-datatable-scrollable-body")).findElement(By.tagName("tr"));
        return rowEmpty.findElement(By.tagName("span")).getText();
    }

    public final String getUpdatePositionUrl() {
        return updatePositionUrl;
    }

    public final boolean isDialogConfirmDeleteDisplay() {
        String style = getDialogConfirmDelete().getAttribute("style");
        return style.contains("display: block");
    }

    private List<String> getListLongNameInTable() {
        List<String> longNames = new ArrayList<>();
        WebElement element = getDataTable();
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.className("ui-cell-data"));
            if (cols.size() > 0) {
                WebElement item = cols.get(COLUMN_LONG_NAME).findElement(By.className("item-name"));
                if (isElementPresented(item)) {
                    longNames.add(item.getText());
                }
            }
        }
        return longNames;
    }

    private List<WebElement> getListCellInRow(final int row) {
        WebElement element = getDataTable();
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        if (rows.size() > row - 1) {
            return rows.get(row - 1).findElements(By.className("ui-cell-data"));
        }
        return null;
    }

    private WebElement getDataTable() {
        waitForElement(driver, dataTable);
        return dataTable;
    }

    private WebElement getDialogConfirmDelete() {
        waitForElement(driver, dialogConfirmDelete);
        return dialogConfirmDelete;
    }
}
