package vn.asiantech.base;

/**
 * @author at-hangtran
 */
public final class Constant {
    public static final String PORTAL_URL = "http://portal-stg.asiantech.vn";
    public static final String TEAM_PAGE_URL = PORTAL_URL + "/organisation/teams";
    public static final String HOME_PAGE_URL = PORTAL_URL + "/homepage";
    public static final String LOGIN_PAGE_URL = PORTAL_URL + "/auth/login";
    public static final String LEAVE_BALANCE_PAGE_URL = PORTAL_URL + "/leave/balance";
    public static final String PROJECT_PAGE_URL = PORTAL_URL + "/project-management/projects";
    public static final String NEW_TEAM_PAGE_URL = PORTAL_URL + "/organisation/teams/new";
    public static final String EMPLOYEE_PAGE_URL = PORTAL_URL + "/organisation/employees";
    public static final String LEAVE_PLANNER_PAGE_URL = PORTAL_URL + "/leave/planning";
    public static final String LEAVE_TRACKING_PAGE_URL = PORTAL_URL + "/leave/tracking";
    public static final String ACCESS_CONTROL_PAGE_URL = PORTAL_URL + "/admin/acl";
    public static final String NEW_EMPLOYEE_PAGE_URL = PORTAL_URL + "/organisation/employees/new";
    public static final String POSITION_PAGE_URL = PORTAL_URL + "/organisation/positions";

    public static final String HOLIDAY_SETTING_URL = PORTAL_URL + "/admin/public-holiday";
    public static final String DEVICE_TRACKING_URL = PORTAL_URL + "/equipments/tracking";
    public static final String AWARD_CATEGORY_PAGE_URL = PORTAL_URL + "/admin/award-category";
    public static final String WIKI_URL = PORTAL_URL + "/wiki/";
    public static final String NEW_POSITION_URL = PORTAL_URL + "/organisation/positions/new";
    public static final String TIME_SHEET_OTHER_URL = PORTAL_URL + "/timesheet/approval";

    public static final int DEFAULT_TIME_OUT = 10;
    public static final int MAXIMUM_TIME_OUT = 20;

    private Constant() {
        // No-op
    }
}
