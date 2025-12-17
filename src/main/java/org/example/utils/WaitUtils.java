package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        ConfigReader configReader = new ConfigReader();
        int explicitWait = configReader.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    public WaitUtils(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public void waitForElementToBeVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.debug("Element is visible");
        } catch (Exception e) {
            logger.error("Element not visible within timeout", e);
            throw e;
        }
    }

    public void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            logger.debug("Element is clickable");
        } catch (Exception e) {
            logger.error("Element not clickable within timeout", e);
            throw e;
        }
    }

    public void waitForElementToBeInvisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            logger.debug("Element is invisible");
        } catch (Exception e) {
            logger.error("Element still visible after timeout", e);
            throw e;
        }
    }

    public void waitForTitleContains(String title) {
        try {
            wait.until(ExpectedConditions.titleContains(title));
            logger.debug("Page title contains: {}", title);
        } catch (Exception e) {
            logger.error("Title does not contain '{}' within timeout", title, e);
            throw e;
        }
    }

    public void waitForUrlContains(String url) {
        try {
            wait.until(ExpectedConditions.urlContains(url));
            logger.debug("URL contains: {}", url);
        } catch (Exception e) {
            logger.error("URL does not contain '{}' within timeout", url, e);
            throw e;
        }
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.debug("Waited for {} seconds", seconds);
        } catch (InterruptedException e) {
            logger.error("Wait interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public void waitForPageLoad() {
        try {
            wait.until(driver -> 
                ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
            logger.debug("Page loaded completely");
        } catch (Exception e) {
            logger.error("Page did not load within timeout", e);
            throw e;
        }
    }
}
