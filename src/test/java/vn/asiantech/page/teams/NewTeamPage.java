package vn.asiantech.page.teams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import vn.asiantech.base.BasePage;

import java.util.List;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/20/18.
 */
public class NewTeamPage extends BasePage<NewTeamPage> {
    @FindBy(css = ".ibox-content")
    private WebElement iboxContent;
    private WebElement itemManager;

    @Override
    public final NewTeamPage navigateTo(final WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/teams/new");
        return this;
    }

    public final Boolean clickPositionView() {
        WebElement positionView = iboxContent.findElements(By.className("form-group")).get(1).findElement(By.className("col-md-8"));
        positionView.click();
        itemManager = positionView.findElement(By.tagName("p-dropdown")).findElement(By.tagName("div"));
        return itemManager.getAttribute("class").contains("ui-dropdown-open");
    }

    public final void selectManager() {
        List<WebElement> positions = itemManager.findElement(By.cssSelector(".ui-dropdown-items-wrapper")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        positions.get(1).click();
    }
}
