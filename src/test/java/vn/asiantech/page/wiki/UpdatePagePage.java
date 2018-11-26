package vn.asiantech.page.wiki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

public class UpdatePagePage extends BasePage<UpdatePagePage> {

    @FindBy(xpath = "//p-dropdown[contains(@formcontrolname,'parent_id')]")
    WebElement inputParent;

    private String itemParentFirst;

    @Override
    public UpdatePagePage navigateTo(WebDriver webDriver) {
        return this;
    }

    public void clickInputParent() {
        inputParent.click();
    }

    public boolean isDropDownMenu() {
        return inputParent.findElement(By.tagName("ul")).isDisplayed();
    }

    public void clickItemParent() {
        WebElement item = inputParent.findElement(By.tagName("ul")).findElements(By.tagName("li")).get(0);
        itemParentFirst = item.getText();
        item.click();
    }

    public boolean isChangeParent() {
        return inputParent.getText().equals(itemParentFirst);
    }
}
