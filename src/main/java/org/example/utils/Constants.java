package org.example.utils;

public class Constants {

    public static final int DEFAULT_EXPLICIT_WAIT = 20;
    public static final int DEFAULT_IMPLICIT_WAIT = 10;
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    
    public static final String CHROME_BROWSER = "chrome";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String EDGE_BROWSER = "edge";
    
    public static final String QA_ENVIRONMENT = "qa";
    public static final String STAGE_ENVIRONMENT = "stage";
    public static final String PROD_ENVIRONMENT = "prod";
    
    public static final String SCREENSHOT_PATH = "target/screenshots/";
    public static final String REPORT_PATH = "target/allure-results/";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    
    private Constants() {
    }
}
