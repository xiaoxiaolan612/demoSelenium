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

public class RadioButtonTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
    }

    @Test
    public void testSingleRadioButton() {
        // Locate and click on the Male radio button
        String maleRadioButtonXpath = "//input[@name='optradio' and @value='Male']";
        String messageXpath = "//p[@class='radiobutton']";
        WebElement maleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(maleRadioButtonXpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maleRadioButton);

        // Click the 'Get Checked value' button
        WebElement getValueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("buttoncheck")));
        getValueButton.click();

        // Wait for and verify the displayed message
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(messageXpath)));
        assertEquals("Radio button 'Male' is checked", message.getText());
    }
    @Test
    public void testGroupRadioButtons() {
        String femaleRadioButtonXpath = "//input[@name='gender' and @value='Female']";
        String ageGroupRadioXpath = "//input[@name='ageGroup' and @value='15 - 50']";
        String getValueButtonXpath = "//button[text()='Get values']";
        String messageXpath = "//p[@class='groupradiobutton']";
        // Locate and click on the Female radio button for gender
        WebElement femaleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(femaleRadioButtonXpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleRadioButton);

        // Locate and click on the 15 to 50 radio button for age group
        WebElement ageGroupRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ageGroupRadioXpath)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ageGroupRadioButton);

        // Click the 'Get values' button
        WebElement getValuesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getValueButtonXpath)));
        getValuesButton.click();

        // Wait for and verify the displayed message
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(messageXpath)));
        assertEquals("Sex : Female\nAge group: 15 - 50", message.getText());
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
