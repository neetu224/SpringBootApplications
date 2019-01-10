package com.example.demo.payload;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
@NotBlank
private String usernameOrEmail;
@NotBlank
private String password;
}
