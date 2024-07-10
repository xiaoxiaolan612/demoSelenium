package popup;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
- Click vào "Run Code" ở mục "Modal A standard modal", lấy header của popup,
click vào "Yep, that's me"
- Click vào "Run Code" ở mục "Basic A modal can reduce its complexity", lấy nội dung trong
thẻ ‘div’ với class "content", click vào "No"

 */
public class Modal {
    public void testModalStandard() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/modal.html");
        try {
            Actions actions = new Actions(driver);

            String runCodeButtonXpath = "(//a[@class='ui black pointing below ignored label'][normalize-space()='Run Code'])[1]";
            String modalHeaderXpath = "(//div[@class='ui header'][normalize-space()='Default Profile Image'])[2]";
            String yepButtonXpath = "//div[@class='ui positive right labeled icon button']";
            // Click vào "Run Code" ở mục "Modal A standard modal"
            WebElement runCodeButton = driver.findElement(By.xpath(runCodeButtonXpath));
            actions.moveToElement(runCodeButton).click().perform();

            // Lấy nội dung trong thẻ p của ui header
            WebElement modalHeader = driver.findElement(By.xpath(modalHeaderXpath));
            System.out.println("Header paragraph: " + modalHeader.getText());

            // Click vào nút "Yep, that's me"
            WebElement yepButton = driver.findElement(By.xpath(yepButtonXpath));
            actions.moveToElement(yepButton).click().perform();
            System.out.println("Test success");
        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }

    }

    public void testModalBasic() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/modal.html");
        try {
            Actions actions = new Actions(driver);

            String runCodeButtonXpath = "(//a[@class='ui black pointing below ignored label'][normalize-space()='Run Code'])[2]";
            String modalContentXpath = "(//p[contains(text(),'Your inbox is getting full, would you like us to e')])[2]";
            String noButtonXpath = "(//div[@class='ui red basic cancel inverted button'][normalize-space()='No'])[2]";
            // Click vào "Run Code" ở mục "Basic A modal can reduce its complexity"
            WebElement runCodeButton = driver.findElement(By.xpath(runCodeButtonXpath));
            actions.moveToElement(runCodeButton).click().perform();

            // Lấy nội dung trong thẻ div class="content"
            WebElement modalContent = driver.findElement(By.xpath(modalContentXpath));
            System.out.println("Content: " + modalContent.getText());

            // Click vào nút "No"
            WebElement noButton = driver.findElement(By.xpath(noButtonXpath));
            actions.moveToElement(noButton).click().perform();
            System.out.println("Test success!!");
        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}
