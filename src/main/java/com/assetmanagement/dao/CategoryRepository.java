package com.assetmanagement.dao;

import com.assetmanagement.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    Boolean existsByName(String name);

    @Query(value = "select * from categories where is_active=1",nativeQuery = true)
    List<Categories> findAllActiveCategories();
}