package com.example.money.manager.repository;

import com.example.money.manager.entity.IncomeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<IncomeEntity,String> {

    // select * from tbl_incomes where profile_id = ?1 order by date desc
    List<IncomeEntity> findByProfileIdOrderByDateDesc(String profileId);

    // select * from tbl_incomes where profile_id order by date desc limit 5
    List<IncomeEntity> findTop5ByProfileIdOrderByDateDesc(String profileId);

    @Query("select sum (e.amount) from IncomeEntity e where e.profile.id = :profileId")
    BigDecimal findTotalIncomeByProfileId(@Param("profileId") String profileId);

    // select * from tbl_incomes where profile_id = : ?1  and date between ?2 and ?3 name like %?4%
    List<IncomeEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            String profileId,
            LocalDate startDate,
            LocalDate endDate,
            String name,
            Sort sort
    );

    // select * from tbl_incomes where profile_id = ?1 and date between ?2 and 3?
    List<IncomeEntity> findByProfileIdAndDateBetween(
            String profileId,
            LocalDate startDate,
            LocalDate endDate
    );
}
