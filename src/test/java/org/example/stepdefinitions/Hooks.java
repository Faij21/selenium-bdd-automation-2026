package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.BaseTest;
import org.example.utils.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Hooks extends BaseTest {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeAll
    public static void cleanupBeforeTests() {
        logger.info("Cleaning up logs and target reports before test execution...");

        deleteDirectory("logs");
        deleteDirectory("target/surefire-reports");
        deleteDirectory("target/cucumber-reports");
        deleteDirectory("target/screenshots");

        logger.info("Cleanup completed successfully");
    }

    private static void deleteDirectory(String directoryPath) {
        try {
            Path path = Paths.get(directoryPath);
            if (Files.exists(path)) {
                Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
                logger.info("Deleted directory: {}", directoryPath);
            } else {
                logger.info("Directory does not exist: {}", directoryPath);
            }
        } catch (IOException e) {
            logger.error("Failed to delete directory: {}", directoryPath, e);
        }
    }

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: {}", scenario.getName());
            try {
                byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Screenshot");

                saveScreenshotToFile(screenshot, scenario.getName());

                logger.info("Screenshot captured for failed scenario");
            } catch (Exception e) {
                logger.error("Failed to capture screenshot", e);
            }
        } else {
            logger.info("Scenario passed: {}", scenario.getName());
        }
        quitDriver();
    }

    private void saveScreenshotToFile(byte[] screenshot, String scenarioName) {
        try {
            File screenshotDir = new File(Constants.SCREENSHOT_PATH);
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date());
            String fileName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_") + "_" + timestamp + ".png";
            File screenshotFile = new File(screenshotDir, fileName);

            try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                fos.write(screenshot);
            }

            logger.info("Screenshot saved to: {}", screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot to file", e);
        }
    }
}
