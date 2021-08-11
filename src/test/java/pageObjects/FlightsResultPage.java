package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class FlightsResultPage {
    public WebDriver driver;
    public WaitHelper waitHelper;

    public FlightsResultPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        waitHelper=new WaitHelper(driver);
    }

    @FindBy(xpath="//div[@class='filtersOuter'][6]//div/label")
    @CacheLookup
    WebElement AirIndiaCheckBox;

    @FindBy(xpath = "//div[@id='premEcon']/div/div")
    @CacheLookup
    List<WebElement> airIndResults;

    @FindBy(xpath = "//div[@id='premEcon']/div/div[1]/div/div/div[@class='fli-list-body-section']/div")
    @CacheLookup
    WebElement trip1;

    @FindBy(xpath="//div[@id='premEcon']/div/div[1]/div/div/div[@class='fli-list-body-section']/div[2]")
    @CacheLookup
    WebElement trip2;

    @FindBy(xpath = "//div[contains(@class,'hsw_inputBox fromTo')]/div/span")
    @CacheLookup
    WebElement fromTo;

    @FindBy(xpath = "//div[@id='premEcon']/div/div/div/div[2]/div")
    @CacheLookup
    WebElement airIndiaFlightTxt;

    public void clickOnAirIndChkBox(){
        AirIndiaCheckBox.click();
    }

    public boolean checkAirIndia(){
        for(WebElement flight:airIndResults){
            waitHelper.waitForElement(airIndiaFlightTxt,3000);
            String txt=airIndiaFlightTxt.getText();
            if(!txt.equals("Air India"))
                return false;
        }
        return true;
    }

    public String getTrip1Details(){
        waitHelper.waitForElement(trip1,5000);
        return trip1.getText();
    }
    public String getTrip2Details(){
        return trip2.getText();
    }

    public String getFromToDetails(){
        return fromTo.getText();
    }

}
