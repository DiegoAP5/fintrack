package com.example.findtrack.services.interfaces;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.CreateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.entities.SharedSaving;

public interface ISharedSavingService {
    SharedSaving findSavingById(Long id);

    BaseResponse getSavingByUserId(Long id);

    BaseResponse create(CreateSharedSavingRequest request);

    BaseResponse update(Long id, UpdateSharedSavingRequest request);

    void delete(Long id);
}
