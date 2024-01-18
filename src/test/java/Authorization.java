import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;



public class Authorization {
    private static WebDriver driver;
    private WaitTools waitTools;
     private final Logger logger = LogManager.getLogger(Authorization.class);
     private String login = System.getProperty("login");
     private String password = System.getProperty("password");


    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public  void driverStart() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        waitTools = new WaitTools(driver);
         driver.get("https://otus.ru");
    }
    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void authorizationUser(){
        String clickLoginLocator = "//div[./input[@name='email']]";
        String clickPassLocator = "//div[./input[@type='password']]";
        String inputEmailLocator = "//input[@name='email']";
        String inputPassLocator = "//input[@type='password']";
        WebElement buttonEnter = driver.findElement(By.xpath("//button[text()=\"Войти\"]"));
        buttonEnter.click();
        driver.findElement(By.xpath(clickLoginLocator)).click();
        driver.findElement(By.xpath(inputEmailLocator)).sendKeys(login);
        driver.findElement(By.xpath(clickPassLocator)).click();
        driver.findElement(By.xpath(inputPassLocator)).sendKeys(password);
        driver.findElement(By.cssSelector("#__PORTAL__ button")).click();
        Assertions.assertTrue(waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.xpath(String.valueOf(buttonEnter))))));
        logger.info("log in");
        logger.info(driver.manage().getCookies());
    }
}
