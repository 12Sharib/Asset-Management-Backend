package com.assetmanagement.controller;

import com.assetmanagement.response.EmployeeResponse;
import com.assetmanagement.service.EmployeeService;
import com.assetmanagement.utils.DateTimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/all")
    public ResponseEntity<EmployeeResponse> getAllEmployees(){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all employees service, starting time: {} - {}",startTime, "ms");
        final EmployeeResponse employeeResponse = employeeService.allEmployees();

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all employee service, execution time: {} - Total time: {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }
}
