package com.assetmanagement.controller;

import com.assetmanagement.dto.AllocationInputDto;
import com.assetmanagement.response.AllocationResponse;
import com.assetmanagement.service.AllocationService;
import com.assetmanagement.utils.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allocation")
@Log4j2
public class AllocationController {
    @Autowired
    private AllocationService allocationService;
    @PostMapping("/allocate")
    public ResponseEntity<AllocationResponse> allocateResource(@Valid @RequestBody final AllocationInputDto allocationInputDto){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started allocate asset controller, starting time: {} - {}", startTime, "ms");
        final AllocationResponse allocationResponse = allocationService.allocate(allocationInputDto);

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed allocate asset controller, execution time: {} - ms, Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(allocationResponse, HttpStatus.OK);
    }
    @GetMapping("all")
    public ResponseEntity<AllocationResponse> getAllAllocations(){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all allocations controller, starting time: {} - {}", startTime, "ms");
        final AllocationResponse allocationResponse = allocationService.allocations();

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all allocations controller, execution time: {} - ms, Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(allocationResponse, HttpStatus.OK);
    }
}
