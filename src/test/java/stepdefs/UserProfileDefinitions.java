package stepdefs;

import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import vn.asiantech.base.DriverBase;

public class UserProfileDefinitions extends DriverBase implements En {
    private WebDriver driver;

    public UserProfileDefinitions() {
        try {
            driver = getDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
        When("^I click on my name$", () -> {
            driver.findElement(By.className("font-bold")).click();
            new WebDriverWait(driver, 10).until(
                    webDriver -> webDriver.findElement(By.className("section-top")).isDisplayed());
        });

        Then("^User profile is displayed$", () -> {
            Assert.assertTrue(driver.findElement(By.className("section-top")).isDisplayed());
        });
        And("^\"([^\"]*)\" is \"([^\"]*)\"$", (String key, String value) -> {
            WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + key + "')]"));
            WebElement parent = label.findElement(By.xpath(".."));
            WebElement valueElement = parent.findElement(By.tagName("span"));
//            System.out.println(valueElement.getText());
            Assert.assertEquals(valueElement.getText(), value);
        });
    }
}
