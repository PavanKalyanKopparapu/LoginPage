
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class LoginPage {
@Test(dataProvider = "credentials")
    public void testing(String scenario, String Username , String password) {
    WebDriver driver = new ChromeDriver();
    System.setProperty("Webdriver.chrome.driver", "C:\\Users\\Pavan.Kopparapu\\Downloads\\chromedriver.exe");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);

            driver.findElement(By.xpath("//input[@placeholder = 'Username'")).sendKeys(Username);
            driver.findElement(By.xpath("//input[@placeholder = 'password")).sendKeys(password);
            driver.findElement(By.xpath("//button[@type = 'submit']")).click();
            if (scenario.equals("Both_correct")) {
                WebElement login = driver.findElement(By.xpath("//h6"));
                Assert.assertTrue(login.isDisplayed(), "login success");
            } else if (scenario.equals("Both-wrong") && scenario.equals("Correct-Username") && scenario.equals("Correct-password")) {
                String errormessage = driver.findElement(By.xpath("//p[text() = 'Invalid credentials']")).getText();
                Assert.assertEquals(errormessage, "login not success");
            }
        }
    }
    @DataProvider(name = "credentials")
        public Object[][] getData()
        {
            return new Object[][]{
                    {"Both-correct","Admin","admin123"},
                    {"Both-wrong","XYZ","passwordadmin"},
                    {"correct username","Admin","admin@123"},
                    {"correct password","username1","admin123"}
            };
    }
    }

