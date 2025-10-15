package com.example.money.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_incomes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String icon;
    private LocalDate date;
    private BigDecimal amount;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private CategoryEntity category;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private ProfileEntity profile;

    @PrePersist
    public void init (){
        if(this.date ==null) {
            this.date = LocalDate.now();
        }
    }
}
