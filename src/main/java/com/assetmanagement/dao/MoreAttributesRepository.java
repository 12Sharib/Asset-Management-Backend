package com.assetmanagement.dao;

import com.assetmanagement.entity.MoreAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoreAttributesRepository extends JpaRepository<MoreAttributes, Integer> {
}
