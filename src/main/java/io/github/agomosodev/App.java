package io.github.agomosodev;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length > 0) {
            String input = args[0];
            System.out.println("Input: " + input);
            String normalized = input == null ? null : input.replaceAll("\\s+", "").toUpperCase();
            System.out.println("Normalized: " + normalized);
            boolean valid = Account.isValidIban(input);
            System.out.println("Valid IBAN: " + valid);
            if (valid) {
                Account a = new Account(input, 0.0, null, true, java.time.LocalDate.now());
                System.out.println("Account created: " + a.getAccountNumber());
            }
            return;
        }

        // Interactive mode
        System.out.println("Hello — IBAN validator (leave blank to exit)");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter IBAN to validate: ");
            String line = sc.nextLine();
            if (line == null || line.trim().isEmpty()) break;
            String normalized = line.replaceAll("\\s+", "").toUpperCase();
            boolean valid = Account.isValidIban(line);
            System.out.println("Normalized: " + normalized);
            System.out.println("Valid IBAN: " + valid);
            if (valid) {
                try {
                    Account a = new Account(line, 0.0, null, true, java.time.LocalDate.now());
                    System.out.println("Account created: " + a.getAccountNumber());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error creating account: " + e.getMessage());
                }
            }
        }
        sc.close();
    }
}
