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

    private static final int TIME_OUT_SECOND = 10;

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

    public String getUrlOfCell() {
        return positionDetailUrl;
    }

    public WebElement getTitle(WebDriver driver) {
        waitForElement(driver, title, TIME_OUT_SECOND);
        return title;
    }

    public WebElement getBtnNewPosition(WebDriver driver) {
        waitForElement(driver, btnNewPosition, TIME_OUT_SECOND);
        return btnNewPosition;
    }

    public WebElement getBtnCareerPath(WebDriver driver) {
        waitForElement(driver, btnCareerPath, TIME_OUT_SECOND);
        return btnCareerPath;
    }

    public WebElement getSearchBox(WebDriver driver) {
        waitForElement(driver, searchBox, TIME_OUT_SECOND);
        return searchBox;
    }

    public void searchPosition(WebDriver driver, String text) {
        waitForElement(driver, searchBox, TIME_OUT_SECOND);
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.RETURN);
    }

    private WebElement getDataTable(WebDriver driver) {
        waitForElement(driver, dataTable, TIME_OUT_SECOND);
        return dataTable;
    }

    public WebElement getCellDataTable(WebDriver driver, int row, int col) {
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

    public WebElement getCellEditInTable(WebDriver driver, int row, int col) {
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

    public WebElement getCellDeleteInTable(WebDriver driver, int row, int col) {
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

    public List<String> getLongNameDataInTable(WebDriver driver) {
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

    public String getUpdatePositionUrl() {
        return updatePositionUrl;
    }

    public boolean isDialogConfirmDeleteDisplay(WebDriver driver) {
        String style = getDialogConfirmDelete(driver).getAttribute("style");
        return style.contains("display: block");
    }

    private WebElement getDialogConfirmDelete(WebDriver driver) {
        waitForElement(driver, dialogConfirmDelete, TIME_OUT_SECOND);
        return dialogConfirmDelete;
    }
}
