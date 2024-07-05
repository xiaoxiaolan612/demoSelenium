package dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
/*
1. Hãy chọn 4 lựa chọn Ember, JavaScript, Meteor, Kitchen Repair và in ra 4 lựa chọn này
2. Xoá 2 lựa chọn và in ra 2 lựa chọn còn lại
 */
public class MultipleSelection {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://semantic-ui.com/modules/dropdown.html");

        String dropdownXpath = "//div[@class='ui fluid dropdown selection multiple']";
        String dropdownAfterXpath = "//div[@class='ui fluid dropdown selection multiple active visible']";
        String optionxpath = "//div[@class='menu transition visible']//div[@class='item' and text()='";
        String selectedOptionXpath = "//a[@class='ui label transition visible']";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
            dropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownAfterXpath)));
            // Chọn các lựa chọn Ember, JavaScript, Meteor, Kitchen Repair
            String[] skills = {"Ember", "JavaScript", "Meteor", "Kitchen Repair"};
            for (String skill : skills) {
                WebElement option = driver.findElement(By.xpath( optionxpath + skill + "']"));
                option.click();
            }
            Thread.sleep(2000);

            // In ra các lựa chọn đã chọn
            List<WebElement> selectedOptions = driver.findElements(By.xpath(selectedOptionXpath));
            System.out.println("Selected skills:");
            for (WebElement selectedOption : selectedOptions) {
                System.out.println(selectedOption.getText());
            }

            // Xoá 2 lựa chọn
            String[] skillsToRemove = {"Ember", "JavaScript"};
            for (String skill : skillsToRemove) {
                WebElement removeIcon = driver.findElement(By.xpath("//a[text()='" + skill + "']/i"));
                removeIcon.click();
            }
            Thread.sleep(2000);

            // In ra các lựa chọn còn lại
            selectedOptions = driver.findElements(By.xpath(selectedOptionXpath));
            System.out.println("Remaining skills:");
            for (WebElement selectedOption : selectedOptions) {
                System.out.println(selectedOption.getText());
            }

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
