package com.example.money.manager.repository;

import com.example.money.manager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    // select * from tbl_categories where profile_id = ?1
    List<CategoryEntity> findByProfileId(String profileId);

    // select * from tbl_categories where id = ?1 and profile_id = ?2
    Optional<CategoryEntity> findByIdAndProfileId(String id, String profileId);


    // select * from tbl_categories where type = ?1 and profile_id = 2?
    List<CategoryEntity> findByTypeAndProfileId(String type, String profileId);

    // select * from tbl_categories where name = ?1 and profile_id = ?2
    Boolean existsByNameAndProfileId(String name, String profile_id);
}
