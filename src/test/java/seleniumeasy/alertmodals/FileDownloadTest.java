package seleniumeasy.alertmodals;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileDownloadTest {
    private WebDriver driver;

    // Define constants for locators and file paths
    private static final String DOWNLOAD_PATH = "C:/Users/Admin/Downloads";
    private static final String FILE_NAME = "easyinfo.txt";
    private static final String TEXTAREA_ID = "textbox";
    private static final String GENERATE_BUTTON_ID = "create";
    private static final String DOWNLOAD_BUTTON_ID = "link-to-download";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");
    }

    @Test
    public void testGenerateFileButton() {
        // Nhập văn bản vào vùng nhập liệu
        WebElement textArea = driver.findElement(By.id(TEXTAREA_ID));
        String inputText = "Đây là văn bản kiểm tra.";
        textArea.sendKeys(inputText);

        // Nhấp vào nút "Generate File"
        WebElement generateButton = driver.findElement(By.id(GENERATE_BUTTON_ID));
        generateButton.click();

        // Xác minh rằng nút "Download" xuất hiện
        WebElement downloadButton = driver.findElement(By.id(DOWNLOAD_BUTTON_ID));
        assertTrue(downloadButton.isDisplayed(), "Nút 'Download' không xuất hiện.");
    }

    @Test
    public void testDownloadFile() throws Exception {
        // Nhập văn bản vào vùng nhập liệu
        WebElement textArea = driver.findElement(By.id(TEXTAREA_ID));
        String inputText = "Đây là văn bản kiểm tra.";
        textArea.sendKeys(inputText);

        // Nhấp vào nút "Generate File"
        WebElement generateButton = driver.findElement(By.id(GENERATE_BUTTON_ID));
        generateButton.click();

        // Nhấp vào nút "Download"
        WebElement downloadButton = driver.findElement(By.id(DOWNLOAD_BUTTON_ID));
        downloadButton.click();

        // Đợi một khoảng thời gian để tệp tải xuống
        Thread.sleep(3000);

        // Xác minh rằng tệp được tải xuống thành công
        File downloadedFile = new File(DOWNLOAD_PATH + "/" + FILE_NAME);
        assertTrue(downloadedFile.exists(), "Tệp không được tải xuống thành công.");

        // Xác minh nội dung của tệp đã tải xuống
        String fileContent = new String(Files.readAllBytes(Paths.get(downloadedFile.getPath())));
        assertEquals(fileContent.trim(), inputText, "Nội dung của tệp không khớp với văn bản nhập.");
    }

    @After
    public void tearDown() {
        driver.quit();
        File downloadedFile = new File(DOWNLOAD_PATH + "/" + FILE_NAME);
        if (downloadedFile.exists()) {
            downloadedFile.delete();
        }
    }
}
