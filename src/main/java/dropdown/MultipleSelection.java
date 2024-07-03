package dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
        try{
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
            dropdown.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownAfterXpath)));

            Select select = new Select(dropdown.findElement(By.tagName("select")));
            selectOption(select, "Ember");
            selectOption(select, "JavaScript");
            selectOption(select, "Meteor");
            selectOption(select, "Kitchen Repair");

            printSelectedOptions(select);

            deselectOption(select, "Kitchen Repair");
            deselectOption(select, "Meteor");

            printSelectedOptions(select);


        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    private static void selectOption(Select select, String optionText) {
        select.selectByVisibleText(optionText);
    }

    private static void deselectOption(Select select, String optionText) {
        select.deselectByVisibleText(optionText);
    }

    private static void printSelectedOptions(Select select) {
        System.out.println("\nSelected options:");
        for (WebElement option : select.getAllSelectedOptions()) {
            System.out.println(option.getText().trim());
        }
    }
}
