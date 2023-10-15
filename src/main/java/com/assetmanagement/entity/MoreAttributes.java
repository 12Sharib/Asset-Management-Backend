package com.assetmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "more_attributes")
public class MoreAttributes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ram")
    @NotBlank(message = "RAM shouldn't be blank, provide valid RAM details")
    private String ram;

    @NotBlank(message = "Hard disk shouldn't be blank, provide valid hard disk details")
    @Column(name = "hard_disk")
    private String hardDisk;

    @NotBlank(message = "OS shouldn't be blank, provide valid operating system details")
    @Column(name = "operating_system")
    private String operatingSystem;

    @NotNull(message = "Screen size shouldn't be empty, provide valid screen size")
    @Column(name = "screen_size")
    private Integer screenSize;
}
