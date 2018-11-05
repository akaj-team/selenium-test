package vn.asiantech.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;

/**
 * @author at-huethai
 */
public class MyTeamPage extends BasePage<MyTeamPage> {

    @FindBy(className = "ui-datatable-data")
    private WebElement lstMember;

    @FindBy(id = "btn-edit-team")
    private WebElement btnUpdateTeam;

    @FindBy(id = "btn-link-to-team-list")
    private WebElement btnTeams;

    @FindBy(id = "btn-add-member")
    private WebElement btnAddMember;

    @FindBy(id = "member-dialog-wrapper")
    private WebElement dlgAddMember;

    @FindBy(name = "search")
    private WebElement txtSearchMember;

    @FindBy(css = ".ui-inputtext.ui-widget.ui-state-default")
    private WebElement txtSearchtoAdd;

    @FindBy(className = "ui-listbox-list-wrapper")
    private WebElement listSearchResult;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel")
    private WebElement btnClose;

    @FindBy(name = "name")
    private WebElement txtName;

    @FindBy(css = ".ng-tns-c2-3.ui-dropdown.ui-widget")
    private WebElement ddlManager;

    @FindBy(css = ".ui-dropdown-filter.ui-inputtext")
    private List<WebElement> txtSearchtoUpload;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private List<WebElement> lstUsertoUpdate;

    @FindBy(css = ".ng-tns-c2-4.ui-dropdown.ui-widget")
    private WebElement ddlTeamOfficer1;

    @FindBy(css = ".ng-tns-c2-5.ui-dropdown.ui-widget")
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

    @FindBy(id = "static-dialog-wrapper")
    private WebElement dlgDeleteMember;

    private String textAddUser = "";
    private String textSearch = "";
    private String emptyMsg = "No records found";
    private static final int ID_DELETE_COLUMN = 6;

    public MyTeamPage() {
    }

    @Override
    public final MyTeamPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final int checkNumberofTeam(final WebDriver driver) {
        waitForElement(driver, lstMember);
        List<WebElement> tr = lstMember.findElements(By.tagName("tr"));
        return tr.size();
    }

    public final void verifyMyTeamInfo(final WebDriver driver, final String key, final String value) {
        WebElement dt = driver.findElement(By.xpath("//dt[contains(text(), '" + key + "')]"));
        WebElement dd = dt.findElement(By.xpath("following-sibling::dd"));
        WebElement span = dd.findElement(By.tagName("span"));
        String text = span.getText();

        if (!text.equals("")) {
            Assert.assertEquals(text, value);
        } else {
            WebElement a = dd.findElement(By.tagName("a"));
            Assert.assertEquals(a.getText(), value);
        }
    }

    public final void clickUpdateTeamBtn(final WebDriver driver) {
        waitForElement(driver, btnUpdateTeam);
        btnUpdateTeam.click();
    }

    public final void redirectPage(final WebDriver driver, final String path) {
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed());
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url.substring(url.length() - path.length()), path);
    }

    public final void clickTeamsBtn(final WebDriver driver) {
        waitForElement(driver, btnTeams);
        btnTeams.click();
    }

    public final void clickAddMemberBtn(final WebDriver driver) {
        waitForElement(driver, btnAddMember);
        btnAddMember.click();
    }

    public final boolean getAddMemberPopupName() {
        try {
            dlgAddMember.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public final void inputUserNametoAdd(final WebDriver driver, final String username) {
        waitForElement(driver, txtSearchtoAdd);
        txtSearchtoAdd.sendKeys(username);
        textAddUser = username;
    }

    public final boolean verifySearchResult(final WebDriver driver, final String n) {
        int j = 0;
        waitForElement(driver, listSearchResult);
        List<WebElement> list = listSearchResult.findElement(By.className("ui-listbox-list")).findElements(By.tagName("li"));
        for (WebElement i : list
        ) {
            WebElement userName = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                    .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                    .findElement(By.cssSelector("span[class=\"m-l-xl info-grouping-text\"]"))
                    .findElement(By.tagName("strong"));
            waitForElement(driver, userName);
            //         Assert.assertEquals(userName.getText(), text);
            if (userName.getText().toLowerCase().contains(textAddUser.toLowerCase())) {
                j++;
            }
        }
        Assert.assertEquals(String.valueOf(j), n);
        return true;
    }

    public final void clickAddBtn(final WebDriver driver) {
        waitForElement(driver, listSearchResult);
        List<WebElement> list = listSearchResult.findElement(By.className("ui-listbox-list")).findElements(By.tagName("li"));
        for (WebElement i : list
        ) {
            WebElement userName = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                    .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                    .findElement(By.cssSelector("span[class=\"m-l-xl info-grouping-text\"]"))
                    .findElement(By.tagName("strong"));
            waitForElement(driver, userName);
            if (userName.getText().contains(textAddUser)) {
                WebElement btnAdd = i.findElement(By.cssSelector(".ui-helper-clearfix.dropdown-item.ng-star-inserted"))
                        .findElement(By.cssSelector("div[class=\"info-grouping name-item\"]"))
                        .findElement(By.cssSelector("button[class=\"btn btn-sm btn-default m-t-xs\"]"));
                waitForElement(driver, btnAdd);
                btnAdd.click();
                break;
            }
        }
    }

    public final void clickCloseBtn(final WebDriver driver) {
        waitForElement(driver, btnClose);
        btnClose.click();
    }

    public final boolean verifyAddMemberPopupDisappeared(final WebDriver driver) {
        waitForElement(driver, dlgAddMember);
        WebElement status = dlgAddMember.findElement(By.cssSelector(".ng-tns-c0-1.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-shadow.ng-trigger.ng-trigger-dialogState"));
        Assert.assertTrue(status.getAttribute("style").contains("display: none"));
        return true;
    }

    public final void inputUserNametoSearch(final WebDriver driver, final String username) {
        waitForElement(driver, txtSearchMember);
        txtSearchMember.clear();
        txtSearchMember.sendKeys(username);
        txtSearchMember.sendKeys(Keys.ENTER);
        textSearch = username;
    }

    public final boolean verifySearchMemberResult(final WebDriver driver, final String n) {
        int k = 0;
        waitForElement(driver, lstMember);
        List<WebElement> tr = lstMember.findElements(By.tagName("tr"));
        for (WebElement i : tr
        ) {
            if (!n.equals("0")) {
                WebElement userName = i.findElements(By.tagName("td")).get(0)
                        .findElement(By.cssSelector("span[class=\"info-grouping-text truncate\"]"))
                        .findElement(By.tagName("Strong"));
                waitForElement(driver, userName);
                if (userName.getText().toLowerCase().contains(textSearch.toLowerCase())) {
                    k++;
                }
            } else {
                WebElement msg = i.findElements(By.tagName("td")).get(0)
                        .findElement(By.tagName("span"));
                waitForElement(driver, msg);
                Assert.assertEquals(msg.getText(), emptyMsg);
                break;
            }
        }
        Assert.assertEquals(String.valueOf(k), n);
        return true;
    }

    public final void updateTeamInfo(final WebDriver driver, final String name, final String manager, final String teamOfficer1,
                                     final String teamOfficer2, final String logo, final String teamFolder, final String description) {
        //update Name of team
        waitForElement(driver, txtName);
        txtName.clear();
        txtName.sendKeys(name);

        //update Manager
        ddlManager.click();
        waitForElement(driver, txtSearchtoUpload.get(0));
        txtSearchtoUpload.get(0).sendKeys(manager);
        List<WebElement> listManager = lstUsertoUpdate.get(0).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-3.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listManager.get(0).click();

        //update Team Officer
        waitForElement(driver, ddlTeamOfficer1);
        ddlTeamOfficer1.click();
        waitForElement(driver, txtSearchtoUpload.get(1));
        txtSearchtoUpload.get(1).sendKeys(teamOfficer1);
        waitForElement(driver, lstUsertoUpdate.get(1));
        List<WebElement> listOfficer1 = lstUsertoUpdate.get(1).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-4.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listOfficer1.get(0).click();

        //add the seoond Team Officer and then detele
        btnAddTeamOfficer.click();
        waitForElement(driver, ddlTeamOfficer2);
        ddlTeamOfficer2.click();
        waitForElement(driver, txtSearchtoUpload.get(2));
        txtSearchtoUpload.get(2).sendKeys(teamOfficer2);
        waitForElement(driver, lstUsertoUpdate.get(2));
        List<WebElement> listOfficer2 = lstUsertoUpdate.get(2).findElement(By.cssSelector(".ui-helper-reset.ng-tns-c2-5.ng-star-inserted"))
                .findElements(By.tagName("li"));
        listOfficer2.get(0).click();
        btnDeleteTeamOfficer.get(1).click();

        //update Logo team
        waitForElement(driver, btnChangeLogo);
        WebElement logoFile = btnChangeLogo.findElement(By.cssSelector("input[type=\"file\"]"));
        logoFile.sendKeys(logo);

        //update Teamfolder
        waitForElement(driver, txtTeamFolder);
        txtTeamFolder.clear();
        txtTeamFolder.sendKeys(teamFolder);

        //update Description
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,1000)", "");

        waitForElement(driver, txtDescription);
        WebElement text = txtDescription.findElement(By.tagName("iframe"));
        driver.switchTo().frame(text);

        WebElement body = driver.findElement(By.tagName("body"));
        body.clear();
        body.sendKeys(description);
        driver.switchTo().defaultContent();
    }

    public final void clickSubmitBtntoUpload(final WebDriver driver) {
        waitForElement(driver, btnSubmittoUpdateTeam);
        btnSubmittoUpdateTeam.click();
    }

    public final void clickDeleteBtn(final WebDriver driver) {
        waitForElement(driver, lstMember);
        List<WebElement> tr = lstMember.findElements(By.tagName("tr"));
        for (WebElement i : tr
        ) {
            WebElement btnDelete = i.findElements(By.tagName("td")).get(ID_DELETE_COLUMN)
                    .findElement(By.cssSelector("span[class=\"ui-cell-data ng-star-inserted\"]"))
                    .findElement(By.tagName("button"));
            waitForElement(driver, btnDelete);
            btnDelete.click();
            break;
        }
        WebElement btnDeleteConfirm = dlgDeleteMember.findElement(By.cssSelector(".ng-tns-c0-2.ui-dialog.ui-widget"))
                .findElement(By.cssSelector(".ui-dialog-footer.ui-widget-content.ng-tns-c0-2"))
                .findElement(By.cssSelector(".btn.btn-sm.btn-warning.btn-submit"));
        btnDeleteConfirm.click();
    }

    public final void verifyDeleteUserSuccessful(final WebDriver driver) {
        driver.navigate().refresh();
        inputUserNametoSearch(driver, textSearch);
        Assert.assertTrue(verifySearchMemberResult(driver, "0"));
    }
}

