package com.example.findtrack.controllers.dtos.requests;

import com.example.findtrack.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CreateSharedSavingRequest {
    private String name;

    private Float goal;

    private Float amount;

    private List<Long> users;

}
