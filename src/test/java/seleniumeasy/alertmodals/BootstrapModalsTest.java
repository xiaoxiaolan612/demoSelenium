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

    // Define constants for locators and class names
    private static final String BASE_URL = "https://demo.seleniumeasy.com/bootstrap-modal-demo.html";
    private static final String FIRST_MODAL_BUTTON_XPATH = "//div[@class='panel-body']/a[@href='#myModal0']";
    private static final String FIRST_MODAL_ID = "myModal0";
    private static final String FIRST_MODAL_CLASS_VISIBLE = "modal fade in";
    private static final String FIRST_MODAL_CLASS_HIDDEN = "modal fade";
    private static final String SECOND_MODAL_BUTTON_XPATH = "//a[@href='#myModal']";
    private static final String SECOND_MODAL_ID = "myModal";
    private static final String SECOND_MODAL_CLASS_VISIBLE = "modal fade in";
    private static final String SECOND_MODAL_CLASS_HIDDEN = "modal fade";
    private static final String SECOND_MODAL_LAUNCH_BUTTON_XPATH = "//div[@class='modal-body']/a[@href='#myModal2']";
    private static final String SECOND_MODAL_SECOND_ID = "myModal2";
    private static final String SECOND_MODAL_SECOND_CLASS_VISIBLE = "modal fade rotate in";
    private static final String SECOND_MODAL_SECOND_CLASS_HIDDEN = "modal fade rotate";
    private static final String SAVE_BUTTON_XPATH = "//div[@class='modal-footer']/a[@class='btn btn-primary']";
    private static final String CLOSE_BUTTON_XPATH = "//div[@class='modal-footer']/a[@class='btn']";
    private static final String CLOSE_SECOND_BUTTON_XPATH = "(//a[@class='btn'][normalize-space()='Close'])[3]";
    private static final String CLOSE_FIRST_BUTTON_XPATH = "(//a[@class='btn'][normalize-space()='Close'])[2]";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
    }

    @Test
    public void testSingleModal() {
        WebElement button = driver.findElement(By.xpath(FIRST_MODAL_BUTTON_XPATH));
        button.click();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRST_MODAL_ID)));
        String display = modal.getAttribute("class");
        assertTrue(display.equals(FIRST_MODAL_CLASS_VISIBLE), "Modal nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Save changes" trong modal
        WebElement saveButton = modal.findElement(By.xpath(SAVE_BUTTON_XPATH));
        saveButton.click();

        // Xác minh rằng modal đã đóng
        WebElement modalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRST_MODAL_ID)));
        String displayAfter = modalAfter.getAttribute("class");
        assertTrue(displayAfter.equals(FIRST_MODAL_CLASS_HIDDEN), "Modal nên đóng lại sau khi nhấp 'Save changes'");
    }

    @Test
    public void testMultipleModals() {
        WebElement button = driver.findElement(By.xpath(SECOND_MODAL_BUTTON_XPATH));
        button.click();

        // Xác minh rằng modal đầu tiên được hiển thị
        WebElement firstModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_ID)));
        String display = firstModal.getAttribute("class");
        assertTrue(display.equals(SECOND_MODAL_CLASS_VISIBLE), "Modal đầu tiên nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Launch modal" trong modal đầu tiên
        WebElement launchSecondModalButton = firstModal.findElement(By.xpath(SECOND_MODAL_LAUNCH_BUTTON_XPATH));
        launchSecondModalButton.click();

        // Xác minh rằng modal thứ hai được hiển thị
        WebElement secondModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_SECOND_ID)));
        String displaySecond = secondModal.getAttribute("class");
        assertTrue(displaySecond.equals(SECOND_MODAL_SECOND_CLASS_VISIBLE), "Modal thứ hai nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Save changes" trong modal thứ hai
        WebElement saveSecondButton = secondModal.findElement(By.xpath(SAVE_BUTTON_XPATH));
        saveSecondButton.click();

        // Xác minh rằng modal thứ hai đã đóng
        WebElement secondModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_SECOND_ID)));
        String displaySecondAfter = secondModalAfter.getAttribute("class");
        assertTrue(displaySecondAfter.equals(SECOND_MODAL_SECOND_CLASS_HIDDEN), "Modal thứ hai nên đóng lại sau khi nhấp 'Save changes'");

        // Xác minh rằng modal đầu tiên đã đóng
        WebElement firstModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_ID)));
        String displayFirstAfter = firstModalAfter.getAttribute("class");
        assertTrue(displayFirstAfter.equals(SECOND_MODAL_CLASS_HIDDEN), "Modal đầu tiên nên đóng lại sau khi nhấp 'Save changes'");
    }

    @Test
    public void testCloseSingleModal() {
        WebElement button = driver.findElement(By.xpath(FIRST_MODAL_BUTTON_XPATH));
        button.click();

        // Xác minh rằng modal được hiển thị
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRST_MODAL_ID)));
        String display = modal.getAttribute("class");
        assertTrue(display.equals(FIRST_MODAL_CLASS_VISIBLE), "Modal nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Close" trong modal
        WebElement closeButton = modal.findElement(By.xpath(CLOSE_BUTTON_XPATH));
        closeButton.click();

        WebElement modalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FIRST_MODAL_ID)));
        String displayAfter = modalAfter.getAttribute("class");
        // Xác minh rằng modal đã đóng
        assertTrue(displayAfter.equals(FIRST_MODAL_CLASS_HIDDEN), "Modal nên đóng lại sau khi nhấp 'Close'");
    }

    @Test
    public void testCloseMultipleModals() {
        WebElement button = driver.findElement(By.xpath(SECOND_MODAL_BUTTON_XPATH));
        button.click();

        // Xác minh rằng modal đầu tiên được hiển thị
        WebElement firstModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_ID)));
        String display = firstModal.getAttribute("class");
        assertTrue(display.equals(SECOND_MODAL_CLASS_VISIBLE), "Modal đầu tiên nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Launch modal" trong modal đầu tiên
        WebElement launchSecondModalButton = firstModal.findElement(By.xpath(SECOND_MODAL_LAUNCH_BUTTON_XPATH));
        launchSecondModalButton.click();

        // Xác minh rằng modal thứ hai được hiển thị
        WebElement secondModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_SECOND_ID)));
        String displaySecond = secondModal.getAttribute("class");
        assertTrue(displaySecond.equals(SECOND_MODAL_SECOND_CLASS_VISIBLE), "Modal thứ hai nên có class 'in' khi hiển thị");

        // Nhấp vào nút "Close" trong modal thứ hai
        WebElement closeSecondButton = secondModal.findElement(By.xpath(CLOSE_SECOND_BUTTON_XPATH));
        closeSecondButton.click();

        // Xác minh rằng modal thứ hai đã đóng
        WebElement secondModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_SECOND_ID)));
        String displaySecondAfter = secondModalAfter.getAttribute("class");
        assertTrue(displaySecondAfter.equals(SECOND_MODAL_SECOND_CLASS_HIDDEN), "Modal thứ hai nên đóng lại sau khi nhấp 'Close'");

        // Xác minh rằng modal đầu tiên vẫn hiển thị
        assertTrue(display.equals(SECOND_MODAL_CLASS_VISIBLE), "Modal đầu tiên vẫn nên hiển thị sau khi modal thứ hai đóng");

        // Nhấp vào nút "Close" trong modal đầu tiên
        WebElement closeFirstButton = firstModal.findElement(By.xpath(CLOSE_FIRST_BUTTON_XPATH));
        closeFirstButton.click();

        // Xác minh rằng modal đầu tiên đã đóng
        WebElement firstModalAfter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SECOND_MODAL_ID)));
        String displayFirstAfter = firstModalAfter.getAttribute("class");
        assertTrue(displayFirstAfter.equals(SECOND_MODAL_CLASS_HIDDEN), "Modal đầu tiên nên đóng lại sau khi nhấp 'Close'");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
