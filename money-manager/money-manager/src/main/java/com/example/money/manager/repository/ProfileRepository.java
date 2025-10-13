package com.example.money.manager.repository;

import com.example.money.manager.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,String> {


    // select * from tbl_profiles where email = ? ;
    Optional<ProfileEntity> findByEmail(String email);
}
