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

    // Define constants for locators and class names
    private static final String BASE_URL = "https://demo.seleniumeasy.com/bootstrap-alert-messages-demo.html";
    private static final String AUTO_CLOSEABLE_SUCCESS_BUTTON_ID = "autoclosable-btn-success";
    private static final String AUTO_CLOSEABLE_SUCCESS_ALERT_CSS = ".alert-autocloseable-success";
    private static final String NORMAL_SUCCESS_BUTTON_ID = "normal-btn-success";
    private static final String NORMAL_SUCCESS_ALERT_CSS = ".alert-normal-success";
    private static final String AUTO_CLOSEABLE_WARNING_BUTTON_ID = "autoclosable-btn-warning";
    private static final String AUTO_CLOSEABLE_WARNING_ALERT_CSS = ".alert-autocloseable-warning";
    private static final String NORMAL_WARNING_BUTTON_ID = "normal-btn-warning";
    private static final String NORMAL_WARNING_ALERT_CSS = ".alert-normal-warning";
    private static final String CLOSE_BUTTON_CSS = "button.close";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void testAutoClosableSuccessMessage() {
        WebElement button = driver.findElement(By.id(AUTO_CLOSEABLE_SUCCESS_BUTTON_ID));
        button.click();

        WebElement successMessage = driver.findElement(By.cssSelector(AUTO_CLOSEABLE_SUCCESS_ALERT_CSS));
        assertTrue(successMessage.isDisplayed(), "Success message should be displayed");

        // Wait for 5 seconds to let the message auto-close
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(successMessage.isDisplayed(), "Success message should be auto-closed after 5 seconds");
    }

    @Test
    public void testNormalSuccessMessage() {
        WebElement button = driver.findElement(By.id(NORMAL_SUCCESS_BUTTON_ID));
        button.click();

        WebElement successMessage = driver.findElement(By.cssSelector(NORMAL_SUCCESS_ALERT_CSS));
        assertTrue(successMessage.isDisplayed(), "Success message should be displayed");

        WebElement closeButton = successMessage.findElement(By.cssSelector(CLOSE_BUTTON_CSS));
        closeButton.click();

        assertFalse(successMessage.isDisplayed(), "Success message should be closed when the close button is clicked");
    }

    @Test
    public void testAutoClosableWarningMessage() {
        WebElement button = driver.findElement(By.id(AUTO_CLOSEABLE_WARNING_BUTTON_ID));
        button.click();

        WebElement warningMessage = driver.findElement(By.cssSelector(AUTO_CLOSEABLE_WARNING_ALERT_CSS));
        assertTrue(warningMessage.isDisplayed(), "Warning message should be displayed");

        // Wait for 5 seconds to let the message auto-close
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(warningMessage.isDisplayed(), "Warning message should be auto-closed after 5 seconds");
    }

    @Test
    public void testNormalWarningMessage() {
        WebElement button = driver.findElement(By.id(NORMAL_WARNING_BUTTON_ID));
        button.click();

        WebElement warningMessage = driver.findElement(By.cssSelector(NORMAL_WARNING_ALERT_CSS));
        assertTrue(warningMessage.isDisplayed(), "Warning message should be displayed");

        WebElement closeButton = warningMessage.findElement(By.cssSelector(CLOSE_BUTTON_CSS));
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
