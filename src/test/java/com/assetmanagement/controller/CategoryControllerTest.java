package com.assetmanagement.controller;

import com.assetmanagement.TestContainerBase;
import com.assetmanagement.dao.CategoryRepository;
import com.assetmanagement.dto.CategoryDto;
import com.assetmanagement.dto.CategoryInputDto;
import com.assetmanagement.response.CategoryResponse;
import com.assetmanagement.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers
@Log4j2
@WebMvcTest(CategoryController.class)
class CategoryControllerTest extends TestContainerBase {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String allCategories = "/category/all";
    private static final String addCategory = "/category/add";

    @Test
    void addCategory() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        final CategoryInputDto categoryInputDto = new CategoryInputDto();
        categoryInputDto.setName("Hardware");

        final String request = objectMapper.writeValueAsString(categoryInputDto);

        final MockHttpServletResponse categoryResponse = mockMvc.perform(MockMvcRequestBuilders.post(addCategory)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        System.out.println("00: " + categoryResponse + " 01: " +categoryResponse.getContentAsString());

//        String responseContent = mockMvc.perform(MockMvcRequestBuilders.post(addCategory)
//                        .contentType(MediaType.APPLICATION_JSON) // Set the content type to JSON
//                        .content(jsonRequest)) // Set the request body
//                .andExpect(MockMvcResultMatchers.status().isOk()) // Check the response status
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) // Check content type
//                .andReturn() // Capture the response
//                .getResponse() // Get the response
//                .getContentAsString(); // Get the response content as a string

    }
    @Test
    void getAllCategories() throws Exception {
        log.info("Started get all categories controller");
        mockMvc.perform(MockMvcRequestBuilders.get(allCategories))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryDtos").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));

        log.info("Completed get all categories controller");
    }

}