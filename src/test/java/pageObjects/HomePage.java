package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class HomePage {
    public WebDriver driver;
    public WaitHelper waitHelper;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        waitHelper=new WaitHelper(driver);
    }

    @FindBy(id="fromCity")
    @CacheLookup
    WebElement fromCity;

    @FindBy(xpath = "//input[contains(@class,'react-autosuggest')]")
    @CacheLookup
    WebElement autoSuggestive;

    @FindBy(xpath = "//input[contains(@class,'react-autosuggest')]")
    @CacheLookup
    WebElement autoSuggestiveToCity;


    @FindBy(css = "div[class='DayPicker-Months'] div.DayPicker-Month")
    @CacheLookup
    List<WebElement> months;


    @FindBy(xpath = "//a[contains(@class,'primaryBtn')]")
    @CacheLookup
    WebElement searchBtn;

    @FindBy(xpath = "//p[contains(@class,'font24')]")
    @CacheLookup
    WebElement searchResultsTitle;

    @FindBy(xpath = "/html")
    @CacheLookup
    WebElement page;

    @FindBy(xpath ="//ul[contains(@class,'fswTabs')]/li[2]/span")
    @CacheLookup
    WebElement roundTripRadioBtn;

    @FindBy(xpath = "//label[@for='return']")
    @CacheLookup
    WebElement returnDay;

    @FindBy(xpath = "//ul[contains(@class,'fswTabs')]/li[3]/span")
    @CacheLookup
    WebElement multiCityRadioBtn;

    @FindBy(xpath = "//label[@for='fromAnotherCity1']")
    @CacheLookup
    WebElement anotherCity;

    @FindBy(xpath = "//input[contains(@class,'react-autosuggest')]")
    @CacheLookup
    WebElement anotherFromCityAutoSuggestive;

    @FindBy(xpath = "//input[contains(@class,'react-autosuggest')]")
    @CacheLookup
    WebElement anotherToCityAutoSuggestive;

    @FindBy(xpath = "//ul[@class='specialFare']/li[4]")
    @CacheLookup
    WebElement seniorCitizen;

    @FindBy(xpath = "//span[@data-cy='sameCityError']")
    @CacheLookup
    WebElement sameCityError;


    public void setFromCity(String fcity){
        waitHelper.waitForElement(fromCity,3000);
        fromCity.sendKeys(fcity);
        waitHelper.waitForElement(autoSuggestive,2000);
        autoSuggestive.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
    }
    public void setToCity(String tcity) throws InterruptedException {
        waitHelper.waitForElement(autoSuggestiveToCity,2000);
        autoSuggestiveToCity.sendKeys(tcity);
       waitHelper.waitForElement(autoSuggestiveToCity,3000);
       Thread.sleep(2000);
        autoSuggestiveToCity.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
    }

   public List<WebElement> getMonths(){
        return months;
   }
    public void clickOnSearch(){
        searchBtn.click();
    }
    public String getResultsTitle(){
        waitHelper.waitForElement(searchResultsTitle,5000);
        return searchResultsTitle.getText();
    }
    public void clickOnPage(){
       page.click();
    }
    public void clickRoundTrip(){
        roundTripRadioBtn.click();
    }
    public String getReturnDayText(){
        return returnDay.getText();
    }
    public void clickMultiCityTrip(){
        multiCityRadioBtn.click();
    }
    public void clickAnotherCity(){
        anotherCity.click();
    }
    public void setAnotherFromCity(String city) throws InterruptedException {
        clickAnotherCity();
        waitHelper.waitForElement(anotherFromCityAutoSuggestive,3000);
        anotherFromCityAutoSuggestive.sendKeys(city);
        Thread.sleep(2000);
        anotherFromCityAutoSuggestive.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
    }
    public void setAnotherToCity(String city) {
        anotherToCityAutoSuggestive.sendKeys(city);
        anotherToCityAutoSuggestive.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
    }

    public void clickOnSeniorCitizen(){
        seniorCitizen.click();
    }

    public String getCityError(){
        return sameCityError.getText();
    }

}
