package org.example.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ThreadLocal<ConfigReader> configReader = new ThreadLocal<>();

    public static void initializeDriver() {
        configReader.set(new ConfigReader());
        String browser = configReader.get().getBrowser();
        String headless = configReader.get().getHeadless();

        logger.info("Initializing WebDriver for browser: {} on thread: {}", browser, Thread.currentThread().getId());

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if ("true".equalsIgnoreCase(headless)) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if ("true".equalsIgnoreCase(headless)) {
                    firefoxOptions.addArguments("--headless");
                }
                driver.set(new FirefoxDriver(firefoxOptions));
                getDriver().manage().window().maximize();
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if ("true".equalsIgnoreCase(headless)) {
                    edgeOptions.addArguments("--headless");
                }
                edgeOptions.addArguments("--start-maximized");
                driver.set(new EdgeDriver(edgeOptions));
                break;

            default:
                logger.error("Invalid browser specified: {}", browser);
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(configReader.get().getImplicitWait()));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configReader.get().getPageLoadTimeout()));
        logger.info("WebDriver initialized successfully on thread: {}", Thread.currentThread().getId());
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting WebDriver on thread: {}", Thread.currentThread().getId());
            driver.get().quit();
            driver.remove();
            configReader.remove();
        }
    }

    public static void navigateToUrl(String url) {
        logger.info("Navigating to URL: {}", url);
        getDriver().get(url);
    }
}
