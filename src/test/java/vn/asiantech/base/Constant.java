package vn.asiantech.base;

/**
 * @author at-hangtran
 */
public final class Constant {
    public static final int DEFAULT_TIME_OUT = 10;
    private static final String PORTAL_URL = "http://portal-stg.asiantech.vn";
    public static final String HOME_PAGE_URL = PORTAL_URL + "/homepage";
    public static final String LOGIN_PAGE_URL = PORTAL_URL + "/auth/login";
    public static final String LEAVE_BALANCE_PAGE_URL = PORTAL_URL + "/leave/balance";
    public static final String PROJECT_PAGE_URL = PORTAL_URL + "/project-management/projects";

    private Constant() {
        // No-op
    }
}
