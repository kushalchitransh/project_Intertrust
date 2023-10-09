import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Test {
    public static void main(String[] args) {
        // Setup WebDriverManager for ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // Perform actions with the WebDriver
        driver.get("https://www.visualcrossing.com");
        WebDriverWait wd=new WebDriverWait(driver,Duration.ofSeconds(20));
        wd.until(ExpectedConditions.titleContains("Weather Data"));
        Assert.assertTrue(driver.getCurrentUrl().contains("visualcrossing"));
        WebElement cokiesAccept= driver.findElement(By.xpath("//*[text()=\"Accept all cookies\"]"));
        if(cokiesAccept.isDisplayed()){
            cokiesAccept.click();
        }
        driver.findElement(By.linkText("Weather Data")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("weather-data"));
        String location="Pune";
        driver.findElement(By.cssSelector("#wxlocation")).sendKeys(location);
        driver.findElement(By.cssSelector("#wxdataform > div.d-sm-flex > button")).click();
        String expectedLocation= driver.findElement(By.cssSelector("#locationDropdownMenuButton")).getText();
        Assert.assertTrue(expectedLocation.equalsIgnoreCase(location));
        Assert.assertTrue(driver.getCurrentUrl().contains(location));
        // Quit the WebDriver
        driver.quit();
    }
}