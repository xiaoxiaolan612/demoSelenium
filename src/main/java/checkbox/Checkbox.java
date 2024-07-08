package checkbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/*
Yêu cầu:
Kiểm tra checkbox đã được check hay chưa
Nếu chưa thì tick vào checkbox và ngược lại
 */
public class Checkbox {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://semantic-ui.com/modules/checkbox.html");
        String checkboxXpath = "//div[@class='ui checkbox']";
        try{
            WebElement checkboxDiv = driver.findElement(By.xpath(checkboxXpath));
            String checkboxClassS = checkboxDiv.getAttribute("class");
            if (!checkboxClassS.contains("checked")) {
                checkboxDiv.click();
                System.out.println("Checkbox đã được tick.");
            } else {
                checkboxDiv.click();
                System.out.println("Checkbox đã được bỏ tick.");
            }

            checkboxClassS = checkboxDiv.getAttribute("class");
            if (checkboxClassS.contains("checked")) {
                System.out.println("Checkbox hiện đang được tick.");
            } else {
                System.out.println("Checkbox hiện đang được bỏ tick.");
            }

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }

    }
}
