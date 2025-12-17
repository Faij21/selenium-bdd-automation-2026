package org.example.pages;

import org.example.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {

     @FindBy(css = "input[name='username']")
    private WebElement usernameField;

    @FindBy(css = "input[name='password']")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "span[class='oxd-userdropdown-tab']")
    private WebElement userDropdown;
    
    @FindBy(css = "a[href*='/logout']")
    private WebElement logoutButton;

    @FindBy(css = "h5[class*='orangehrm-login-title']") //$ used for end-with.
    private WebElement hrmTitle;

    @FindBy(css = "p.oxd-alert-content-text")
    private WebElement errorMessage;

    @FindBy(xpath = "//h1[@class='welcome-message']")
    private WebElement welcomeMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void verifyLogout() throws InterruptedException {
        waitUtils.waitForElementToBeVisible(userDropdown);
        click(userDropdown);
        Thread.sleep(2000);
        waitUtils.waitForElementToBeVisible(logoutButton);
        assertThat(logoutButton.isDisplayed()).as("Logout CTA is not visible").isTrue();
        click(logoutButton);
        Thread.sleep(2000);
        waitUtils.waitForElementToBeVisible(hrmTitle);
        assertThat(hrmTitle.isDisplayed()).as("HRM Title is not visible").isTrue();
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isWelcomeMessageDisplayed() {
        return isDisplayed(welcomeMessage);
    }

    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
}
