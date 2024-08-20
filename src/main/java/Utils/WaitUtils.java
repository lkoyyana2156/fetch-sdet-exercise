package Utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private final WebDriver driver;

    public WaitUtils(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Wait For Element to be visible on Web Page
     * @param locator = Locator of the element
     * @return WebElement to interact with
     */
    public WebElement waitForElement(By locator){
       return waitForElement(locator, 30);
    }

    /**
     * Wait For Element to be visible on page with custom timeout
     * @param locator = Locator of the web element
     * @param timeOutInSeconds = Time out for the method to wait until element is found
     * @return WebElement to interact with
     */
    public WebElement waitForElement(By locator, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Navigate to URL
     * @param url = URL of the web page you want your browser to navigate to
     */
    public void navigateToUrl(String url){
        driver.get(url);
    }

    /**
     * Wait For Elements to be visible on the web page
     * @param locator = Locator of the web elements
     * @return = List<WebElement> to interact with
     */
    public List<WebElement> waitForElements(By locator){
        return waitForElements(locator, 30);
    }

    /**
     * Wait for Elements to be visible on the web page with timeout
     * @param locator = Locator of the web elements
     * @param timeOutInSeconds = Time out for the method to wait before throwing error
     * @return = List<WebElement> to interact with
     */
    public List<WebElement> waitForElements(By locator, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for all the Elements to be visible on UI
     * @param locator = Locator of the Web Element
     * @param expectedCount = Expected count of elements
     * @param timeOutInSeconds = Time out for the method to wait before throwing error
     */
    public void waitForElementsCountGreaterThanExpectedCount(By locator, int expectedCount,  int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(driver -> driver.findElements(locator).size() >= expectedCount);
    }

    /**
     * Wait For Alert to be visible on UI
     * @param timeoutInSeconds = Timeout for the method to wait before throwing exception
     * @return Alert to interact with
     */
    public Alert waitForAlert(int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}