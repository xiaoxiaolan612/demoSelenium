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

public class JQueryListboxTest {

    private WebDriver driver;

    // XPaths
    private static final String LEFT_LIST_XPATH = "//select[@class='form-control pickListSelect pickData']//option";
    private static final String RIGHT_LIST_XPATH = "//select[@class='form-control pickListSelect pickListResult']//option";
    private static final String ADD_BUTTON_XPATH = "//button[contains(text(), 'Add')]";
    private static final String ADD_ALL_BUTTON_XPATH = "//button[contains(text(), 'Add All')]";
    private static final String REMOVE_BUTTON_XPATH = "//button[contains(text(), 'Remove')]";
    private static final String REMOVE_ALL_BUTTON_XPATH = "//button[contains(text(), 'Remove All')]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/jquery-dual-list-box-demo.html");
    }

    @Test
    public void testMoveSingleItemToRight() {
        WebElement leftItem = driver.findElement(By.xpath(LEFT_LIST_XPATH + "[contains(text(),'Isis')]"));
        leftItem.click();

        WebElement moveRightButton = driver.findElement(By.xpath(ADD_BUTTON_XPATH));
        moveRightButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(RIGHT_LIST_XPATH + "[contains(text(),'Isis')]"));
        assertTrue(rightItems.size() > 0, "Item was not moved to the right list.");
    }

    @Test
    public void testMoveMultipleItemsToRight() {
        List<WebElement> leftItems = driver.findElements(By.xpath(LEFT_LIST_XPATH));
        leftItems.get(0).click();
        leftItems.get(1).click();

        WebElement moveRightButton = driver.findElement(By.xpath(ADD_BUTTON_XPATH));
        moveRightButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(RIGHT_LIST_XPATH));
        assertTrue(rightItems.size() > 1, "Items were not moved to the right list.");
    }

    @Test
    public void testMoveAllItemsToRight() {
        WebElement moveAllRightButton = driver.findElement(By.xpath(ADD_ALL_BUTTON_XPATH));
        moveAllRightButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(LEFT_LIST_XPATH));
        assertTrue(leftItems.size() == 0, "Not all items were moved to the right list.");
    }

    @Test
    public void testMoveSingleItemToLeft() {
        WebElement moveAllRightButton = driver.findElement(By.xpath(ADD_ALL_BUTTON_XPATH));
        moveAllRightButton.click();

        WebElement rightItem = driver.findElement(By.xpath(RIGHT_LIST_XPATH + "[contains(text(),'Isis')]"));
        rightItem.click();

        WebElement moveLeftButton = driver.findElement(By.xpath(REMOVE_BUTTON_XPATH));
        moveLeftButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(LEFT_LIST_XPATH + "[contains(text(),'Isis')]"));
        assertTrue(leftItems.size() > 0, "Item was not moved to the left list.");
    }

    @Test
    public void testMoveMultipleItemsToLeft() {
        WebElement moveAllRightButton = driver.findElement(By.xpath(ADD_ALL_BUTTON_XPATH));
        moveAllRightButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(RIGHT_LIST_XPATH));
        rightItems.get(0).click();
        rightItems.get(1).click();

        WebElement removeButton = driver.findElement(By.xpath(REMOVE_BUTTON_XPATH));
        removeButton.click();

        List<WebElement> leftItems = driver.findElements(By.xpath(LEFT_LIST_XPATH));
        assertTrue(leftItems.size() > 1, "Items were not moved to the left list.");
    }

    @Test
    public void testMoveAllItemsToLeft() {
        WebElement removeButton = driver.findElement(By.xpath(REMOVE_ALL_BUTTON_XPATH));
        removeButton.click();

        List<WebElement> rightItems = driver.findElements(By.xpath(RIGHT_LIST_XPATH));
        assertTrue(rightItems.size() == 0, "Not all items were moved to the left list.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
