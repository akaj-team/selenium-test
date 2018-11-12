package vn.asiantech.page.wiki;

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
    private static final int POS_ICON_PACKAGE = 5;
    private static final int POS_TITLE = 0;

    @FindBy(id = "link-to-wiki-author-detail")
    private WebElement txtAuthor;

    @FindBy(id = "wiki-tree-wrapper")
    private WebElement treeContainer;

    @FindBy(id = "btn-create-wiki")
    private WebElement btnChildPage;

    @FindBy(id = "btn-delete-wiki")
    private WebElement btnDelete;

    @FindBy(id = "btn-edit-wiki")
    private WebElement btnEdit;

    private WebDriver driver;

    public WikiPage(final WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public final WikiPage navigateTo(final WebDriver webDriver) {
        return null;
    }

    public final void clickAuthor() {
        txtAuthor.click();
    }

    public final void clickChildPageButton() {
        waitForElement(driver, btnChildPage);
        btnChildPage.click();
    }

    public final void clickUpdateButton() {
        btnEdit.click();
    }

    public final boolean isEnableDeleteButton() {
        waitForElement(driver, btnDelete);
        return btnDelete.isEnabled();
    }

    public final void clickTitle() {
        treeContainer.findElements(By.tagName("li")).get(POS_TITLE).click();
    }

    public final void clickIconPackageWikiHome() {
        getIconPackage(POS_TITLE).click();
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
        getIconPackage(POS_ICON_PACKAGE).click();
    }

    public final void clickIconPackageCompanyDocumentAgain() {
        getIconPackage(POS_ICON_PACKAGE).click();
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
        return treeContainer.findElements(By.cssSelector(".ui-treenode.ng-star-inserted")).get(POS_ICON_PACKAGE);
    }
}
