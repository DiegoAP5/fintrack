package com.example.findtrack.services.interfaces;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.entities.Saving;

public interface ISavingService {

    Saving findSavingById(Long id);

    BaseResponse getSavingById(Long id);

    BaseResponse getSavingByUserId(Long id);

    BaseResponse create(CreateSavingRequest request);

    BaseResponse update(Long id,UpdateSavingRequest request);

    void delete(Long id);
}
