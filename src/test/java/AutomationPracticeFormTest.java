import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest extends BaseTestConf {

    @Test
    void successfulFullFieldsTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byId("lastName")).setValue("Иванов");
        $(byId("userEmail")).setValue("ivanov@example.com");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("9991234567");
        $(byId("dateOfBirthInput")).click();
        $(byClassName("react-datepicker__year-select")).selectOption("1987");
        $(byClassName("react-datepicker__month-select")).selectOption("May");
        $(byClassName("react-datepicker__day--009")).click();
        $(byId("subjectsInput")).setValue("Maths").pressEnter();
        $(byCssSelector("input[value='" + "Sports" + "']")).click();
        $(byId("uploadPicture")).uploadFromClasspath("test.png");
        $(byId("currentAddress")).setValue("г. Дели, ул. Ленина, д. 1, кв. 27");
        $(byId("state")).scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $(byId("city")).click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $(byId("submit")).scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Иван Иванов"));
        $(".table-responsive").shouldHave(text("ivanov@example.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("9991234567"));
        $(".table-responsive").shouldHave(text("09 May 1987"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("test.png"));
        $(".table-responsive").shouldHave(text("г. Дели, ул. Ленина, д. 1, кв. 27"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
    }

    @Test
    void successfulMandatoryFieldsTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byId("lastName")).setValue("Иванов");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("9991234567");

        $(byId("submit")).scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Иван Иванов"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("9991234567"));
    }

    @Test
    void negativeShortMobileNumberTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byId("lastName")).setValue("Иванов");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("999123456");

        $(byId("submit")).scrollTo().click();

        $(byId("formError")).shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeWithoutLastNameTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("9991234567");

        $(byId("submit")).scrollTo().click();

        $(byId("formError")).shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeWithoutNameTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("lastName")).setValue("Иванов");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("9991234567");

        $(byId("submit")).scrollTo().click();

        $(byId("formError")).shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNoGenderTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byId("lastName")).setValue("Иванов");
        $(byId("userNumber")).setValue("9991234567");

        $(byId("submit")).scrollTo().click();

        $(byId("formError")).shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}
