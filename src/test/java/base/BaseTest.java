package base;

import application.BuiltInUsers;
import application.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.Header;
import pages.ProductsPage;
import pages.StartingPage;

import java.time.Duration;
import java.util.Map;

public class BaseTest {

    // static da bi svi delili jedan driver
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static StartingPage startingPage;
    public static ProductsPage productsPage;
    public static Header header;
    public static CartPage cartPage;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void pageSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to(URLs.STARTING_PAGE_URL);

        startingPage = new StartingPage();
        productsPage = new ProductsPage();
        header = new Header();
        cartPage = new CartPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
