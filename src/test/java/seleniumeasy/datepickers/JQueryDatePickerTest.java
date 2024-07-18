package seleniumeasy.datepickers;

import driver.DriverSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JQueryDatePickerTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("https://demo.seleniumeasy.com/jquery-date-picker-demo.html");
    }

    @Test
    public void testDatePicker() {
        // Tìm và click vào trường nhập ngày bắt đầu
        WebElement startDateField = driver.findElement(By.xpath("//input[@id='from']"));
        startDateField.click();

        // Chọn ngày bắt đầu
        WebElement startDateToSelect = driver.findElement(By.xpath("//a[text()='10']"));
        startDateToSelect.click();

        // Tìm và click vào trường nhập ngày kết thúc
        WebElement endDateField = driver.findElement(By.xpath("//input[@id='to']"));
        endDateField.click();

        // Chọn ngày kết thúc
        WebElement endDateToSelect = driver.findElement(By.xpath("//a[text()='20']"));
        endDateToSelect.click();
        // Kiểm tra xem ngày bắt đầu nhỏ hơn ngày kết thúc
        String startDate = startDateField.getAttribute("value");
        String endDate = endDateField.getAttribute("value");

        assert startDate.compareTo(endDate) < 0 : "Ngày bắt đầu nhỏ hơn ngày kết thúc!";

        // Kiểm tra các ngày trước ngày bắt đầu disable trong lịch của ngày kết thúc
        endDateField.click();
        WebElement disabledStartDate = driver.findElement(By.xpath("(//td[contains(@class,'ui-state-disabled')])[10]"));
        WebElement disabledStartDate2 = driver.findElement(By.xpath("(//td[contains(@class,'ui-state-disabled')])[9]"));

        assert disabledStartDate != null &&disabledStartDate2 != null  : "Các ngày trước ngày bắt đầu disable trong ngày kết thúc!";

        // Kiểm tra các ngày sau ngày kết thúc disable trong ngày bắt đầu
        startDateField.click();
        WebElement disabledEndDate = driver.findElement(By.xpath("(//td[contains(@class,'ui-state-disabled')])[3]"));
        WebElement disabledEndDate2 = driver.findElement(By.xpath("(//td[contains(@class,'ui-state-disabled')])[1]"));
        assert disabledEndDate != null && disabledEndDate2 != null : "Các ngày sau ngày kết thúc phải disable trong ngày bắt đầu!";
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
