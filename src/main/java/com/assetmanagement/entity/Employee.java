package com.assetmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "employee")
public class Employee {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "location")
    private String location;
    @Column(name = "pu_id")
    private Integer puId;
    @Column(name = "designation_id")
    private Integer designationId;
}
