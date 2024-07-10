package dropdown;

import driver.DriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Đếm tổng số quốc gia trong dropdown.
Kiểm tra xem các quốc gia có xếp theo thứ tự bảng chữ cái không.
Tìm kiếm từ khóa "b" và đếm số kết quả.
Tìm "Vietnam".
 */
public class SearchSelection {
    public void checkSearchSelection() {
        WebDriver driver = DriverSetup.getDriver();
        driver.get("https://semantic-ui.com/modules/dropdown.html");
        String dropdownXpath = "//div[@class='ui fluid search selection dropdown']";
        String dropdownAfterXpath = "//div[@class='menu transition visible']";
        String countryXpath = "//div[@class='menu transition visible']//div[@class='item']";
        String searchInputXpath = ".//input[@class='search']";
        String searchResultXpath = "//div[@class='menu transition visible']//div[@class='item']";
        try{
            WebDriverWait wait = new WebDriverWait(driver, 1000);
            WebElement dropdown = driver.findElement(By.xpath(dropdownXpath));
            dropdown.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownAfterXpath)));
            List<WebElement> countries = driver.findElements(By.xpath(countryXpath));
            int totalCountries = countries.size();
            System.out.println("Total number of countries: " + totalCountries);

            List<String> countryNames = new ArrayList<>();
            for (WebElement country : countries) {
                countryNames.add(country.getText());
            }
            List<String> sortedCountryNames = new ArrayList<>(countryNames);
            Collections.sort(sortedCountryNames);
            boolean isAlphabetical = countryNames.equals(sortedCountryNames);
            System.out.println("Countries are sorted alphabetically: " + isAlphabetical);

            WebElement searchInput = dropdown.findElement(By.xpath(searchInputXpath));
            wait.until(ExpectedConditions.elementToBeClickable(searchInput));
            searchInput.clear();
            searchInput.sendKeys("b");
            Thread.sleep(1000);

            List<WebElement> searchResults = driver.findElements(By.xpath(searchResultXpath));
            int searchResultCount = searchResults.size();
            System.out.println("Number of countries after searching 'b': " + searchResultCount);

            boolean foundVietnam = false;
            for (WebElement country : countries) {
                if (country.getText().equalsIgnoreCase("Vietnam")) {
                    foundVietnam = true;
                    break;
                }
            }
            System.out.println("Vietnam is found in the list: " + foundVietnam);

        }catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
