package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;
import vn.asiantech.page.LeavePlannerPage;
import vn.asiantech.page.MyTeamPage;

import java.util.List;

public class MyTeamDefinitions extends DriverBase implements En {

    private WebDriver driver;
    private MyTeamPage myTeamPage;


    public MyTeamDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        When("^I click on Organisation tab$", () -> {
            myTeamPage = initPage(getDriver(), MyTeamPage.class);
            myTeamPage.clickOrganisationTab(driver);
            Thread.sleep(5000);
        });

        And("^I click on My Teams tab$", () -> {
            myTeamPage.clickMyTeamTab(driver);
        });

        And("^I click on Android tab$", () -> {
            myTeamPage.clickTeamTab(driver);

        });
        Then("^The member of Android team is displayed$", () -> {
           Assert.assertEquals(39,myTeamPage.checkNumberofTeam(driver));

        });

        And("^The \"([^\"]*)\" value is \"([^\"]*)\"$", (String key, String value) -> {

            WebElement dt = driver.findElement(By.xpath("//dt[contains(text(), '" + key + "')]"));
            WebElement dd = dt.findElement(By.xpath("following-sibling::dd"));
            WebElement span = dd.findElement(By.tagName("span"));
            String text = span.getText();

            if (text != "") {
                Assert.assertEquals(text, value);
            } else {
                WebElement a = dd.findElement(By.tagName("a"));
                Assert.assertEquals(a.getText(), value);
            }


            Thread.sleep(2000);


        });
        When("^I click on 'Update Team' button$", () -> {

           myTeamPage.clickUpdateTeam(driver);

        });
        Then("^The web page navigates to the \"([^\"]*)\" page$", (String page) -> {

            while (true) {
                Thread.sleep(1000);
                WebElement pageNameq = driver.findElement(By.tagName("h2"));
                if (pageNameq != null) {
                    break;
                }
            }

            WebElement pageName = driver.findElement(By.tagName("h2"));
            Assert.assertEquals(pageName.getText(), page);
            driver.navigate().forward();
        });
        When("^I click on 'Teams' button$", () -> {

            WebElement teams = driver.findElement(By.className("btn-list"));
            teams.click();
            Thread.sleep(3000);

        });

        When("^I click on 'Employee Avatar'$", () -> {

            List<WebElement> member_lst = driver.findElement(By.cssSelector("*[class='ui-datatable-data ui-widget-content']"))
                    .findElements(By.tagName("tr"));
            List<WebElement> td = member_lst.get(0).findElements(By.tagName("td"));
            WebElement avatar = td.get(0).findElement(By.tagName("span")).findElement(By.tagName("a"));
            avatar.click();
//
//
//            List<WebElement> empAvatarList = driver.findElements(By.className("avatar-sm"));
//            empAvatarList.get(0).click();
            Thread.sleep(5000);

        });

        When("^I click on 'Employee Name'$", () -> {

            List<WebElement> member_lst = driver.findElement(By.cssSelector("*[class='ui-datatable-data ui-widget-content']"))
                    .findElements(By.tagName("tr"));
            List<WebElement> td = member_lst.get(0).findElements(By.tagName("td"));
            WebElement avatar = td.get(0).findElement(By.tagName("span")).findElement(By.tagName("a"));
            avatar.click();

        });
        When("^I click on 'Manager Name'$", () -> {
//            WebElement magName = driver.findElement(By.cssSelector("a[href='*']"));
//            magName.click();
//            Thread.sleep(2000);
        });
        When("^I click on 'Officer name'$", () -> {
//            WebElement offName = driver.findElement(By.cssSelector("btn.btn-default.btn-sm.btn-list"));
//            offName.click();
        });
    }

}

