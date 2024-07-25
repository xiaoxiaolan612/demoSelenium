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
//    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
//        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/jquery-dual-list-box-demo.html");
    }

    @Test
    public void testMoveSingleItemToRight() {
        // Chọn một mục trong danh sách bên trái
        WebElement leftItem = driver.findElement(By.xpath("//select[@class='form-control pickListSelect pickData']//option[contains(text(),'Isis')]"));
        leftItem.click();

        // Nhấp vào nút "Add"
        WebElement moveRightButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
        moveRightButton.click();

        // Xác minh rằng mục đã được chuyển sang danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickListResult']//option[contains(text(),'Isis')]"));
        assertTrue(rightItems.size() > 0, "Mục không được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveMultipleItemsToRight() {
        // Chọn nhiều mục trong danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickData']//option"));
        leftItems.get(0).click();
        leftItems.get(1).click();

        // Nhấp vào nút ">"
        WebElement moveRightButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
        moveRightButton.click();

        // Xác minh rằng các mục đã được chuyển sang danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickListResult']//option"));
        assertTrue(rightItems.size() > 1, "Các mục không được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveAllItemsToRight() {
        // Nhấp vào nút "Add all"
        WebElement moveAllRightButton = driver.findElement(By.xpath("//button[contains(text(), 'Add All')]"));
        moveAllRightButton.click();

        // Xác minh rằng tất cả các mục đã được chuyển sang danh sách bên phải
        List<WebElement> leftItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickData']//option"));
        assertTrue(leftItems.size() == 0, "Không phải tất cả các mục đã được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveSingleItemToLeft() {
        WebElement moveAllRightButton = driver.findElement(By.xpath("//button[contains(text(), 'Add All')]"));
        moveAllRightButton.click();
        // Chọn một mục trong danh sách bên phải
        WebElement rightItem = driver.findElement(By.xpath("//select[@class='form-control pickListSelect pickListResult']//option[contains(text(),'Isis')]"));
        rightItem.click();

        // Nhấp vào nút "<"
        WebElement moveLeftButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        moveLeftButton.click();

        // Xác minh rằng mục đã được chuyển sang danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickData']//option[contains(text(),'Isis')]"));
        assertTrue(leftItems.size() > 0, "Mục không được chuyển sang danh sách bên trái.");
    }

    @Test
    public void testMoveMultipleItemsToLeft() {
        WebElement moveAllRightButton = driver.findElement(By.xpath("//button[contains(text(), 'Add All')]"));
        moveAllRightButton.click();
        // Chọn nhiều mục trong danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickListResult']//option"));
        rightItems.get(0).click();
        rightItems.get(1).click();

        // Nhấp vào nút "<"
        WebElement moveLeftButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        moveLeftButton.click();

        // Xác minh rằng các mục đã được chuyển sang danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickData']//option"));
        assertTrue(leftItems.size() > 1, "Các mục không được chuyển sang danh sách bên trái.");
    }

    @Test
    public void testMoveAllItemsToLeft() {
        // Nhấp vào nút "<<"
        WebElement moveAllLeftButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove All')]"));
        moveAllLeftButton.click();

        // Xác minh rằng tất cả các mục đã được chuyển sang danh sách bên trái
        List<WebElement> rightItems = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickListResult']//option"));
        assertTrue(rightItems.size() == 0, "Không phải tất cả các mục đã được chuyển sang danh sách bên trái.");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
