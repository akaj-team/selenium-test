package vn.asiantech.base;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.xml.XmlTest;
import vn.asiantech.object.Account;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;
import static vn.asiantech.base.DriverType.*;

/**
 * DriverFactory
 */
public class DriverFactory {

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean proxyEnabled = Boolean.getBoolean("proxyEnabled");
    private final String proxyHostname = System.getProperty("proxyHost");
    private final Integer proxyPort = Integer.getInteger("proxyPort");
    private final String proxyDetails = String.format("%s:%d", proxyHostname, proxyPort);

    public static DriverFactory instance = new DriverFactory();
    private ThreadLocal<RemoteWebDriver> driverFactoryThread = new ThreadLocal<>();
    private ThreadLocal<Account> accountThread = new ThreadLocal<>();
    private static List<Integer> busyAccounts = new ArrayList<>();

    public final Account getAccountCanUse() {
        return accountThread.get();
    }

    private void initSessionAccounts() {
        for (Account account : Constant.ACCOUNT_LOGIN) {
            if (!busyAccounts.contains(account.hashCode())) {
                busyAccounts.add(account.hashCode());
                accountThread.set(account);
                System.out.println("Using first login account: " + account.email);
                break;
            }
        }
    }

    public final synchronized RemoteWebDriver getDriver() {
        if (driverFactoryThread.get() == null) {
            XmlTest xmlTest = new XmlTest();
            xmlTest.setParameters(defaultParam());
            startDriver(xmlTest);
        }
        return driverFactoryThread.get();
    }

    private static Map<String, String> defaultParam() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("browserName", Constant.BROWSER_CHROME);
        parameters.put("server", "http://localhost/wd/hub");
        return parameters;
    }

    public final synchronized void startDriver(final XmlTest xmlTest) {
        initSessionAccounts();
        //get param suite
        String browserName = xmlTest.getParameter("browserName");
        String server = xmlTest.getParameter("server");

        DriverType driverType = getDriverType(browserName);
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        driverType = DriverType.valueOf(browser);
        try {
            instantiateWebDriver(driverType, server);
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

    public final void quitDriver() {
        if (driverFactoryThread.get() != null) {
            busyAccounts.clear();
            driverFactoryThread.get().quit();
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

        RemoteWebDriver driver;
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
            desiredCapabilities.setBrowserName(driverType.toString());
            driver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            driver = driverType.getWebDriverObject(desiredCapabilities);
        }

        driverFactoryThread.set(driver);
    }
}
