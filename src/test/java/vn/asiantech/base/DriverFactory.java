package vn.asiantech.base;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.xml.XmlTest;
import vn.asiantech.object.Account;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;
import static vn.asiantech.base.DriverType.*;

class DriverFactory {

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private final String proxyHostname = System.getProperty("proxyHost");
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);
    private RemoteWebDriver driver;

    private static List<Integer> busyAccounts = new ArrayList<>();
    private Account accountCanUse;

    final Account getAccountCanUse() {
        return accountCanUse;
    }

    private void initSessionAccounts() {
        for (Account account : Constant.ACCOUNT_LOGIN) {
            if (!busyAccounts.contains(account.hashCode())) {
                busyAccounts.add(account.hashCode());
                accountCanUse = account;
                break;
            }
        }
    }

    DriverFactory(XmlTest xmlTest) {
        initSessionAccounts();
        //get param suite
        String browserName = xmlTest.getParameter("browserName");
        String server = xmlTest.getParameter("server");

        DriverType driverType = getDriverType(browserName);
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = DriverType.valueOf(browser);
            instantiateWebDriver(driverType, server);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private DriverType getDriverType(final String browserName) {
        DriverType driverType;
        switch (browserName) {
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

    RemoteWebDriver getDriver() {
        return driver;
    }

    void quitDriver() {
        if (null != driver) {
            busyAccounts.clear();
            driver.quit();
            driver = null;
        }
    }

    private void instantiateWebDriver(final DriverType driverType, final String server) throws MalformedURLException {
        //TODO add in a real logger instead of System.out
        System.out.println(" ");
        System.out.println("Local Operating System: " + operatingSystem);
        System.out.println("Local Architecture: " + systemArchitecture);
        System.out.println("Selected Browser: " + driverType);
        System.out.println("Selected server: " + server);
        System.out.println(" ");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (proxyEnabled) {
            Proxy proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
            desiredCapabilities.setCapability(PROXY, proxy);
        }

        if (server != null && !server.isEmpty()) {
            URL seleniumGridURL = new URL(server);
//            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
//            String desiredPlatform = System.getProperty("desiredPlatform");
//
//            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
//                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
//            }
//
//            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
//                desiredCapabilities.setVersion(desiredBrowserVersion);
//            }

            desiredCapabilities.setBrowserName(driverType.toString());
            driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }
}
