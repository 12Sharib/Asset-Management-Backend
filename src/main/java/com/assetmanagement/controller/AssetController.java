package com.assetmanagement.controller;

import com.assetmanagement.dto.AssetDto;
import com.assetmanagement.dto.AssetInputDto;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.response.AssetResponse;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.utils.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/all")
    private ResponseEntity<AssetResponse> getAllAssets(){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all assets controller, starting time: {} - {}", startTime, "ms");
        final AssetResponse assetResponse = assetService.allAssets();

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all assets controller, execution time: {} - ms - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }
    @PatchMapping("/updateStatus")
    private ResponseEntity<AssetResponse> updateAssetStatus(@PathVariable("assetId") final Integer assetId, @PathVariable("statusId") final Integer statusId){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started update asset status controller, starting time: {} - {}", startTime, "ms");
        final AssetResponse assetResponse = assetService.updateStatus(assetId, statusId);;

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed update asset status controller, execution time: {} - ms - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(assetResponse, HttpStatus.OK);
    }
    @GetMapping("/category")
    private ResponseEntity<AssetResponse> getAllAssetByCategory(@PathVariable("categoryId") final Integer categoryId){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all assets by category id controller, starting time: {} - {}", startTime, "ms");
        final AssetResponse assetResponse = assetService.assetsByCategory(categoryId);

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all assets by category id controller, execution time: {} - ms - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(assetResponse, HttpStatus.OK);

    }
}
