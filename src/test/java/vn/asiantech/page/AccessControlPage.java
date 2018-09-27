package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import vn.asiantech.base.BasePage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/27/18.
 */
public class AccessControlPage extends BasePage<AccessControlPage> {
    private static final String URL_PAGE_ACCESS_CONTROL = "http://portal-stg.asiantech.vn/admin/acl";
    @Override
    public AccessControlPage navigateTo(WebDriver webDriver) {
        webDriver.get(URL_PAGE_ACCESS_CONTROL);
        return this;
    }
}
