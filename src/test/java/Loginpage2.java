import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Loginpage2 {
    @Test(dataProvider = "credentials")
    public void testing(String scenario , String username , String password){
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Pavan.Kopparapu\\Downloads\\chromedriver_win32\\chromedriver.exe");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        System.out.println(e);
    }
    driver.findElement(By.xpath("//[@placeholder = 'username']")).sendKeys(username);
    driver.findElement(By.xpath("//[@placeholder = 'password']")).sendKeys(password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    if (scenario.equals("Both_correct")){
        WebElement login = driver.findElement(By.xpath("//h6"));
        Assert.assertTrue(login.isDisplayed(),"login success");
    } else if (scenario.equals("Both_wrong") && scenario.equals("correct username") && scenario.equals("correct password")) {
        String errormessage = driver.findElement(By.xpath("//P[text()='Invalid credentials']")).getText();
        Assert.assertEquals(errormessage,"Login not success");

    }
    }
    @DataProvider(name = "credentials")
    public Object[][]getData(){
        return new Object[][]{
                {"Both_correct","Admin","admin123"},
                {"Both_wrong","Xyz","passwordadmin"},
                {"correct_username","Admin","admin@123"},
                {"correct_password","username1","admin123"}
        };
    }
}
