package com.example.findtrack.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SavingResponse {

    private Long id;

    private String name;

    private float amount;

    private float goal;
}
