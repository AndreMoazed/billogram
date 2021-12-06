package discount.code.controller;

public final class AdminApi {
    public static final String rootPath = "/admin";

    private AdminApi() {}

    public static final String GENERATE_NEW_CODE = "/generate-code";

    public static final String ANALYTICS_DEFAULT = "/analytics-default";
    public static final String ANALYTICS_COUNTRY_SPECIFIC = "/analytics-country";
    public static final String ANALYTICS_DATE_SPECIFIC = "/analytics-date";
}
