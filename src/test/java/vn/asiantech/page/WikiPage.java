package vn.asiantech.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

/**
 * @author at-anh.quach
 * Wiki page
 */

public class WikiPage extends BasePage<WikiPage> {

    @FindBy(id = "link-to-employee-detail")
    private WebElement txtAuthor;

    @FindBy(id = "child-page-list-wrapper")
    private WebElement listTitle;

    @FindBy(css = ".ui-tree-container.ng-star-inserted")
    private WebElement treeContainer;

    @FindBy(id = "link-to-wiki-create")
    private WebElement btnChildPage;

    @FindBy(id = "btn-delete-wiki")
    private WebElement btnDelete;

    @FindBy(id = "link-to-wiki-edit")
    private WebElement btnEdit;

    @Override
    public final WikiPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void clickAuthor() {
        txtAuthor.click();
    }

    public final void clickChildPageButton() {
        btnChildPage.click();
    }

    public final void clickUpdateButton() {
        btnEdit.click();
    }

    public final boolean isEnableDeleteButton() {
        return btnDelete.isEnabled();
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
            getTitleCategory().findElement(By.cssSelector(".ui-treenode-children.ng-star-inserted")).isDisplayed();
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    private WebElement getIconPackage(final int pos) {
        return treeContainer.findElements(By.xpath("//span[contains(@class,'ui-tree-toggler')]")).get(pos);
    }

    private WebElement getTitleCategory() {
        return treeContainer.findElements(By.cssSelector(".ui-treenode.ng-star-inserted")).get(5);
    }
}
