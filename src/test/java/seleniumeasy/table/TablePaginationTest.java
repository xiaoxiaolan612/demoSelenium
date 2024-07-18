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

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/table-pagination-demo.html");
    }
    @Test
    public void testTotalRecords() {
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr"));
        assertEquals(15, rows.size());
    }

    @Test
    public void testMaxRecordsPerPage() {
        for (int page = 1; page <= 3; page++) {
            driver.findElement(By.xpath("//a[text()='" + page + "']")).click();
            List<WebElement> visibleRows = driver.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr[not(contains(@style, 'display: none'))]"));
            assertTrue(visibleRows.size() <= 5);
        }
    }

    @Test
    public void testPrevNextButtonsOnSecondPage() {
        driver.findElement(By.xpath("//a[text()='2']")).click();
        WebElement prevButton = driver.findElement(By.xpath("//a[normalize-space()='«']"));
        WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class, 'next_link')]"));
        assertTrue(prevButton.getAttribute("style").equals("display: block;") );
        assertFalse(nextButton.getAttribute("style").contains("display: none;"));
    }

    @Test
    public void testPrevButtonOnFirstPage() {
        driver.findElement(By.xpath("//a[text()='1']")).click();
        WebElement prevButton = driver.findElement(By.xpath("//a[normalize-space()='«']"));
        assertTrue(prevButton.getAttribute("style").contains("none"));
    }

    @Test
    public void testNextButtonOnLastPage() {
        driver.findElement(By.xpath("//a[text()='3']")).click();
        WebElement nextButton = driver.findElement(By.xpath("//a[normalize-space()='»']"));
        assertTrue(nextButton.getAttribute("style").contains("none"));
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
