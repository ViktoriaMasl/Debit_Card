import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;


class TestDebitCard {

    @BeforeEach
    void init() {
        open("http://localhost:9999");
    }

    @AfterEach
    void finish() {
        closeWebDriver();
    }

    @Test
    void shouldSuccessfulSending() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+77777777777");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNullSending() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue(null);
        form.$("[data-test-id=phone] input").setValue(null);
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidSending() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("567789");
        form.$("[data-test-id=phone] input").setValue("gdgdggdg");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInvalidNumberOverMax() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("fsfsfsfsfffsfsfsffsfffbgdgfsf");
        form.$(".checkbox__box").click();
        form.$(".button").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
