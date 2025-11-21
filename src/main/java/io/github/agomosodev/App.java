package io.github.agomosodev;

import java.time.LocalDate;

/**
 * Simple runner to demonstrate deposit and withdrawal operations.
 * Replaces the previous interactive App and focuses on account service tests.
 */
public class App {
    public static void main(String[] args) {
        // Only provide a transfer demo from App. Previously the file contained deposit/withdraw demos;
        // we remove those and keep only demo-transfer to make tests focused.
        System.out.println("=== Transfer demo runner ===");

        // args: optional amount (default 50.0). If no args provided, still run demo with default.
        double amount = 50.0;
        if (args != null && args.length > 0) {
            try {
                amount = Double.parseDouble(args[0]);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid transfer amount '" + args[0] + "', using 50.0");
            }
        }

        System.out.println("--- Transfer demo ---");
        Account from = new Account("ES6621000418401234567891", 200.0, null, true, LocalDate.now());
        Account to = new Account("ES6000491500051234567892", 20.0, null, true, LocalDate.now());
        System.out.println("Before transfer: from=" + from.getBalance() + ", to=" + to.getBalance());
        try {
            AccountService.transfer(from, to, amount);
            System.out.println("After transfer: from=" + from.getBalance() + ", to=" + to.getBalance());
        } catch (InsufficientFundsException ex) {
            System.out.println("Transfer failed: " + ex.getMessage());
        }
        System.out.println("Transfer demo finished.");
    }
}
