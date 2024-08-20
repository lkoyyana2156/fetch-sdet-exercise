package tests;

import PageObject.WeighScalePage;
import Utils.WaitUtils;
import config.TestSettings;
import data.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseTest {
    protected static WebDriver driver;
    protected static WaitUtils waitUtils;
    protected static WeighScalePage weighScalePage;
    protected static String Url = TestSettings.getInstance().Url;

    @Before
    public void setUp() throws Exception {
        String browser = TestSettings.getInstance().Browser;

        switch (BrowserType.valueOf(browser.toUpperCase())){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case IE:
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new Exception("Invalid Browser Type");
        }

        driver.manage().window().maximize();
        waitUtils = new WaitUtils(driver);
        weighScalePage = new WeighScalePage(waitUtils);
    }

    @After
    public void closeBrowser(){
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (Exception e){
            // No alert Present
        }
        finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
