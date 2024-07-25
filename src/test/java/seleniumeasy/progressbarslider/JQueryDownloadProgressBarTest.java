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

public class JQueryDownloadProgressBarTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constants for locators and parameters
    private static final String DOWNLOAD_BUTTON_ID = "downloadButton";
    private static final String PROGRESS_LABEL_CLASS = "progress-label";
    private static final String CLOSE_BUTTON_XPATH = "//button[text()='Close']";
    private static final String DIALOG_CLASS = "ui-dialog";
    private static final String COMPLETE_TEXT = "Complete!";
    private static final int WAIT_TIMEOUT_SECONDS = 10;

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        driver.get("https://demo.seleniumeasy.com/jquery-download-progress-bar-demo.html");
    }

    @Test
    public void testStartDownload() {
        // Click the "Start Download" button
        WebElement startDownloadButton = driver.findElement(By.id(DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        // Verify that the progress bar is displayed
        WebElement progressBar = driver.findElement(By.className(PROGRESS_LABEL_CLASS));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(progressBar.isDisplayed());
    }

    @Test
    public void testProgressCompletion() {
        // Start download
        WebElement startDownloadButton = driver.findElement(By.id(DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        // Wait for the progress to complete
        WebElement progressBar = driver.findElement(By.className(PROGRESS_LABEL_CLASS));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, COMPLETE_TEXT));

        // Verify that the progress bar text indicates completion
        assertTrue(progressBar.getText().contains(COMPLETE_TEXT));
    }

    @Test
    public void testCloseDialogAfterCompletion() {
        // Start download
        WebElement startDownloadButton = driver.findElement(By.id(DOWNLOAD_BUTTON_ID));
        startDownloadButton.click();

        // Wait for the progress to complete
        WebElement progressBar = driver.findElement(By.className(PROGRESS_LABEL_CLASS));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, COMPLETE_TEXT));

        // Click the "Close" button
        WebElement closeButton = driver.findElement(By.xpath(CLOSE_BUTTON_XPATH));
        closeButton.click();

        // Verify that the dialog is closed
        boolean isDialogClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(DIALOG_CLASS)));
        assertTrue(isDialogClosed);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
