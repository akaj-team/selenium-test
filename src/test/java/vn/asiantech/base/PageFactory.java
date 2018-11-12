package vn.asiantech.base;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tien.hoang
 */
public class PageFactory<T> {
    private Class<T> clazz;
    private WebDriver driver;
    private static Map<String, Object> pages = new HashMap<>();

    public PageFactory(WebDriver driver, Class<T> clazz) {
        this.clazz = clazz;
        this.driver = driver;
    }

    public T create() {
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
            Constructor<?> constructor = newClazz.getConstructor(WebDriver.class);
            Object object = constructor.newInstance(new Object[]{driver});
            pages.put(newClazz.getName(), object);
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}