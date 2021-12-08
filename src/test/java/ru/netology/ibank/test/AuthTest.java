package ru.netology.ibank.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.ibank.data.UserDataGenerator;
import ru.netology.ibank.data.UserInfo;
import ru.netology.ibank.pageObject.FormObject;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

class AuthTest {
    private FormObject form = new FormObject();

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void ifUserExistsAndPasswordCorrect() {
        UserInfo user = UserDataGenerator.Registration.generateUser("active");
        form.fillAndSubmit(user.getLogin(), user.getPassword());
        form.successContainer.shouldHave(text("Личный кабинет"), Duration.ofSeconds(3));
    }

    @Test
    void ifUserExistsButPasswordIncorrect() {
        UserInfo user = UserDataGenerator.Registration.generateUser("active");
        form.fillAndSubmit(user.getLogin(), UserDataGenerator.generatePassword());
        form.errorContainer.shouldHave(text("Неверно указан логин или пароль"), Duration.ofSeconds(3));
    }

    @Test
    void ifUserDoesNotExist() {
        UserInfo user = UserDataGenerator.Registration.generateUser("active");
        form.fillAndSubmit(UserDataGenerator.generateLogin(), user.getPassword());
        form.errorContainer.shouldHave(text("Неверно указан логин или пароль"), Duration.ofSeconds(3));
    }

    @Test
    void ifUserExistsAndPasswordCorrectButBlocked() {
        UserInfo user = UserDataGenerator.Registration.generateUser("blocked");
        form.fillAndSubmit(user.getLogin(), user.getPassword());
        form.errorContainer.shouldHave(text("Пользователь заблокирован"), Duration.ofSeconds(3));
    }

    @Test
    void ifUserExistsButPasswordIncorrectAndBlocked() {
        UserInfo user = UserDataGenerator.Registration.generateUser("blocked");
        form.fillAndSubmit(user.getLogin(), UserDataGenerator.generatePassword());
        form.errorContainer.shouldHave(text("Неверно указан логин или пароль"), Duration.ofSeconds(3));
    }
}