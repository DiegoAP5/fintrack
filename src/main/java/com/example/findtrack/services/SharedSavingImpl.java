package com.example.findtrack.services;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.CreateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.controllers.dtos.responses.SavingResponse;
import com.example.findtrack.controllers.dtos.responses.SharedSavingResponse;
import com.example.findtrack.controllers.excepecion.Excepcion;
import com.example.findtrack.entities.Saving;
import com.example.findtrack.entities.SharedSaving;
import com.example.findtrack.entities.User;
import com.example.findtrack.entities.projections.SavingsProjection;
import com.example.findtrack.repositories.ISharedSavingRepository;
import com.example.findtrack.services.interfaces.ISharedSavingService;
import com.example.findtrack.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharedSavingImpl implements ISharedSavingService {

    @Autowired
    private ISharedSavingRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public SharedSaving findSavingById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Not shared saving found"));
    }

    @Override
    public BaseResponse getSavingByUserId(Long id) {
        List<SharedSavingResponse> response = repository.getSharedSavingByUserId(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(response)
                .message("Shared saving created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse create(CreateSharedSavingRequest request) {
        SharedSaving sharedSaving = new SharedSaving();
        sharedSaving = create(request, sharedSaving);
        SharedSavingResponse response = from(repository.save(sharedSaving));
        return BaseResponse.builder()
                .data(response)
                .message("Shared saving created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateSharedSavingRequest request) {
        SharedSaving sharedSaving = findSavingById(id);
        sharedSaving = update(request,sharedSaving);
        SharedSavingResponse response = from(repository.save(sharedSaving));
        return BaseResponse.builder()
                .data(response)
                .message("Shared saving created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SharedSavingResponse from(SharedSaving savings){
        SharedSavingResponse response = new SharedSavingResponse();
        response.setName(savings.getName());
        response.setId(savings.getId());
        response.setGoal(savings.getGoal());
        response.setAmount(savings.getAmount());
        return response;
    }

    private SharedSavingResponse from(SavingsProjection savings){
        SharedSavingResponse response = new SharedSavingResponse();
        response.setAmount(savings.getAmount());
        response.setGoal(savings.getGoal());
        response.setName(savings.getName());
        response.setId(savings.getId());
        return response;
    }

    private SharedSaving create(CreateSharedSavingRequest request, SharedSaving savings){
        List<User> users = new ArrayList<>();
        if (request.getUsers() != null){
            for (Long id: request.getUsers()){
                User user = userService.findUserById(id);
                users.add(user);
            }
            savings.setUsers(users);
        }
        else {
            System.out.println("No hay id");
        }
        savings.setGoal(request.getGoal());
        savings.setName(request.getName());
        savings.setAmount(request.getAmount());
        return savings;
    }

    private SharedSaving update(UpdateSharedSavingRequest request, SharedSaving savings){
        if (request.getAmountAdded()!=null){
            Float newAmount = savings.getAmount() + request.getAmountAdded();
            savings.setAmount(newAmount);
            savings.setAmountAdded(request.getAmountAdded());
        }
        else if(request.getWithdraw()!= null){
            Float newAmount = savings.getAmount() - request.getWithdraw();
            savings.setAmount(newAmount);
            savings.setWithdraw(request.getWithdraw());
        }
        return savings;
    }
}
