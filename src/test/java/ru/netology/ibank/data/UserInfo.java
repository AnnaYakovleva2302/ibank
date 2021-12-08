package ru.netology.ibank.data;

import lombok.Value;

@Value
public class UserInfo {
    String login;
    String password;
    String status;
}
