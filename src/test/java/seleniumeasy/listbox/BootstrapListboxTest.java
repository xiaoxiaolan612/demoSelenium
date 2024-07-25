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

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/bootstrap-dual-list-box-demo.html");
    }
    @Test
    public void testMoveSingleItemToRight() {
        // Chọn một mục trong danh sách bên trái
        WebElement leftItem = driver.findElement(By.xpath("//div[@class='dual-list list-left col-md-5']//li[contains(text(),'bootstrap-duallist')]"));
        leftItem.click();

        // Nhấp vào nút ">"
        WebElement moveRightButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-right']"));
        moveRightButton.click();

        // Xác minh rằng mục đã được chuyển sang danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//div[@class='dual-list list-right col-md-5']//li[contains(text(),'bootstrap-duallist')]"));
        assertTrue(rightItems.size() > 0, "Mục không được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveMultipleItemsToRight() {
        // Chọn nhiều mục trong danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//div[@class='dual-list list-left col-md-5']//li"));
        leftItems.get(0).click();
        leftItems.get(1).click();

        // Nhấp vào nút ">"
        WebElement moveRightButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-right']"));
        moveRightButton.click();

        // Xác minh rằng các mục đã được chuyển sang danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//div[@class='dual-list list-right col-md-5']//li"));
        assertTrue(rightItems.size() > 1, "Các mục không được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveAllItemsToRight() {
        WebElement clickAllButton = driver.findElement(By.xpath("//div[@class='well text-right']//a[@title='select all']"));
        clickAllButton.click();
        // Nhấp vào nút ">"
        WebElement moveAllRightButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-right']"));
        moveAllRightButton.click();

        // Xác minh rằng tất cả các mục đã được chuyển sang danh sách bên phải
        List<WebElement> leftItems = driver.findElements(By.xpath("//div[@class='dual-list list-left col-md-5']//li"));
        assertTrue(leftItems.size() == 0, "Không phải tất cả các mục đã được chuyển sang danh sách bên phải.");
    }

    @Test
    public void testMoveSingleItemToLeft() {
        // Chọn một mục trong danh sách bên phải
        WebElement rightItem = driver.findElement(By.xpath("(//li[normalize-space()='Cras justo odio'])[1]"));
        rightItem.click();

        // Nhấp vào nút "<"
        WebElement moveLeftButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-left']"));
        moveLeftButton.click();

        // Xác minh rằng mục đã được chuyển sang danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//div[@class='dual-list list-left col-md-5']//li[contains(text(),'Cras justo odio')]"));
        assertTrue(leftItems.size() > 0, "Mục không được chuyển sang danh sách bên trái.");
    }

    @Test
    public void testMoveMultipleItemsToLeft() {
        // Chọn nhiều mục trong danh sách bên phải
        List<WebElement> rightItems = driver.findElements(By.xpath("//div[@class='dual-list list-right col-md-5']//li"));
        rightItems.get(0).click();
        rightItems.get(1).click();

        // Nhấp vào nút "<"
        WebElement moveLeftButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-left']"));
        moveLeftButton.click();

        // Xác minh rằng các mục đã được chuyển sang danh sách bên trái
        List<WebElement> leftItems = driver.findElements(By.xpath("//div[@class='dual-list list-left col-md-5']//li"));
        assertTrue(leftItems.size() > 1, "Các mục không được chuyển sang danh sách bên trái.");
    }

    @Test
    public void testMoveAllItemsToLeft() {

        WebElement clickAllButton = driver.findElement(By.xpath("//div[@class='well']//a[@title='select all']"));
        clickAllButton.click();
        // Nhấp vào nút "<<"
        WebElement moveAllLeftButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm move-left']"));
        moveAllLeftButton.click();

        // Xác minh rằng tất cả các mục đã được chuyển sang danh sách bên trái
        List<WebElement> rightItems = driver.findElements(By.xpath("//div[@class='dual-list list-right col-md-5']//li"));
        assertTrue(rightItems.size() == 0, "Không phải tất cả các mục đã được chuyển sang danh sách bên trái.");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
