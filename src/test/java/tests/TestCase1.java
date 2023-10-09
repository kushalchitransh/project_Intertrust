package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page.LandingPage;
import page.WeatherDataPage;
import util.BasePage;

import java.time.Duration;

public class TestCase1 extends BasePage {
    WebDriver driver;
    LandingPage landingPage;
    WeatherDataPage weatherDataPage;

    Response response;
    private String city;

    @BeforeSuite
    public void startTest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        setDriver(driver);
    }

    @Test
    public void testCase1() {
        landingPage = new LandingPage(driver);
        weatherDataPage = new WeatherDataPage(driver);
        getURL("https://www.visualcrossing.com");
        waitForLoadingTitleWhoseTextContains(this.driver, 20, "Weather Data");
        Assert.assertTrue(getCurrentPageURL().contains("visualcrossing"));
        if (landingPage.isCookiesAcceptButtonVisible()) {
            landingPage.clickAcceptCookieButton();
        }
        landingPage.clickWeatherDataButton();
        Assert.assertTrue(getCurrentPageURL().contains("weather-data"));
        weatherDataPage.getSearchButton().isDisplayed();
        String location = "Pune";
        weatherDataPage.setLocationTextBox(location);
        weatherDataPage.clickOnSearchButton();
        String expectedLocation = weatherDataPage.getWeatherOfLocation();
        Assert.assertTrue(expectedLocation.equalsIgnoreCase(location));
        Assert.assertTrue(getCurrentPageURL().contains(location));
    }

    @Test
    public void testCase2() {
        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
        String apiKey = "GS4DBCN3UAMVRR4VRFZR8DHJL";
        String city = "Pune";

        // Send a GET request to the VisualCrossing API
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath(city)
                .queryParam("unitGroup", "metric")
                .queryParam("key", apiKey)
                .queryParam("contentType", "json")
                .when()
                .get();

        response.then().statusCode(200);
        this.response=response;
        this.city=city;
        //System.out.println(response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testCase3() {
        String response=this.response.getBody().asString();
        JsonPath js=new JsonPath(response);
        String expectedCity=js.getString("resolvedAddress");
        Assert.assertTrue(expectedCity.contains(this.city));
    }

}
