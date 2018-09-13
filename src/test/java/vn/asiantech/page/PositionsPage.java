package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * @author at-trungnguyen
 */
public class PositionsPage extends BasePage<PositionsPage> {

    public static final int TIME_OUT_SECOND = 10;

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

    @FindBy(tagName = "h2")
    private WebElement title;
    @FindBy(className = "btn-add")
    private WebElement btnNewPosition;
    @FindBy(className = "btn-org")
    private WebElement btnCareerPath;
    @FindBy(css = "input[name=search]")
    private WebElement searchBox;


    @Override
    public final PositionsPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/positions");
        return this;
    }
}
