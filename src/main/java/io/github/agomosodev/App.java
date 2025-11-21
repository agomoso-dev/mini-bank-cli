package io.github.agomosodev;

import java.time.LocalDate;

/**
 * Simple runner to demonstrate deposit and withdrawal operations.
 * Replaces the previous interactive App and focuses on account service tests.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== Deposit / Withdraw demo ===");

        // Basic account
        Account account = new Account("ES6621000418401234567891", 100.0, null, true, LocalDate.now());
        System.out.println("Initial balance (Account): " + account.getBalance());

        System.out.println("Depositing 50.0 into Account...");
        AccountService.deposit(account, 50.0);
        System.out.println("Balance after deposit: " + account.getBalance());

        System.out.println("Withdrawing 30.0 from Account...");
        AccountService.withdraw(account, 30.0);
        System.out.println("Balance after withdrawal: " + account.getBalance());

        System.out.println();
        // Checking account: supports overdraft
        CheckingAccount checking = new CheckingAccount("ES6000491500051234567892", 20.0, null, true, LocalDate.now(), 100.0, true);
        System.out.println("Initial balance (Checking): " + checking.getBalance() + ", overdraft limit=" + checking.getOverdraftLimit());

        System.out.println("Withdrawing 50.0 from Checking (within overdraft)...");
        AccountService.withdraw(checking, 50.0);
        System.out.println("Balance after withdrawal: " + checking.getBalance());

        System.out.println("Attempting to withdraw 100.0 from Checking (may exceed overdraft)...");
        try {
            AccountService.withdraw(checking, 100.0);
            System.out.println("Balance after withdrawal: " + checking.getBalance());
        } catch (InsufficientFundsException ex) {
            System.out.println("Withdrawal failed: " + ex.getMessage());
        }

        System.out.println();
        System.out.println("Demo finished.");
    }
}
