package task4;

import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.apache.commons.io.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Test1 {

    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
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
    @Description("Тест 1")
    public void test() throws InterruptedException {
        try {
            Allure.step("Нажимаем на поиск");
            page.clickSearch();
            Allure.step("Вводим название бренда в поиск");
            logger.info("Вводим в поисковое поле название бренда и нажимаем поиск");
            page.search("New Balance");
            Allure.step("Проверяем товары на соответствие поиску и выводим названия");
            logger.info("Проверяем товары на соответствие поиску и выводим их названия");
            for (int i = 0; i < 5; i++) {
                Assertions.assertTrue(page.checkName("New Balance", i), "Товар не соответствует поиску");
                System.out.println(page.getName(i));
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
