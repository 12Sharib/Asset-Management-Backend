package com.assetmanagement.controller;

import com.assetmanagement.dto.CategoryInputDto;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.response.CategoryResponse;
import com.assetmanagement.service.CategoryService;
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
@RequestMapping("/category")
@Log4j2
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    private ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody final CategoryInputDto category){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started add category controller, starting time: {} - {}", startTime, "ms");
        final CategoryResponse categoryResponse = categoryService.addCategory(category);
        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed add category controller, execution time: {} - ms - Total Time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
    @GetMapping("/all")
    private ResponseEntity<CategoryResponse> getAllCategories(){
        final long startTime = DateTimeUtil.getCurrentTime();
        log.info("Started get all categories controller, starting time: {} - {}", startTime, "ms");
        final CategoryResponse categoryResponse = categoryService.getAllCategories();

        final long endTime = DateTimeUtil.getCurrentTime();
        log.info("Completed get all categories controller, execution time: {} - ms - Total time:- {}", endTime, DateTimeUtil.getTotalTime(startTime));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
}
