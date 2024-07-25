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

    // Define constants for element locators
    private static final String NAME_FIELD_ID = "title";
    private static final String COMMENT_FIELD_ID = "description";
    private static final String SUBMIT_BUTTON_ID = "btn-submit";
    private static final String SUCCESS_MESSAGE_ID = "submit-control";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Form submited Successfully!";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10); // Adjusted timeout to 10 seconds for practicality
        driver.get("https://demo.seleniumeasy.com/ajax-form-submit-demo.html");
    }

    @Test
    public void testAjaxFormSubmit() {
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id(NAME_FIELD_ID)));
        nameField.sendKeys("Lam Lam");

        WebElement commentField = wait.until(ExpectedConditions.elementToBeClickable(By.id(COMMENT_FIELD_ID)));
        commentField.sendKeys("Comment is null hehe");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SUCCESS_MESSAGE_ID)));

        boolean messageUpdated = wait.until(ExpectedConditions.textToBe(By.id(SUCCESS_MESSAGE_ID), EXPECTED_SUCCESS_MESSAGE));
        if (messageUpdated) {
            assertEquals(EXPECTED_SUCCESS_MESSAGE, successMessage.getText());
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
