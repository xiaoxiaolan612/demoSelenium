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

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/jquery-dropdown-search-demo.html");
    }

    @Test
    public void testDropdownSearch() {
        String selectDropdownXpath = "(//span[@role='combobox'])[1]";
        String searchInputXpath = "//span[@class='select2-search select2-search--dropdown']//input[@role='textbox']";
        String resultXpath = "//li[contains(text(), 'Denmark')]";
        String selectedValueXpath = "//span[@aria-labelledby='select2-country-container']";
        WebElement selectDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selectDropdownXpath)));
        selectDropdown.click();

        // Nhập giá trị tìm kiếm trong ô input
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchInputXpath)));
        searchInput.sendKeys("Denmark");

        WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultXpath)));
        result.click();

        // Kiểm tra giá trị được chọn
        WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectedValueXpath)));
        assertTrue(selectedValue.getText().contains("Denmark"), "Selected value is not Denmark.");
    }

    @Test
    public void testMultiSelectSearch() {
        List<String> states = Arrays.asList("Alaska", "California", "Florida");

        String multiSelectXpath = "//input[@placeholder='Select state(s)']";
        String searchInputXpath = "//input[@class='select2-search__field']";
        WebElement multiSelectDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(multiSelectXpath)));
        multiSelectDropdown.click();

        for (String state : states) {
            // Nhập giá trị tìm kiếm trong ô input
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchInputXpath)));
            searchInput.sendKeys(state);

            WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), '" + state + "')]")));
            result.click();

        }

        // Kiểm tra các giá trị đã được chọn
        for (String state : states) {
            WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.
                    xpath("//li[contains(@class, 'select2-selection__choice') and contains(@title, '" + state + "')]")));
            assertTrue(selectedValue.isDisplayed(), "State " + state + " is not selected.");
        }
    }
    @Test
    public void testDisabledDropdownValues() {

        String dropdownXpath = "(//span[@role='presentation'])[2]";
        String disabledOptionXpath = "//li[contains(@class, 'select2-results__option') and text()='Guam']";
        String validOptionXpath = "//li[contains(@class, 'select2-results__option') and text()='Puerto Rico']";
        String selectedValueXpath = "//span[@title='Puerto Rico']";
        WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
        dropdown.click();

        // Kiểm tra giá trị disable không thể click được
        WebElement disabledOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(disabledOptionXpath)));
        assertTrue(disabledOption.isEnabled(), "Disabled option 'Guam' should not be enabled.");

        // Chọn một giá trị hợp lệ
        WebElement validOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(validOptionXpath)));
        validOption.click();

        // Kiểm tra giá trị được chọn
        WebElement selectedValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectedValueXpath)));
        assertTrue(selectedValue.getText().contains("Puerto Rico"), "Selected value is not Puerto Rico.");
    }
    @Test
    public void testCategoryDropdown() {

        WebElement categoryDropDown = driver.findElement(By.id("files"));
        Select select = new Select(categoryDropDown);

        select.selectByVisibleText("Python");
        WebElement selectedOption = select.getFirstSelectedOption();
        String selectedText = selectedOption.getText();
        assertEquals("Python", selectedText);

        select.selectByVisibleText("Java");
        selectedOption = select.getFirstSelectedOption();
        selectedText = selectedOption.getText();
        assertEquals("Java", selectedText);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
