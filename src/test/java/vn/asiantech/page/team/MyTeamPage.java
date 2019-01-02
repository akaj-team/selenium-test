package vn.asiantech.page.team;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import java.util.List;

import static vn.asiantech.base.Constant.DEFAULT_TIME_OUT;
import static vn.asiantech.base.Constant.URL_MYTEAM_PAGE;

/**
 * @author at-huethai
 */
public class MyTeamPage extends BasePage<MyTeamPage> {

    @FindBy(className = "ui-datatable-data")
    private WebElement tableMember;

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
    private WebElement txtSearchToAdd;

    @FindBy(className = "ui-listbox-list-wrapper")
    private WebElement listSearchResult;

    @FindBy(css = ".btn.btn-sm.btn-default.btn-cancel")
    private WebElement btnClose;

    @FindBy(name = "name")
    private WebElement txtName;

    @FindBy(xpath = "//label[contains(text(),'Manager')]/../div")
    private WebElement dropDownManager;

    @FindBy(xpath = "//label[contains(text(),'Team Officer')]/../div")
    private WebElement dropTeamOfficer;

    @FindBy(css = ".btn.btn-white.m-n")
    private List<WebElement> btnDeleteTeamOfficer;

    @FindBy(css = ".btn.btn-white.ng-star-inserted")
    private WebElement btnAddTeamOfficer;

    @FindBy(name = "email")
    private WebElement txtEmail;

    @FindBy(name = "url")
    private WebElement txtTeamFolder;

    @FindBy(id = "cke_1_contents")
    private WebElement txtDescription;

    @FindBy(id = "btn-submit-team")
    private WebElement btnSubmitToUpdateTeam;

    private String textAddUser = "";
    private String textSearch = "";
    private static final int ID_DELETE_COLUMN = 6;
    private static final int DEFAULT_TIME_SLEEP = 5000;

    private WebDriver driver;

    public MyTeamPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final MyTeamPage navigateTo(final WebDriver webDriver) {
        webDriver.get(URL_MYTEAM_PAGE);
        return this;
    }

    public final int checkNumberOfTeam() {
        waitForElement(driver, tableMember);
        return tableMember.findElements(By.tagName("tr")).size();
    }

    public final void verifyMyTeamInfo(final String key, final String value) {
        WebElement infoTitle = driver.findElement(By.xpath("//dt[contains(text(), '" + key + "')]"));
        WebElement infoContent = infoTitle.findElement(By.xpath("following-sibling::dd"));
        WebElement keyContent = infoContent.findElement(By.tagName("span"));
        String text = keyContent.getText();
        if (!text.equals("")) {
            Assert.assertEquals(text, value);
        } else {
            Assert.assertEquals(infoContent.findElement(By.tagName("a")).getText(), value);
        }
    }

    public final void clickUpdateTeamButton() {
        waitForElement(driver, btnUpdateTeam);
        btnUpdateTeam.click();
    }

    public final void redirectPage(final String path) throws InterruptedException {
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(
                webDriver -> webDriver.findElement(By.className("col-sm-8")).findElement(By.tagName("h2")).isDisplayed());
        Thread.sleep(DEFAULT_TIME_SLEEP);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url.substring(url.length() - path.length()), path);
    }

    public final void clickTeamsButton() {
        waitForElement(driver, btnTeams);
        btnTeams.click();
    }

    public final void clickAddMemberButton() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.getCurrentUrl();
        driver.manage().window().maximize();
        Thread.sleep(DEFAULT_TIME_SLEEP);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForElement(driver, btnAddMember);
        btnAddMember.click();
    }

    public final boolean getAddMemberPopupName() {
        return isElementPresented(dlgAddMember);
    }

    public final void inputUserNameToAdd(final String username) {
        waitForElement(driver, txtSearchToAdd);
        txtSearchToAdd.sendKeys(username);
        textAddUser = username;
    }

    public final boolean verifySearchResult() {
        waitForElement(driver, listSearchResult);
        List<WebElement> items = listSearchResult.findElements(By.tagName("li"));
        for (WebElement item : items) {
            if (item.getText().toLowerCase().trim().contains(textAddUser.toLowerCase().trim())) {
                return true;
            }
        }
        return false;
    }

    public final void clickAddButton() {
        waitForElement(driver, listSearchResult);
        List<WebElement> items = listSearchResult.findElements(By.tagName("li"));
        for (WebElement item : items) {
            if (item.getText().toLowerCase().trim().contains(textAddUser.toLowerCase().trim())) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public final void clickCloseButton() throws InterruptedException {
        Thread.sleep(DEFAULT_TIME_SLEEP);
        waitForElement(driver, btnClose);
        btnClose.click();
    }

    public final void verifyAddMemberPopupDisappeared() {
        waitForElement(driver, dlgAddMember);
        WebElement status = dlgAddMember.findElement(By.cssSelector(".ui-dialog.ui-widget"));
        Assert.assertTrue(status.getAttribute("style").contains("display: none"));
    }

    public final void inputUserNameToSearch(final String username) {
        waitForElement(driver, txtSearchMember);
        txtSearchMember.clear();
        txtSearchMember.sendKeys(username);
        txtSearchMember.sendKeys(Keys.ENTER);
        textSearch = username;
    }

    public final boolean verifySearchMemberResult(final String record) {
        int k = 0;
        waitForElement(driver, tableMember);
        List<WebElement> tr = tableMember.findElements(By.tagName("tr"));
        for (WebElement i : tr) {
            if (!record.equals("0")) {
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
                Assert.assertEquals(msg.getText(), "No records found");
                break;
            }
        }
        Assert.assertEquals(String.valueOf(k), record);
        return true;
    }

    public final MyTeamPage fillTeamName(final String teamName) {
        waitForElement(driver, txtName);
        txtName.clear();
        txtName.sendKeys(teamName);
        return this;
    }

    public final MyTeamPage selectTeamManager(final String managerName) {
        dropDownManager.click();
        waitForElement(driver, dropDownManager.findElement(By.className("ui-dropdown-items-wrapper")));
        dropDownManager.findElement(By.cssSelector(".ui-dropdown-filter.ui-inputtext")).sendKeys(managerName);
        List<WebElement> managers = dropDownManager.findElements(By.tagName("li"));
        managers.get(0).click();
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.invisibilityOf(dropDownManager.findElement(By.className("ui-dropdown-items-wrapper"))));
        return this;
    }

    public final MyTeamPage selectTeamOfficers(final String teamOfficer1, final String teamOfficer2) {
        //add first Team Officer
        dropTeamOfficer.click();
        waitForElement(driver, dropTeamOfficer.findElement(By.className("ui-dropdown-items-wrapper")));
        dropTeamOfficer.findElements(By.cssSelector(".ui-dropdown-filter.ui-inputtext")).get(0).sendKeys(teamOfficer1);
        List<WebElement> firstTeamOfficers = dropTeamOfficer.findElements(By.tagName("li"));
        firstTeamOfficers.get(0).click();
        new WebDriverWait(driver, DEFAULT_TIME_OUT).until(ExpectedConditions.invisibilityOf(dropTeamOfficer.findElement(By.className("ui-dropdown-items-wrapper"))));

        //add the second Team Officer and then delete
        btnAddTeamOfficer.click();
        waitForElement(driver, dropTeamOfficer.findElement(By.cssSelector(".ng-untouched.ng-pristine.ng-invalid.ng-star-inserted")));
        dropTeamOfficer.findElement(By.cssSelector(".ng-untouched.ng-pristine.ng-invalid.ng-star-inserted")).click();
        waitForElement(driver, dropTeamOfficer.findElement(By.className("ui-dropdown-items-wrapper")));
        dropTeamOfficer.findElements(By.cssSelector(".ui-dropdown-filter.ui-inputtext")).get(1).sendKeys(teamOfficer2);
        List<WebElement> secondTeamOfficers = dropTeamOfficer.findElements(By.tagName("li"));
        secondTeamOfficers.get(0).click();
        btnDeleteTeamOfficer.get(1).click();
        return this;
    }

    public final MyTeamPage fillEmail(final String email) {
        waitForElement(driver, txtEmail);
        txtEmail.sendKeys(email);
        return this;
    }

    public final MyTeamPage fillTeamFolder(final String teamFolder) {
        waitForElement(driver, txtTeamFolder);
        txtTeamFolder.clear();
        txtTeamFolder.sendKeys(teamFolder);
        return this;
    }

    public final void fillDescription(final String description) {
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

    public final void clickSubmitButtonToUpload() throws InterruptedException {
        Thread.sleep(DEFAULT_TIME_SLEEP);
        waitForElement(driver, btnSubmitToUpdateTeam);
        btnSubmitToUpdateTeam.click();
    }

    public final void clickDeleteButton() {
        waitForElement(driver, tableMember);
        List<WebElement> rows = tableMember.findElements(By.tagName("tr"));
        WebElement btnDelete = rows.get(0).findElements(By.tagName("td")).get(ID_DELETE_COLUMN)
                .findElement(By.tagName("button"));
        waitForElement(driver, btnDelete);
        btnDelete.click();
        WebElement confirmDialog = driver.findElement(By.id("static-dialog-wrapper"));
        waitForElement(driver, confirmDialog);
        confirmDialog.findElement(By.className("btn-submit")).click();
    }

    public final void verifyDeleteUserSuccessful() {
        driver.navigate().refresh();
        inputUserNameToSearch(textSearch);
        Assert.assertTrue(verifySearchMemberResult("0"));
    }
}
