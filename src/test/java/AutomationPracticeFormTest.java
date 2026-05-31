import org.junit.jupiter.api.Test;

import java.io.File;
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
        $(byText("9")).click();
        $(byId("subjectsInput")).setValue("Maths").pressEnter();
        $(byCssSelector("input[value='" + "Sports" + "']")).click();
        $(byId("uploadPicture")).uploadFile(new File
                ("D:/IdeaProjects/automation-practice-form-tests/src/test/resources/test.png"));
        $(byId("currentAddress")).setValue("г. Дели, ул. Ленина, д. 1, кв. 27");
        $(byId("state")).scrollTo().click();
        $(byText("NCR")).click();
        $(byId("city")).click();
        $(byText("Delhi")).click();

        $(byId("submit")).scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $x("//td[text()='Student Name']//following-sibling::td").shouldHave(text("Иван" + " " + "Иванов"));
        $x("//td[text()='Student Email']//following-sibling::td").shouldHave(text("ivanov@example.com"));
        $x("//td[text()='Gender']//following-sibling::td").shouldHave(text("Male"));
        $x("//td[text()='Mobile']//following-sibling::td").shouldHave(text("9991234567"));
        $x("//td[text()='Date of Birth']//following-sibling::td").shouldHave(text("9" + " " + "May" + " " + "1987"));
        $x("//td[text()='Subjects']//following-sibling::td").shouldHave(text("Maths"));
        $x("//td[text()='Hobbies']//following-sibling::td").shouldHave(text("Sports"));
        $x("//td[text()='Picture']//following-sibling::td").shouldHave(text("test.png"));
        $x("//td[text()='Address']//following-sibling::td").shouldHave(text("г. Дели, ул. Ленина, д. 1, кв. 27"));
        $x("//td[text()='State and City']//following-sibling::td").shouldHave(text("NCR" + " " + "Delhi"));
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
        $x("//td[text()='Student Name']//following-sibling::td").shouldHave(text("Иван" + " " + "Иванов"));
        $x("//td[text()='Gender']//following-sibling::td").shouldHave(text("Male"));
        $x("//td[text()='Mobile']//following-sibling::td").shouldHave(text("9991234567"));
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
    void negativeLongMobileNumberTest() {

        open("/automation-practice-form");
        $("[aria-label=Close]").shouldBe(visible, Duration.ofSeconds(10)).click();
        $(byId("firstName")).setValue("Иван");
        $(byId("lastName")).setValue("Иванов");
        $(byCssSelector("input[value='" + "Male" + "']")).click();
        $(byId("userNumber")).setValue("99912345678");

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
