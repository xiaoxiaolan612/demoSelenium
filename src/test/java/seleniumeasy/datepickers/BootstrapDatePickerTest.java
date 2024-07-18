package seleniumeasy.datepickers;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BootstrapDatePickerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/bootstrap-date-picker-demo.html");
    }

    @Test
    public void testDateExample() {

        WebElement dateInput = driver.findElement(By.xpath("//input[@placeholder='dd/mm/yyyy']"));
        dateInput.click();

        // Chờ cho date picker xuất hiện
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("datepicker-days")));

        // Kiểm tra rằng các ngày trong tương lai disable
        List<WebElement> futureDates = driver.findElements(By.xpath("//td[@class='day' and @class='new' or @class='old']"));
        for (WebElement futureDate : futureDates) {
            assertTrue(futureDate.getAttribute("class").contains("disabled"));
        }

        LocalDate currentDate = LocalDate.now();

        // Kiểm tra rằng các ngày Chủ Nhật từ ngày hiện tại trở đi disable
        List<WebElement> sundays = driver.findElements(By.xpath("//td[@class='day' and contains(@class, 'disabled') and (position() mod 7 = 1)]"));
        for (WebElement sunday : sundays) {
            // Lấy ngày của element
            String dayText = sunday.getText();
            int day = Integer.parseInt(dayText);

            // Kiểm tra xem ngày này có phải là Chủ Nhật từ ngày hiện tại trở đi không
            if (day >= currentDate.getDayOfMonth()) {
                assertTrue("Sunday should be disabled from today onwards", sunday.getAttribute("class").contains("disabled"));
            }
        }

        // Kiểm tra rằng tuần bắt đầu từ thứ Hai
        WebElement firstDayOfWeek = driver.findElement(By.xpath("//th[@class='dow' and position()=1]"));
        assertEquals("Mo", firstDayOfWeek.getText());

        // Click vào nút "Today" để chọn ngày hiện tại
        WebElement todayButton = driver.findElement(By.xpath("//th[contains(text(),'Today')]"));
        todayButton.click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedCurrentDate = currentDate.format(formatter);
        String selectedDate = dateInput.getAttribute("value");
        assertEquals(formattedCurrentDate, selectedDate);

        //Click nút clear
        dateInput.click();
        WebElement clearButton = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='clear'][normalize-space()='Clear']"));
        clearButton.click();

        // Xác minh rằng trường nhập ngày đã được xóa
        selectedDate = dateInput.getAttribute("value");
        assertEquals("", selectedDate);
    }
    @Test
    public void testDateRangeExample() {
        // Tìm và click vào trường nhập ngày bắt đầu cho Date Range Example
        WebElement startDateInput = driver.findElement(By.xpath("//input[@placeholder='Start date']"));
        startDateInput.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("datepicker-days")));

        // Kiểm tra rằng tuần bắt đầu từ Chủ Nhật
        WebElement firstDayOfWeek = driver.findElement(By.xpath("//th[@class='dow disabled']"));
        assertEquals("Su", firstDayOfWeek.getText());

        // Kiểm tra rằng Chủ Nhật disable
        List<WebElement> sundays = driver.findElements(By.xpath("//td[@class='day' and (position() mod 7 = 1)]"));
        for (WebElement sunday : sundays) {
            assertTrue(sunday.getAttribute("class").contains("disabled"));
        }

        // Chọn một ngày bắt đầu (ví dụ: ngày 10 của tháng hiện tại)
        WebElement startDayToSelect = driver.findElement(By.xpath("//td[@class='day' and text()='10']"));
        startDayToSelect.click();

        // Xác minh rằng ngày bắt đầu đã được chọn chính xác
        String selectedStartDate = startDateInput.getAttribute("value");
        assertEquals("10/07/2024", selectedStartDate);  // Đảm bảo rằng định dạng ngày phù hợp với định dạng trang web

        // Tìm và click vào trường nhập ngày kết thúc cho Date Range Example
        WebElement endDateInput = driver.findElement(By.xpath("//input[@placeholder='End date']"));
        endDateInput.click();

        // Chọn một ngày kết thúc (ví dụ: ngày 20 của tháng hiện tại)
        WebElement endDayToSelect = driver.findElement(By.xpath("//td[@class='day' and text()='20']"));
        endDayToSelect.click();

        // Xác minh rằng ngày kết thúc đã được chọn chính xác
        String selectedEndDate = endDateInput.getAttribute("value");
        assertEquals("20/07/2024", selectedEndDate);  // Đảm bảo rằng định dạng ngày phù hợp với định dạng trang web

        // Kiểm tra rằng ngày bắt đầu nhỏ hơn ngày kết thúc
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(selectedStartDate, formatter);
        LocalDate endDate = LocalDate.parse(selectedEndDate, formatter);
        assertTrue(startDate.isBefore(endDate));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
