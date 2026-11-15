import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends BaseTestConf {

    @Test
    void successfulTextBoxTest() {
        open("/text-box");

        // Безопасное удаление баннера и футера через нативный JavaScript
        executeJavaScript("document.querySelector('#fixedban')?.remove()");
        executeJavaScript("document.querySelector('footer')?.remove()");

        $("#userName").setValue("Иван Иванов");
        $("#userEmail").setValue("ivanov@example.com");
        $("#currentAddress").setValue("г. Уфа, ул. Сталина, д. 6");
        $("#permanentAddress").setValue("г. Саратов, ул. Южная, д. 96");

        $("#submit").scrollTo().click();

        $("#output #name").shouldHave(text("Иван Иванов"));
        $("#output #email").shouldHave(text("ivanov@example.com"));
        $("#output #currentAddress").shouldHave(text("г. Уфа, ул. Сталина, д. 6"));
        $("#output #permanentAddress").shouldHave(text("г. Саратов, ул. Южная, д. 96"));
    }
}
