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

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
    }

    @Test
    public void testStartDownload() {
        WebElement startDownloadButton = driver.findElement(By.id("cricle-btn"));
        startDownloadButton.click();

        // Kiểm tra thanh tiến trình có xuất hiện và bắt đầu tải xuống
        WebElement progressBar = driver.findElement(By.className("percenttext"));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(progressBar.isDisplayed());
    }

    @Test
    public void testProgressCompletion() {
        WebElement startDownloadButton = driver.findElement(By.id("cricle-btn"));
        startDownloadButton.click();
        WebElement progressBar = driver.findElement(By.className("percenttext"));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "100%"));

        // Kiểm tra kết quả
        assertTrue(progressBar.getText().contains("100%"));
    }

    @Test
    public void testDownloadButtonDisabledDuringDownload() {
        WebElement startDownloadButton = driver.findElement(By.id("cricle-btn"));
        startDownloadButton.click();

        // Kiểm tra rằng nút "Download" bị vô hiệu hóa trong khi tải xuống
        WebElement progressBar = driver.findElement(By.className("percenttext"));
        wait.until(ExpectedConditions.visibilityOf(progressBar));
        assertTrue(startDownloadButton.getAttribute("class").contains("active"));
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
