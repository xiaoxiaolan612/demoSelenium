package seleniumeasy.table;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.*;

public class TablePaginationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constants for locators and values
    private static final String ROWS_XPATH = "//table[@class='table table-hover']/tbody/tr";
    private static final String PAGE_LINK_XPATH = "//a[text()='%d']";
    private static final String PREV_BUTTON_XPATH = "//a[normalize-space()='«']";
    private static final String NEXT_BUTTON_XPATH = "//a[contains(@class, 'next_link')]";
    private static final String NEXT_BUTTON_LAST_PAGE_XPATH = "//a[normalize-space()='»']";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/table-pagination-demo.html");
    }

    @Test
    public void testTotalRecords() {
        List<WebElement> rows = driver.findElements(By.xpath(ROWS_XPATH));
        assertEquals(15, rows.size());
    }

    @Test
    public void testMaxRecordsPerPage() {
        for (int page = 1; page <= 3; page++) {
            navigateToPage(page);
            List<WebElement> visibleRows = driver.findElements(By.xpath(ROWS_XPATH + "[not(contains(@style, 'display: none'))]"));
            assertTrue(visibleRows.size() <= 5);
        }
    }

    @Test
    public void testPrevNextButtonsOnSecondPage() {
        navigateToPage(2);
        WebElement prevButton = driver.findElement(By.xpath(PREV_BUTTON_XPATH));
        WebElement nextButton = driver.findElement(By.xpath(NEXT_BUTTON_XPATH));
        assertTrue(prevButton.getAttribute("style").contains("display: block;"));
        assertFalse(nextButton.getAttribute("style").contains("display: none;"));
    }

    @Test
    public void testPrevButtonOnFirstPage() {
        navigateToPage(1);
        WebElement prevButton = driver.findElement(By.xpath(PREV_BUTTON_XPATH));
        assertTrue(prevButton.getAttribute("style").contains("display: none;"));
    }

    @Test
    public void testNextButtonOnLastPage() {
        navigateToPage(3);
        WebElement nextButton = driver.findElement(By.xpath(NEXT_BUTTON_LAST_PAGE_XPATH));
        assertTrue(nextButton.getAttribute("style").contains("display: none;"));
    }

    private void navigateToPage(int pageNumber) {
        WebElement pageLink = driver.findElement(By.xpath(String.format(PAGE_LINK_XPATH, pageNumber)));
        pageLink.click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
