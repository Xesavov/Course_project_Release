package com.orangeteam.NewAuc.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    Long id;
    String login;
    String password1;
    String password2;
    String fullName;
    String email;
    String phone;
}
