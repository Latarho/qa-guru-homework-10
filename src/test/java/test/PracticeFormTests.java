package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AttachHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;


public class PracticeFormTests {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    String firstName = "Serg";
    String lastName = "Pomytkin";
    String email = "latarho@mail.ru";
    String mobileNumber = "8800555555";
    String subject = "Accounting";
//    String pictureName = "test.jpg";
    String address = "Moscow, Surikova street";
    String headerFormTitle = "Thanks for submitting the form";

    @BeforeAll
    static void beforeAll() {
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        String browserSize = System.getProperty("browserSize");

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = browserSize;
        Configuration.remote = "https://" + user + ":" + password + "@selenoid.autotests.cloud/wd/hub";


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        AttachHelper.screenshotAs("Last screenshot");
        AttachHelper.pageSource();
        AttachHelper.browserConsoleLogs();
        AttachHelper.addVideo();
        closeWebDriver();
    }

    @Test
    public void fillAllFieldsPracticeFormTest()  {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть страницу", () -> {
            practiceFormPage.openPage();
        });

        step("Ввести имя", () -> {
            practiceFormPage.setFirstName(firstName);
        });

        step("Ввести фамилию", () -> {
            practiceFormPage.setLastName(lastName);
        });

        step("Ввести электронную почту", () -> {
            practiceFormPage.setEmail(email);
        });

        step("Выбрать пол", () -> {
            practiceFormPage.selectMaleGender();
        });

        step("Ввести номер телефона", () -> {
            practiceFormPage.setMobileNumberInput(mobileNumber);
        });

        step("Выбрать дату рождения", () -> {
            practiceFormPage.setBirthDate("9", "October", "1991");
        });

        step("Ввести предмет", () -> {
            practiceFormPage.selectSubjects(subject);
        });

        step("Выбрать хобби", () -> {
            practiceFormPage.selectMusicHobbies();
        });

//        step("Загрузить изображение", () -> {
//            practiceFormPage.uploadPicture(pictureName);
//        });

        step("Ввести адрес", () -> {
            practiceFormPage.setAddress(address);
        });

        step("Выбрать штат и город", () -> {
            practiceFormPage.selectStateAndCityNCRDelhi();
        });

        step("Отправить форму", () -> {
            practiceFormPage.clickSubmitButton();
        });

        step("Проверка хедера", () -> {
            practiceFormPage.checkHeader(headerFormTitle);
        });

        step("Проверка формы", () -> {
            practiceFormPage.checkForm("Student Name", firstName + " " + lastName)
                    .checkForm("Student Email", email)
                    .checkForm("Gender", "Male")
                    .checkForm("Mobile", mobileNumber)
                    .checkForm("Date of Birth", "09 October,1991")
                    .checkForm("Subjects", subject)
                    .checkForm("Hobbies", "Music")
                    .checkForm("Address", address)
                    .checkForm("State and City", "NCR Delhi");
        });
    }
}