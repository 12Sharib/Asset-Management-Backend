package com.assetmanagement.dao;

import com.assetmanagement.entity.ManufacturingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturingInfoRepository extends JpaRepository<ManufacturingInfo, Integer> {
}
