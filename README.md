
Part 1 – UI test
For UI test you can use whether Selenium/Selenide or Playwright for Java - our choice is Playwright, but you can use what is more familiar for you. Using Page Objects Model (POM) is highly encouraged. Create UI automated test for the following user scenario:
1. Open VisualCrossing URL: https://www.visualcrossing.com
2. Select the "Weather Data" menu
3. Enter your current city in the text field and press "Search" button
4. Verify that weather forecast for your city is shown. Up to you to come up with other test scenarios that should be verified, imagine that you were given abstract requirement to verify weather forecast what else would you verify?

Part 2 - API test
Create an automated test for getting weather data using API endpoint. You can use framework of your choice for RESTful API test automation. We use REST Assured.
In order to use API, you need to get a key, for this you have to sign up for a VisualCrossing account at https://www.visualcrossing.com.
You can find the API key under you "Account Details" menu after you log in to VisualCrossing (click on "Account" button on the top right).
The test should send GET request to the VisualCrossing API endpoint (replace Tallinn withyour city in request):https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Tallinn?unitGroup=metric&key=YOUR_API_KEY&contentType=json
Verify returned response contains correct data. Decide what assertions should be used to verify the correctness of the response and add them to the test.


