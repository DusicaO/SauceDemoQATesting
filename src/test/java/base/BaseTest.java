package base;

import application.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.ProductsPage;
import pages.StartingPage;

import java.time.Duration;

public class BaseTest {

    // static da bi svi delili jedan driver
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static StartingPage startingPage;
    public static ProductsPage productsPage;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to(URLs.STARTING_PAGE_URL);

        startingPage = new StartingPage();
        productsPage = new ProductsPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
