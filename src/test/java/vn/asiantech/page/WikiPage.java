package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import static vn.asiantech.base.DriverBase.getDriver;

public class WikiPage extends BasePage<WikiPage> {

    @FindBy(linkText = "Huy Dinh Q.")
    private WebElement txtAuthor;

    @FindBy(css = ".m-b.m-l.pull-right")
    private WebElement pullRight;

    @FindBy(className = "list-unstyled")
    private WebElement listTitle;

    @FindBy(css = ".ui-tree-container.ng-star-inserted")
    private WebElement treeContainer;

    @Override
    public WikiPage navigateTo(WebDriver webDriver) {
        return null;
    }

    public final void clickAuthor() {
        txtAuthor.click();
    }

    public final void clickChildPageButton() {
        getButton(0).click();
    }

    public final void clickUpdateButton() {
        getButton(1).click();
    }

    public final void hoverDeleteButton() {
        Actions builder = new Actions(getDriver());
        builder.moveToElement(pullRight.findElement(By.tagName("button"))).build().perform();
    }

    public final boolean isEnableDeleteButton() {
        return pullRight.findElement(By.tagName("button")).isEnabled();
    }

    public final void clickTitle() {
        listTitle.findElements(By.tagName("li")).get(0).click();
    }

    public final void clickIconPackageWikiHome() {
        getIconPackage(0).click();
    }

    public final boolean isRemoveCategories() {
        try {
            treeContainer.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public final void clickIconPackageCompanyDocument() {
        clickIconPackageWikiHome();
        getIconPackage(5).click();
    }

    public final void clickIconPackageCompanyDocumentAgain() {
        getIconPackage(5).click();
    }

    public final boolean isHideCategoryInside() {
        try {
            getTitleCategory(5).findElement(By.cssSelector(".ui-treenode-children.ng-star-inserted")).isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    private WebElement getButton(int pos) {
        return pullRight.findElements(By.tagName("a")).get(pos);
    }

    private WebElement getIconPackage(int pos) {
        return treeContainer.findElements(By.xpath("//span[contains(@class,'ui-tree-toggler')]")).get(pos);
    }

    private WebElement getTitleCategory(int pos) {
        return treeContainer.findElements(By.cssSelector(".ui-treenode.ng-star-inserted")).get(pos);
    }
}
