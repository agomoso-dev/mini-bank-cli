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

        // If first arg is 'list-clients' or 'list-accounts', run listing flows
        if (args != null && args.length > 0 && "list-clients".equalsIgnoreCase(args[0])) {
            Bank bank = new Bank();
            // sample data
            Client c1 = new Client(1, "Alice", "Smith", "alice@example.com", "+34123456789", null);
            Client c2 = new Client(2, "Bob", "Jones", "bob@example.com", "+34987654321", null);
            Account a1 = new Account("ES6621000418401234567891", 100.0, null, true, LocalDate.now());
            Account a2 = new Account("ES6000491500051234567892", 50.0, null, true, LocalDate.now());
            c1.addCuenta(a1);
            c2.addCuenta(a2);
            bank.addClient(c1);
            bank.addClient(c2);

            System.out.println("--- Clients ---");
            for (Client c : bank.getClients()) {
                System.out.println(c.toString());
            }
            return;
        }

        if (args != null && args.length > 0 && "list-accounts".equalsIgnoreCase(args[0])) {
            Bank bank = new Bank();
            // sample data (same as above)
            Client c1 = new Client(1, "Alice", "Smith", "alice@example.com", "+34123456789", null);
            Client c2 = new Client(2, "Bob", "Jones", "bob@example.com", "+34987654321", null);
            Account a1 = new Account("ES6621000418401234567891", 100.0, null, true, LocalDate.now());
            Account a2 = new Account("ES6000491500051234567892", 50.0, null, true, LocalDate.now());
            c1.addCuenta(a1);
            c2.addCuenta(a2);
            bank.addClient(c1);
            bank.addClient(c2);

            System.out.println("--- Accounts ---");
            for (Account a : bank.getAllAccounts()) {
                System.out.println(a.toString());
            }
            return;
        }

        // Default: run the transfer demo (amount optional first arg)
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
