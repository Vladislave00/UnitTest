package pages.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Page1 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public Page1(WebDriver driver){
        Page1.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(){
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    public String getHeader(){
        return wait.until(driver -> driver.findElement(By.tagName("h2"))).getText();
    }

    public String getRemaining(){
        return driver.findElement(By.className("ng-binding")).getText();
    }

    public List<WebElement> getList(){
        return driver.findElement(By.className("list-unstyled")).findElements(By.className("ng-scope"));
    }

    public boolean isItemDone(int itemIndex) {
        WebElement item = getList().get(itemIndex - 1);
        return item.findElement(By.cssSelector("span")).getAttribute("class").contains("done-true");
    }

    public void toggleCheckbox(int itemId){
        WebElement item = getList().get(itemId - 1);
        WebElement checkbox = item.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
    }

    public void newItem(String text){
        WebElement textfield = driver.findElement(By.id("sampletodotext"));
        textfield.sendKeys(text);
        driver.findElement(By.id("addbutton")).click();
    }
}
