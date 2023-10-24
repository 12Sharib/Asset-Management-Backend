package com.assetmanagement.dao;

import com.assetmanagement.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
    @Query(value = "select * from asset_allocation where asset_id=?1", nativeQuery = true)
    List<Allocation> findAllById(Integer assetId);

    @Query(value = "select * from asset_allocation where is_active=1", nativeQuery = true)
    List<Allocation> findAllActiveAllocations();

    boolean existsByAssetId(Integer assetId);
}
