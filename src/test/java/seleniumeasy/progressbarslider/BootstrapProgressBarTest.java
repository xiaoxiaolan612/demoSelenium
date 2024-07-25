package seleniumeasy.progressbarslider;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class BootstrapProgressBarTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constants for locators
    private static final String START_DOWNLOAD_BUTTON_ID = "cricle-btn";
    private static final String PROGRESS_BAR_CLASS_NAME = "percenttext";
    private static final String ACTIVE_CLASS = "active";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10); // Adjusted timeout to 10 seconds
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
    }

    @Test
    public void testStartDownload() {
        WebElement startDownloadButton = driver.findElement(By.id(START_DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        // Check that the progress bar is visible after clicking the start download button
        WebElement progressBar = driver.findElement(By.className(PROGRESS_BAR_CLASS_NAME));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(progressBar.isDisplayed());
    }

    @Test
    public void testProgressCompletion() {
        WebElement startDownloadButton = driver.findElement(By.id(START_DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        WebElement progressBar = driver.findElement(By.className(PROGRESS_BAR_CLASS_NAME));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "100%"));

        // Check if the progress bar text contains "100%"
        assertTrue(progressBar.getText().contains("100%"));
    }

    @Test
    public void testDownloadButtonDisabledDuringDownload() {
        WebElement startDownloadButton = driver.findElement(By.id(START_DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        // Check that the start download button is in an active state during the download
        WebElement progressBar = driver.findElement(By.className(PROGRESS_BAR_CLASS_NAME));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(startDownloadButton.getAttribute("class").contains(ACTIVE_CLASS));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
