# Selenium BDD Automation Framework 2026

A comprehensive BDD (Behavior Driven Development) automation framework using Selenium WebDriver, Cucumber, TestNG, and Allure Reports.

## Framework Structure

```
selenium-bdd-automation-2026/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── base/
│   │   │   │   └── BaseTest.java
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java
│   │   │   │   └── LoginPage.java
│   │   │   ├── retryanalyzer/
│   │   │   │   └── RetryAnalyzer.java
│   │   │   └── utils/
│   │   │       ├── Constants.java
│   │   │       ├── JavascriptUtils.java
│   │   │       └── WaitUtils.java
│   │   └── resources/
│   │       ├── log4j2.xml
│   │       ├── qa.properties
│   │       └── stage.properties
│   └── test/
│       └── java/org/example/
│           ├── features/
│           │   └── Login.feature
│           ├── stepdefinitions/
│           │   ├── Hooks.java
│           │   └── LoginStepDefinitions.java
│           └── runners/
│               └── TestRunner.java
├── pom.xml
└── testng.xml
```

## Technologies Used

- **Java 11** - Programming Language
- **Selenium WebDriver 4.16.1** - Browser Automation
- **Cucumber 7.15.0** - BDD Framework
- **TestNG 7.8.0** - Test Execution Framework
- **Allure 2.25.0** - Test Reporting
- **Log4j2 2.22.0** - Logging Framework
- **Maven** - Build Tool

## Prerequisites

Before running the tests, ensure you have the following installed:

1. **Java JDK 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/javase-downloads.html
   - Verify installation: `java -version`

2. **Maven 3.6 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **Allure Command Line** (for generating reports)
   - Install via Homebrew (Mac): `brew install allure`
   - Install via Scoop (Windows): `scoop install allure`
   - Or download from: https://github.com/allure-framework/allure2/releases

4. **Web Browsers**
   - Chrome, Firefox, or Edge browser installed

## Setup Instructions

### 1. Clone or Download the Project

```bash
git clone <repository-url>
cd selenium-bdd-automation-2026
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Configure Environment

Edit the properties files in `src/main/resources/`:

**qa.properties** - QA Environment Configuration
```properties
browser=chrome
url=https://www.example.com
headless=false
implicit.wait=10
explicit.wait=20
page.load.timeout=30
```

**stage.properties** - Stage Environment Configuration
```properties
browser=chrome
url=https://stage.example.com
headless=false
implicit.wait=10
explicit.wait=20
page.load.timeout=30
```

## Running Tests

### Run All Tests (Default: QA Environment)

```bash
mvn clean test
```

### Run Tests on Specific Environment

```bash
mvn clean test -Denv=qa
mvn clean test -Denv=stage
```

### Run Tests with Specific Tags

```bash
mvn clean test '-Dcucumber.filter.tags=@smoke'
mvn clean test -Dcucumber.filter.tags="@regression"
mvn clean test -Dcucumber.filter.tags="@smoke and @regression"
```

### Run Tests in Headless Mode

Update the properties file:
```properties
headless=true
```

### Run Tests with Different Browser

Update the properties file:
```properties
browser=chrome    # or firefox, edge
```

## Generating Allure Reports

### Generate and Open Allure Report

After running tests, generate the Allure report:

```bash
mvn allure:serve
```

This will automatically open the report in your default browser.

### Generate Allure Report (without opening)

```bash
mvn allure:report
```

The report will be generated in `target/allure-reports/` directory.

### View Existing Report

```bash
allure open target/allure-reports
```

## Framework Features

### 1. Base Classes
- **BaseTest.java** - WebDriver initialization and management
- **BasePage.java** - Common page object methods

### 2. Configuration Management
- **ConfigReader.java** - Reads environment-specific properties
- Multiple environment support (QA, Stage, Prod)

### 3. Page Object Model
- **LoginPage.java** - Example page object with WebElements
- Uses @FindBy annotations for element location
- Reusable page methods

### 4. Utility Classes
- **Constants.java** - Framework constants
- **JavascriptUtils.java** - JavaScript executor methods
- **WaitUtils.java** - Explicit wait utilities

### 5. Retry Mechanism
- **RetryAnalyzer.java** - Automatically retries failed tests (max 2 attempts)

### 6. BDD Features
- **Login.feature** - Example feature file with scenarios
- Supports scenario outlines and data tables
- Tag-based execution (@smoke, @regression)

### 7. Step Definitions
- **Hooks.java** - Before/After hooks with screenshot on failure
- **LoginStepDefinitions.java** - Step implementations

### 8. Reporting
- **Allure Reports** - Rich HTML reports with screenshots
- **Cucumber Reports** - JSON and HTML reports
- **Log4j2** - Detailed logging in console and file

## Writing New Tests

### 1. Create Feature File

Create a new `.feature` file in `src/test/java/org/example/features/`:

```gherkin
Feature: New Feature
  Scenario: Test scenario
    Given I perform some action
    When I do something
    Then I verify the result
```

### 2. Create Page Object

Create a new page class in `src/main/java/org/example/pages/`:

```java
public class NewPage extends BasePage {
    @FindBy(id = "elementId")
    private WebElement element;
    
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    public void performAction() {
        click(element);
    }
}
```

### 3. Create Step Definitions

Create step definition class in `src/test/java/org/example/stepdefinitions/`:

```java
public class NewStepDefinitions extends BaseTest {
    @Given("I perform some action")
    public void iPerformSomeAction() {
        // Implementation
    }
}
```

## Logging

Logs are generated in two locations:
- **Console** - Real-time logs during execution
- **File** - `logs/automation.log` (with daily rotation)

Log levels: DEBUG, INFO, WARN, ERROR

## Troubleshooting

### Issue: WebDriver not found
**Solution**: Selenium 4.x includes WebDriver Manager. Ensure you have internet connectivity for automatic driver download.

### Issue: Tests fail with timeout
**Solution**: Increase wait times in properties file:
```properties
implicit.wait=15
explicit.wait=30
page.load.timeout=60
```

### Issue: Allure command not found
**Solution**: Install Allure CLI:
- Mac: `brew install allure`
- Windows: `scoop install allure`

### Issue: Maven build fails
**Solution**: Clean and rebuild:
```bash
mvn clean install -U
```

## Best Practices

1. **Page Object Model** - Keep page elements and methods in page classes
2. **Reusable Methods** - Use BasePage methods for common actions
3. **Explicit Waits** - Use WaitUtils instead of Thread.sleep()
4. **Logging** - Add meaningful log messages for debugging
5. **Tags** - Use tags (@smoke, @regression) for test organization
6. **Screenshots** - Automatically captured on test failure
7. **Environment Config** - Use properties files for environment-specific data

## CI/CD Integration

### Jenkins

```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Denv=qa'
            }
        }
        stage('Report') {
            steps {
                allure includeProperties: false, 
                       jdk: '', 
                       results: [[path: 'target/allure-results']]
            }
        }
    }
}
```

### GitHub Actions

```yaml
name: Test Automation
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: '11'
      - name: Run tests
        run: mvn clean test
      - name: Generate Allure Report
        run: mvn allure:report
```

## Support

For issues or questions:
1. Check the logs in `logs/automation.log`
2. Review Allure reports for detailed test execution
3. Verify configuration in properties files

## License

This project is licensed under the MIT License.

## Author

Created for Selenium BDD Automation Framework 2026

---

**Happy Testing! 🚀**
