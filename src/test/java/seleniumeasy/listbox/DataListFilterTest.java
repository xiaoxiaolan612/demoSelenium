package seleniumeasy.listbox;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class DataListFilterTest {

    private WebDriver driver;

    // XPaths and IDs
    private static final String SEARCH_BOX_ID = "input-search";
    private static final String SEARCH_RESULTS_XPATH = "//div[contains(@class, 'items') and @style='display: block;']";
    private static final String RESULT_NAME_XPATH_TEMPLATE = ".//h4[contains(text(),'%s')]";
    private static final String RESULT_EMAIL_XPATH_TEMPLATE = "//span[contains(text(),'%s')]";
    private static final String RESULT_PHONE_XPATH_TEMPLATE = "//span[contains(text(),'%s')]";
    private static final String ALL_ITEMS_XPATH = "//div[@class='searchable-container']//div[contains(@class, 'items')]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/data-list-filter-demo.html");
    }

    @Test
    public void testSearchByName() {
        WebElement searchBox = driver.findElement(By.id(SEARCH_BOX_ID));
        searchBox.sendKeys("Brenda Tree");

        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS_XPATH));
        System.out.println(searchResults.size());
        assertTrue(searchResults.size() == 1, "No results found or more than one result.");
        WebElement resultName = searchResults.get(0).findElement(By.xpath(String.format(RESULT_NAME_XPATH_TEMPLATE, "Brenda Tree")));
        assertTrue(resultName.isDisplayed(), "Search result does not contain the entered name.");
    }

    @Test
    public void testSearchByEmail() {
        WebElement searchBox = driver.findElement(By.id(SEARCH_BOX_ID));
        searchBox.sendKeys("test3@company.com");

        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS_XPATH));
        System.out.println(searchResults.size());
        assertTrue(searchResults.size() == 1, "No results found or more than one result.");
        WebElement resultEmail = searchResults.get(0).findElement(By.xpath(String.format(RESULT_EMAIL_XPATH_TEMPLATE, "test3@company.com")));
        assertTrue(resultEmail.isDisplayed(), "Search result does not contain the entered email.");
    }

    @Test
    public void testSearchByPhone() {
        WebElement searchBox = driver.findElement(By.id(SEARCH_BOX_ID));
        searchBox.sendKeys("564-234-4444");

        List<WebElement> searchResults = driver.findElements(By.xpath(SEARCH_RESULTS_XPATH));
        assertTrue(searchResults.size() == 1, "No results found or more than one result.");
        WebElement resultPhone = searchResults.get(0).findElement(By.xpath(String.format(RESULT_PHONE_XPATH_TEMPLATE, "564-234-4444")));
        assertTrue(resultPhone.isDisplayed(), "Search result does not contain the entered phone number.");
    }

    @Test
    public void testClearSearch() {
        WebElement searchBox = driver.findElement(By.id(SEARCH_BOX_ID));
        searchBox.sendKeys("Brenda Tree");

        searchBox.clear();

        List<WebElement> allItems = driver.findElements(By.xpath(ALL_ITEMS_XPATH));
        assertTrue(allItems.size() > 1, "Not all items are displayed after clearing the search.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
