package com.example.findtrack.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    private Long id;

    private String name;

    private String lastname;

    private String email;
}
