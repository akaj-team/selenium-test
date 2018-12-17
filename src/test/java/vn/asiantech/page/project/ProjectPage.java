package vn.asiantech.page.project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

/**
 * @author at-vinhhuynh
 */
public class ProjectPage extends BasePage<ProjectPage> {

    @FindBy(className = "ui-datatable-data")
    private WebElement projectList;

    @FindBy(className = "form-control")
    private WebElement inputSearch;

    @FindBy(className = "ui-dropdown-label")
    private WebElement tvStatus;

    @FindBy(className = "ui-dropdown-items-wrapper")
    private WebElement filterList;

    @FindBy(css = ".ui-multiselect.ui-widget.ui-state-default.ui-corner-all")
    private WebElement tvTableOption;

    @FindBy(css = "div.ui-multiselect-panel.ui-widget.ui-widget-content.ui-corner-all.ui-shadow")
    private WebElement listTableOption;

    private String projectUrl;

    private String currentStatusFilter;

    private WebDriver driver;

    public ProjectPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public final ProjectPage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.PROJECT_PAGE_URL);
        return this;
    }

    public final int getProjectCount() {
        waitForElementDisplay(driver, projectList, Constant.DEFAULT_TIME_OUT);
        return projectList.findElements(By.tagName("tr")).size();
    }

    public final ProjectPage searchWith(final String key) {
        waitForElementDisplay(driver, inputSearch, Constant.DEFAULT_TIME_OUT);
        inputSearch.sendKeys(key);
        inputSearch.sendKeys(Keys.ENTER);
        return this;
    }

    public final boolean isEmptyMessageShowed() {
        new WebDriverWait(driver, Constant.DEFAULT_TIME_OUT).until(it -> projectList.findElements(By.tagName("tr")).size() == 1);
        WebElement firstProject = projectList.findElement(By.tagName("tr"));
        return firstProject.findElement(By.tagName("td")).getAttribute("class").equals("ui-datatable-emptymessage");
    }

    public final ProjectPage projectNameClick() {
        waitForElementDisplay(driver, projectList, Constant.DEFAULT_TIME_OUT);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("strong")).click();
        return this;
    }

    public final ProjectPage projectAvatarClick() {
        waitForElementDisplay(driver, projectList, Constant.DEFAULT_TIME_OUT);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("img")).click();
        return this;
    }

    public final ProjectPage projectCodeClick() {
        waitForElementDisplay(driver, projectList, Constant.DEFAULT_TIME_OUT);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(0);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("a")).click();
        return this;
    }

    public final boolean tableFilterDisplayed() {
        waitForElementDisplay(driver, listTableOption, Constant.DEFAULT_TIME_OUT);
        return listTableOption.isDisplayed();
    }

    public final ProjectPage statusFilterClick() {
        waitForElementDisplay(driver, tvStatus, Constant.DEFAULT_TIME_OUT);
        tvStatus.click();
        return this;
    }

    public final ProjectPage filterItemClick() {
        waitForElementDisplay(driver, filterList, Constant.DEFAULT_TIME_OUT);
        WebElement secondFilterItem = filterList.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        String currentFilterStatus = secondFilterItem.findElement(By.tagName("span")).getText();
        setCurrentStatusFilter(currentFilterStatus);
        secondFilterItem.click();
        return this;
    }

    public final String getStatusDisplayed() {
        waitForElementDisplay(driver, tvStatus, Constant.DEFAULT_TIME_OUT);
        return tvStatus.getText();
    }

    public final Boolean filterListDisplayed() {
        waitForElementDisplay(driver, filterList, Constant.DEFAULT_TIME_OUT);
        return filterList.isDisplayed();
    }

    public final ProjectPage currentTableOptionItemClick() {
        waitForElementDisplay(driver, tvTableOption, Constant.DEFAULT_TIME_OUT);
        tvTableOption.click();
        return this;
    }

    public final String getProjectUrl() {
        return projectUrl;
    }

    private void setProjectUrl(final String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public final String getCurrentStatusFilter() {
        return currentStatusFilter;
    }

    private void setCurrentStatusFilter(final String currentStatusFilter) {
        this.currentStatusFilter = currentStatusFilter;
    }
}
