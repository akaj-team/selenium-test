package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

public class UpdateWikiPage extends BasePage<UpdateWikiPage> {

    @FindBy(xpath = "//p-dropdown[contains(@formcontrolname,'parent_id')]")
    WebElement inputParent;

    private String itemParentFirst;

    @Override
    public UpdateWikiPage navigateTo(WebDriver webDriver) {
        return this;
    }

    public void clickInputParent() {
        inputParent.click();
    }

    public boolean isDropDownMenu() {
        return inputParent.findElement(By.tagName("ul")).isDisplayed();
    }

    public void clickItemParent() {
        List<WebElement> items = inputParent.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        if (items.size() > 0) {
            itemParentFirst = items.get(0).getText();
            items.get(0).click();
        }
    }

    public boolean isChangeParent() {
        return inputParent.getText().equals(itemParentFirst);
    }
}
