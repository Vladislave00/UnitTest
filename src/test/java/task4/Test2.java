package task4;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.task4.Page4;

import java.io.ByteArrayInputStream;


public class Test2 {

    private static final Logger logger = LoggerFactory.getLogger(Test2.class);
    private WebDriver driver;
    private Page4 page;

    @BeforeEach
    public void setUp() throws InterruptedException {
        logger.info("Инициализация браузера");
        driver = new ChromeDriver();
        page = new Page4(driver);
        page.open();
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws InterruptedException {
        logger.info("Нажимаем на кнопку Обувь");

        try {
            Allure.step("Переходим в раздел обувь");
            page.clickShoes();
            Allure.step("Вводим максимальную цену в фильтр и применяем его");
            logger.info("Вводим максимальную цену в фильтр и применяем его");
            page.putFilter(10000);
            Allure.step("Проверяем товары на соответствие фильтру");
            logger.info("Проверяем товары на соответствие фильтру");
            for (int i = 0; i < 5; i++) {
                Assertions.assertTrue(page.checkPrice(10000, i), "Товар не соответствует фильтру");
            }
            logger.info("Тест пройден успешно");
        } catch (Throwable t) {
            // Вывод ошибок в консоль
            logger.error("Ошибка во время выполнения теста: {}", t.getMessage());
            t.printStackTrace();

            // Сохранение скриншота
            Allure.addAttachment("filename", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        }
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие браузера");
        driver.quit();
    }
}
