package seleniumeasy.other;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class DragDropTest {
    private WebDriver driver;

    // XPaths and IDs
    private static final String ITEM_TO_DRAG_XPATH_TEMPLATE = "//span[contains(text(),'%s')]";
    private static final String DROP_AREA_ID = "mydropzone";
    private static final String DROPPED_ITEMS_XPATH = "//div[@id='droppedlist']//span[contains(text(),'Draggable')]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        driver.get("https://demo.seleniumeasy.com/drag-and-drop-demo.html");
    }

    @Test
    public void testDragAndDropSingleItem() {
        String itemToDragText = "Draggable 1";
        WebElement itemToDrag = driver.findElement(By.xpath(String.format(ITEM_TO_DRAG_XPATH_TEMPLATE, itemToDragText)));
        WebElement dropArea = driver.findElement(By.id(DROP_AREA_ID));

        // Kéo và thả phần tử
        Actions actions = new Actions(driver);
        actions.dragAndDrop(itemToDrag, dropArea).perform();

        // Xác minh rằng phần tử đã được thả vào khu vực thả
        List<WebElement> droppedItems = driver.findElements(By.xpath(DROPPED_ITEMS_XPATH + "[contains(text(),'" + itemToDragText + "')]"));
        assertTrue(droppedItems.size() > 0, "Item was not dropped into the drop area.");
    }

    @Test
    public void testDragAndDropMultipleItems() {
        String itemToDrag1Text = "Draggable 1";
        String itemToDrag2Text = "Draggable 2";

        WebElement itemToDrag1 = driver.findElement(By.xpath(String.format(ITEM_TO_DRAG_XPATH_TEMPLATE, itemToDrag1Text)));
        WebElement itemToDrag2 = driver.findElement(By.xpath(String.format(ITEM_TO_DRAG_XPATH_TEMPLATE, itemToDrag2Text)));
        WebElement dropArea = driver.findElement(By.id(DROP_AREA_ID));

        // Kéo và thả các phần tử
        Actions actions = new Actions(driver);
        actions.dragAndDrop(itemToDrag1, dropArea).perform();
        actions.dragAndDrop(itemToDrag2, dropArea).perform();

        // Xác minh rằng các phần tử đã được thả vào khu vực thả
        List<WebElement> droppedItems = driver.findElements(By.xpath(DROPPED_ITEMS_XPATH));
        assertTrue(droppedItems.size() > 1, "Not all items were dropped into the drop area.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
