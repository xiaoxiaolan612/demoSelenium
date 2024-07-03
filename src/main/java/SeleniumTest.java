import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class SeleniumTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testSuccessfulLogin() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
        boolean loginSuccess = driver.getCurrentUrl().contains("/dashboard/index");
        System.out.println("Login thành công: " + loginSuccess);
        assertTrue("Login không thành công", loginSuccess);
    }

    @Test
    public void testMissingUsername() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        passwordField.sendKeys("admin123");
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and text()='Required']")));
        System.out.println("Thông báo lỗi có hiện: " + (errorMessage != null && errorMessage.isDisplayed()));
        assertTrue("Thông báo lỗi không hiện", errorMessage.isDisplayed());
    }

    @Test
    public void testMissingPassword() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("Admin");
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and text()='Required']")));
        System.out.println("Thông báo lỗi có hiện: " + (errorMessage != null && errorMessage.isDisplayed()));
        assertTrue("Thông báo lỗi không hiện", errorMessage.isDisplayed());
    }

    @Test
    public void testMissingUsernameAndPassword() {
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        loginButton.click();

        WebElement usernameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and text()='Required']")));
        System.out.println("Thông báo lỗi tên người dùng: " + (usernameErrorMessage != null && usernameErrorMessage.isDisplayed()));
        assertTrue("Thông báo lỗi tên người dùng", usernameErrorMessage.isDisplayed());

        WebElement passwordErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and text()='Required']")));
        System.out.println("Thông báo lỗi mật khẩu: " + (passwordErrorMessage != null && passwordErrorMessage.isDisplayed()));
        assertTrue("Thông báo lỗi mật khẩu", passwordErrorMessage.isDisplayed());
    }

    @Test
    public void testFailedLogin() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys("abc");
        passwordField.sendKeys("xyz");
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")));
        System.out.println("Thông báo lỗi có hiện: " + (errorMessage != null && errorMessage.isDisplayed()));
        assertTrue("Thông báo lỗi không hiện", errorMessage.isDisplayed());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
