package com.example.findtrack.services;

import com.example.findtrack.controllers.dtos.requests.CreateUserRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.controllers.dtos.responses.UserResponse;
import com.example.findtrack.controllers.excepecion.Excepcion;
import com.example.findtrack.entities.User;
import com.example.findtrack.repositories.IUserRepository;
import com.example.findtrack.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Not user found"));
    }

    @Override
    public BaseResponse getUserById(Long id) {
        UserResponse response = from(findUserById(id));
        return BaseResponse.builder()
                .data(response)
                .message("User")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse listUsers() {
        List<UserResponse> responses = repository.findAll().stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("User list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        User user = new User();
        user = create(request,user);
        UserResponse response = from(repository.save(user));
        return BaseResponse.builder()
                .data(response)
                .message("User created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserResponse from(User user){
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setLastname(user.getLastname());
        return response;
    }

    private User create(CreateUserRequest request, User user) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLastname(request.getLastname());
        return user;
    }
}
