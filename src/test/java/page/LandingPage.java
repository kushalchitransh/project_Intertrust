package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private final WebDriver driver;
    @FindBy (xpath = "//*[text()=\"Accept all cookies\"]")
    private WebElement cookiesAcceptButton;

    @FindBy(linkText = "Weather Data")
    private WebElement weatherDataButton;

    public WebElement getCookiesAcceptButton() {
        return cookiesAcceptButton;
    }

    public Boolean isCookiesAcceptButtonVisible(){
        return getCookiesAcceptButton().isDisplayed();
    }
    public void setCookiesAcceptButton(WebElement cookiesAcceptButton) {
        this.cookiesAcceptButton = cookiesAcceptButton;
    }

    public WebElement getWeatherDataButton() {
        return weatherDataButton;
    }

    public void setWeatherDataButton(WebElement weatherDataButton) {
        this.weatherDataButton = weatherDataButton;
    }

    public void clickAcceptCookieButton(){
        getCookiesAcceptButton().click();
    }

    public void clickWeatherDataButton(){
        getWeatherDataButton().click();
    }


    public LandingPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
