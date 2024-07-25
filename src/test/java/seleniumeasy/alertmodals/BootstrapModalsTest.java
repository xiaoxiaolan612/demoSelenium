package seleniumeasy.alertmodals;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;

public class BootstrapModalsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/bootstrap-modal-demo.html");
    }
    @Test
    public void testSingleModal() {
        WebElement button = driver.findElement(By.xpath("//div[@class='panel-body']/a[@href='#myModal0']"));
        button.click();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='myModal0'])[1]")));
        String display = modal.getAttribute("class");
        System.out.println(display);
        assertTrue(display.equals("modal fade in"), "Modal nên có class in khi hiển thị");
//         Nhấp vào nút "Save changes" trong modal
        WebElement modalContent = driver.findElement(By.xpath("(//div[@class='modal-content'])[1]"));
        WebElement saveButton = modalContent.findElement(By.xpath("//div[@class='modal-footer']/a[@class='btn btn-primary']"));
        saveButton.click();

        // Xác minh rằng modal đã đóng
        WebElement modalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='myModal0'])[1]")));
        String display2 = modalAfter.getAttribute("class");
        assertTrue(display2.equals("modal fade"), "Modal nên đóng lại sau khi nhấp 'Save changes'");
    }

    @Test
    public void testMultipleModals() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath("//a[@href='#myModal']"));
        button.click();

        // Xác minh rằng modal đầu tiên được hiển thị
        WebElement firstModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
        String display = firstModal.getAttribute("class");
        System.out.println(display);
        assertTrue(display.equals("modal fade in"), "Modal nên có class in khi hiển thị");

        // Nhấp vào nút "Launch modal" trong modal đầu tiên
        WebElement launchSecondModalButton = firstModal.findElement(By.xpath("//div[@class='modal-body']/a[@href='#myModal2']"));
        launchSecondModalButton.click();

        // Xác minh rằng modal thứ hai được hiển thị
        WebElement secondModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal2")));
        String display2 = secondModal.getAttribute("class");
        System.out.println(display2);
        assertTrue(display2.equals("modal fade rotate in"), "Modal 2 nên có class in khi hiển thị");

        // Nhấp vào nút "Save changes" trong modal thứ hai
        WebElement saveSecondButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='btn btn-primary'][normalize-space()='Save changes'])[3]")));
        saveSecondButton.click();

        // Xác minh rằng modal thứ hai đã đóng
        WebElement secondModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal2")));
        String display2After = secondModalAfter.getAttribute("class");
        assertTrue(display2After.equals("modal fade rotate"), "Modal thứ hai nên đóng lại sau khi nhấp 'Save changes'");
        // Xác minh rằng modal đầu tiên đã đóng
        WebElement firstModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
        String displayAfter = firstModalAfter.getAttribute("class");
        assertTrue(displayAfter.equals("modal fade"), "Modal đầu tiên nên đóng lại sau khi nhấp 'Save changes'");
    }

    @Test
    public void testCloseSingleModal() throws InterruptedException {
        WebElement button = driver.findElement(By.xpath("//div[@class='panel-body']/a[@href='#myModal0']"));
        button.click();

        // Xác minh rằng modal được hiển thị
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal0")));
        String display = modal.getAttribute("class");
        System.out.println(display);
        assertTrue(display.equals("modal fade in"), "Modal nên có class in khi hiển thị");

        // Nhấp vào nút "Close" trong modal
        WebElement closeButton = modal.findElement(By.xpath("//div[@class='modal-footer']/a[@class='btn']"));
        closeButton.click();
        WebElement modalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal0")));
        String display2 = modalAfter.getAttribute("class");
        // Xác minh rằng modal đã đóng
        assertTrue(display2.equals("modal fade"), "Modal nên đóng lại sau khi nhấp 'Close'");
    }

    @Test
    public void testCloseMultipleModals() {
        WebElement button = driver.findElement(By.xpath("//div[@class='panel-body']/a[@href='#myModal']"));
        button.click();

        // Xác minh rằng modal đầu tiên được hiển thị
        WebElement firstModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
        String display = firstModal.getAttribute("class");
        System.out.println(display);
        assertTrue(display.equals("modal fade in"), "Modal nên có class in khi hiển thị");

        // Nhấp vào nút "Launch modal" trong modal đầu tiên
        WebElement launchSecondModalButton = firstModal.findElement(By.xpath("//div[@class='modal-body']/a[@href='#myModal2']"));
        launchSecondModalButton.click();

        // Xác minh rằng modal thứ hai được hiển thị
        WebElement secondModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal2")));
        String display2 = secondModal.getAttribute("class");
        System.out.println(display2);
        assertTrue(display2.equals("modal fade rotate in"), "Modal 2 nên có class in khi hiển thị");

        // Nhấp vào nút "Close" trong modal thứ hai
        WebElement closeSecondButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='btn'][normalize-space()='Close'])[3]")));
        closeSecondButton.click();

        // Xác minh rằng modal thứ hai đã đóng
        WebElement secondModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal2")));
        String display2After = secondModalAfter.getAttribute("class");
        assertTrue(display2After.equals("modal fade rotate"), "Modal thứ hai nên đóng lại sau khi nhấp 'Close'");

        // Xác minh rằng modal đầu tiên vẫn hiển thị
        assertTrue(display.equals("modal fade in"), "Modal đầu tiên vẫn nên hiển thị sau khi modal thứ hai đóng");

        // Nhấp vào nút "Close" trong modal đầu tiên
        WebElement closeFirstButton = firstModal.findElement(By.xpath("(//a[@class='btn'][normalize-space()='Close'])[2]"));
        closeFirstButton.click();

        // Xác minh rằng modal đầu tiên đã đóng
        WebElement firstModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
        String displayAfter = firstModalAfter.getAttribute("class");
        assertTrue(displayAfter.equals("modal fade"), "Modal đầu tiên nên đóng lại sau khi nhấp 'Close'");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
