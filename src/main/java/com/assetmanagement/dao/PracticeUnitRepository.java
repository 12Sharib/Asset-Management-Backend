package com.assetmanagement.dao;

import com.assetmanagement.entity.PracticeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeUnitRepository extends JpaRepository<PracticeUnit, Integer> {
}
