package pages.task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Page4 {
    private WebDriver driver;

    private WebDriverWait wait;

    private Actions action;

    public Page4(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
    }
    public void open() throws InterruptedException {
        driver.get("https://sneakerhead.ru/");
    }
    public void clickSearch(){
        WebElement element = driver.findElement(By.xpath("//a[@title=\"Избранное\"]"));
        element.click();
    }
    public void clickShoes(){
        WebElement element = driver.findElement(By.xpath("//a[@title=\"Обувь\"]"));
        element.click();
    }
    public void search(String text) throws InterruptedException {
        WebElement field = wait.until(driver -> driver.findElement(By.xpath("//input[@class=\"search-dropdown__input digi-autocomplete jc-ignore\"]")));
        field.sendKeys(text);
        WebElement button = driver.findElement(By.xpath("//*[@id=\"search_submit\"]"));
        TimeUnit.SECONDS.sleep(1);
        button.click();
    }

    public List<WebElement> getList() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return driver.findElement(By.cssSelector("div[class=\"product-cards__list\"]")).findElements(By.cssSelector("div[class=\"product-cards__item\"]"));
    }
    public boolean checkName(String text, int id) throws InterruptedException {
        if (getList().get(id).findElement(By.tagName("h5")).getText().contains(text)){
            return true;
        }
        return false;
    }
    public String getName(int id) throws InterruptedException {
        return getList().get(id).findElement(By.tagName("h5")).getText();
    }

    public void putFilter(int max) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"arFilter_106_MAX\"]"));
        element.sendKeys(String.valueOf(max));
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("//*[@id=\"modef\"]/div[3]/a")).click();
    }

    public boolean checkPrice(int max, int id) throws InterruptedException {
        int price = Integer.parseInt(getList().get(id).findElement(By.cssSelector("meta[itemprop=\"price\"]")).getAttribute("content"));
        if (price <= max){
            return true;
        }
        return false;
    }
    public void clickShoe() throws InterruptedException {
        getList().get(0).click();
    }
    public void clickAddToCart(){
        driver.findElement(By.xpath("//*[@id=\"frm_add\"]/div[1]/button")).click();
    }
    public void clickCart() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("//*[@id=\"bx_basketFKauiI\"]/a")).click();
    }
    public String getName() throws InterruptedException {
        return getList().get(0).findElement(By.tagName("h5")).getText();
    }
    public boolean checkShoe(String in){
        String name = driver.findElement(By.cssSelector("div[class=\"sc-1f70kwv-18 gqwOfK\"]")).findElement(By.tagName("a")).getText();
        return in.toLowerCase().contains(name.toLowerCase());
    }
}
