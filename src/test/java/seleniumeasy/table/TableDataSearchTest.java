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

    // Constants for locators and parameters
    private static final String TASK_FILTER_ID = "task-table-filter";
    private static final String USERNAME_FILTER_XPATH = "//input[@placeholder='Username']";
    private static final String FIRST_NAME_FILTER_XPATH = "//input[@placeholder='First Name']";
    private static final String LAST_NAME_FILTER_XPATH = "//input[@placeholder='Last Name']";
    private static final String FILTER_BUTTON_XPATH = "//button[contains(text(),'Filter')]";
    private static final String TASK_TABLE_XPATH = "//table[@id='task-table']/tbody/tr";
    private static final String USER_TABLE_XPATH = "//table[@id='example']/tbody/tr";
    private static final String WIREFRAMES_TASK = "Wireframes";
    private static final String JOHN_SMITH_ASSIGNEE = "John Smith";
    private static final String COMPLETED_STATUS = "completed";
    private static final String USERNAME = "bootstrap";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/table-search-filter-demo.html");
    }

    @Test
    public void testFilterByTask() {
        // Filter by Task
        WebElement taskFilter = driver.findElement(By.id(TASK_FILTER_ID));
        taskFilter.sendKeys(WIREFRAMES_TASK);

        // Verify filter results
        List<WebElement> rows = driver.findElements(By.xpath(TASK_TABLE_XPATH));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement taskCell = row.findElement(By.xpath("./td[2]"));
                assertTrue(taskCell.getText().contains(WIREFRAMES_TASK));
            }
        }
    }

    @Test
    public void testFilterByAssignee() {
        // Filter by Assignee
        WebElement assigneeFilter = driver.findElement(By.id(TASK_FILTER_ID));
        assigneeFilter.sendKeys(JOHN_SMITH_ASSIGNEE);

        // Verify filter results
        List<WebElement> rows = driver.findElements(By.xpath(TASK_TABLE_XPATH));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement assigneeCell = row.findElement(By.xpath("./td[3]"));
                assertTrue(assigneeCell.getText().contains(JOHN_SMITH_ASSIGNEE));
            }
        }
    }

    @Test
    public void testFilterByStatus() {
        // Filter by Status
        WebElement statusFilter = driver.findElement(By.id(TASK_FILTER_ID));
        statusFilter.sendKeys("Completed");

        // Verify filter results
        List<WebElement> rows = driver.findElements(By.xpath(TASK_TABLE_XPATH));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement statusCell = row.findElement(By.xpath("./td[4]"));
                assertTrue(statusCell.getText().toLowerCase().contains(COMPLETED_STATUS));
            }
        }
    }

    @Test
    public void testFilterListedUsersTable() {
        // Click the "Filter" button
        WebElement filterButton = driver.findElement(By.xpath(FILTER_BUTTON_XPATH));
        filterButton.click();

        // Filter by Username
        WebElement usernameFilter = driver.findElement(By.xpath(USERNAME_FILTER_XPATH));
        usernameFilter.sendKeys(USERNAME);

        // Verify filter results for Username
        List<WebElement> rows = driver.findElements(By.xpath(USER_TABLE_XPATH));
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement userCell = row.findElement(By.xpath("./td[2]"));
                assertTrue(userCell.getText().contains(USERNAME));
            }
        }

        // Filter by First Name
        WebElement firstNameFilter = driver.findElement(By.xpath(FIRST_NAME_FILTER_XPATH));
        firstNameFilter.sendKeys(FIRST_NAME);

        // Verify filter results for First Name
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement firstNameCell = row.findElement(By.xpath("./td[3]"));
                assertTrue(firstNameCell.getText().contains(FIRST_NAME));
            }
        }

        // Filter by Last Name
        WebElement lastNameFilter = driver.findElement(By.xpath(LAST_NAME_FILTER_XPATH));
        lastNameFilter.sendKeys(LAST_NAME);

        // Verify filter results for Last Name
        for (WebElement row : rows) {
            if (row.isDisplayed()) {
                WebElement lastNameCell = row.findElement(By.xpath("./td[4]"));
                assertTrue(lastNameCell.getText().contains(LAST_NAME));
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
