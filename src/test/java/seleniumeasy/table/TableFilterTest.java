package seleniumeasy.table;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TableFilterTest {
    private WebDriver driver;

    // Constants for locators and filter values
    private static final String FILTER_BUTTON_XPATH_PREFIX = "//button[contains(@class, 'btn-";
    private static final String FILTER_BUTTON_XPATH_SUFFIX = "')]";
    private static final String TABLE_ROWS_XPATH = "//table[@class='table table-filter']/tbody/tr";
    private static final String GREEN_STATUS_XPATH = "//span[contains(text(),'(Green)')]";
    private static final String ORANGE_STATUS_XPATH = "//span[contains(text(),'(Orange)')]";
    private static final String RED_STATUS_XPATH = "//span[contains(text(),'(Red)')]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/table-records-filter-demo.html");
    }

    @Test
    public void testFilterGreen() {
        applyFilter("success"); // Filter by green

        verifyFilterResults(GREEN_STATUS_XPATH, ORANGE_STATUS_XPATH, RED_STATUS_XPATH);
    }

    @Test
    public void testFilterOrange() {
        applyFilter("warning"); // Filter by orange

        verifyFilterResults(ORANGE_STATUS_XPATH, GREEN_STATUS_XPATH, RED_STATUS_XPATH);
    }

    @Test
    public void testFilterRed() {
        applyFilter("danger"); // Filter by red

        verifyFilterResults(RED_STATUS_XPATH, GREEN_STATUS_XPATH, ORANGE_STATUS_XPATH);
    }

    @Test
    public void testFilterAll() {
        WebElement allFilterButton = driver.findElement(By.xpath(FILTER_BUTTON_XPATH_PREFIX + "default" + FILTER_BUTTON_XPATH_SUFFIX));
        allFilterButton.click();

        // Verify all rows are displayed
        List<WebElement> rows = driver.findElements(By.xpath(TABLE_ROWS_XPATH));
        for (WebElement row : rows) {
            assertTrue(row.isDisplayed());
        }
    }

    private void applyFilter(String filterType) {
        WebElement filterButton = driver.findElement(By.xpath(FILTER_BUTTON_XPATH_PREFIX + filterType + FILTER_BUTTON_XPATH_SUFFIX));
        filterButton.click();
    }

    private void verifyFilterResults(String expectedVisibleXPath, String... expectedHiddenXPaths) {
        List<WebElement> rows = driver.findElements(By.xpath(TABLE_ROWS_XPATH));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                assertTrue(row.findElement(By.xpath(expectedVisibleXPath)).isDisplayed());
                for (String hiddenXPath : expectedHiddenXPaths) {
                    assertFalse(row.findElement(By.xpath(hiddenXPath)).isDisplayed());
                }
            }
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
