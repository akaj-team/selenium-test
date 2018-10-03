package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-huethai
 */
public class MyTeamPage extends BasePage<MyTeamPage> {

    public static final int TIME_OUT_SECOND = 50;

    @FindBy(css = ".ui-datatable-data.ui-widget-content")
    private WebElement listMember;

    @FindBy(className = "btn-edit")
    private WebElement btnUpdateTeam;

    @FindBy(className = "btn-list")
    private WebElement btnTeams;

    @FindBy(css = ".btn.btn-block.btn-white.btn-add")
    private WebElement btnAddMember;

    @FindBy(css = ".ng-tns-c0-1.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-dialogState")
    private WebElement dlgAddMember;

    @FindBy(css = "input[type=\"text\"]")
    private WebElement txtSearch;

    @FindBy(css = "input[class=\"ui-inputtext ui-widget ui-state-default ui-corner-all\"]")
    private WebElement txtSearch2;

    @FindBy(className = "ui-listbox-list-wrapper")
    private WebElement listSearchResult;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel")
    private WebElement btnClose;

    private String textAddUser = "";
    private String textSearch = "";

    public MyTeamPage() {
    }

    @Override
    public final MyTeamPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final int checkNumberofTeam(final WebDriver driver) {
        waitForElement(driver, listMember, TIME_OUT_SECOND);
        List<WebElement> tr = listMember.findElements(By.tagName("tr"));
        int i = tr.size();
        return i;
    }

    public final void clickUpdateTeamBtn(final WebDriver driver) {
        waitForElement(driver, btnUpdateTeam, TIME_OUT_SECOND);
        btnUpdateTeam.click();
    }

    public final void redirectPage(final WebDriver driver, final String path) {
        new WebDriverWait(driver, TIME_OUT_SECOND).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed());
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url.substring(url.length() - path.length()), path);
    }

    public final void clickTeamsBtn(final WebDriver driver) {
        waitForElement(driver, btnTeams, TIME_OUT_SECOND);
        btnTeams.click();
    }

    public final void clickAddMemberBtn(final WebDriver driver) {
        waitForElement(driver, btnAddMember, TIME_OUT_SECOND);
        btnAddMember.click();
    }

    public final boolean getAddMemberPopupName(final WebDriver driver) {
        try {
            dlgAddMember.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void inputUserNametoAdd(final WebDriver driver, final String username) {
        waitForElement(driver, txtSearch2, TIME_OUT_SECOND);
        txtSearch2.sendKeys(username);
        textAddUser = username;
    }

    public final boolean verifySearchResult(final WebDriver driver, final String n) {
        Integer j = 0;
        waitForElement(driver, listSearchResult, TIME_OUT_SECOND);
        List<WebElement> list = listSearchResult.findElement(By.className("ui-listbox-list")).findElements(By.tagName("li"));
        for (WebElement i : list
        ) {
            WebElement userName = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                    .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                    .findElement(By.cssSelector("span[class=\"m-l-xl info-grouping-text\"]"))
                    .findElement(By.tagName("strong"));
            waitForElement(driver, userName, TIME_OUT_SECOND);
            //         Assert.assertEquals(userName.getText(), text);
            if (userName.getText().toLowerCase().contains(textAddUser.toLowerCase())) {
                j++;
            }
        }
        if (1 == 1) {
            Assert.assertEquals(j.toString(), n);
            return true;
        } else {
            return false;
        }
    }

    public final void clickAddBtn(final WebDriver driver) {
        waitForElement(driver, listSearchResult, TIME_OUT_SECOND);
        List<WebElement> list = listSearchResult.findElement(By.className("ui-listbox-list")).findElements(By.tagName("li"));
        for (WebElement i : list
        ) {
            WebElement userName = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                    .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                    .findElement(By.cssSelector("span[class=\"m-l-xl info-grouping-text\"]"))
                    .findElement(By.tagName("strong"));
            waitForElement(driver, userName, TIME_OUT_SECOND);
            if (userName.getText().contains(textAddUser)) {
                WebElement btnAdd = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                        .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                        .findElement(By.cssSelector("button[class=\"btn btn-sm btn-default m-t-xs\"]"));
                waitForElement(driver, btnAdd, TIME_OUT_SECOND);
                btnAdd.click();
                break;
            }
        }
    }

    public final void clickCloseBtn(final WebDriver driver) {
        waitForElement(driver, btnClose, TIME_OUT_SECOND);
        btnClose.click();
    }

    public final boolean verifyAddMemberPopupDisappeared(final WebDriver driver) {
        waitForElement(driver, dlgAddMember, TIME_OUT_SECOND);
        Assert.assertTrue(dlgAddMember.getAttribute("style").contains("display: none"));
        return true;
    }

    public final void inputUserNametoSearch(final WebDriver driver, final String username) {
        waitForElement(driver, txtSearch, TIME_OUT_SECOND);
        txtSearch.sendKeys(username);
        txtSearch.sendKeys(Keys.ENTER);
        textSearch = username;
    }

    public final boolean verifySearchMemberResult(final WebDriver driver, final String n) {
        Integer k = 0;
        waitForElement(driver, listMember, TIME_OUT_SECOND);
        List<WebElement> tr = listMember.findElements(By.tagName("tr"));
        for (WebElement i : tr
        ) {
            WebElement userName = i.findElements(By.tagName("td")).get(0)
                    .findElement(By.cssSelector("span[class=\"info-grouping-text truncate\"]"))
                    .findElement(By.tagName("Strong"));

            waitForElement(driver, userName, TIME_OUT_SECOND);
            if (userName.getText().toLowerCase().contains(textSearch.toLowerCase())) {
                k++;
            }
        }
        if (1 == 1) {
            Assert.assertEquals(k.toString(), n);
            return true;
        } else {
            return false;
        }
    }
}

