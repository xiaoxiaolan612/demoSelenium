package checkbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
Kiểm tra xem radio button có enable không.
Kiểm tra xem mục "Twice a day" đã được chọn hay chưa.
Nếu đã được chọn thì chọn mục "Once a week".
 */
public class Radio {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://semantic-ui.com/modules/checkbox.html");
        String twiceADayRadioXpath = "//div[@class='ui radio checkbox'][.//label[text()='Twice a day']]";
        String onceAWeekRadioXpath = "//div[@class='ui radio checkbox'][.//label[text()='Once a week']]";
        try{
            WebElement twiceADayRadio = driver.findElement(By.xpath(twiceADayRadioXpath));

            boolean isEnabled = twiceADayRadio.isEnabled();
            System.out.println("Radio button 'Twice a day' is enabled: " + isEnabled);

            boolean isChecked = twiceADayRadio.getAttribute("class").contains("checked");
            System.out.println("Radio button 'Twice a day' is checked: " + isChecked);

            if (!isChecked) {
                WebElement onceAWeekRadio = driver.findElement(By.xpath(onceAWeekRadioXpath));
                onceAWeekRadio.click();
                System.out.println("Radio button 'Once a week' is selected.");
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
