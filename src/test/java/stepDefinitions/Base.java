package stepDefinitions;

import org.openqa.selenium.WebDriver;
import pageObjects.FlightsResultPage;
import pageObjects.HomePage;
import utilities.DateHelper;
import utilities.WaitHelper;

public class Base {
    public WebDriver driver;
    HomePage homePage;
    WaitHelper waitHelper;
    DateHelper dateHelper;
    FlightsResultPage flightsResultPage;
}
