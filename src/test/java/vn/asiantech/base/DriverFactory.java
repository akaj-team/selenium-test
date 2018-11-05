package vn.asiantech.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;
import static vn.asiantech.base.DriverType.*;

public class DriverFactory {

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private final String proxyHostname = System.getProperty("proxyHost");
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
    private RemoteWebDriver driver;

    public DriverFactory(String browserType) {
        DriverType driverType = getDriverType(browserType);
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }

        try {
            instantiateWebDriver(driverType);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private DriverType getDriverType(String browserType) {
        DriverType driverType;
        switch (browserType) {
            case Constant.BROWSER_CHROME:
                driverType = CHROME;
                break;
            case Constant.BROWSER_FIREFOX:
                driverType = FIREFOX;
                break;
            case Constant.BROWSER_SAFARI:
                driverType = SAFARI;
                break;
            case Constant.BROWSER_IE:
                driverType = IE;
                break;
            case Constant.BROWSER_EDGE:
                driverType = EDGE;
                break;
            case Constant.BROWSER_OPERA:
                driverType = OPERA;
                break;
            default:
                driverType = CHROME;
        }
        return driverType;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    RemoteWebDriver getStoredDriver() {
        return driver;
    }

    void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        //TODO add in a real logger instead of System.out
        System.out.println(" ");
        System.out.println("Local Operating System: " + operatingSystem);
        System.out.println("Local Architecture: " + systemArchitecture);
        System.out.println("Selected Browser: " + driverType);
        System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (proxyEnabled) {
            Proxy proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
            desiredCapabilities.setCapability(PROXY, proxy);
        }

        if (useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            desiredCapabilities.setBrowserName(driverType.toString());
            driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }
}
