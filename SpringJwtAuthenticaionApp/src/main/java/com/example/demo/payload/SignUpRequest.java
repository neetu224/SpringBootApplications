package com.example.demo.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
@NotBlank
@Size(min=4,max=40)
private String name;
@NotBlank
@Size(min=3,max=15)
private String username;
@NotBlank
@Size(max=40)
private String email;
@NotBlank
@Size(min=6,max=20)
private String password;
}
