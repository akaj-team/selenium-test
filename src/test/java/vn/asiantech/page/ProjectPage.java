package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import static vn.asiantech.base.DriverBase.TIME_OUT_SECONDS_NORMAL;

/**
 * @author at-vinhhuynh
 */
public class ProjectPage extends BasePage<ProjectPage> {
    public static final String PROJECT_PAGE_URL = "http://portal-stg.asiantech.vn/project-management/projects";
    public static final int DEFAULT_PAGE_COUNT = 30;

    @FindBy(css = "div.ui-datatable-scrollable-view.ui-datatable-frozen-view.ng-star-inserted")
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

    private String currentTableOption;

    @Override
    public final ProjectPage navigateTo(final WebDriver webDriver) {
        webDriver.get(PROJECT_PAGE_URL);
        return this;
    }

    public final int getProjectCount(final WebDriver driver) {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        return projectList.findElements(By.tagName("tr")).size();
    }

    public final ProjectPage searchWith(final WebDriver driver, final String key) {
        waitForElementDisplay(driver, inputSearch, TIME_OUT_SECONDS_NORMAL);
        inputSearch.sendKeys(key);
        return this;
    }

    public final String getProjectName(final WebDriver driver) {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0);
        return firstProject.findElements(By.tagName("td")).get(1).findElement(By.tagName("strong")).getText();
    }

    public final ProjectPage projectNameClick(final WebDriver driver) {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("strong")).click();
        return this;
    }

    public final ProjectPage projectAvatarClick(final WebDriver driver) {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(1);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("img")).click();
        return this;
    }

    public final ProjectPage projectCodeClick(final WebDriver driver) {
        waitForElementDisplay(driver, projectList, TIME_OUT_SECONDS_NORMAL);
        WebElement firstProject = projectList.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td")).get(0);
        projectUrl = firstProject.findElement(By.tagName("a")).getAttribute("href");
        setProjectUrl(projectUrl);
        firstProject.findElement(By.tagName("a")).click();
        return this;
    }

    public final boolean tableFilterDisplayed(final WebDriver driver) {
        waitForElementDisplay(driver, listTableOption, TIME_OUT_SECONDS_NORMAL);
        return listTableOption.isDisplayed();
    }

    public final ProjectPage statusFilterClick(final WebDriver driver) {
        waitForElementDisplay(driver, tvStatus, TIME_OUT_SECONDS_NORMAL);
        tvStatus.click();
        return this;
    }

    public final ProjectPage filterItemClick(final WebDriver driver) {
        waitForElementDisplay(driver, filterList, TIME_OUT_SECONDS_NORMAL);
        WebElement secondFilterItem = filterList.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(1);
        String currentFilterStatus = secondFilterItem.findElement(By.tagName("span")).getText();
        setCurrentStatusFilter(currentFilterStatus);
        secondFilterItem.click();
        return this;
    }

    public final String getStatusDisplayed(final WebDriver driver) {
        waitForElementDisplay(driver, tvStatus, TIME_OUT_SECONDS_NORMAL);
        return tvStatus.getText();
    }

    public final Boolean filterListDisplayed(final WebDriver driver) {
        waitForElementDisplay(driver, filterList, TIME_OUT_SECONDS_NORMAL);
        return filterList.isDisplayed();
    }

    public final ProjectPage currentTableOptionItemClick(final WebDriver driver) {
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

    public final String tableOptionDisplayed() {
        return tvTableOption.getText();
    }

    public final String getCurrentTableOption() {
        return currentTableOption;
    }

    private void setCurrentTableOption(final String currentTableOption) {
        this.currentTableOption = currentTableOption;
    }

    public final ProjectPage tableOptionItemClick(final WebDriver driver) {
        waitForElementDisplay(driver, listTableOption, TIME_OUT_SECONDS_NORMAL);
        WebElement listOption = listTableOption.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        setCurrentTableOption(listOption.findElement(By.tagName("label")).getText());
        listOption.findElement(By.tagName("label")).click();
        return this;
    }
}
