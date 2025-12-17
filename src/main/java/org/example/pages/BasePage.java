package org.example.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.BaseTest;
import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        waitUtils.waitForElementToBeClickable(element);
        element.click();
        logger.info("Clicked on element: {}", element);
    }

    protected void sendKeys(WebElement element, String text) {
        waitUtils.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' in element: {}", text, element);
    }

    protected String getText(WebElement element) {
        waitUtils.waitForElementToBeVisible(element);
        String text = element.getText();
        logger.info("Retrieved text '{}' from element: {}", text, element);
        return text;
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            waitUtils.waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed: {}", element);
            return false;
        }
    }

    protected String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Page title: {}", title);
        return title;
    }

    protected String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: {}", url);
        return url;
    }
}
