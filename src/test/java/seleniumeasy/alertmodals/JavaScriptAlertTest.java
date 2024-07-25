package seleniumeasy.alertmodals;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class JavaScriptAlertTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
    }


    @Test
    public void testAlertBox() {
        WebElement alertButton = driver.findElement(By.xpath("//button[contains(text(),'Click me!')]"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        assertEquals(alertMessage, "I am an alert box!");

        alert.accept();
    }

    @Test
    public void testConfirmBox() {
        WebElement confirmButton = driver.findElement(By.xpath("(//button[@class='btn btn-default btn-lg'][normalize-space()='Click me!'])[1]"));
        confirmButton.click();

        Alert confirmAlert = driver.switchTo().alert();
        String confirmMessage = confirmAlert.getText();
        assertEquals(confirmMessage, "Press a button!");

        confirmAlert.accept();
        WebElement resultMessage = driver.findElement(By.id("confirm-demo"));
        assertEquals(resultMessage.getText(), "You pressed OK!");

        confirmButton.click();

        confirmAlert = driver.switchTo().alert();
        confirmAlert.dismiss();
        assertEquals(resultMessage.getText(), "You pressed Cancel!");
    }

    @Test
    public void testPromptBox() {
        WebElement promptButton = driver.findElement(By.xpath("//button[contains(text(),'Click for Prompt Box')]"));

        promptButton.click();

        Alert promptAlert = driver.switchTo().alert();
        String promptMessage = promptAlert.getText();
        assertEquals(promptMessage, "Please enter your name");

        promptAlert.sendKeys("Test User");
        promptAlert.accept();
        WebElement resultMessage = driver.findElement(By.id("prompt-demo"));
        assertEquals(resultMessage.getText(), "You have entered 'Test User' !");

        promptButton.click();

        promptAlert = driver.switchTo().alert();
        promptAlert.dismiss();
        assertEquals(resultMessage.getText(), "You have entered 'Test User' !");
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
