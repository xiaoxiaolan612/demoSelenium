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
    // private WebDriverWait wait; // Not used in this version

    // Constants for locators and values
    private static final String SEARCH_BOX_XPATH = "//input[@type='search']";
    private static final String NAME_CELL_XPATH = "./td[1]";
    private static final String AGE_HEADER_XPATH = "//th[contains(text(), 'Age')]";
    private static final String AGE_CELLS_XPATH = "//table[@id='example']/tbody/tr/td[4]";
    private static final String POSITION_FILTER_XPATH = "//select[@name='example_length']";
    private static final String POSITION_CELL_XPATH = "./td[2]";
    private static final String PAGES_XPATH = "//div[@id='example_paginate']//a";
    private static final String PAGE_XPATH = "//div[@id='example_paginate']//a[contains(text(), '%d')]";

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        // wait = new WebDriverWait(driver, 1000); // Not used in this version
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");
    }

    @Test
    public void testSearchFunctionality() {
        WebElement searchBox = driver.findElement(By.xpath(SEARCH_BOX_XPATH));
        searchBox.sendKeys("Bradley");

        // Kiểm tra kết quả tìm kiếm
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement nameCell = row.findElement(By.xpath(NAME_CELL_XPATH));
                assertTrue(nameCell.getText().contains("Bradley"));
            }
        }
    }

    @Test
    public void testSortByAge() {
        WebElement ageHeader = driver.findElement(By.xpath(AGE_HEADER_XPATH));
        ageHeader.click();

        List<WebElement> ageCells = driver.findElements(By.xpath(AGE_CELLS_XPATH));
        List<Integer> ages = ageCells.stream()
                .map(cell -> Integer.parseInt(cell.getText()))
                .collect(Collectors.toList());

        // Kiểm tra danh sách đã được sắp xếp tăng dần
        for (int i = 0; i < ages.size() - 1; i++) {
            assertTrue(ages.get(i) <= ages.get(i + 1));
        }
    }

    @Test
    public void testFilterByPosition() {
        WebElement positionFilter = driver.findElement(By.xpath(POSITION_FILTER_XPATH));
        positionFilter.sendKeys("10");

        WebElement searchBox = driver.findElement(By.xpath(SEARCH_BOX_XPATH));
        searchBox.sendKeys("Software Engineer");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement positionCell = row.findElement(By.xpath(POSITION_CELL_XPATH));
                assertTrue(positionCell.getText().contains("Software Engineer"));
            }
        }
    }

    @Test
    public void testPagination() {
        List<WebElement> pages = driver.findElements(By.xpath(PAGES_XPATH));
        assertTrue(pages.size() > 1);

        WebElement page2 = driver.findElement(By.xpath(String.format(PAGE_XPATH, 2)));
        page2.click();

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
