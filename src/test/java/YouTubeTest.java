import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class YouTubeTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.navigate().to("https://www.youtube.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest
    public void teamDown(){
        driver.quit();
    }

    @Test
    public void YouTube_HoverVideo_Test() {
        By video = By.cssSelector("#contents #content img");
        By hover = By.cssSelector("ytd-moving-thumbnail-renderer img");

        String video_no_hover = driver.findElement(video).getDomAttribute("src");

        Actions actionProvider = new Actions(driver);
        actionProvider.moveToElement(driver.findElement(video)).build().perform();

        String video_hover = driver.findElement(hover).getDomAttribute("src");

        if (video_no_hover.equals(video_hover)){
            Assert.fail();
        }
    }
}
