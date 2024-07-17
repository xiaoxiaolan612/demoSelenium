package frame;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NestedFrame {
    public void nestedFrameCheck(){
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://demoqa.com/nestedframes");
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        String frameFatherXpath = "//iframe[@id='frame1']";
        String frameChildXpath = "//iframe";
        String textFatherXpath = "//body[contains(text(),'Parent frame')]";
        String textChildXpath = "//p[contains(text(),'Child Iframe')]";
        try{

            driver.switchTo().frame(driver.findElement(By.xpath(frameFatherXpath)));
            WebElement textFather = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textFatherXpath)));
            System.out.println(textFather.getText());

            driver.switchTo().frame(driver.findElement(By.xpath(frameChildXpath)));
            WebElement textChild = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(textChildXpath)));
            System.out.println(textChild.getText());
            driver.switchTo().parentFrame();
            driver.switchTo().defaultContent();

        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
