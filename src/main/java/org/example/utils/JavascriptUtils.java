package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

    private static final Logger logger = LogManager.getLogger(JavascriptUtils.class);
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    public JavascriptUtils(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public void clickElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].click();", element);
            logger.info("Clicked element using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to click element using JavaScript", e);
            throw e;
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to scroll to element using JavaScript", e);
            throw e;
        }
    }

    public void scrollToBottom() {
        try {
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            logger.info("Scrolled to bottom of page using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to scroll to bottom using JavaScript", e);
            throw e;
        }
    }

    public void scrollToTop() {
        try {
            jsExecutor.executeScript("window.scrollTo(0, 0)");
            logger.info("Scrolled to top of page using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to scroll to top using JavaScript", e);
            throw e;
        }
    }

    public void highlightElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
            logger.info("Highlighted element using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to highlight element using JavaScript", e);
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            jsExecutor.executeScript("arguments[0].value='" + text + "';", element);
            logger.info("Entered text using JavaScript: {}", text);
        } catch (Exception e) {
            logger.error("Failed to enter text using JavaScript", e);
            throw e;
        }
    }

    public String getPageTitle() {
        try {
            String title = jsExecutor.executeScript("return document.title;").toString();
            logger.info("Retrieved page title using JavaScript: {}", title);
            return title;
        } catch (Exception e) {
            logger.error("Failed to get page title using JavaScript", e);
            throw e;
        }
    }

    public void refreshPage() {
        try {
            jsExecutor.executeScript("history.go(0)");
            logger.info("Refreshed page using JavaScript");
        } catch (Exception e) {
            logger.error("Failed to refresh page using JavaScript", e);
            throw e;
        }
    }

    public Object executeScript(String script, Object... args) {
        try {
            Object result = jsExecutor.executeScript(script, args);
            logger.info("Executed custom JavaScript: {}", script);
            return result;
        } catch (Exception e) {
            logger.error("Failed to execute JavaScript: {}", script, e);
            throw e;
        }
    }
}
