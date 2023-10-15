package com.assetmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "asset")
public class Asset {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "description")
    private String description;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "categories_id")
    private Integer categoriesId;

    @Column(name = "manufacturing_info_id")
    private Integer manufacturingInfoId;

    @Column(name = "common_attributes_id")
    private Integer commonAttributesId;

    @Column(name = "more_attribute_id")
    private Integer moreAttributeId;

    @Column(name = "is_active")
    private Boolean isActive;
}
