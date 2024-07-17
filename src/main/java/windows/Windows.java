package windows;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

// sử dụng javascript excecutor
public class Windows {



    public void testNewTab() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://demoqa.com/browser-windows");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String newTabButtonXpath = "//button[@id='tabButton']";
        String headingXpath = "//h1[@id='sampleHeading']";
        try {
            // Click the "New Tab" button using XPath
            WebElement newTabButton = driver.findElement(By.xpath(newTabButtonXpath));
            jsExecutor.executeScript("arguments[0].click();", newTabButton);

            // Switch to the new tab
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            // Verify the text in the new tab
            WebElement heading = driver.findElement(By.xpath(headingXpath));
            System.out.println(heading.getText());

            // Close the new tab and switch back to the original tab
            driver.close();
            driver.switchTo().window(tabs.get(0));
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }

    }

    public void testNewWindow() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://demoqa.com/browser-windows");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String newWindowButtonXpath = "//button[@id='windowButton']";
        String headingXpath = "//h1[@id='sampleHeading']";
        try {
            // Click the "New Window" button using XPath
            WebElement newWindowButton = driver.findElement(By.xpath(newWindowButtonXpath));
            jsExecutor.executeScript("arguments[0].click();", newWindowButton);

            // Switch to the new window
            ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(1));

            // Verify the text in the new window
            WebElement heading = driver.findElement(By.xpath(headingXpath));
            System.out.println(heading.getText());

            // Close the new window and switch back to the original window
            driver.close();
            driver.switchTo().window(windows.get(0));
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    public void testNewWindowMessage(){
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://demoqa.com/browser-windows");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String newWindowMessageButtonXpath = "//button[@id='messageWindowButton']";
        try{
            // Click the "New Window Message" button using XPath
            WebElement newWindowMessageButton = driver.findElement(By.xpath(newWindowMessageButtonXpath));
            jsExecutor.executeScript("arguments[0].click();", newWindowMessageButton);

            // Switch to the new window message
            ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(windows.get(1));

            // Verify the text in the new window message
            WebElement body = driver.findElement(By.xpath("/html/body"));
            System.out.println(body.getText());

            // Close the new window message and switch back to the original window
            driver.close();
            driver.switchTo().window(windows.get(0));
        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
