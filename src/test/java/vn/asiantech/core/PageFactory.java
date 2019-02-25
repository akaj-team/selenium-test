package vn.asiantech.core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tien.hoang
 */
public class PageFactory<T> {
    private static Map<String, Object> pages = new HashMap<>();
    private Class<T> clazz;
    private WebDriver driver;

    public PageFactory(final WebDriver driver, final Class<T> clazz) {
        this.clazz = clazz;
        this.driver = driver;
    }

    public T create() {
        try {
            Class<?> newClazz = Class.forName(clazz.getName());
            String classHash = DriverFactory.instance.getDriver().hashCode() + newClazz.getName();
            if (newClazz == null) {
                System.out.println(this.getClass().getSimpleName() + ": Can not create page from " + clazz.getName());
                newClazz = clazz;
            }
            if (pages.containsKey(classHash)) {
                return (T) pages.get(classHash);
            }
            System.out.println(this.getClass().getSimpleName() + ": Create instance for " + newClazz.getSimpleName() + ": " + classHash);
            Object object = newClazz.getConstructor(WebDriver.class).newInstance(driver);
            pages.put(classHash, object);
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
