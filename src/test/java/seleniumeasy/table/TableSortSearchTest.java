package seleniumeasy.table;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class TableSortSearchTest {

    private WebDriver driver;
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
    }

    @Test
    public void testSearchFunctionality() {
        // Tìm kiếm từ khóa "Bradley"
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchBox.sendKeys("Bradley");

        // Kiểm tra kết quả tìm kiếm
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement nameCell = row.findElement(By.xpath("./td[1]"));
                assertTrue(nameCell.getText().contains("Bradley"));
            }
        }
    }

    @Test
    public void testSortByAge() {
        // Nhấp vào cột "Age" để sắp xếp
        WebElement ageHeader = driver.findElement(By.xpath("//th[contains(text(), 'Age')]"));
        ageHeader.click();

        // Lấy tất cả các giá trị trong cột "Age"
        List<WebElement> ageCells = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[4]"));
        List<Integer> ages = ageCells.stream().map(cell -> Integer.parseInt(cell.getText())).collect(Collectors.toList());

        // Kiểm tra danh sách đã được sắp xếp tăng dần
        for (int i = 0; i < ages.size() - 1; i++) {
            assertTrue(ages.get(i) <= ages.get(i + 1));
        }
    }

    @Test
    public void testFilterByPosition() {
        // Chọn giá trị lọc "Software Engineer"
        WebElement positionFilter = driver.findElement(By.xpath("//select[@name='example_length']"));
        positionFilter.sendKeys("10");

        // Nhập từ khóa vào ô tìm kiếm
        WebElement searchBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchBox.sendKeys("Software Engineer");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement positionCell = row.findElement(By.xpath("./td[2]"));
                assertTrue(positionCell.getText().contains("Software Engineer"));
            }
        }
    }

    @Test
    public void testPagination() {
        // Kiểm tra số trang có tồn tại
        List<WebElement> pages = driver.findElements(By.xpath("//div[@id='example_paginate']//a"));
        assertTrue(pages.size() > 1);

        // Nhấp vào trang 2
        WebElement page2 = driver.findElement(By.xpath("//div[@id='example_paginate']//a[contains(text(), '2')]"));
        page2.click();

        // Kiểm tra có dữ liệu ở trang 2
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        assertTrue(rows.size() > 0);
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
