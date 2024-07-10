package dropdown;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//Chọn Elliot Fu
public class Selection {
    public void checkSelection() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/dropdown.html");
        String selectFriendXpath = "//div[@class='ui fluid selection dropdown']";
        String menuXpath = "//div[@class='menu transition visible']";
        String eliotXpath = "//div[@class='menu transition visible']//div[@data-value='elliot']";
        String hiddenXpath = "//input[@type='hidden'][@name='user'][@value='elliot']";
        try{
            WebDriverWait wait = new WebDriverWait(driver, 100);
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectFriendXpath)));
            dropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuXpath)));

            WebElement elliotFu = driver.findElement(By.xpath(eliotXpath));
            elliotFu.click();

            WebElement hiddenInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hiddenXpath)));
            String value = hiddenInput.getAttribute("value");
            if ("elliot".equals(value)) {
                System.out.println("Test Passed! Dropdown selection successful.");
            } else {
                System.out.println("Test Failed! Dropdown selection unsuccessful.");
            }
        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();


        }
    }
}
