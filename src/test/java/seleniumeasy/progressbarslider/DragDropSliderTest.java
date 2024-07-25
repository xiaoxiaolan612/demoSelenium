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

    // Constants for locators and parameters
    private static final String SLIDER_XPATH = "//div[@class='range']//input[@type='range']";
    private static final String OUTPUT_ID = "range";
    private static final int SLIDER_MOVE_PERCENT = 40;
    private static final int WAIT_TIMEOUT_SECONDS = 10;

    @Before
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        actions = new Actions(driver);
        driver.get("https://demo.seleniumeasy.com/drag-drop-range-sliders-demo.html");
    }

    @Test
    public void testHorizontalSlider() {
        try {
            WebElement slider = driver.findElement(By.xpath(SLIDER_XPATH));
            WebElement output = driver.findElement(By.id(OUTPUT_ID));
            String initialValue = output.getText();

            // Calculate move offset for the slider
            int sliderWidth = slider.getSize().width;
            int moveOffset = (sliderWidth * SLIDER_MOVE_PERCENT / 100) - sliderWidth / 2; // Move to 40% of the slider

            // Determine start and end coordinates
            int startX = slider.getLocation().getX() + sliderWidth / 2;
            int startY = slider.getLocation().getY();
            int endX = startX + moveOffset;

            // Drag the slider from the current position to the new position
            actions.clickAndHold(slider)
                    .moveByOffset(moveOffset, 0)
                    .release()
                    .perform();

            // Wait for the value to update
            wait.until(ExpectedConditions.textToBePresentInElement(output, String.valueOf(SLIDER_MOVE_PERCENT)));

            // Check the slider value after moving
            String newValue = output.getText();
            assertTrue(Integer.parseInt(newValue) >= 1 && Integer.parseInt(newValue) <= 100);
            assertTrue(!initialValue.equals(newValue)); // Ensure the value has changed

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
