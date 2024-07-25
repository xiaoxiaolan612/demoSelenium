package seleniumeasy.datepickers;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JQueryDatePickerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Define constants for element locators
    private static final String START_DATE_FIELD_ID = "from";
    private static final String END_DATE_FIELD_ID = "to";
    private static final String START_DATE_TO_SELECT_TEXT = "10";
    private static final String END_DATE_TO_SELECT_TEXT = "20";
    private static final String DISABLED_START_DATE_XPATH_TEMPLATE = "(//td[contains(@class,'ui-state-disabled')])[%d]";
    private static final String DISABLED_END_DATE_XPATH_TEMPLATE = "(//td[contains(@class,'ui-state-disabled')])[%d]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10); // Adjusted timeout to 10 seconds for practicality
        driver.get("https://demo.seleniumeasy.com/jquery-date-picker-demo.html");
    }

    @Test
    public void testDatePicker() {
        // Find and click on the start date field
        WebElement startDateField = wait.until(ExpectedConditions.elementToBeClickable(By.id(START_DATE_FIELD_ID)));
        startDateField.click();

        // Select start date
        WebElement startDateToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[text()='%s']", START_DATE_TO_SELECT_TEXT))));
        startDateToSelect.click();

        // Find and click on the end date field
        WebElement endDateField = wait.until(ExpectedConditions.elementToBeClickable(By.id(END_DATE_FIELD_ID)));
        endDateField.click();

        // Select end date
        WebElement endDateToSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//a[text()='%s']", END_DATE_TO_SELECT_TEXT))));
        endDateToSelect.click();

        // Check if the start date is less than the end date
        String startDate = startDateField.getAttribute("value");
        String endDate = endDateField.getAttribute("value");
        assertTrue("Start date should be less than end date!", startDate.compareTo(endDate) < 0);

        // Check if dates before the start date are disabled in the end date picker
        endDateField.click();
        WebElement disabledStartDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(DISABLED_START_DATE_XPATH_TEMPLATE, 10))));
        WebElement disabledStartDate2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(DISABLED_START_DATE_XPATH_TEMPLATE, 9))));

        assertNotNull("Dates before the start date should be disabled in the end date picker!", disabledStartDate);
        assertNotNull("Dates before the start date should be disabled in the end date picker!", disabledStartDate2);

        // Check if dates after the end date are disabled in the start date picker
        startDateField.click();
        WebElement disabledEndDate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(DISABLED_END_DATE_XPATH_TEMPLATE, 3))));
        WebElement disabledEndDate2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(DISABLED_END_DATE_XPATH_TEMPLATE, 1))));

        assertNotNull("Dates after the end date should be disabled in the start date picker!", disabledEndDate);
        assertNotNull("Dates after the end date should be disabled in the start date picker!", disabledEndDate2);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
