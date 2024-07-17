package seleniumeasy.inputform;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class SimpleFormTest {

    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
    }
    @Test
    public void testSingleInputField() {
        WebElement messageInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='user-message']")));
        WebElement showMessageButton = driver.findElement(By.xpath("//button[contains(text(), 'Show Message')]"));
        WebElement displayMessage = driver.findElement(By.xpath("//span[@id='display']"));

        String inputMessage = "Hello, Selenium!";

        // Using JavaScript Executor to enter text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + inputMessage + "';", messageInput);

        showMessageButton.click();

        wait.until(ExpectedConditions.textToBePresentInElement(displayMessage, inputMessage));

        assertEquals(inputMessage, displayMessage.getText());
    }
    @Test
    public void testTwoInputFields() {
        WebElement sum1Input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='value1']")));
        WebElement sum2Input = driver.findElement(By.xpath("//input[@id='value2']"));
        WebElement getTotalButton = driver.findElement(By.xpath("//button[contains(text(), 'Get Total')]"));
        WebElement displayValue = driver.findElement(By.xpath("//span[@id='displayvalue']"));

        String value1 = "10";
        String value2 = "20";

        // Using JavaScript Executor to enter text
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value1 + "';", sum1Input);
        js.executeScript("arguments[0].value='" + value2 + "';", sum2Input);

        getTotalButton.click();

        wait.until(ExpectedConditions.textToBePresentInElement(displayValue, "30"));

        assertEquals("30", displayValue.getText());
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
