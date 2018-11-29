package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * @author at-anh.quach
 * UpdatePage page
 */

public class UpdateWikiPage extends BasePage<UpdateWikiPage> {

    @FindBy(xpath = "//p-dropdown[contains(@formcontrolname,'parent_id')]")
    private WebElement inputParent;

    private String itemParentFirst;

    @Override
    final public UpdateWikiPage navigateTo(final WebDriver webDriver) {
        return this;
    }

    final public void clickInputParent() {
        inputParent.click();
    }

    final public boolean isDropDownMenu() {
        return inputParent.findElement(By.tagName("ul")).isDisplayed();
    }

    final public void clickItemParent() {
        List<WebElement> items = inputParent.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        if (items.size() > 0) {
            itemParentFirst = items.get(0).getText();
            items.get(0).click();
        }
    }

    final public boolean isChangeParent() {
        return inputParent.getText().equals(itemParentFirst);
    }
}
