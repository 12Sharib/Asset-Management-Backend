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

import java.util.Date;

@Getter
@Setter
@Entity(name = "asset_allocation")
public class Allocation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "asset_id")
    @NotNull(message = "Invalid asset id, id shouldn't be blank or empty")
    private Integer assetId;

    @Column(name = "employee_id")
    @NotBlank(message = "Invalid employee id, id shouldn't be blank or empty")
    private String employeeId;

    @Column(name = "is_active")
    private Boolean isActive;
}
