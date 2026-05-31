import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends BaseTestConf {

    @Test
    void successfulTextBoxTest () {

    open("/text-box.html");
    $(byId("userName")).setValue("Иван Иванов");
    $(byId("userEmail")).setValue("ivanov@example.com");
    $(byId("currentAddress")).setValue("г. Уфа, ул. Сталина, д. 6");
    $(byId("permanentAddress")).setValue("г. Саратов, ул. Южная, д. 96");

    $(byId("submit")).click();

    $("[id=output] [id=name]").shouldHave(text("Иван Иванов"));
    $("[id=output] [id=email]").shouldHave(text("ivanov@example.com"));
    $("[id=output] [id=currentAddress]").shouldHave(text("г. Уфа, ул. Сталина, д. 6"));
    $("[id=output] [id=permanentAddress]").shouldHave(text("г. Саратов, ул. Южная, д. 96"));
    }
}
