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
//    private WebDriverWait wait;
    String downloadPath = "C:/Users/Admin/Downloads";
    String fileName = "easyinfo.txt";
    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/generate-file-to-download-demo.html");
    }

    @Test
    public void testGenerateFileButton() {
        // Nhập văn bản vào vùng nhập liệu
        WebElement textArea = driver.findElement(By.id("textbox"));
        String inputText = "Đây là văn bản kiểm tra.";
        textArea.sendKeys(inputText);

        // Nhấp vào nút "Generate File"
        WebElement generateButton = driver.findElement(By.id("create"));
        generateButton.click();

        // Xác minh rằng nút "Download" xuất hiện
        WebElement downloadButton = driver.findElement(By.id("link-to-download"));
        assertTrue(downloadButton.isDisplayed(), "Nút 'Download' không xuất hiện.");
    }

    @Test
    public void testDownloadFile() throws Exception {
        // Nhập văn bản vào vùng nhập liệu
        WebElement textArea = driver.findElement(By.id("textbox"));
        String inputText = "Đây là văn bản kiểm tra.";
        textArea.sendKeys(inputText);

        // Nhấp vào nút "Generate File"
        WebElement generateButton = driver.findElement(By.id("create"));
        generateButton.click();

        // Nhấp vào nút "Download"
        WebElement downloadButton = driver.findElement(By.id("link-to-download"));
        downloadButton.click();

        // Đợi một khoảng thời gian để tệp tải xuống
        Thread.sleep(3000);

        // Xác minh rằng tệp được tải xuống thành công
        File downloadedFile = new File(downloadPath + "/" + fileName);
        assertTrue(downloadedFile.exists(), "Tệp không được tải xuống thành công.");

        // Xác minh nội dung của tệp đã tải xuống
        String fileContent = new String(Files.readAllBytes(Paths.get(downloadedFile.getPath())));
        assertEquals(fileContent.trim(), inputText, "Nội dung của tệp không khớp với văn bản nhập.");
    }
    @After
    public void tearDown() {
        driver.quit();
        File downloadedFile = new File(downloadPath + "/" + fileName);
        if (downloadedFile.exists()) {
            downloadedFile.delete();
        }
    }
}
