package io.github.agomosodev;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        if (args.length > 0) {
                if ("demo-savings".equalsIgnoreCase(args[0])) {
                    String iban = args.length > 1 ? args[1] : "GB82 WEST 1234 5698 7654 32";
                    System.out.println("--- SavingsAccount demo ---");
                    try {
                        SavingsAccount sa = new SavingsAccount(iban, 1000.0, null, true, java.time.LocalDate.now(), 2.0);
                        System.out.println("Before applyInterest: balance=" + sa.getBalance());
                        sa.applyInterest();
                        System.out.println("After applyInterest: balance=" + sa.getBalance());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create SavingsAccount: " + e.getMessage());
                    }
                    return;
                }
                if ("demo-checking".equalsIgnoreCase(args[0])) {
                    String iban = args.length > 1 ? args[1] : "GB82 WEST 1234 5698 7654 32";
                    System.out.println("--- CheckingAccount demo ---");
                    try {
                        CheckingAccount ca = new CheckingAccount(iban, 500.0, null, true, java.time.LocalDate.now(), 200.0, true);
                        System.out.println(ca.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create CheckingAccount: " + e.getMessage());
                    }
                    return;
                }
                if ("demo-student".equalsIgnoreCase(args[0])) {
                    String iban = args.length > 1 ? args[1] : "GB82 WEST 1234 5698 7654 32";
                    System.out.println("--- StudentAccount demo ---");
                    try {
                        StudentAccount sa = new StudentAccount(iban, 0.0, null, true, java.time.LocalDate.now(), "Example University", true);
                        System.out.println(sa.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create StudentAccount: " + e.getMessage());
                    }
                    return;
                }
                if ("demo-payroll".equalsIgnoreCase(args[0])) {
                    String iban = args.length > 1 ? args[1] : "GB82 WEST 1234 5698 7654 32";
                    System.out.println("--- PayrollAccount demo ---");
                    try {
                        PayrollAccount pa = new PayrollAccount(iban, 1000.0, null, true, java.time.LocalDate.now(), "ACME Corp", 25);
                        System.out.println(pa.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create PayrollAccount: " + e.getMessage());
                    }
                    return;
                }
                if ("demo-investment".equalsIgnoreCase(args[0])) {
                    String iban = args.length > 1 ? args[1] : "GB82 WEST 1234 5698 7654 32";
                    System.out.println("--- InvestmentAccount demo ---");
                    try {
                        InvestmentAccount ia = new InvestmentAccount(iban, 5000.0, null, true, java.time.LocalDate.now(), "Medium", 10000.0);
                        System.out.println(ia.toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create InvestmentAccount: " + e.getMessage());
                    }
                    return;
                }
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
        System.out.println("Hello — IBAN validator (commands: plain IBAN to validate, 'savings <IBAN> [interest]' to create a SavingsAccount, blank to exit)");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter command or IBAN: ");
                String line = sc.nextLine();
                if (line == null || line.trim().isEmpty()) break;
                String trimmed = line.trim();

                // support: savings <IBAN> [interest]
                if (trimmed.toLowerCase().startsWith("savings ") || trimmed.toLowerCase().startsWith("create-savings ")) {
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length < 2) {
                        System.out.println("Usage: savings <IBAN> [interestPercent]");
                        continue;
                    }
                    String iban = parts[1];
                    double interest = 1.0;
                    if (parts.length >= 3) {
                        try {
                            interest = Double.parseDouble(parts[2]);
                        } catch (NumberFormatException ex) {
                            System.out.println("Invalid interest value: " + parts[2]);
                            continue;
                        }
                    }
                    try {
                        SavingsAccount sa = new SavingsAccount(iban, 0.0, null, true, java.time.LocalDate.now(), interest);
                        System.out.println("SavingsAccount created: " + sa.getAccountNumber() + " balance=" + sa.getBalance() + " interest=" + sa.getInterestRate() + "%");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Failed to create SavingsAccount: " + ex.getMessage());
                    }
                    continue;
                }

                // create checking: checking <IBAN> <overdraftLimit> <hasDebitCard>
                if (trimmed.toLowerCase().startsWith("checking ") || trimmed.toLowerCase().startsWith("create-checking ")) {
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length < 4) {
                        System.out.println("Usage: checking <IBAN> <overdraftLimit> <hasDebitCard:true|false>");
                        continue;
                    }
                    String iban = parts[1];
                    double overdraft;
                    boolean hasDebit;
                    try {
                        overdraft = Double.parseDouble(parts[2]);
                        hasDebit = Boolean.parseBoolean(parts[3]);
                    } catch (Exception ex) {
                        System.out.println("Invalid arguments for checking: overdraft must be number, hasDebitCard true|false");
                        continue;
                    }
                    try {
                        CheckingAccount ca = new CheckingAccount(iban, 0.0, null, true, java.time.LocalDate.now(), overdraft, hasDebit);
                        System.out.println("CheckingAccount created: " + ca.getAccountNumber() + " overdraft=" + ca.getOverdraftLimit() + " hasDebit=" + ca.hasDebitCard());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Failed to create CheckingAccount: " + ex.getMessage());
                    }
                    continue;
                }

                // create student: student <IBAN> <schoolName> <canOverdraft:true|false>
                if (trimmed.toLowerCase().startsWith("student ") || trimmed.toLowerCase().startsWith("create-student ")) {
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length < 4) {
                        System.out.println("Usage: student <IBAN> <schoolName> <canOverdraft:true|false>");
                        continue;
                    }
                    String iban = parts[1];
                    String schoolName = parts[2];
                    boolean canOverdraft = Boolean.parseBoolean(parts[3]);
                    try {
                        StudentAccount sa = new StudentAccount(iban, 0.0, null, true, java.time.LocalDate.now(), schoolName, canOverdraft);
                        System.out.println("StudentAccount created: " + sa.getAccountNumber() + " school=" + sa.getSchoolName() + " canOverdraft=" + sa.canOverdraft());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Failed to create StudentAccount: " + ex.getMessage());
                    }
                    continue;
                }

                // create payroll: payroll <IBAN> <employerName> <payDay>
                if (trimmed.toLowerCase().startsWith("payroll ") || trimmed.toLowerCase().startsWith("create-payroll ")) {
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length < 4) {
                        System.out.println("Usage: payroll <IBAN> <employerName> <payDay(1-31)>");
                        continue;
                    }
                    String iban = parts[1];
                    String employer = parts[2];
                    int payDay;
                    try {
                        payDay = Integer.parseInt(parts[3]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid payDay: must be integer 1-31");
                        continue;
                    }
                    try {
                        PayrollAccount pa = new PayrollAccount(iban, 0.0, null, true, java.time.LocalDate.now(), employer, payDay);
                        System.out.println("PayrollAccount created: " + pa.getAccountNumber() + " employer=" + pa.getEmployerName() + " payDay=" + pa.getPayDay());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Failed to create PayrollAccount: " + ex.getMessage());
                    }
                    continue;
                }

                // create investment: investment <IBAN> <riskProfile> <portfolioValue>
                if (trimmed.toLowerCase().startsWith("investment ") || trimmed.toLowerCase().startsWith("create-investment ")) {
                    String[] parts = trimmed.split("\\s+");
                    if (parts.length < 4) {
                        System.out.println("Usage: investment <IBAN> <riskProfile> <portfolioValue>");
                        continue;
                    }
                    String iban = parts[1];
                    String risk = parts[2];
                    double portfolio;
                    try {
                        portfolio = Double.parseDouble(parts[3]);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid portfolio value: must be a number");
                        continue;
                    }
                    try {
                        InvestmentAccount ia = new InvestmentAccount(iban, 0.0, null, true, java.time.LocalDate.now(), risk, portfolio);
                        System.out.println("InvestmentAccount created: " + ia.getAccountNumber() + " risk=" + ia.getRiskProfile() + " value=" + ia.getPortfolioValue());
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Failed to create InvestmentAccount: " + ex.getMessage());
                    }
                    continue;
                }

                // plain IBAN validation
                String normalized = trimmed.replaceAll("\\s+", "").toUpperCase();
                boolean valid = Account.isValidIban(trimmed);
                System.out.println("Normalized: " + normalized);
                System.out.println("Valid IBAN: " + valid);
                if (valid) {
                    try {
                        Account a = new Account(trimmed, 0.0, null, true, java.time.LocalDate.now());
                        System.out.println("Account created: " + a.getAccountNumber());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error creating account: " + e.getMessage());
                    }
                }
            }
        }
    }
}
