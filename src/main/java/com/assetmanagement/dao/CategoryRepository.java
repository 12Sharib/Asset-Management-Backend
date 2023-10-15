package com.assetmanagement.dao;

import com.assetmanagement.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    Boolean existsByName(String name);
}