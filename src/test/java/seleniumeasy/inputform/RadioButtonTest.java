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

    // Locators as constants
    private static final String MALE_RADIO_BUTTON_XPATH = "//input[@name='optradio' and @value='Male']";
    private static final String MESSAGE_XPATH = "//p[@class='radiobutton']";
    private static final String GET_VALUE_BUTTON_ID = "buttoncheck";

    private static final String FEMALE_RADIO_BUTTON_XPATH = "//input[@name='gender' and @value='Female']";
    private static final String AGE_GROUP_RADIO_XPATH = "//input[@name='ageGroup' and @value='15 - 50']";
    private static final String GET_VALUES_BUTTON_XPATH = "//button[text()='Get values']";
    private static final String GROUP_MESSAGE_XPATH = "//p[@class='groupradiobutton']";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/basic-radiobutton-demo.html");
    }

    @Test
    public void testSingleRadioButton() {
        // Locate and click on the Male radio button
        WebElement maleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MALE_RADIO_BUTTON_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maleRadioButton);

        // Click the 'Get Checked value' button
        WebElement getValueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(GET_VALUE_BUTTON_ID)));
        getValueButton.click();

        // Wait for and verify the displayed message
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MESSAGE_XPATH)));
        assertEquals("Radio button 'Male' is checked", message.getText());
    }

    @Test
    public void testGroupRadioButtons() {
        // Locate and click on the Female radio button for gender
        WebElement femaleRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FEMALE_RADIO_BUTTON_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", femaleRadioButton);

        // Locate and click on the 15 to 50 radio button for age group
        WebElement ageGroupRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AGE_GROUP_RADIO_XPATH)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ageGroupRadioButton);

        // Click the 'Get values' button
        WebElement getValuesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GET_VALUES_BUTTON_XPATH)));
        getValuesButton.click();

        // Wait for and verify the displayed message
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GROUP_MESSAGE_XPATH)));
        assertEquals("Sex : Female\nAge group: 15 - 50", message.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
