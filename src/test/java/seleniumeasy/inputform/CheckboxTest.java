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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckboxTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/basic-checkbox-demo.html");
    }
    @Test
    public void testSingleCheckbox() {
        WebElement singleCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("isAgeSelected")));
        WebElement successMessage = driver.findElement(By.id("txtAge"));

        assertFalse(singleCheckbox.isSelected());

        // Using JavaScript Executor to click checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", singleCheckbox);

        assertTrue(singleCheckbox.isSelected());
        assertTrue(successMessage.isDisplayed());

        String message = successMessage.getText();
        assertTrue(message.contains("Success"));
    }

    @Test
    public void testMultipleCheckboxes() {
        WebElement option1Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[4]")));
        WebElement option3Checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='checkbox'])[6]")));
        WebElement checkAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check1")));

        assertFalse(option1Checkbox.isSelected());
        assertFalse(option3Checkbox.isSelected());

        // Click option 1 checkbox
        option1Checkbox.click();
        assertTrue(option1Checkbox.isSelected());
        assertFalse(option3Checkbox.isSelected());

        // Click option 3 checkbox
        option3Checkbox.click();
        assertTrue(option1Checkbox.isSelected());
        assertTrue(option3Checkbox.isSelected());

        // Click "Check All" button
        checkAllButton.click();

        assertTrue(option1Checkbox.isSelected());
        assertTrue(option3Checkbox.isSelected());

        //Click "Uncheck All" button
        checkAllButton.click();

        assertFalse(option1Checkbox.isSelected());
        assertFalse(option3Checkbox.isSelected());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
