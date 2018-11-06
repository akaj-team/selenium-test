package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author at-trungnguyen
 */
public class PositionsPage extends BasePage<PositionsPage> {

    @FindBy(tagName = "h2")
    private WebElement title;
    @FindBy(className = "btn-add")
    private WebElement btnNewPosition;
    @FindBy(className = "btn-org")
    private WebElement btnCareerPath;
    @FindBy(css = "input[name=search]")
    private WebElement searchBox;
    @FindBy(className = "ui-datatable")
    private WebElement dataTable;
    @FindBy(className = "ui-dialog")
    private WebElement dialogConfirmDelete;
    private String positionDetailUrl;
    private String updatePositionUrl;

    @Override
    public final PositionsPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/positions");
        return this;
    }

    public final String getUrlOfCell() {
        return positionDetailUrl;
    }

    public final String getTitle(final WebDriver driver) {
        waitForElement(driver, title);
        return title.getText();
    }

    public final WebElement getBtnNewPosition(final WebDriver driver) {
        waitForElement(driver, btnNewPosition);
        return btnNewPosition;
    }

    public final WebElement getBtnCareerPath(final WebDriver driver) {
        waitForElement(driver, btnCareerPath);
        return btnCareerPath;
    }

    public final WebElement getSearchBox(final WebDriver driver) {
        waitForElement(driver, searchBox);
        return searchBox;
    }

    public final void searchPosition(final WebDriver driver, final String text) {
        waitForElement(driver, searchBox);
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.RETURN);
    }

    private WebElement getDataTable(final WebDriver driver) {
        waitForElement(driver, dataTable);
        return dataTable;
    }

    public final WebElement getCellDataTable(final WebDriver driver, final int row, final int col) {
        WebElement element = getDataTable(driver);
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        if (rows.size() > row - 1) {
            List<WebElement> cols = rows.get(row - 1).findElements(By.className("ui-cell-data"));
            if (cols.size() > col - 1) {
                WebElement item = cols.get(col - 1).findElement(By.className("item-name"));
                positionDetailUrl = item.getAttribute("href");
                return item;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public final WebElement getCellEditInTable(final WebDriver driver, final int row, final int col) {
        WebElement element = getDataTable(driver);
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        if (rows.size() > row - 1) {
            List<WebElement> cols = rows.get(row - 1).findElements(By.className("ui-cell-data"));
            if (cols.size() > col - 1) {
                WebElement item = cols.get(col + 2).findElement(By.className("update"));
                updatePositionUrl = item.getAttribute("href");
                return item;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public final WebElement getCellDeleteInTable(final WebDriver driver, final int row, final int col) {
        WebElement element = getDataTable(driver);
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        if (rows.size() > row - 1) {
            List<WebElement> cols = rows.get(row - 1).findElements(By.className("ui-cell-data"));
            if (cols.size() > col - 1) {
                return cols.get(col + 2).findElement(By.className("delete"));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public final List<String> getLongNameDataInTable(final WebDriver driver) {
        List<String> longNames = new ArrayList<>();
        WebElement element = getDataTable(driver);
        List<WebElement> rows = element.findElements(By.cssSelector(".ui-widget-content.ng-star-inserted"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.className("ui-cell-data"));
            for (WebElement col : cols) {
                //long name column
                if (cols.indexOf(col) == 1) {
                    WebElement item = col.findElement(By.className("item-name"));
                    longNames.add(item.getText());
                }
            }
        }
        return longNames;
    }

    public final String getUpdatePositionUrl() {
        return updatePositionUrl;
    }

    public final boolean isDialogConfirmDeleteDisplay(final WebDriver driver) {
        String style = getDialogConfirmDelete(driver).getAttribute("style");
        return style.contains("display: block");
    }

    private WebElement getDialogConfirmDelete(final WebDriver driver) {
        waitForElement(driver, dialogConfirmDelete);
        return dialogConfirmDelete;
    }
}
