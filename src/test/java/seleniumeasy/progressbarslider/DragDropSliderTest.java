package seleniumeasy.progressbarslider;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class DragDropSliderTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/drag-drop-range-sliders-demo.html");
    }
    @Test
    public void testHorizontalSlider() {
        try {
            WebElement slider = driver.findElement(By.xpath("//div[@class='range']//input[@type='range']"));
            WebElement output = driver.findElement(By.id("range"));
            System.out.println("Slider found: " + (slider != null));
            System.out.println("Output found: " + (output != null));
            String initialValue = output.getText();

            // Tính toán tọa độ cần kéo
            int sliderWidth = slider.getSize().width;
            int moveOffset = (sliderWidth * 40 / 100) - sliderWidth / 2; // Ví dụ: di chuyển đến 40% của thanh trượt

            // Xác định tọa độ bắt đầu và kết thúc
            int startX = slider.getLocation().getX() + sliderWidth / 2;
            int startY = slider.getLocation().getY();
            int endX = startX + moveOffset;

            // Kéo thanh trượt từ vị trí hiện tại đến vị trí mới
            actions.clickAndHold(slider)
                    .moveByOffset(moveOffset, 0)
                    .release()
                    .perform();
            // Chờ để giá trị cập nhật
            wait.until(ExpectedConditions.textToBePresentInElement(output, "40"));

            // Kiểm tra giá trị của thanh trượt sau khi di chuyển
            String newValue = output.getText();
            assertTrue(Integer.parseInt(newValue) >= 1 && Integer.parseInt(newValue) <= 100);
            assertTrue(!initialValue.equals(newValue)); // Đảm bảo giá trị đã thay đổi

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
