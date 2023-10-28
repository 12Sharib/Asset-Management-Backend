package com.assetmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoreAttributesInputDto {
    @NotBlank(message = "RAM shouldn't be blank, provide valid RAM details")
    private String ram;

    @NotBlank(message = "Hard disk shouldn't be blank, provide valid hard disk details")
    private String hardDisk;

    @NotBlank(message = "OS shouldn't be blank, provide valid operating system details")
    private String operatingSystem;

    @NotNull(message = "Screen size shouldn't be empty, provide valid screen size")
    private Integer screenSize;}
