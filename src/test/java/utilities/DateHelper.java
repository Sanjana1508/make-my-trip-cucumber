package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.HomePage;

import java.util.List;

public class DateHelper {
    public WebDriver driver;
    public  HomePage homePage;

    public DateHelper(WebDriver driver){
        this.driver=driver;
        homePage=new HomePage(driver);
    }


    public void pickDate(String month, int day) throws InterruptedException {
        month = getMonth(month);
        boolean selected=false;
        while(!selected) {
            int i = 1;
            List<WebElement> months = homePage.getMonths();

            for (WebElement m : months) {

                if (m.getText().split(" ")[0].equals(month)) {
                    int disabledDays=0;
                    int weekNo=0;
                    if(i>1) {
                        List<WebElement> disabled = driver.findElements(By.xpath("(//div[@class='DayPicker-Month'])[" + (i - 1) + "]//div[@class='DayPicker-Body']" +
                                "//div[@class='DayPicker-Week'][5]//div[contains(@class,'DayPicker-Day--outside')]"));

                        disabledDays = disabled.size();
                        weekNo = (day+7-disabledDays) / 7;
                    }
                   weekNo=day/7;
                    List<WebElement> days = driver.findElements(By.xpath("(//div[@class='DayPicker-Month'])[" + i + "]//div[@class='DayPicker-Body']" +
                            "//div[@class='DayPicker-Week'][" + (weekNo + 1) + "]/div[contains(@class,'DayPicker-Day')]"));
                    for (WebElement d : days) {
                        String dayStr = String.valueOf(day);
                        if (d.getText().equals(dayStr)) {
                            d.click();
                            Thread.sleep(3000);
                            selected = true;
                            return;
                        }
                    }
                }
                i++;
            }
            driver.findElement(By.xpath("//span[contains(@class,'DayPicker-NavButton--next')]")).click();
        }
    }

    public String getMonth(String month){
        switch (month){
            case "1":return "January";
            case "2":return "February";
            case "3":return "March";
            case "4":return "April";
            case "5":return "May";
            case "6":return "June";
            case "7":return "July";
            case "8":return "August";
            case "9":return "September";
            case "10":return "October";
            case "11":return "November";
            case "12":return "December";
        }
        return null;
    }
}
