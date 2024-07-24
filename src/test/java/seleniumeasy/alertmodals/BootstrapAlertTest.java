package seleniumeasy.alertmodals;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BootstrapAlertTest {
    private WebDriver driver;
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html");
    }
    @Test
    public void testAutoClosableSuccessMessage() {
        WebElement button = driver.findElement(By.id("autoclosable-btn-success"));
        button.click();

        WebElement successMessage = driver.findElement(By.cssSelector(".alert-autocloseable-success"));
        assertTrue(successMessage.isDisplayed(), "Success message should be displayed");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(successMessage.isDisplayed(), "Success message should be auto-closed after 5 seconds");
    }

    @Test
    public void testNormalSuccessMessage() {
        WebElement button = driver.findElement(By.id("normal-btn-success"));
        button.click();

        WebElement successMessage = driver.findElement(By.cssSelector(".alert-normal-success"));
        assertTrue(successMessage.isDisplayed(), "Success message should be displayed");

        WebElement closeButton = successMessage.findElement(By.cssSelector("button.close"));
        closeButton.click();

        assertFalse(successMessage.isDisplayed(), "Success message should be closed when the close button is clicked");
    }

    @Test
    public void testAutoClosableWarningMessage() {
        WebElement button = driver.findElement(By.id("autoclosable-btn-warning"));
        button.click();

        WebElement warningMessage = driver.findElement(By.cssSelector(".alert-autocloseable-warning"));
        assertTrue(warningMessage.isDisplayed(), "Warning message should be displayed");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(warningMessage.isDisplayed(), "Warning message should be auto-closed after 5 seconds");
    }

    @Test
    public void testNormalWarningMessage() {
        WebElement button = driver.findElement(By.id("normal-btn-warning"));
        button.click();

        WebElement warningMessage = driver.findElement(By.cssSelector(".alert-normal-warning"));
        assertTrue(warningMessage.isDisplayed(), "Warning message should be displayed");

        WebElement closeButton = warningMessage.findElement(By.cssSelector("button.close"));
        closeButton.click();

        assertFalse(warningMessage.isDisplayed(), "Warning message should be closed when the close button is clicked");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
