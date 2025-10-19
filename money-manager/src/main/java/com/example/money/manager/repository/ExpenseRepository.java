package com.example.money.manager.repository;

import com.example.money.manager.entity.ExpenseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, String> {

    // select * from tbl_expenses where profile_id = ?1 order by date desc
    List<ExpenseEntity> findByProfileIdOrderByDateDesc(String profileId);

    // select * from tbl_expenses where profile_id order by date desc limit 5
    List<ExpenseEntity> findTop5ByProfileIdOrderByDateDesc(String profileId);

    @Query("select sum (e.amount) from ExpenseEntity e where e.profile.id = :profileId")
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") String profileId);

    // select * from tbl_expenses where profile_id = : ?1  and date between ?2 and ?3 keyword like %?4%
    List<ExpenseEntity> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            String profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    // select * from tbl_expenses where profile_id = ?1 and date between ?2 and 3?
    List<ExpenseEntity> findByProfileIdAndDateBetween(
            String profileId,
            LocalDate startDate,
            LocalDate endDate
    );


    // select * from tbl_expenses where profile_id=?1 and date = ?2
    List<ExpenseEntity> findByProfileIdAndDate(String profileId,LocalDate date);
}
