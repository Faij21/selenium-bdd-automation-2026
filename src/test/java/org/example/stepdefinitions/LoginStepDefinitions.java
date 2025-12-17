package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.Assert;

public class LoginStepDefinitions extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
    private LoginPage loginPage;

    @Given("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        logger.info("Navigating to login page");
        navigateToUrl(configReader.get().getUrl());
        loginPage = new LoginPage(getDriver());
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        logger.info("Entering password");
        loginPage.enterPassword(password);
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        logger.info("Clicking login button");
        loginPage.clickLoginButton();
    }

    @Then("I should see logout button")
    public void iShouldSeeTheWelcomeMessage() throws InterruptedException {
        logger.info("Verifying welcome message is displayed");
        loginPage.verifyLogout();

    }

}
