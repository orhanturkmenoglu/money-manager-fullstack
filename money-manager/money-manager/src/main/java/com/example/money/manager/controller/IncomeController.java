package com.example.money.manager.controller;

import com.example.money.manager.dto.ExpenseDTO;
import com.example.money.manager.dto.IncomeDTO;
import com.example.money.manager.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeService incomeService;

    // incomes : gelirler
    @PostMapping
    public ResponseEntity<IncomeDTO> addIncome(@RequestBody IncomeDTO incomeDTO) {
        return ResponseEntity.ok(incomeService.addIncome(incomeDTO));
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getExpenses (){
        List<IncomeDTO> expenses = incomeService.getCurrentMonthExpensesForCurrentUser();
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable String incomeId) {
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.noContent().build();
    }
}
