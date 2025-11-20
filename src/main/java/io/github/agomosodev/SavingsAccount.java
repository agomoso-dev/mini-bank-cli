package io.github.agomosodev;

import java.time.LocalDate;

public class SavingsAccount extends Account {
    // Attributes
    private double interestRate;

    // Constructors
    public SavingsAccount(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate, double interestRate) {
        super(accountNumber, balance, owner, state, creationDate);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public SavingsAccount(String accountNumber, Client owner, double interestRate) {
        super(accountNumber, 0.0, owner, true, LocalDate.now());
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    // Getters y Setters
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    // Methods
    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100);
        setBalance(getBalance() + interest);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", owner=" + getOwner() +
                ", state=" + isState() +
                ", creationDate=" + getCreationDate() +
                ", interestRate=" + interestRate +
                "%}";
    }
}
