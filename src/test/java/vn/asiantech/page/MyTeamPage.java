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
    private WebElement lstMember;

    @FindBy(id = "btn-edit-team")
    private WebElement btnUpdateTeam;

    @FindBy(className = "btn-list")
    private WebElement btnTeams;

    @FindBy(css = ".btn.btn-block.btn-white.btn-add")
    private WebElement btnAddMember;

    @FindBy(css = ".ng-tns-c0-1.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-dialogState")
    private WebElement dlgAddMember;

    @FindBy(css = "input[type=\"text\"]")
    private WebElement txtSearchMember;

    @FindBy(css = "input[class=\"ui-inputtext ui-widget ui-state-default ui-corner-all\"]")
    private WebElement txtSearch2;

    @FindBy(className = "ui-listbox-list-wrapper")
    private WebElement listSearchResult;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel")
    private WebElement btnClose;

    @FindBy(name = "name")
    private WebElement txtName;

    @FindBy(css = ".ng-tns-c2-3.ui-dropdown.ui-widget.ui-state-default.ui-corner-all.ui-helper-clearfix")
    private WebElement ddlManager;

    @FindBy(css = ".ui-dropdown-filter.ui-inputtext.ui-widget.ui-state-default.ui-corner-all")
    private List<WebElement> txtSearchtoUpload;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private List<WebElement> lstUsertoUpdate;

    @FindBy(css = ".ng-tns-c2-4.ui-dropdown.ui-widget.ui-state-default.ui-corner-all.ui-helper-clearfix")
    private WebElement ddlTeamOfficer1;

    @FindBy(css = ".ng-tns-c2-5.ui-dropdown.ui-widget.ui-state-default.ui-corner-all.ui-helper-clearfix")
    private WebElement ddlTeamOfficer2;

    @FindBy(css = ".btn.btn-white.m-n")
    private List<WebElement> btnDeleteTeamOfficer;

    @FindBy(css = ".btn.btn-white.ng-star-inserted")
    private WebElement btnAddTeamOfficer;

    @FindBy(css = ".ui-fileupload-choose")
    private WebElement btnChangeLogo;

    @FindBy(name = "url")
    private WebElement txtTeamFolder;

    @FindBy(id = "cke_1_contents")
    private WebElement txtDescription;

    @FindBy(id = "btn-submit-team")
    private WebElement btnSubmittoUpdateTeam;


    private String textAddUser = "";
    private String textSearch = "";

    public MyTeamPage() {
    }

    @Override
    public final MyTeamPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final int checkNumberofTeam(final WebDriver driver) {
        waitForElement(driver, lstMember, TIME_OUT_SECOND);
        List<WebElement> tr = lstMember.findElements(By.tagName("tr"));
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
        waitForElement(driver, txtSearchMember, TIME_OUT_SECOND);
        txtSearchMember.sendKeys(username);
        txtSearchMember.sendKeys(Keys.ENTER);
        textSearch = username;
    }

    public final boolean verifySearchMemberResult(final WebDriver driver, final String n) {
        Integer k = 0;
        waitForElement(driver, lstMember, TIME_OUT_SECOND);
        List<WebElement> tr = lstMember.findElements(By.tagName("tr"));
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

    public final void updateTeamInfo(final WebDriver driver, final String name, final String manager, final String teamOfficer1,
                                     final String teamOfficer2, final String logo, final String teamFolder, final String description) {
        //update Name of team
        waitForElement(driver, txtName, TIME_OUT_SECOND);
        txtName.clear();
        txtName.sendKeys(name);

        //update Manager
        ddlManager.click();
        waitForElement(driver, txtSearchtoUpload.get(0), TIME_OUT_SECOND);
        txtSearchtoUpload.get(0).sendKeys(manager);
        List<WebElement> listManager = lstUsertoUpdate.get(0).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-3.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listManager.get(0).click();

        //update Team Officer
        waitForElement(driver, ddlTeamOfficer1, TIME_OUT_SECOND);
        ddlTeamOfficer1.click();
        waitForElement(driver, txtSearchtoUpload.get(1), TIME_OUT_SECOND);
        txtSearchtoUpload.get(1).sendKeys(teamOfficer1);
        waitForElement(driver, lstUsertoUpdate.get(1), TIME_OUT_SECOND);
        List<WebElement> listOfficer1 = lstUsertoUpdate.get(1).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-4.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listOfficer1.get(0).click();

        //add the seoond Team Officer and then detele
        btnAddTeamOfficer.click();
        waitForElement(driver, ddlTeamOfficer2, TIME_OUT_SECOND);
        ddlTeamOfficer2.click();
        waitForElement(driver, txtSearchtoUpload.get(2), TIME_OUT_SECOND);
        txtSearchtoUpload.get(2).sendKeys(teamOfficer2);
        waitForElement(driver, lstUsertoUpdate.get(2), TIME_OUT_SECOND);
        List<WebElement> listOfficer2 = lstUsertoUpdate.get(2).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-5.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listOfficer2.get(0).click();
        btnDeleteTeamOfficer.get(1).click();

        //update Logo team
        waitForElement(driver, btnChangeLogo, TIME_OUT_SECOND);
        WebElement logoFile = btnChangeLogo.findElement(By.cssSelector("input[type=\"file\"]"));
        logoFile.sendKeys(logo);

        //update Teamfolder
        waitForElement(driver, txtTeamFolder, TIME_OUT_SECOND);
        txtTeamFolder.clear();
        txtTeamFolder.sendKeys(teamFolder);

        //update Description
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,1000)", "");

        waitForElement(driver, txtDescription, TIME_OUT_SECOND);
        WebElement text = txtDescription.findElement(By.tagName("iframe"));
        driver.switchTo().frame(text);

        WebElement body = driver.findElement(By.tagName("body"));
        body.clear();
        body.sendKeys(description);
        driver.switchTo().defaultContent();
    }

    public final void clickSubmitBtntoUpload(final WebDriver driver) {
        waitForElement(driver, btnSubmittoUpdateTeam, TIME_OUT_SECOND);
        btnSubmittoUpdateTeam.click();
    }

    public final void verifyUpdateTeamSuccessful(final WebDriver driver, final String name, final String manager, final String teamOfficer1,
                                                 final String teamFolder, final String description) {
        waitForElement(driver, btnUpdateTeam, TIME_OUT_SECOND);
        driver.navigate().refresh();
        clickUpdateTeamBtn(driver);

        waitForElement(driver, txtName, TIME_OUT_SECOND);
        Assert.assertEquals(txtName.getText(), name);

        WebElement actualManager = ddlManager.findElement(By.tagName("label"));
        Assert.assertEquals(actualManager.getText().contains(manager), true);

        WebElement actualTeamOfficer = ddlTeamOfficer1.findElement(By.tagName("label"));
        Assert.assertEquals(actualTeamOfficer.getText().contains(teamOfficer1), true);

        Assert.assertEquals(txtTeamFolder.getText(), teamFolder);

        waitForElement(driver, txtDescription, TIME_OUT_SECOND);
        WebElement text = txtDescription.findElement(By.tagName("iframe"));
        driver.switchTo().frame(text);
        WebElement body = driver.findElement(By.tagName("body"));
        Assert.assertEquals(body.getText(), description);

    }
}

