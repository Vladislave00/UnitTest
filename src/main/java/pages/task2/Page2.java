package pages.task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Page2 {
    private WebDriver driver;

    private WebDriverWait wait;

    private Actions action;

    public Page2(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
    }

    public void open(){
        driver.get("https://mospolytech.ru/");
    }

    public void clickSchedule(){
        driver.findElement(By.xpath("//a[@class=\"user-nav__item-link\"and@href=\"/obuchauschimsya/raspisaniya/\"]")).click();
    }

    public void clickOnsite(){
        driver.findElement(By.xpath("//a[@href=\"https://rasp.dmami.ru/session\"]")).click();
    }

    public void findGroup(String text){
        ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(1));
        WebElement textfield = driver.findElement(By.className("groups"));
        textfield.sendKeys(text);
    }

    public boolean checkGroup(String text){
        try {
            WebElement element = wait.until(driver1 -> driver.findElement(By.id(text)));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void groupClick(){
        WebElement element = wait.until(driver1 -> driver.findElement(By.id("221-361")));
        element.click();
    }
    public String getScheduleDayOfWeek() {
        WebElement scheduleToday = wait.until(driver -> driver.findElement(By.cssSelector("div[class='schedule-day schedule-day_today']")));
        return scheduleToday.getText().split(" ")[0].toLowerCase();
    }
}
