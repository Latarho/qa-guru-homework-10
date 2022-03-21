package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    // Компоненты
    private CalendarComponent calendarComponent = new CalendarComponent();

    // Локаторы
    private SelenideElement
            headerTitle = $(".practice-form-wrapper"),
            firstNameInput = $x("//*[@id ='firstName']"),
            lastNameInput = $x("//*[@id ='lastName']"),
            emailInput = $x("//input[@placeholder='name@example.com']"),
            maleGenderCheckBox = $x("//*[text() = 'Male']"),
            mobileNumberInput = $x("//input[@placeholder='Mobile Number']"),
            birthDate = $x("//*[@id ='dateOfBirthInput']"),
            subjectInput = $x("//*[@id ='subjectsInput']"),
            musicHobbiesCheckBox = $x("//*[text() = 'Music']"),
//            uploadPicture = $x("//*[@id ='uploadPicture']"),
            addressInput = $x("//*[@id ='currentAddress']"),
            stateField = $x("//*[@id='state']"),
            cityField = $x("//*[@id ='city']"),
            submitButton = $x("//*[text() = 'Submit']"),
            headerFormTitle = $x("//*[@class ='modal-header']"),
            resultsTable = $x("//*[@class='table-responsive']");

    // Действия с элементами страницы
    public void openPage() {
        open("/automation-practice-form");
        // Проверка тайтла страницы
        headerTitle.shouldHave(text("Student Registration Form"));
    }

    public void setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.setValue(lastName);
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void selectMaleGender() {
        maleGenderCheckBox.click();
    }

    public void setMobileNumberInput(String mobileNumber) {
        mobileNumberInput.setValue(mobileNumber);
    }

    public void setBirthDate(String day, String month, String year) {
        birthDate.click();
        calendarComponent.setDate(day, month, year);
    }

    public void selectSubjects(String subject) {
        subjectInput.setValue(subject).pressEnter();
    }

    public void selectMusicHobbies() {
        musicHobbiesCheckBox.click();
    }

//    public void uploadPicture(String pictureName) {
//        uploadPicture.uploadFromClasspath(pictureName);
//    }

    public void setAddress(String address) {
        addressInput.setValue(address);
    }

    public void selectStateAndCityNCRDelhi() {
        stateField.click();
        $x("//*[text() = 'NCR']").click();
        cityField.click();
        $x("//*[text() ='Delhi']").click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void checkHeader(String value) {
        // Проверка тайтла заполненной формы
        headerFormTitle.shouldHave(text(value));
    }

    public PracticeFormPage checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }
}