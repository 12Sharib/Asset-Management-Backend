package com.assetmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryInputDto {
    @NotBlank(message = "Invalid name, name shouldn't be empty or null")
    private String name;
}
