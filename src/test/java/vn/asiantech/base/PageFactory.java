package vn.asiantech.base;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tien.hoang
 */
class PageFactory<T> {
    private Class<T> clazz;
    private WebDriver driver;
    private static Map<String, Object> pages = new HashMap<>();

    PageFactory(final WebDriver driver, final Class<T> clazz) {
        this.clazz = clazz;
        this.driver = driver;
    }

    T create() {
        try {
            Class<?> newClazz = Class.forName(clazz.getName());
            if (newClazz == null) {
                System.out.println(this.getClass().getSimpleName() + ": Can not create page from " + clazz.getName());
                newClazz = clazz;
            }
            if (pages.containsKey(newClazz.getName())) {
                return (T) pages.get(newClazz.getName());
            }
            System.out.println(this.getClass().getSimpleName() + ": Create instance for " + newClazz.getSimpleName());
            Object object = newClazz.getConstructor(WebDriver.class).newInstance(driver);
            pages.put(newClazz.getName(), object);
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}