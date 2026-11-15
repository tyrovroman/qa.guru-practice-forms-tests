import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTestConf {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://qa-guru.github.io/one-page-form";
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

}
