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

public class AjaxFormSubmitTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/ajax-form-submit-demo.html");
    }
    @Test
    public void testAjaxFormSubmit() {

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("title")));
        nameField.sendKeys("Lam Lam");

        WebElement commentField = wait.until(ExpectedConditions.elementToBeClickable(By.id("description")));
        commentField.sendKeys("Comment is null hehe");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btn-submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit-control")));

        boolean messageUpdated = wait.until(ExpectedConditions.textToBe(By.id("submit-control"), "Form submited Successfully!"));
        if (messageUpdated) {
            assertEquals("Form submited Successfully!", successMessage.getText());
        } else {
            throw new AssertionError("Expected message not found!");
        }
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

