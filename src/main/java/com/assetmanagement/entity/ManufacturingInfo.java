package com.assetmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity(name = "manufacturing_info")
@ToString
public class ManufacturingInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand")
    private String brand;
    @Column(name = "manufacturing_date")
    private Date manufacturingDate;
    @Column(name = "price")
    private Double price;
    @Column(name = "warranty_start")
    private Date warrantyStart;
    @Column(name = "warranty_end")
    private Date warrantyEnd;
    @Column(name = "vendor_id")
    private Integer vendorId;
    @Column(name = "vendor_name")
    private String vendorName;
    @Column(name = "vendor_location")
    private String vendorLocation;
}
