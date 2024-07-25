package seleniumeasy.listbox;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BootstrapListboxTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // XPaths and class names
    private static final String LEFT_LIST_ITEM_XPATH = "//div[@class='dual-list list-left col-md-5']//li[contains(text(),'%s')]";
    private static final String RIGHT_LIST_ITEM_XPATH = "//div[@class='dual-list list-right col-md-5']//li[contains(text(),'%s')]";
    private static final String MOVE_RIGHT_BUTTON_XPATH = "//button[@class='btn btn-default btn-sm move-right']";
    private static final String MOVE_LEFT_BUTTON_XPATH = "//button[@class='btn btn-default btn-sm move-left']";
    private static final String SELECT_ALL_BUTTON_XPATH = "//div[@class='well text-right']//a[@title='select all']";
    private static final String ALL_LEFT_ITEMS_XPATH = "//div[@class='dual-list list-left col-md-5']//li";
    private static final String ALL_RIGHT_ITEMS_XPATH = "//div[@class='dual-list list-right col-md-5']//li";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10); // Adjusted timeout to 10 seconds
        driver.get("https://demo.seleniumeasy.com/bootstrap-dual-list-box-demo.html");
    }

    @Test
    public void testMoveSingleItemToRight() {
        WebElement leftItem = driver.findElement(By.xpath(String.format(LEFT_LIST_ITEM_XPATH, "bootstrap-duallist")));
        leftItem.click();

        WebElement moveRightButton = driver.findElement(By.xpath(MOVE_RIGHT_BUTTON_XPATH));
        moveRightButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(String.format(RIGHT_LIST_ITEM_XPATH, "bootstrap-duallist")));
        assertTrue(rightItems.size() > 0, "Item was not moved to the right list.");
    }

    @Test
    public void testMoveMultipleItemsToRight() {
        List<WebElement> leftItems = driver.findElements(By.xpath(ALL_LEFT_ITEMS_XPATH));
        leftItems.get(0).click();
        leftItems.get(1).click();

        WebElement moveRightButton = driver.findElement(By.xpath(MOVE_RIGHT_BUTTON_XPATH));
        moveRightButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(ALL_RIGHT_ITEMS_XPATH));
        assertTrue(rightItems.size() > 1, "Items were not moved to the right list.");
    }

    @Test
    public void testMoveAllItemsToRight() {
        WebElement selectAllButton = driver.findElement(By.xpath(SELECT_ALL_BUTTON_XPATH));
        selectAllButton.click();

        WebElement moveAllRightButton = driver.findElement(By.xpath(MOVE_RIGHT_BUTTON_XPATH));
        moveAllRightButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(ALL_LEFT_ITEMS_XPATH));
        assertTrue(leftItems.size() == 0, "Not all items were moved to the right list.");
    }

    @Test
    public void testMoveSingleItemToLeft() {
        WebElement rightItem = driver.findElement(By.xpath("(//li[normalize-space()='Cras justo odio'])[1]"));
        rightItem.click();

        WebElement moveLeftButton = driver.findElement(By.xpath(MOVE_LEFT_BUTTON_XPATH));
        moveLeftButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(String.format(LEFT_LIST_ITEM_XPATH, "Cras justo odio")));
        assertTrue(leftItems.size() > 0, "Item was not moved to the left list.");
    }

    @Test
    public void testMoveMultipleItemsToLeft() {
        List<WebElement> rightItems = driver.findElements(By.xpath(ALL_RIGHT_ITEMS_XPATH));
        rightItems.get(0).click();
        rightItems.get(1).click();

        WebElement moveLeftButton = driver.findElement(By.xpath(MOVE_LEFT_BUTTON_XPATH));
        moveLeftButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(ALL_LEFT_ITEMS_XPATH));
        assertTrue(leftItems.size() > 1, "Items were not moved to the left list.");
    }

    @Test
    public void testMoveAllItemsToLeft() {
        WebElement selectAllButton = driver.findElement(By.xpath("//div[@class='well']//a[@title='select all']"));
        selectAllButton.click();

        WebElement moveAllLeftButton = driver.findElement(By.xpath(MOVE_LEFT_BUTTON_XPATH));
        moveAllLeftButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(ALL_RIGHT_ITEMS_XPATH));
        assertTrue(rightItems.size() == 0, "Not all items were moved to the left list.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
