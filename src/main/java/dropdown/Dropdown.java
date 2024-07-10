package dropdown;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//nhấp vào Private FTP
public class Dropdown {
    public void checkDropdown() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/dropdown.html");
        String dropdownXpath = "//div[@class='simple example']//div[@class='ui dropdown']";
        String menuXpath = "//div[@class='menu transition visible']";
        String publishToWebXpath = "//div[@class='item']//i[@class='dropdown icon']/..";
        String fTPXpath = "//div[@class='menu transition visible']//div[text()='Private FTP']";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdownXpath)));
            dropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuXpath)));
            Actions actions = new Actions(driver);
            WebElement publishToWeb = driver.findElement(By.xpath(publishToWebXpath));
            actions.moveToElement(publishToWeb).perform();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fTPXpath)));

            WebElement privateFTP = driver.findElement(By.xpath(fTPXpath));
            actions.moveToElement(privateFTP).click().perform();

            String selectedText = dropdown.findElement(By.className("text")).getText();
            if (selectedText.contains("Private FTP")) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();


        }
    }
}
