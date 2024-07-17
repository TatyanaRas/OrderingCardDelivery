package ru.netology.delivery.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    private String dateSelection(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldTest() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue("Казань");
        String planningDate = dateSelection(4, "DD.MM.YYYY");

        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys("Keys.DELETE");
        $("[data-test-id=date] input").setValue("planningDate");
        $("[data-test-id=name] input").setValue("Иванова Юлия");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));

    }
}