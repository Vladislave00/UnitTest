package task3;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.task3.Page3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;


public class Test3 {
    private static final Logger logger = LoggerFactory.getLogger(Test3.class);
    private WebDriver driver;
    private Page3 page;

    @BeforeEach
    public void setUp() throws InterruptedException {
        logger.info("Инициализация браузера");
        driver = new ChromeDriver();
        page = new Page3(driver);
        page.open();
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws InterruptedException {
        logger.info("Запуск теста");

        try {
            Allure.step("Переход в каталог -> ноутбуки");
            logger.info("Переход в каталог");
            page.clickCatalog();

            logger.info("Наведение на раздел 'Компьютеры'");
            page.hoverComputers();
            logger.info("Переход в раздел 'Ноутбуки'");
            page.clickLaptop2();

            Allure.step("Вывод названий и цен первых 5 товаров");
            logger.info("Вывод названий и цен первых 5 товаров");
            for (int i = 0; i < 5; i++) {
                System.out.print(page.getList().get(i).findElement(By.tagName("h3")).getText() + "   ");
                System.out.println(page.getList().get(i).findElement(By.className("_3gYEe")).getText());
            }
            Allure.step("Добавление 2-го товара в корзину и запоминание названия");
            logger.info("Добавление 2-го товара в корзину");
            String name = page.getList().get(1).findElement(By.tagName("h3")).getText();
            page.clickadd();
            logger.info("Проверка появления уведомления о добавлении товара в корзину");
            Assertions.assertTrue(page.checkNotification(), "Уведомление о добавлении товара в корзину не отображается");
            Allure.step("Переход в корзину");
            logger.info("Переход в корзину");
            page.clickBasket();
            Allure.step("Проверка товара в корзине");
            logger.info("Проверка, что в корзине находится нужный товар");
            Assertions.assertTrue(page.checkBasket().contains(name));
            Allure.step("Увеличение количества товара");
            logger.info("Увеличение количества товара в корзине");
            page.clickPlus();
            Allure.step("Удаление товара из корзины");
            logger.info("Удаление товара из корзины");
            page.clickDel();
            Allure.step("Проверка удаления");
            logger.info("Проверяем удаление товара из корзины");
            page.checkDelete();
            logger.info("Тест успешно завершен");
        } catch (Throwable t) {
            // Вывод ошибок в консоль
            logger.error("Ошибка во время выполнения теста: {}", t.getMessage());
            t.printStackTrace();

            // Сохранение скриншота
            Allure.addAttachment("filename", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

            throw t;
        }
    }

    @AfterEach
    public void tearDown() {
        logger.info("Закрытие браузера");
        driver.quit();
    }
}
