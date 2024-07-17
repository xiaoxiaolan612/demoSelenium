package uploadanddownload;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadDownload {
    public void uploadDownloadCheck(){
        WebDriver driver = DriverSetup.getDriver();

//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String downloadPath = "C:/Users/Admin/Downloads";
        String uploadFilePath = System.getProperty("user.dir") + "/resources/texttest.txt";

        // Create a test file to upload
        try {
            Files.write(Paths.get(uploadFilePath), "This is a test file".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.get("https://demoqa.com/upload-download");
        String uploadElementXpath = "//input[@id='uploadFile']";
        String uploadFileXpath = "//p[@id='uploadedFilePath']";
        String downloadButtonXpath = "//a[@id='downloadButton']";

        try{
            // Upload file
            WebElement uploadElement = driver.findElement(By.xpath(uploadElementXpath));
            uploadElement.sendKeys(uploadFilePath);

            // Verify the uploaded file path
            WebElement uploadedFilePath = driver.findElement(By.xpath(uploadFileXpath));
            System.out.println(uploadedFilePath.getText());

            WebElement downloadButton = driver.findElement(By.xpath(downloadButtonXpath));
            downloadButton.click();

            Thread.sleep(5000);
//            jsExecutor.executeScript("arguments[0].click();", downloadButton);
            // Verify the file is downloaded
            File downloadedFile = new File(downloadPath + "/sampleFile.jpeg");
            if(downloadedFile.exists()){
                System.out.println("Tải file thành công");
            }
            else{
                System.out.println("Tải file thất bại");
            }

        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
        new File(uploadFilePath).delete();
        new File(downloadPath + "/sampleFile.jpeg").delete();
    }
}
