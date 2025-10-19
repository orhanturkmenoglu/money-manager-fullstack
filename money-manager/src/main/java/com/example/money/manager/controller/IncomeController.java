package com.example.money.manager.controller;

import com.example.money.manager.dto.ExpenseDTO;
import com.example.money.manager.dto.IncomeDTO;
import com.example.money.manager.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/latest")
    public ResponseEntity<List<IncomeDTO>> getLatest5Incomes() {
        List<IncomeDTO> latestIncomes = incomeService.getLatest5IncomesForCurrentUser();
        return ResponseEntity.ok(latestIncomes);
    }

    @GetMapping("/total")
    public ResponseEntity<BigDecimal> getTotalIncome() {
        BigDecimal total = incomeService.getTotalIncomeForCurrentUser();
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable String incomeId) {
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.noContent().build();
    }
}
