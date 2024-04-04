package com.example.findtrack.controllers;

import com.example.findtrack.controllers.dtos.requests.CreateSavingRequest;
import com.example.findtrack.controllers.dtos.requests.UpdateSavingRequest;
import com.example.findtrack.controllers.dtos.responses.BaseResponse;
import com.example.findtrack.services.interfaces.ISavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("saving")
public class SavingController {

    @Autowired
    private ISavingService service;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getSavinById(@PathVariable Long id){
        BaseResponse baseResponse = service.getSavingById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<BaseResponse> getSavinByUserId(@PathVariable Long id){
        BaseResponse baseResponse = service.getSavingByUserId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateSavingRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateSavingRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}
}
