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
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/drag-and-drop-demo.html");
    }
    @Test
    public void testDragAndDropSingleItem() {
        WebElement itemToDrag = driver.findElement(By.xpath("//span[contains(text(),'Draggable 1')]"));
        WebElement dropArea = driver.findElement(By.id("mydropzone"));

        // Kéo và thả phần tử
        Actions actions = new Actions(driver);
        actions.dragAndDrop(itemToDrag, dropArea).perform();

        // Xác minh rằng phần tử đã được thả vào khu vực thả
        List<WebElement> droppedItems = driver.findElements(By.xpath("//div[@id='droppedlist']//span[contains(text(),'Draggable 1')]"));
        assertTrue(droppedItems.size() > 0, "Phần tử không được thả vào khu vực thả.");
    }

    @Test
    public void testDragAndDropMultipleItems() {
        WebElement itemToDrag1 = driver.findElement(By.xpath("//span[contains(text(),'Draggable 1')]"));
        WebElement itemToDrag2 = driver.findElement(By.xpath("//span[contains(text(),'Draggable 2')]"));
        WebElement dropArea = driver.findElement(By.id("mydropzone"));

        // Kéo và thả các phần tử
        Actions actions = new Actions(driver);
        actions.dragAndDrop(itemToDrag1, dropArea).perform();
        actions.dragAndDrop(itemToDrag2, dropArea).perform();

        // Xác minh rằng các phần tử đã được thả vào khu vực thả
        List<WebElement> droppedItems = driver.findElements(By.xpath("//div[@id='droppedlist']//span[contains(text(),'Draggable')]"));
        assertTrue(droppedItems.size() > 1, "Không phải tất cả các phần tử được thả vào khu vực thả.");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
