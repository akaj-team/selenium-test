package vn.asiantech.base;

/**
 * @author at-hangtran
 */
public final class Constant {
    private static final String PORTAL_URL = "http://portal-stg.asiantech.vn";
    public static final String HOME_PAGE_URL = PORTAL_URL + "/homepage";
    public static final String LOGIN_PAGE_URL = PORTAL_URL + "/auth/login";
    public static final String ACCESS_CONTROL_PAGE_URL = PORTAL_URL + "/admin/acl";
    public static final String TEAMS_PAGE_URL = PORTAL_URL + "/organisation/teams";
    public static final String NEW_TEAM_PAGE_URL = PORTAL_URL + "organisation/teams/new";

    public static final int DEFAULT_TIME_OUT = 10;

    private Constant() {
        // No-op
    }
}
