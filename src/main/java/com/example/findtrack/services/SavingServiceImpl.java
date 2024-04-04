package com.example.findtrack.services;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.controllers.dtos.responses.SavingResponse;
import com.example.findtrack.controllers.excepecion.Excepcion;
import com.example.findtrack.entities.Saving;
import com.example.findtrack.entities.User;
import com.example.findtrack.entities.projections.SavingsProjection;
import com.example.findtrack.repositories.ISavingRepository;
import com.example.findtrack.services.interfaces.ISavingService;
import com.example.findtrack.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavingServiceImpl implements ISavingService {

    @Autowired
    private ISavingRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public Saving findSavingById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Saving not found"));
    }

    @Override
    public BaseResponse getSavingById(Long id) {
        SavingResponse response = from(repository.getSavingsById(id));
        return BaseResponse.builder()
                .data(response)
                .message("Saving by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getSavingByUserId(Long id) {
        List<SavingResponse> responses = repository.getSavingsByUserId(id).stream().map(this::from).collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("Saving by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateSavingRequest request) {
        Saving savings = new Saving();
        savings = create(request, savings);
        SavingResponse response = from(repository.save(savings));
        return BaseResponse.builder()
                .data(response)
                .message("Saving created")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateSavingRequest request) {
        Saving savings = findSavingById(id);
        savings = update(request, savings);
        SavingResponse response = from(repository.save(savings));
        return BaseResponse.builder()
                .data(response)
                .message("Saving updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SavingResponse from(Saving savings){
        SavingResponse response = new SavingResponse();
        response.setName(savings.getName());
        response.setId(savings.getId());
        response.setGoal(savings.getGoal());
        response.setAmount(savings.getAmount());
        return response;
    }

    private SavingResponse from(SavingsProjection savings){
        SavingResponse response = new SavingResponse();
        response.setAmount(savings.getAmount());
        response.setGoal(savings.getGoal());
        response.setName(savings.getName());
        response.setId(savings.getId());
        return response;
    }

    private Saving create(CreateSavingRequest request, Saving savings){
        User user = userService.findUserById(request.getUserId());
        savings.setGoal(request.getGoal());
        savings.setName(request.getName());
        savings.setAmount(request.getAmount());
        savings.setUser(user);
        return savings;
    }

    private Saving update(UpdateSavingRequest request, Saving savings){
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
