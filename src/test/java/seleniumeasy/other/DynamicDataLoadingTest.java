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

    // XPaths and IDs
    private static final String USER_INFO_XPATH = "//div[@id='loading']/img";
    private static final String GET_NEW_USER_BUTTON_ID = "save";

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
    }

    @Test
    public void testLoadNewUser() {
        WebElement getNewUserButton = driver.findElement(By.id(GET_NEW_USER_BUTTON_ID));
        getNewUserButton.click();

        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(USER_INFO_XPATH)));

        // Xác minh rằng thông tin người dùng mới đã được hiển thị
        assertTrue(userInfo.isDisplayed(), "New user information is not displayed.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
