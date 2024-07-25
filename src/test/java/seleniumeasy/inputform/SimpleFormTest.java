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

    // XPaths for elements
    private static final String MESSAGE_INPUT_XPATH = "//input[@id='user-message']";
    private static final String SHOW_MESSAGE_BUTTON_XPATH = "//button[contains(text(), 'Show Message')]";
    private static final String DISPLAY_MESSAGE_XPATH = "//span[@id='display']";

    private static final String VALUE1_INPUT_XPATH = "//input[@id='value1']";
    private static final String VALUE2_INPUT_XPATH = "//input[@id='value2']";
    private static final String GET_TOTAL_BUTTON_XPATH = "//button[contains(text(), 'Get Total')]";
    private static final String DISPLAY_VALUE_XPATH = "//span[@id='displayvalue']";

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
    }

    @Test
    public void testSingleInputField() {
        WebElement messageInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MESSAGE_INPUT_XPATH)));
        WebElement showMessageButton = driver.findElement(By.xpath(SHOW_MESSAGE_BUTTON_XPATH));
        WebElement displayMessage = driver.findElement(By.xpath(DISPLAY_MESSAGE_XPATH));

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
        WebElement sum1Input = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(VALUE1_INPUT_XPATH)));
        WebElement sum2Input = driver.findElement(By.xpath(VALUE2_INPUT_XPATH));
        WebElement getTotalButton = driver.findElement(By.xpath(GET_TOTAL_BUTTON_XPATH));
        WebElement displayValue = driver.findElement(By.xpath(DISPLAY_VALUE_XPATH));

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
