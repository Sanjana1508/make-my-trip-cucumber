package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FlightsResultPage;
import pageObjects.HomePage;
import utilities.DateHelper;
import utilities.WaitHelper;


public class BookFlightSteps extends Base{

    @Given("User launch chrome browser")
    public void user_launch_chrome_browser() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        homePage=new HomePage(driver);
        waitHelper=new WaitHelper(driver);
        flightsResultPage=new FlightsResultPage(driver);
    }


    @When("User opens url {string}")
    public void user_opens_url(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }
    @When("User enters fromCity {string}")
    public void user_enters_from_city(String fromCity){
        homePage.clickOnPage();
        homePage.setFromCity(fromCity);
    }

    @When("User enters ToCity {string}")
    public void user_enters_to_city(String toCity){
        homePage.setToCity(toCity);
    }
    @When("User selects date of departure {string}")
    public void user_selects_date_of_departure(String myDate) throws InterruptedException {
        String[] dateArr=myDate.split("-");
        int year=Integer.parseInt(dateArr[2]);
        int month=Integer.parseInt(dateArr[1]);
        int day=Integer.parseInt(dateArr[0]);
        LocalDate date=new LocalDate(year,month,day);
        dateHelper=new DateHelper(driver);
        dateHelper.pickDate(String.valueOf(date.getMonthOfYear()),date.getDayOfMonth());
    }

    @When("Users selects senior citizen")
    public void users_selects_senior_citizen() {
       homePage.clickOnSeniorCitizen();
    }


    @When("User clicks on search")
    public void user_clicks_on_search(){
        homePage.clickOnSearch();
    }
    @Then("title is {string}")
    public void title_is(String title) {
        Assert.assertEquals(homePage.getResultsTitle(),title);

    }
    @Then("User closes the browser")
    public void user_closes_the_browser() {
        driver.quit();
    }
    @When("User selects Air India")
    public void user_selects_air_india() throws InterruptedException {

        flightsResultPage.clickOnAirIndChkBox();
        Thread.sleep(2000);
    }
    @Then("Flight results are Air India")
    public void flight_results_are_air_india() {
        Assert.assertTrue(flightsResultPage.checkAirIndia());
    }
    @When("User clicks round trip")
    public void user_clicks_round_trip() {
      homePage.clickRoundTrip();
      System.out.println(driver.findElement(By.xpath("//label[@for='return']")).getText());
    }

    @Then("User should see next day")
    public void user_should_see_next_day() {
       if(homePage.getReturnDayText().contains("15 Sep21")){
           Assert.assertTrue(true);
       }
       else {
           Assert.assertFalse(true);
       }
    }

    @When("User selects multicity")
    public void user_selects_multicity() {
       homePage.clickMultiCityTrip();
    }


    @When("User enters another from city {string}")
    public void user_enters_another_from_city(String city){
        homePage.setAnotherFromCity(city);
    }
    @When("User enters another to city {string}")
    public void user_enters_another_to_city(String city){
       homePage.setAnotherToCity(city);
    }
    @Then("User should see trip1 details as {string}")
    public void user_should_see_trip1_details_as(String details){
        System.out.println(flightsResultPage.getTrip1Details());
      Assert.assertTrue(flightsResultPage.getTrip1Details().contains(details));
    }
    @Then("User should see trip2Details as {string}")
    public void user_should_see_trip2details_as(String details) {
       Assert.assertTrue(flightsResultPage.getTrip2Details().contains(details));
    }

    @Then("User should see same city error message")
    public void user_should_see_same_city_error_message() {
        Assert.assertEquals(homePage.getCityError(),"From & To airports cannot be the same");
    }


}
