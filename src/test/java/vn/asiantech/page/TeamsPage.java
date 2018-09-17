package vn.asiantech.page;

import org.openqa.selenium.WebDriver;
import vn.asiantech.base.BasePage;

/**
 * Copyright © 2018 Asian Tech Co., Ltd.
 * Created by at-vietphan on 9/13/18.
 */
public class TeamsPage extends BasePage<TeamsPage> {
    @Override
    public TeamsPage navigateTo(WebDriver webDriver) {
        webDriver.get("http://portal-stg.asiantech.vn/organisation/teams");
        return this;
    }

}
