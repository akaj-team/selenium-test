package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import vn.asiantech.base.BasePage;

import java.util.List;

public class MyTeamPage extends BasePage<MyTeamPage> {

    public static final int TIME_OUT_SECOND = 50;

    @FindBy(className = "nav-label")
    private List<WebElement> menuList;

    // @FindBy(css = "a[href='/organisation/teams/24']")
    @FindBy(css = ".nav.nav-third-level.collapse.in")
    private WebElement btnTeamTabParent;


    @FindBy(css = ".ui-datatable-data.ui-widget-content")
    private WebElement memberList;

    @FindBy(className = "btn-edit")
    private WebElement btnUpdateTeam;

    public MyTeamPage() {

    }

    @Override
    public final MyTeamPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/homepage");
        return null;
    }

    public final void clickOrganisationTab(final WebDriver driver) {
        waitForElement(driver, menuList.get(3), TIME_OUT_SECOND);
        menuList.get(3).click();
    }

    public final void clickMyTeamTab(final WebDriver driver) {

        waitForElement(driver, menuList.get(4), TIME_OUT_SECOND);
        menuList.get(4).click();
    }

    public final void clickTeamTab(final WebDriver driver) throws InterruptedException {

        waitForElement(driver, btnTeamTabParent, TIME_OUT_SECOND);
        List<WebElement> btnTeamTab = btnTeamTabParent.findElements(By.tagName("li"));


        //  WebElement btnTeamTab = btnTeamTabParent.findElement(By.cssSelector("*[class='m-l-sm ng-star-inserted active']"))
        //        .findElement((By.cssSelector("a[href='/organisation/teams/24']")));
        btnTeamTab.get(0).click();

    }

    public final int checkNumberofTeam(final WebDriver driver) {

        waitForElement(driver, memberList, TIME_OUT_SECOND);
        List<WebElement> tr = memberList.findElements(By.tagName("tr"));
        int i = tr.size();
        return i;

    }

    public final void clickUpdateTeam(final WebDriver driver) {

        waitForElement(driver, btnUpdateTeam, TIME_OUT_SECOND);
        btnUpdateTeam.click();

    }


}
