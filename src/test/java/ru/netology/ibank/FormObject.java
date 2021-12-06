package ru.netology.ibank;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.$;

@Data
public class FormObject {
    private SelenideElement errorContainer;
    private SelenideElement successContainer;

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
