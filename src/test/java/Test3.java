import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;


public class Test3 {
     WebDriver driver;
     private final Logger logger = LogManager.getLogger(Test3.class);

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public  void driverStart() {
        WebDriverManager.chromedriver();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void maxSize() {
        driver.get("https://otus.ru");
        String login = "electro1005@yandex.ru";
        String password = "1234567sS!";
        String clickLogin = "//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1 liHMCp']";
        String clickPass = "//div[@class='sc-rq8xzv-1 hGvqzc sc-11ptd2v-1-Component ciraFX']";
        String inputEmail = "//input[@name='email']";
        String inputPass = "//input[@type='password']";
        driver.findElement(By.xpath("//button[@class='sc-mrx253-0 enxKCy sc-945rct-0 iOoJwQ']")).click();
        driver.findElement(By.xpath(clickLogin)).click();
        driver.findElement(By.xpath(inputEmail)).sendKeys(login);
        driver.findElement(By.xpath(clickPass)).click();
        driver.findElement(By.xpath(inputPass)).sendKeys(password);
        driver.findElement(By.xpath("//div[@class='sc-9a4spb-2' and text() ='Войти']")).click();
        logger.info("log in");
        logger.info(driver.manage().getCookies());
    }
}
