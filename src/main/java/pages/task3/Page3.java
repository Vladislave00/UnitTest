package pages.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Page3 {
    private WebDriver driver;

    private WebDriverWait wait;

    private Actions action;

    public Page3(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);

    }

    public void open() throws InterruptedException {
        driver.get("https://market.yandex.ru/");
        TimeUnit.SECONDS.sleep(1);
    }

    public void clickCatalog(){
        driver.findElement(By.xpath("//button[@class='_30-fz button-focus-ring Hkr1q _1pHod _2rdh3 _3rbM-']")).click();
    }

    public void hoverComputers() throws InterruptedException {
        WebElement element = wait.until(driver1 -> driver.findElement(By.xpath("//a[@href='/catalog--kompiuternaia-tekhnika/54425']/span[@class='_3W4t0']")));
        TimeUnit.SECONDS.sleep(2);
        action.moveToElement(element).perform();
        action.moveToLocation(0,0).perform();
    }

    public void clickLaptop2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-150)", "");
        WebElement element = driver.findElement(By.xpath("//a[@href='/catalog--noutbuki/54544/list?hid=91013' and contains(@class, '_2TBT0')]"));
        element.click();
    }

    public List<WebElement> getList(){
        return driver.findElements(By.cssSelector("div[class=\"_1nuzB _1FEQF _1MOwX _1bCJz\"]"));
    }

    public void clickadd(){
        getList().get(1).findElement(By.cssSelector("button[class=\"_30-fz button-focus-ring _1pHod _2rdh3 _2bFey\"]")).click();
    }
    public void clickBasket(){
        driver.findElement(By.xpath("//a[@href='/my/cart' and @class='EQlfk _2h0Ng _1IKOp']")).click();
    }
    public void clickPlus() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//article[@aria-label='Товар']//button[@aria-label='Увеличить']")).click();
    }
    public void clickDel() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.xpath("//div//article[@aria-label='Товар']//button[@data-auto='remove-button']")).click();
    }
    public boolean checkNotification(){
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-auto='notification']")))).isDisplayed();
    }
    public String checkBasket(){
        return driver.findElement(By.cssSelector("h3[class=\"_3YHut _2SUA6 _33utW _13aK2 _1A5yJ\"]")).getText();
    }
    public boolean checkDelete(){
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("ul[class=\"_3LylS _3NyVE\"]")))).isDisplayed();
    }
}
