package com.assetmanagement.controller;

import com.assetmanagement.dto.TypeInputDto;
import com.assetmanagement.response.TypeResponse;
import com.assetmanagement.service.TypeService;
import com.assetmanagement.utils.DateTimeUtil;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@Log4j2
public class TypeController {
    @Autowired
    private TypeService typeService;
    @PostMapping("/add")
    public ResponseEntity<TypeResponse> createType(@Valid @RequestBody TypeInputDto typeInputDto){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Starting add type controller, starting time: {} - {}", startTime, "ms");
        final TypeResponse typeResponse = typeService.addType(typeInputDto);

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed add type controller, execution time: {} - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(typeResponse, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<TypeResponse> getAllType(){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all type controller, starting time: {} - {}", startTime, "ms");
        final TypeResponse typeResponse = typeService.getAllActiveTypes();

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all type controller, execution time: {} - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(typeResponse, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<TypeResponse> updateTypeDetails(@Valid @RequestBody final TypeInputDto typeInputDto, @PathParam("typeId") Integer typeId){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started update type controller, starting time:- {} - {}", startTime, "ms");
        final TypeResponse typeResponse = typeService.updateDetails(typeInputDto, typeId);

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all type controller, execution time: {} - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(typeResponse, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<TypeResponse> deleteType(@PathParam("typeId") Integer typeId){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started deleting a type controller, starting time: {} - {}", startTime, "ms");
        final TypeResponse typeResponse = typeService.delete(typeId);

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed deleting a type controller, execution time: {} - Total time:- {}",endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(typeResponse, HttpStatus.OK);
    }
}
