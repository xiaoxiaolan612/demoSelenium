package frame;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameTest {
    public void frameCheck() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://demoqa.com/frames");
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String frame1Xpath = "//iframe[@id='frame1']";
        String frame2Xpath = "//iframe[@id='frame2']";
        String headingXpath = "//h1[@id='sampleHeading']";
        try{

            driver.switchTo().frame(driver.findElement(By.xpath(frame1Xpath)));
            WebElement heading1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headingXpath)));
            System.out.println(heading1.getText());
            driver.switchTo().defaultContent();

            driver.switchTo().frame(driver.findElement(By.xpath(frame2Xpath)));
            WebElement heading2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(headingXpath)));
            System.out.println(heading2.getText());
            driver.switchTo().defaultContent();

        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}
