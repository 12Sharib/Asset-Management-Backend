package com.assetmanagement.dao;

import com.assetmanagement.entity.CommonAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonAttributesRepository extends JpaRepository<CommonAttributes, Integer> {
}
