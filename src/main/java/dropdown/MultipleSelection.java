package dropdown;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
1. Hãy chọn 4 lựa chọn Ember, JavaScript, Meteor, Kitchen Repair và in ra 4 lựa chọn này
2. Xoá 2 lựa chọn và in ra 2 lựa chọn còn lại
 */
public class MultipleSelection {
    public void checkMultipleSelenium() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/dropdown.html");
        String dropdownXpath = "//div[@class='ui fluid dropdown selection multiple']";
        String dropdownAfterXpath = "//div[@class='ui fluid dropdown selection multiple active visible']";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
            dropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownAfterXpath)));

            String emberXpath = "(//div[@class='item'][normalize-space()='Ember'])[1]";
            String jsXpath = "(//div[@class='item'][normalize-space()='Javascript'])[1]";
            String meXpath = "(//div[@class='item'][normalize-space()='Meteor'])[1]";
            String krXpath = "(//div[@class='item'][normalize-space()='Kitchen Repair'])[1]";
            // Chọn các lựa chọn Ember, JavaScript, Meteor, Kitchen Repair
            selectDropdownOption(driver, emberXpath);
            WebElement ember = driver.findElement(By.cssSelector("a[data-value='ember']"));
            System.out.print(" lựa chọn đã chọn:" + ember.getAttribute("data-value").trim());
            selectDropdownOption(driver, jsXpath);
            WebElement js = driver.findElement(By.cssSelector("a[data-value='javascript']"));
            System.out.print(" " + js.getAttribute("data-value").trim());
            selectDropdownOption(driver, meXpath);
            WebElement me = driver.findElement(By.cssSelector("a[data-value='meteor']"));
            System.out.print(" " + me.getAttribute("data-value").trim());
            selectDropdownOption(driver, krXpath);
            WebElement repair = driver.findElement(By.cssSelector("a[data-value='repair']"));
            System.out.print(" " + repair.getAttribute("data-value").trim());
            System.out.println();


            // Xoá 2 lựa chọn
            String deleEmberCSS = "a[data-value='ember'] i[class='delete icon']";
            String deleJsCSS = "a[data-value='javascript'] i[class='delete icon']";
            deselectDropdownOption(driver, deleEmberCSS);
            deselectDropdownOption(driver, deleJsCSS);

            System.out.print("lựa chọn còn lại: " + me.getAttribute("data-value").trim());
            System.out.print(" " + repair.getAttribute("data-value").trim());

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
    private static void selectDropdownOption(WebDriver driver, String optionXpath) {

        WebElement option = driver.findElement(By.xpath(optionXpath));
        option.click();
    }

    private static void deselectDropdownOption(WebDriver driver, String optionCSS) {
        WebElement selectedOption = driver.findElement(By.cssSelector(optionCSS));
        selectedOption.click();
    }
}
