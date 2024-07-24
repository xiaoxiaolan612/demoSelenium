package seleniumeasy.table;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TableDataSearchTest {
    private WebDriver driver;
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/table-search-filter-demo.html");
    }

    @Test
    public void testFilterByTask() {
        // Lọc theo Task
        WebElement taskFilter = driver.findElement(By.id("task-table-filter"));
        taskFilter.sendKeys("Wireframes");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='task-table']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement taskCell = row.findElement(By.xpath("./td[2]"));
                assertTrue(taskCell.getText().contains("Wireframes"));
            }
        }
    }

    @Test
    public void testFilterByAssignee() {
        // Lọc theo Assignee
        WebElement assigneeFilter = driver.findElement(By.id("task-table-filter"));
        assigneeFilter.sendKeys("John Smith");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='task-table']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement assigneeCell = row.findElement(By.xpath("./td[3]"));
                assertTrue(assigneeCell.getText().contains("John Smith"));
            }
        }
    }

    @Test
    public void testFilterByStatus() {
        // Lọc theo Status
        WebElement statusFilter = driver.findElement(By.id("task-table-filter"));
        statusFilter.sendKeys("Completed");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='task-table']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement statusCell = row.findElement(By.xpath("./td[4]"));
                assertTrue(statusCell.getText().contains("completed"));
            }
        }
    }
    @Test
    public void testFilterListedUsersTable() {
        // Nhấp vào nút "Filter"
        WebElement filterButton = driver.findElement(By.xpath("//button[contains(text(),'Filter')]"));
        filterButton.click();

        // Lọc theo Username
        WebElement usernameFilter = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        usernameFilter.sendKeys("bootstrap");

        // Kiểm tra kết quả lọc
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement userCell = row.findElement(By.xpath("./td[2]"));
                assertTrue(userCell.getText().contains("bootstrap"));
            }
        }

        // Lọc theo First Name
        WebElement firstNameFilter = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        firstNameFilter.sendKeys("John");

        // Kiểm tra kết quả lọc
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement firstNameCell = row.findElement(By.xpath("./td[3]"));
                assertTrue(firstNameCell.getText().contains("John"));
            }
        }

        // Lọc theo Last Name
        WebElement lastNameFilter = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        lastNameFilter.sendKeys("Doe");

        // Kiểm tra kết quả lọc
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement lastNameCell = row.findElement(By.xpath("./td[4]"));
                assertTrue(lastNameCell.getText().contains("Doe"));
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
