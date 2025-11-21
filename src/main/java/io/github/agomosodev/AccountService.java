package io.github.agomosodev;

public class AccountService {

    /**
     * Deposit an amount into the given account.
     * @throws IllegalArgumentException if amount <= 0
     */
    public static void deposit(Account account, double amount) {
        if (account == null) throw new IllegalArgumentException("Account cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be > 0");
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }

    /**
     * Withdraw an amount from the account. For CheckingAccount, overdraftLimit is honored.
     * Throws InsufficientFundsException if funds (and overdraft) are insufficient.
     */
    public static void withdraw(Account account, double amount) {
        if (account == null) throw new IllegalArgumentException("Account cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Withdraw amount must be > 0");

        double current = account.getBalance();
        double allowedMin = 0.0;

        if (account instanceof CheckingAccount) {
            CheckingAccount ca = (CheckingAccount) account;
            allowedMin = -ca.getOverdraftLimit();
        }

        double result = current - amount;
        if (result < allowedMin) {
            throw new InsufficientFundsException("Insufficient funds. Available: " + current + ", allowed min: " + allowedMin + ", attempted withdraw: " + amount);
        }
        account.setBalance(result);
    }

    /**
     * Transfer amount from one account to another. Operation is atomic: if deposit fails,
     * it attempts to roll back the withdrawal.
     */
    public static void transfer(Account from, Account to, double amount) {
        if (from == null || to == null) throw new IllegalArgumentException("Accounts cannot be null");
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount must be > 0");

        // withdraw first (may throw InsufficientFundsException)
        withdraw(from, amount);

        try {
            deposit(to, amount);
        } catch (RuntimeException ex) {
            // attempt rollback: put money back to source
            try {
                deposit(from, amount);
            } catch (RuntimeException rollbackEx) {
                // If rollback fails, wrap both exceptions
                throw new RuntimeException("Transfer failed and rollback also failed", rollbackEx);
            }
            throw ex;
        }
    }
}
