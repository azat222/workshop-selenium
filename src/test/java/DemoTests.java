import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class DemoTests {

    WebDriver driver;

    // TC1 Existing email and password

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearUp() {

        driver.quit();
    }

    @Test
    public void loginSuccessTest() throws InterruptedException {

        driver.get("https://deens-master.now.sh/login");
        driver.findElement(By.cssSelector("#email")).sendKeys("azat+workshop");
        driver.findElement(By.cssSelector("#password")).sendKeys("Qwerty1!");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Thread.sleep(4000);

        driver.navigate().refresh();

        Assert.assertTrue(driver.findElement(By.cssSelector("[class*='DesktopDropDownMenu__AvatarWrapper']")).isDisplayed());

    }

    // TC2 Not existing email and password

    @Test
    public void loginNotExistingUser() {
        driver.get("https://deens-master.now.sh/login");
        driver.findElement(By.cssSelector("#email")).sendKeys("qweerwer");
        driver.findElement(By.cssSelector("#password")).sendKeys("Qwerwerwerty1!");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());
    }

    // TC3 Empty email and empty password
    @Test
    public void loginEmptyCredentials() {
        driver.get("https://deens-master.now.sh/login");
        driver.findElement(By.cssSelector("#email")).sendKeys("");
        driver.findElement(By.cssSelector("#password")).sendKeys("");
        driver.findElement(By.cssSelector("[data-testid='loginSubmit']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".ui.error.message")).isDisplayed());
    }

}
