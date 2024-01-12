import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Test1 {
    WebDriver driver;

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    public void driverStart() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);

    }
    @AfterEach
    public void driverStop() {
        if (driver!= null) {
            driver.quit();
        }
    }
    @Test
    public void headless() {
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.xpath("//*[@id=\"searchbox_input\"]"))
                .sendKeys("ОТУС" + Keys.ENTER);
        String expectedText = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        WebElement firstResult = driver.findElement(By.xpath("//a[@href='https://otus.ru/'" +
                " and @class='eVNpHGjtxRBq_gLOfGDr LQNqh2U1kzYxREs65IJu']"));
        String actualText = firstResult.getText().trim();
        Assertions.assertEquals(expectedText, actualText);
    }
    }