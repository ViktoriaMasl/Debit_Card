import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class TestDebitCard {
    @Test
    void shouldSuccessfulSending() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+77777777777");
        form.$("[data-test-id=agreement] input").click();
        form.$(".button").click();
        $(".alert-success").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
