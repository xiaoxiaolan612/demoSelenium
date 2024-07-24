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
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/table-records-filter-demo.html");
    }

    @Test
    public void testFilterGreen() {
        // Nhấp vào nút "Green"
        WebElement greenFilterButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-success')]"));
        greenFilterButton.click();

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-filter']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement statusCell1 = row.findElement(By.xpath("//span[contains(text(),'(Green)')]"));
                WebElement statusCell2 = row.findElement(By.xpath("//span[contains(text(),'(Orange)')]"));
                WebElement statusCell3 = row.findElement(By.xpath("//span[contains(text(),'(Red)')]"));
                assertTrue(statusCell1.isDisplayed());
                assertFalse(statusCell2.isDisplayed());
                assertFalse(statusCell3.isDisplayed());
            }
        }
    }

    @Test
    public void testFilterOrange() {
        // Nhấp vào nút "Orange"
        WebElement orangeFilterButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-warning')]"));
        orangeFilterButton.click();

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-filter']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement statusCell1 = row.findElement(By.xpath("//span[contains(text(),'(Green)')]"));
                WebElement statusCell2 = row.findElement(By.xpath("//span[contains(text(),'(Orange)')]"));
                WebElement statusCell3 = row.findElement(By.xpath("//span[contains(text(),'(Red)')]"));
                assertTrue(statusCell2.isDisplayed());
                assertFalse(statusCell1.isDisplayed());
                assertFalse(statusCell3.isDisplayed());
            }
        }
    }

    @Test
    public void testFilterRed() {
        // Nhấp vào nút "Red"
        WebElement redFilterButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-danger')]"));
        redFilterButton.click();

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-filter']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement statusCell1 = row.findElement(By.xpath("//span[contains(text(),'(Green)')]"));
                WebElement statusCell2 = row.findElement(By.xpath("//span[contains(text(),'(Orange)')]"));
                WebElement statusCell3 = row.findElement(By.xpath("//span[contains(text(),'(Red)')]"));
                assertTrue(statusCell3.isDisplayed());
                assertFalse(statusCell2.isDisplayed());
                assertFalse(statusCell1.isDisplayed());
            }
        }
    }

    @Test
    public void testFilterAll() {
        // Nhấp vào nút "All"
        WebElement allFilterButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-default')]"));
        allFilterButton.click();

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-filter']/tbody/tr"));
        for (WebElement row : rows) {
            assertTrue(row.isDisplayed());
        }
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
