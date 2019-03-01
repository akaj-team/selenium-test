package vn.asiantech.core;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyListener extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext context) {
        super.onFinish(context);
        try {
            RemoteWebDriver driver = (RemoteWebDriver) context.getAttribute("webDriver");
            Properties prop = new Properties();
            OutputStream output = new FileOutputStream("target/browser.properties");
            prop.setProperty("BrowserName", driver.getCapabilities().getBrowserName());
            prop.setProperty("BrowserVersion", driver.getCapabilities().getVersion());
            prop.setProperty("Platform", driver.getCapabilities().getPlatform().name());
            prop.setProperty("Platform", context.getCurrentXmlTest().getSuite().getParameter("server"));
            prop.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
    }
}

