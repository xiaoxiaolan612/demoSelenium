package seleniumeasy.other;

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

public class DynamicDataLoadingTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
    }

    @Test
    public void testLoadNewUser() {
        WebElement getNewUserButton = driver.findElement(By.id("save"));
        getNewUserButton.click();
        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));

        // Xác minh rằng thông tin người dùng mới đã được hiển thị
        assertTrue(userInfo.isDisplayed(), "Thông tin người dùng mới không được tải.");
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
