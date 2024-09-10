package task1;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.task1.Page1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

@Feature("Lambda Test")
@Story("Проверка Todo App")
public class Test1 {
    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
    private WebDriver driver;
    private Page1 page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new Page1(driver);
        page.open();
    }

    @Test
    public void test(){
        logger.info("Запуск теста");

        try {
            Allure.step("Проверяем заголовок страницы");
            logger.info("Проверяем заголовок страницы");
            try {
                Assertions.assertEquals("LambdaTest Sample App", page.getHeader());
            } catch (Exception e) {
                String expectedMessage = "LambdaTest Sample App";
                Assertions.assertEquals(expectedMessage, e.getMessage(), "Заголовок страницы некорректен");
                throw e;
            }
            Allure.step("Проверяем текст '5 of 5 remaining'");
            logger.info("Проверяем текст '5 of 5 remaining'");
            try {
                Assertions.assertEquals("5 of 5 remaining", page.getRemaining());
            } catch (Exception e) {
                String expectedMessage = "5 of 5 remaining";
                Assertions.assertEquals(expectedMessage, e.getMessage(), "Текст '5 of 5 remaining' некорректен");
                throw e;
            }
            Allure.step("Проверка зачеркнутости элементов списка");
            for (int i = 1; i <= 5; i++) {
                logger.info("Проверяем элемент {} из 5", i);
                Assertions.assertFalse(page.isItemDone(i));
                page.toggleCheckbox(i);
                Assertions.assertTrue(page.isItemDone(i));
                try {
                    Assertions.assertEquals((5-i) + " of 5 remaining", page.getRemaining());
                } catch (Exception e) {
                    String expectedMessage = (5-i) + " of 5 remaining";
                    Assertions.assertEquals(expectedMessage, e.getMessage(), "Текст оставшихся элементов некорректен");
                    throw e;
                }
            }
            Allure.step("Добавление нового элемента");
            logger.info("Добавляем новый элемент 'Прес качат'");
            page.newItem("Прес качат");
            try {
                Assertions.assertEquals("1 of 6 remaining", page.getRemaining());
            } catch (Exception e) {
                String expectedMessage = "1 of 6 remaining";
                Assertions.assertEquals(expectedMessage, e.getMessage(), "Текст оставшихся элементов некорректен");
                throw e;
            }
            Assertions.assertFalse(page.isItemDone(6));
            Allure.step("Проверка нажатия на галочку у нового элемента");
            page.toggleCheckbox(6);
            Assertions.assertTrue(page.isItemDone(6));
            try {
                Assertions.assertEquals("0 of 6 remaining", page.getRemaining());
            } catch (Exception e) {
                String expectedMessage = "0 of 6 remaining";
                Assertions.assertEquals(expectedMessage, e.getMessage(), "Текст оставшихся элементов некорректен");
                throw e;
            }

            logger.info("Тест успешно завершен");
        } catch (Throwable t) {
            // Вывод ошибок в консоль
            logger.error("Ошибка во время выполнения теста: {}", t.getMessage());
            t.printStackTrace();

            // Сохранение скриншота
            Allure.addAttachment("filename", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            // Выбрасывание исключения, чтобы тест был помечен как неудавшийся
            throw new AssertionError("Тест не пройден");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
