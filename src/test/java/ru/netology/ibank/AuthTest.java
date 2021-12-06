package ru.netology.ibank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

class AuthTest {
    private static UserData user = new UserData();
    private static FormObject form = new FormObject();

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
    }

    @Test
    void ifUserExistsAndPasswordCorrect() {
        user = AuthService.activateUser(user);
        form.fillAndSubmit(user.getLogin(), user.getPassword());
        form.getSuccessContainer().shouldHave(text("Личный кабинет"));
    }

    @Test
    void ifUserExistsButPasswordIncorrect() {
        user = AuthService.activateUser(user);
        form.fillAndSubmit(user.getLogin(), user.getPassword() + "!");
        form.getErrorContainer().shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void ifUserDoesNotExist() {
        user = AuthService.activateUser(user);
        form.fillAndSubmit(user.getLogin() + "!", user.getPassword());
        form.getErrorContainer().shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void ifUserExistsAndPasswordCorrectButBlocked() {
        user = AuthService.blockUser(user);
        form.fillAndSubmit(user.getLogin(), user.getPassword());
        form.getErrorContainer().shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void ifUserExistsButPasswordIncorrectAndBlocked() {
        user = AuthService.blockUser(user);
        form.fillAndSubmit(user.getLogin(), user.getPassword() + "!");
        form.getErrorContainer().shouldHave(text("Неверно указан логин или пароль"));
    }
}