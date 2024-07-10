package popup;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/*
- Kiểm tra xem popup có xuất hiện khi hover hoặc click vào một phần tử không.
- Kiểm tra nội dung của popup.
- Kiểm tra vị trí của popup so với phần tử kích hoạt.

 */
public class PopupTest {
    public void checkPopup() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/popup.html");
        String popup1TriggerXpath = "//div[@class='example'][1]//div[@class='ui icon button']";
        String popup2TriggerXpath = "//div[@class='example'][2]//img[@src='/images/avatar/small/elliot.jpg']";
        String popup3TriggerXpath = "//div[@class='example'][3]//div[@class='ui card']";
        String popup1Xpath = "//div[@data-content='Add users to your feed']";
        String popup2Xpath = "//img[@src='/images/avatar/small/elliot.jpg']";
        String popup3Xpath = "//div[@data-html=\"<div class='header'>User Rating</div><div class='content'><div class='ui star rating'><i class='active icon'></i><i class='active icon'></i><i class='active icon'></i><i class='active icon'></i><i class='active icon'></i></div></div>\"]";
        try {
            Actions actions = new Actions(driver);

            // Phần tử popup đầu tiên: "Add users to your feed"
            WebElement popup1Trigger = driver.findElement(By.xpath(popup1TriggerXpath));
            actions.moveToElement(popup1Trigger).perform();
            verifyPopup(driver, popup1Xpath, "data-content");

            // Phần tử popup thứ hai: images with data-title and data-content
            List<WebElement> popup2Triggers = driver.findElements(By.xpath(popup2TriggerXpath));
            for (WebElement trigger : popup2Triggers) {
                actions.moveToElement(trigger).perform();
                verifyPopup(driver, popup2Xpath, "data-content");
            }

            // Phần tử popup thứ ba: "User Rating" trong thẻ div
            WebElement popup3Trigger = driver.findElement(By.xpath(popup3TriggerXpath));
            actions.moveToElement(popup3Trigger).perform();
            verifyPopup(driver, popup3Xpath,"data-html");

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }

    }

    private static void verifyPopup(WebDriver driver, String popupXPath, String datacontent) {
        // Kiểm tra xem popup có xuất hiện không
        WebElement popup = driver.findElement(By.xpath(popupXPath));
        boolean isPopupDisplayed = popup.isDisplayed();
        System.out.println("Popup is displayed: " + isPopupDisplayed);

        // Kiểm tra nội dung của popup
        String popupText = popup.getAttribute(datacontent).trim();
        System.out.println("Popup content: " + popupText);

        // Kiểm tra vị trí của popup
        int popupX = popup.getLocation().getX();
        int popupY = popup.getLocation().getY();
        System.out.println("Popup position: (" + popupX + ", " + popupY + ")");

    }
}
