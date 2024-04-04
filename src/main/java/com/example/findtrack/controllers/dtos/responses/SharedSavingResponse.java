package com.example.findtrack.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SharedSavingResponse {

    private Long id;

    private String name;

    private Float amount;

    private Float goal;
}
