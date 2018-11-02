package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;
import vn.asiantech.base.Constant;

import static vn.asiantech.base.DriverBase.TIME_OUT_SECONDS_NORMAL;

/**
 * @author at-vinhhuynh
 */
public class ProjectPage extends BasePage<ProjectPage> {

    @FindBy(className = "ui-datatable-scrollable-view")
    private WebElement projectPageContainer;

    @FindBy(css = "tbody.ui-datatable-data.ui-widget-content")
    private WebElement projectList;

    @FindBy(css = "input.form-control.ng-untouched.ng-pristine.ng-valid")
    private WebElement inputSearch;

    @FindBy(css = "label.ng-tns-c1-1.ui-dropdown-label.ui-inputtext.ui-corner-all.ng-star-inserted")
    private WebElement tvStatus;

    @FindBy(css = "div.ui-dropdown-items-wrapper")
    private WebElement filterList;

    @FindBy(css = "label.ui-multiselect-label.ui-corner-all")
    private WebElement tvTableOption;

    @FindBy(css = "div.ui-multiselect-panel.ui-widget.ui-widget-content.ui-corner-all.ui-shadow")
    private WebElement listTableOption;

    private String projectUrl;

    private String currentStatusFilter;

    private WebDriver driver;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final ProjectPage navigateTo(final WebDriver webDriver) {
        webDriver.get(Constant.PROJECT_PAGE_URL);
        return this;
    }

    public final int getProjectCount() {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        return projectList.findElements(By.tagName("tr")).size();
    }

    public final ProjectPage searchWith(final String key) {
        waitForElementDisplay(driver, inputSearch, TIME_OUT_SECONDS_NORMAL);
        inputSearch.sendKeys(key);
        return this;
    }

    public final String getProjectName() {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0);
        return firstProject.findElements(By.tagName("td")).get(1).findElement(By.tagName("strong")).getText();
    }

    public final ProjectPage projectNameClick() {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("strong")).click();
        return this;
    }

    public final ProjectPage projectAvatarClick() {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("img")).click();
        return this;
    }

    public final ProjectPage projectCodeClick() {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(0);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("a")).click();
        return this;
    }

    public final boolean tableFilterDisplayed() {
        waitForElementDisplay(driver, listTableOption, TIME_OUT_SECONDS_NORMAL);
        return listTableOption.isDisplayed();
    }

    public final ProjectPage statusFilterClick() {
        waitForElementDisplay(driver, tvStatus, TIME_OUT_SECONDS_NORMAL);
        tvStatus.click();
        return this;
    }

    public final ProjectPage filterItemClick() {
        waitForElementDisplay(driver, filterList, TIME_OUT_SECONDS_NORMAL);
        WebElement secondFilterItem = filterList.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        String currentFilterStatus = secondFilterItem.findElement(By.tagName("span")).getText();
        setCurrentStatusFilter(currentFilterStatus);
        secondFilterItem.click();
        return this;
    }

    public final String getStatusDisplayed() {
        waitForElementDisplay(driver, tvStatus, TIME_OUT_SECONDS_NORMAL);
        return tvStatus.getText();
    }

    public final Boolean filterListDisplayed() {
        waitForElementDisplay(driver, filterList, TIME_OUT_SECONDS_NORMAL);
        return filterList.isDisplayed();
    }

    public final ProjectPage currentTableOptionItemClick() {
        waitForElementDisplay(driver, tvTableOption, TIME_OUT_SECONDS_NORMAL);
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
