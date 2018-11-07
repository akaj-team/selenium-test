package vn.asiantech.base;

import vn.asiantech.object.Account;

/**
 * @author at-hangtran
 */
public final class Constant {
    public static final String PORTAL_URL = "http://portal-stg.asiantech.vn";
    public static final String HOME_PAGE_URL = PORTAL_URL + "/homepage";
    public static final String LOGIN_PAGE_URL = PORTAL_URL + "/auth/login";
    public static final String TEAM_PAGE_URL = PORTAL_URL + "/organisation/teams";
    public static final String NEW_TEAM_PAGE_URL = PORTAL_URL + "/organisation/teams/new";
    public static final String EMPLOYEE_PAGE_URL = PORTAL_URL + "/organisation/employees";
    public static final String LEAVE_PLANNER_PAGE_URL = PORTAL_URL + "/leave/planning";
    public static final int DEFAULT_TIME_OUT = 10;
    static final int MAXIMUM_TIME_OUT = 20;

    static final String BROWSER_CHROME = "chrome";
    static final String BROWSER_FIREFOX = "firefox";
    static final String BROWSER_SAFARI = "safari";
    static final String BROWSER_IE = "ie";
    static final String BROWSER_EDGE = "edge";
    static final String BROWSER_OPERA = "opera";

    //init Account
    static final Account[] ACCOUNT_LOGIN = new Account[]{
            new Account("stg.tien.hoang@asiantech.vn", "Abc123@@"),
            new Account("stg.tu.le.2@asiantech.vn", "Abc123@@"),
            new Account("stg.thien.dang2@asiantech.vn", "Abc123@@"),
            new Account("stg.hang.nguyen@asiantech.vn", "Abc123@@"),
            new Account("stg.lam.le2@asiantech.vn", "Abc123@@"),
            new Account("automation-01@asiantech.vn", "Abc123@@"),
            new Account("automation-02@asiantech.vn", "Abc123@@")
    };

    private Constant() {
        // No-op
    }
}
