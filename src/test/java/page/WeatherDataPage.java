package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeatherDataPage {
    private final WebDriver driver;
    @FindBy(css = "#wxlocation")
    private WebElement locationTextBox;

    @FindBy(css = "#wxdataform > div.d-sm-flex > button")
    private WebElement searchButton;

    @FindBy(css = "#locationDropdownMenuButton")
    private WebElement weatherOfLocation;

    public WebElement getLocationTextBox() {
        return locationTextBox;
    }

    public void setLocationTextBox(String locationText) {
        getLocationTextBox().sendKeys(locationText);
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(WebElement searchButton) {
        this.searchButton = searchButton;
    }

    public void clickOnSearchButton(){
        getSearchButton().click();
    }

    public void setLocationTextBox(WebElement locationTextBox) {
        this.locationTextBox = locationTextBox;
    }

    public String getWeatherOfLocation() {
        return weatherOfLocation.getText();
    }

    public void setWeatherOfLocation(WebElement weatherOfLocation) {
        this.weatherOfLocation = weatherOfLocation;
    }

    public WeatherDataPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
