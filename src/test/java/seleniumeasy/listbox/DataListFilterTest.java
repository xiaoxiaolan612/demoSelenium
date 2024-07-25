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
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/data-list-filter-demo.html");
    }
    @Test
    public void testSearchByName() {
        // Nhập tên vào ô tìm kiếm
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("Brenda Tree");

        // Xác minh rằng kết quả hiển thị đúng với tên đã nhập
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 'items') and @style='display: block;']"));
        System.out.println(searchResults.size());
        assertTrue(searchResults.size() == 1, "Không tìm thấy kết quả hoặc có nhiều hơn một kết quả.");
        WebElement resultName = searchResults.get(0).findElement(By.xpath(".//h4[contains(text(),'Brenda Tree')]"));
        assertTrue(resultName.isDisplayed(), "Kết quả tìm kiếm không chứa tên đã nhập.");
    }

    @Test
    public void testSearchByEmail() {
        // Nhập email vào ô tìm kiếm
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("test3@company.com");

        // Xác minh rằng kết quả hiển thị đúng với email đã nhập
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 'items') and @style='display: block;']"));
        System.out.println(searchResults.size());
        assertTrue(searchResults.size() == 1, "Không tìm thấy kết quả hoặc có nhiều hơn một kết quả.");
        WebElement resultEmail = searchResults.get(0).findElement(By.xpath("//span[contains(text(), 'test3@company.com')]"));
        assertTrue(resultEmail.isDisplayed(), "Kết quả tìm kiếm không chứa email đã nhập.");
    }

    @Test
    public void testSearchByPhone() {
        // Nhập số điện thoại vào ô tìm kiếm
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("564-234-4444");

        // Xác minh rằng kết quả hiển thị đúng với số điện thoại đã nhập
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 'items') and @style='display: block;']"));
        assertTrue(searchResults.size() == 1, "Không tìm thấy kết quả hoặc có nhiều hơn một kết quả.");
        WebElement resultPhone = searchResults.get(0).findElement(By.xpath("//span[contains(text(),'564-234-4444')]"));
        assertTrue(resultPhone.isDisplayed(), "Kết quả tìm kiếm không chứa số điện thoại đã nhập.");
    }

    @Test
    public void testClearSearch() {
        // Nhập nội dung vào ô tìm kiếm
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("Brenda Tree");

        // Xóa nội dung tìm kiếm
        searchBox.clear();

        // Xác minh rằng tất cả các mục hiển thị lại
        List<WebElement> allItems = driver.findElements(By.xpath("//div[@class='searchable-container']//div[contains(@class, 'items')]"));
        assertTrue(allItems.size() > 1, "Không phải tất cả các mục hiển thị lại.");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
