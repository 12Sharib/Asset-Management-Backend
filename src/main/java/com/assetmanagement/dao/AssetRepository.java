package com.assetmanagement.dao;

import com.assetmanagement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    @Query(value = "select * from asset where is_active=1", nativeQuery = true)
    List<Asset> findAllActiveAssets();

    @Query(value = "select * from asset where categories_id=?1", nativeQuery = true)
    List<Asset> findByCategoryId(Integer categoryId);
}
