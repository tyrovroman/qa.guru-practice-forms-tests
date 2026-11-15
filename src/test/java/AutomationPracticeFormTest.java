import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest extends BaseTestConf {

        @Test
    void successfulFullFieldsTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userEmail").setValue("ivanov@example.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9991234567");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__day--009:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("test.png");
        $("#currentAddress").setValue("г. Дели, ул. Ленина, д. 1, кв. 27");
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Иван Иванов"),
                text("ivanov@example.com"),
                text("Male"),
                text("9991234567"),
                text("09 May 1987"),
                text("Maths"),
                text("Sports"),
                text("test.png"),
                text("г. Дели, ул. Ленина, д. 1, кв. 27"),
                text("NCR Delhi")
        );

        $("#closeModal").click();
    }


    @Test
    void successfulMandatoryFieldsTest() {
        open("/automation-practice-form");

        // Убираем рекламные баннеры и футер, чтобы не перекрывали кнопку отправки
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Заполнение только обязательных полей
        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9991234567");

        // Отправка формы
        $("#submit").scrollTo().click();

        // Проверка результатов в модальном окне
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Иван Иванов"),
                text("Male"),
                text("9991234567")
        );

        // Закрытие модального окна в самом конце теста
        $("#closeModal").click();
    }

    @Test
    void negativeShortMobileNumberTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("999123456");

        $("#submit").scrollTo().click();

        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeWithoutLastNameTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Иван");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9991234567");

        $("#submit").scrollTo().click();

        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeWithoutNameTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#lastName").setValue("Иванов");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9991234567");

        $("#submit").scrollTo().click();

        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void negativeNoGenderTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Иван");
        $("#lastName").setValue("Иванов");
        $("#userNumber").setValue("9991234567");

        $("#submit").scrollTo().click();

        $("#formError").shouldHave(text("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}