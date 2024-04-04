package com.example.findtrack.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    private String name;

    private String lastname;

    private String email;

    private String password;
}
