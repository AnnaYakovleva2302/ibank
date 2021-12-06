package ru.netology.ibank;

import com.github.javafaker.Faker;
import lombok.Data;

import java.util.Locale;

@Data
public class UserData {
    private String login;
    private String password;
    private String status;

    public UserData() {
        Faker faker = new Faker(new Locale("en"));
        login = faker.name().username();
        password = faker.internet().password();
        status = "active";
        AuthService.setUser(this);
    }
}
