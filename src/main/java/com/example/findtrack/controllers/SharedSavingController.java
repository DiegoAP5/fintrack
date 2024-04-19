package com.example.findtrack.controllers;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.CreateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSharedSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.services.interfaces.ISharedSavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sharedSaving")
public class SharedSavingController {

    @Autowired private ISharedSavingService service;

    @GetMapping("/user/{id}")
    public ResponseEntity<BaseResponse> getSavinById(@PathVariable Long id){
        BaseResponse baseResponse = service.getSavingByUserId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSharedSavingRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateSharedSavingRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
