package task2;

import io.qameta.allure.Allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.task2.Page2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;


public class Test2 {
    private static final Logger logger = LoggerFactory.getLogger(Test2.class);
    private WebDriver driver;
    private Page2 page;

    @BeforeEach
    public void setUp() {
        logger.info("Инициализация браузера");
        driver = new ChromeDriver();
        page = new Page2(driver);
        page.open();
    }

    @Test
    public void test() {
        logger.info("Запуск теста");

        try {
            Allure.step("Переход на вкладку 'Расписания'");
            logger.info("Переход на вкладку 'Расписания'");
            page.clickSchedule();

            Allure.step("Переход на вкладку 'Смотреть на сайте'");
            logger.info("Переход на вкладку 'Смотреть на сайте'");
            page.clickOnsite();

            Allure.step("Поиск группы '221-361'");
            logger.info("Поиск группы '221-361'");
            page.findGroup("221-361");

            Allure.step("Проверка наличия в результате группы '221-361'");
            logger.info("Проверка наличия группы '221-361'");
            Assertions.assertTrue( page.checkGroup("221-361"), "Группа '221-361' не найдена");

            Allure.step("Нажатие на кнопку группы '221-361'");
            logger.info("Нажатие на кнопку группы '221-361'");
            page.groupClick();

            logger.info("Получение дня недели из расписания");
            String scheduleDayOfWeek = page.getScheduleDayOfWeek();

            Allure.step("Проверка выделения дня недели");
            logger.info("Получение текущего дня недели");
            DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();
            String currentDayOfWeekText = currentDayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).toLowerCase();
            logger.info("Проверка соответствия дня недели");
            Assertions.assertEquals(currentDayOfWeekText, scheduleDayOfWeek, "День недели в расписании не соответствует текущему");

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
