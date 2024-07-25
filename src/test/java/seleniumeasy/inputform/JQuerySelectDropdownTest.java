package seleniumeasy.inputform;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JQuerySelectDropdownTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Define XPaths as constants
    private static final String DROPDOWN_XPATH = "(//span[@role='combobox'])[1]";
    private static final String SEARCH_INPUT_XPATH = "//span[@class='select2-search select2-search--dropdown']//input[@role='textbox']";
    private static final String RESULT_XPATH_TEMPLATE = "//li[contains(text(), '%s')]";
    private static final String SELECTED_VALUE_XPATH = "//span[@aria-labelledby='select2-country-container']";

    private static final String MULTI_SELECT_XPATH = "//input[@placeholder='Select state(s)']";
    private static final String MULTI_SEARCH_INPUT_XPATH = "//input[@class='select2-search__field']";
    private static final String MULTI_SELECTED_VALUE_XPATH_TEMPLATE = "//li[contains(@class, 'select2-selection__choice') and contains(@title, '%s')]";

    private static final String DISABLED_DROPDOWN_XPATH = "(//span[@role='presentation'])[2]";
    private static final String DISABLED_OPTION_XPATH = "//li[contains(@class, 'select2-results__option') and text()='Guam']";
    private static final String VALID_OPTION_XPATH = "//li[contains(@class, 'select2-results__option') and text()='Puerto Rico']";
    private static final String DISABLED_OPTION_TEXT = "Guam";
    private static final String VALID_OPTION_TEXT = "Puerto Rico";
    private static final String SELECTED_VALUE_XPATHH = "//span[@title='Puerto Rico']";

    private static final String CATEGORY_DROPDOWN_ID = "files";
    private static final String CATEGORY_SELECT_TEXT_1 = "Python";
    private static final String CATEGORY_SELECT_TEXT_2 = "Java";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10); // Adjusted timeout to 10 seconds for practicality
        driver.get("https://demo.seleniumeasy.com/jquery-dropdown-search-demo.html");
    }

    @Test
    public void testDropdownSearch() {
        WebElement selectDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DROPDOWN_XPATH)));
        selectDropdown.click();

        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_INPUT_XPATH)));
        searchInput.sendKeys("Denmark");

        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(RESULT_XPATH_TEMPLATE, "Denmark"))));
        result.click();

        WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SELECTED_VALUE_XPATHH)));
        assertTrue(selectedValue.getText().contains("Denmark"), "Selected value is not Denmark.");
    }

    @Test
    public void testMultiSelectSearch() {
        List<String> states = Arrays.asList("Alaska", "California", "Florida");

        WebElement multiSelectDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MULTI_SELECT_XPATH)));
        multiSelectDropdown.click();

        for (String state : states) {
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MULTI_SEARCH_INPUT_XPATH)));
            searchInput.sendKeys(state);

            WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(RESULT_XPATH_TEMPLATE, state))));
            result.click();
        }

        for (String state : states) {
            WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(MULTI_SELECTED_VALUE_XPATH_TEMPLATE, state))));
            assertTrue(selectedValue.isDisplayed(), "State " + state + " is not selected.");
        }
    }

    @Test
    public void testDisabledDropdownValues() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DISABLED_DROPDOWN_XPATH)));
        dropdown.click();

        WebElement disabledOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(DISABLED_OPTION_XPATH)));
        assertTrue(!disabledOption.isEnabled(), "Disabled option 'Guam' should not be enabled.");

        WebElement validOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(VALID_OPTION_XPATH)));
        validOption.click();

        WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SELECTED_VALUE_XPATHH)));
        assertTrue(selectedValue.getText().contains(VALID_OPTION_TEXT), "Selected value is not " + VALID_OPTION_TEXT + ".");
    }

    @Test
    public void testCategoryDropdown() {
        WebElement categoryDropDown = driver.findElement(By.id(CATEGORY_DROPDOWN_ID));
        Select select = new Select(categoryDropDown);

        select.selectByVisibleText(CATEGORY_SELECT_TEXT_1);
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        assertEquals(CATEGORY_SELECT_TEXT_1, selectedText);

        select.selectByVisibleText(CATEGORY_SELECT_TEXT_2);
        selectedOption = select.getFirstSelectedOption();
        selectedText = selectedOption.getText();
        assertEquals(CATEGORY_SELECT_TEXT_2, selectedText);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
