package com.example.findtrack.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSharedSavingRequest {
    private String name;

    private Float goal;

    private Float amount;

}
