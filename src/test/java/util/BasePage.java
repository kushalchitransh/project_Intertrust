package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.time.Duration;

public class BasePage {

    WebDriver driver;

    public void getURL(String url){
        this.driver.get(url);
    }
    public void setDriver(WebDriver driver){
        this.driver=driver;
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void waitForLoadingTitleWhoseTextContains(WebDriver wdriver, int duration,String title){
        WebDriverWait wd=new WebDriverWait(wdriver,Duration.ofSeconds(duration));
        wd.until(ExpectedConditions.titleContains(title));
    }

    public String getCurrentPageURL(){
       return this.driver.getCurrentUrl();
    }

    @AfterSuite
    public void cleanup(){
        this.driver.quit();
    }
}
