package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class CallbackTest {
    public String generateDate(int days, String pattern) {
        if (days == 0) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Симферополь");
        $("[data-test-id='date'] .input__control").click();
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.BACK_SPACE);
        String planningDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue("Волков Василий");
        $(" [name='phone']").setValue("+79260911212");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(withText("Забронировать")).click();
        $(By.cssSelector(".notification__content")).shouldHave(exactText("Встреча успешно забронирована на " + planningDate), Duration.ofMillis(15000)).shouldBe(visible);
    }

}




