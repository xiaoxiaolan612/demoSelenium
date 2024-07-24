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

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/jquery-download-progress-bar-demo.html");
    }

    @Test
    public void testStartDownload() {
        // Nhấp vào nút "Start Download"
        WebElement startDownloadButton = driver.findElement(By.id("downloadButton"));
        startDownloadButton.click();

        // Kiểm tra thanh tiến trình có xuất hiện
        WebElement progressBar = driver.findElement(By.className("progress-label"));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(progressBar.isDisplayed());
    }

    @Test
    public void testProgressCompletion() {
        WebElement startDownloadButton = driver.findElement(By.id("downloadButton"));
        startDownloadButton.click();
        WebElement progressBar = driver.findElement(By.className("progress-label"));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "Complete!"));

        // Kiểm tra kết quả
        assertTrue(progressBar.getText().contains("Complete!"));
    }

    @Test
    public void testCloseDialogAfterCompletion() {
        WebElement startDownloadButton = driver.findElement(By.id("downloadButton"));
        startDownloadButton.click();
        WebElement progressBar = driver.findElement(By.className("progress-label"));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "Complete!"));

        // Nhấp vào nút "Close"
        WebElement closeButton = driver.findElement(By.xpath("//button[text()='Close']"));
        closeButton.click();

        // Kiểm tra hộp thoại đã đóng
        boolean isDialogClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-dialog")));
        assertTrue(isDialogClosed);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
