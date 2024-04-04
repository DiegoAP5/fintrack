package com.example.findtrack.services.interfaces;

import com.example.findtrack.controllers.dtos.requests.CreateUserRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.entities.User;

public interface IUserService {

    User findUserById(Long id);

    BaseResponse getUserById(Long id);

    BaseResponse listUsers();

    BaseResponse create(CreateUserRequest request);

    void delete(Long id);
}
