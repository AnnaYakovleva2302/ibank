package ru.netology.ibank.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FormObject {
    public SelenideElement errorContainer;
    public SelenideElement successContainer;

    public FormObject() {
        errorContainer = $("[data-test-id=error-notification] .notification__content");
        successContainer = $("h2");
    }

    public void fillAndSubmit(String login, String password) {
        $("input[name=login]").setValue(login);
        $("input[name=password]").setValue(password);
        $("[data-test-id=action-login]").click();
    }
}
