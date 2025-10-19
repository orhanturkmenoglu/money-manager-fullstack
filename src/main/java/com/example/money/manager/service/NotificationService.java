package com.example.money.manager.service;

import com.example.money.manager.dto.ExpenseDTO;
import com.example.money.manager.entity.ProfileEntity;
import com.example.money.manager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final ExpenseService expenseService;

    @Value("${money.manager.frontend.url}")
    private String frontendUrl;

    /**
     * Günlük gelir/gider hatırlatma maili (her gün 23:00'te)
     */
    @Scheduled(cron = "0 0 23 * * *", zone = "Europe/Istanbul")
    public void sendDailyIncomeExpenseReminder() {
        log.info("Job started: sendDailyIncomeExpenseReminder");

        List<ProfileEntity> profiles = profileRepository.findAll();

        for (ProfileEntity profile : profiles) {
            String body = """
                    <html>
                        <body style="font-family: Arial, sans-serif; color: #333;">
                            <p>Hi <strong>%s</strong>,</p>
                            <p>Just a friendly reminder to log your income and expenses for today in <strong>Money Manager</strong>.</p>
                            <p>Click the button below to quickly add your transactions:</p>
                            <p>
                                <a href="%s" style="
                                    display:inline-block;
                                    padding:12px 22px;
                                    font-size:15px;
                                    color:#fff;
                                    background-color:#007BFF;
                                    text-decoration:none;
                                    border-radius:6px;
                                ">Go to Money Manager</a>
                            </p>
                            <p style="margin-top: 20px;">Keep your finances up to date! 💰</p>
                        </body>
                    </html>
                    """.formatted(profile.getFullName(), frontendUrl);

            try {
                emailService.sendEmail(profile.getEmail(), "📅 Daily Income & Expense Reminder", body);
                log.info("Reminder sent to {}", profile.getEmail());
            } catch (Exception e) {
                log.error("Failed to send reminder to {}: {}", profile.getEmail(), e.getMessage());
            }
        }

        log.info("Job finished: sendDailyIncomeExpenseReminder");
    }

    /**
     * Günlük harcama özeti maili (her gün 23:30'da)
     */
    @Scheduled(cron = "0 30 23 * * *", zone = "Europe/Istanbul")
    public void sendDailyExpenseSummary() {
        log.info("Job started: sendDailyExpenseSummary");

        List<ProfileEntity> profiles = profileRepository.findAll();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        for (ProfileEntity profile : profiles) {
            List<ExpenseDTO> todaysExpenses = expenseService.getExpensesForUserOnDate(profile.getId(), LocalDate.now());

            if (!todaysExpenses.isEmpty()) {
                StringBuilder table = new StringBuilder();
                table.append("""
                        <table style='border-collapse: collapse; width: 100%; font-family: Arial, sans-serif;'>
                            <thead style='background-color: #007BFF; color: white;'>
                                <tr>
                                    <th style='border: 1px solid #ddd; padding: 8px;'>#</th>
                                    <th style='border: 1px solid #ddd; padding: 8px;'>Name</th>
                                    <th style='border: 1px solid #ddd; padding: 8px;'>Category</th>
                                    <th style='border: 1px solid #ddd; padding: 8px;'>Amount (₺)</th>
                                    <th style='border: 1px solid #ddd; padding: 8px;'>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                        """);

                int index = 1;
                for (ExpenseDTO expense : todaysExpenses) {
                    table.append(String.format("""
                            <tr style='background-color:%s;'>
                                <td style='border: 1px solid #ddd; padding: 8px;'>%d</td>
                                <td style='border: 1px solid #ddd; padding: 8px;'>%s</td>
                                <td style='border: 1px solid #ddd; padding: 8px;'>%s</td>
                                <td style='border: 1px solid #ddd; padding: 8px;'>%.2f</td>
                                <td style='border: 1px solid #ddd; padding: 8px;'>%s</td>
                            </tr>
                            """,
                            index % 2 == 0 ? "#f9f9f9" : "#ffffff",
                            index++,
                            expense.getName(),
                            expense.getCategoryName() != null ? expense.getCategoryName() : "N/A",
                            expense.getAmount(),
                            expense.getDate().format(dateFormatter)
                    ));
                }

                table.append("</tbody></table>");

                String body = """
                        <html>
                            <body style="font-family: Arial, sans-serif; color: #333;">
                                <p>Hi <strong>%s</strong>,</p>
                                <p>Here’s your summary of today’s expenses:</p>
                                %s
                                <p style="margin-top: 20px;">Keep tracking your spending and stay financially smart! 💪</p>
                            </body>
                        </html>
                        """.formatted(profile.getFullName(), table);

                try {
                    emailService.sendEmail(profile.getEmail(), "💸 Daily Expense Summary", body);
                    log.info("Expense summary sent to {}", profile.getEmail());
                } catch (Exception e) {
                    log.error("Failed to send summary to {}: {}", profile.getEmail(), e.getMessage());
                }
            }
        }

        log.info("Job finished: sendDailyExpenseSummary");
    }
}
