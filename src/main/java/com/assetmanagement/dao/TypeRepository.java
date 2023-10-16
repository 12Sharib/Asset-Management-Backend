package com.assetmanagement.dao;

import com.assetmanagement.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    @Query(value = "select * from asset_type where name=?1", nativeQuery = true)
    Boolean existByName(String name);

    @Query(value = "select * from asset_type where is_active=1", nativeQuery = true)
    List<Type> findAllActiveTypes();
}
