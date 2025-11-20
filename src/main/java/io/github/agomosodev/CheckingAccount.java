package io.github.agomosodev;

import java.time.LocalDate;

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private boolean hasDebitCard;

    public CheckingAccount(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate, double overdraftLimit, boolean hasDebitCard) {
        super(accountNumber, balance, owner, state, creationDate);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
        this.hasDebitCard = hasDebitCard;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
    public boolean hasDebitCard() { return hasDebitCard; }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", owner=" + getOwner() +
                ", state=" + isState() +
                ", creationDate=" + getCreationDate() +
                ", overdraftLimit=" + overdraftLimit +
                ", hasDebitCard=" + hasDebitCard +
                '}';
    }
}
