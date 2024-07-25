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

import java.util.Set;

import static org.testng.Assert.assertTrue;

public class WindowPopupModalTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Define XPaths as constants
    private static final String TWITTER_BUTTON_XPATH = "//a[contains(text(),'Follow On Twitter')]";
    private static final String FACEBOOK_BUTTON_XPATH = "//a[contains(text(),'Like us On Facebook')]";
    private static final String MULTIPLE_BUTTON_XPATH = "//a[contains(text(),'Follow Twitter & Facebook')]";
    private static final String FOLLOW_ALL_BUTTON_XPATH = "//a[contains(text(),'Follow All')]";

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/window-popup-modal-demo.html");
    }

    @Test
    public void testSingleWindowPopup() {
        // Nhấp vào nút "Follow On Twitter"
        WebElement twitterButton = driver.findElement(By.xpath(TWITTER_BUTTON_XPATH));
        twitterButton.click();

        // Chờ cửa sổ mới được mở
        String mainWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Chuyển sang cửa sổ mới
        switchToNewWindow(mainWindowHandle);

        // Kiểm tra tiêu đề của cửa sổ mới
        String expectedTitle = "X";
        assertTrue(driver.getTitle().contains(expectedTitle), "Tiêu đề cửa sổ nên chứa 'X'");

        // Đóng cửa sổ popup và chuyển về cửa sổ chính
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test
    public void testSingleWindowPopupLikeUsOnFacebook() {
        // Nhấp vào nút "Like us On Facebook"
        WebElement facebookButton = driver.findElement(By.xpath(FACEBOOK_BUTTON_XPATH));
        facebookButton.click();

        // Chờ cửa sổ mới được mở
        String mainWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Chuyển sang cửa sổ mới
        switchToNewWindow(mainWindowHandle);

        // Kiểm tra tiêu đề của cửa sổ mới
        String expectedTitle = "Facebook";
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().contains(expectedTitle), "Tiêu đề cửa sổ nên chứa 'Facebook'");

        // Đóng cửa sổ popup và chuyển về cửa sổ chính
        driver.close();
        driver.switchTo().window(mainWindowHandle);
    }

    @Test
    public void testMultipleWindowPopup() {
        // Nhấp vào nút "Follow Twitter & Facebook"
        WebElement multipleButton = driver.findElement(By.xpath(MULTIPLE_BUTTON_XPATH));
        multipleButton.click();

        // Chờ các cửa sổ mới được mở
        String mainWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));

        // Chuyển qua tất cả các cửa sổ và kiểm tra tiêu đề
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();
                assertTrue(title.contains("X") || title.contains("Facebook"), "Tiêu đề cửa sổ nên chứa 'X' hoặc 'Facebook'");
                driver.close();
            }
        }

        // Chuyển về cửa sổ chính
        driver.switchTo().window(mainWindowHandle);
    }

    @Test
    public void testSingleWindowPopupFollowAll() {
        // Nhấp vào nút "Follow All"
        WebElement followAllButton = driver.findElement(By.xpath(FOLLOW_ALL_BUTTON_XPATH));
        followAllButton.click();

        // Chờ các cửa sổ mới được mở
        String mainWindowHandle = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));

        // Chuyển qua tất cả các cửa sổ và kiểm tra tiêu đề
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();
                System.out.println(title);
                assertTrue(title.contains("X") || title.contains("Facebook") || title.contains("Google"), "Tiêu đề cửa sổ nên chứa 'X' hoặc 'Facebook' hoặc 'Google'");
                driver.close();
            }
        }

        // Chuyển về cửa sổ chính
        driver.switchTo().window(mainWindowHandle);
    }

    private void switchToNewWindow(String mainWindowHandle) {
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
