@smoke
Feature: Login Functionality
  As a user
  I want to login to the application
  So that I can access my account

  Background:
    Given I navigate to the login page

  @validCredentials
  Scenario Outline: Login with valid credentials
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on login button

    Examples:
      | username | password |
      | Admin    | admin123 |

  @invalidCredentials
  Scenario Outline: Login with invalid credentials
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on login button
    Then I should see logout button

    Examples:
      | username | password      |
      | Admin    | admin12322222 |
