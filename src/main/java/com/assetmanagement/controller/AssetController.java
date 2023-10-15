package com.assetmanagement.controller;

import com.assetmanagement.dto.AssetInputDto;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.response.AssetResponse;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.utils.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/asset")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @PostMapping("/add")
    private ResponseEntity<AssetResponse> createAsset(@Valid @RequestBody final AssetInputDto assetInputDto){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started adding asset controller, starting time: {} - {} ", startTime, "ms");
        final AssetResponse assetResponse = assetService.addAsset(assetInputDto);
        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed adding asset controller, execution time: {} - ms, Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }
}
