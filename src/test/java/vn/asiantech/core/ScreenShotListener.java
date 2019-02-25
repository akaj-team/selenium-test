package vn.asiantech.core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenShotListener extends TestListenerAdapter {

    private boolean createFile(File screenShot) {
        boolean fileCreated = false;

        if (screenShot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenShot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenShot.createNewFile();
                } catch (IOException errorCreatingScreenShot) {
                    errorCreatingScreenShot.printStackTrace();
                }
            }
        }

        return fileCreated;
    }

    private void writeScreenShotToFile(WebDriver driver, File screenshot) {
        try {
            FileOutputStream screenShotStream = new FileOutputStream(screenshot);
            screenShotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenShotStream.close();
        } catch (IOException unableToWriteScreenShot) {
            System.err.println("Unable to write " + screenshot.getAbsolutePath());
            unableToWriteScreenShot.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult failingTest) {
        try {
            WebDriver driver = DriverFactory.instance.getDriver();
            String screenShotDirectory = System.getProperty("screenShotDirectory", "target/screenshots");
            String screenShotAbsolutePath = screenShotDirectory + File.separator + System.currentTimeMillis() + "_" + failingTest.getName() + ".png";
            File screenShot = new File(screenShotAbsolutePath);
            if (createFile(screenShot)) {
                try {
                    writeScreenShotToFile(driver, screenShot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenShotToFile(new Augmenter().augment(driver), screenShot);
                }
                System.out.println("Written screenShot to " + screenShotAbsolutePath);
            } else {
                System.err.println("Unable to create " + screenShotAbsolutePath);
            }
        } catch (Exception ex) {
            System.err.println("Unable to capture screenShot...");
            ex.printStackTrace();
        }
    }
}