package com.example.money.manager.controller;

import com.example.money.manager.entity.ProfileEntity;
import com.example.money.manager.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final ExcelService  excelService;
    private final IncomeService  incomeService;
    private final ExpenseService expenseService;
    private final EmailService emailService;
    private final ProfileService profileService;


    @GetMapping("/income-excel")
    public ResponseEntity<Void> emailIncomeExcel() {
        ProfileEntity profile = profileService.getCurrentProfile();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        excelService.writeIncomesToExcel(outputStream, incomeService.getCurrentMonthExpensesForCurrentUser());
        emailService.sendEmailWithAttachment(profile.getEmail(),
                "Income Excel",
                "Please find attached the income excel file.",
                outputStream.toByteArray(),
                "income.xlsx");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/expense-excel")
    public ResponseEntity<Void> emailExpenseExcel() {
        ProfileEntity profile = profileService.getCurrentProfile();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        excelService.writeExpensesToExcel(outputStream, expenseService.getCurrentMonthExpensesForCurrentUser());
        emailService.sendEmailWithAttachment(profile.getEmail(),
                "Expense Excel",
                "Please find attached the income excel file.",
                outputStream.toByteArray(),
                "income.xlsx");
        return ResponseEntity.ok().build();
    }

}
