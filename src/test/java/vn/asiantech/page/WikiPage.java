package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

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

    public void clickAuthor() {
        txtAuthor.click();
    }

    public void clickChildPageButton() {
        getButton(0).click();
    }

    public void clickUpdateButton() {
        getButton(1).click();
    }

    public void clickDeleteButton() {
        getButton(2).click();
    }

    public boolean isEnableDeleteButton() {
        return getButton(2).isEnabled();
    }

    public void clickTitle() {
        listTitle.findElements(By.tagName("li")).get(0).click();
    }

    public void clickIconPackageWikiHome() {
        getIconPackage(0).click();
    }

    public boolean isRemoveCategories() {
        try {
            treeContainer.isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clickIconPackageCompanyDocument() {
        clickIconPackageWikiHome();
        getIconPackage(5).click();
    }

    public boolean isHideCatogoryInside() {
        return getTitleCategory(2).findElement(By.cssSelector(".ui-treenode-children.ng-star-inserted")).getRect().height == 0;
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
