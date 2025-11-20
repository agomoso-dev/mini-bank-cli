package io.github.agomosodev;

import java.time.LocalDate;

public class InvestmentAccount extends Account {
        private String riskProfile;
    private double portfolioValue;

    public InvestmentAccount(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate, String riskProfile, double portfolioValue) {
        super(accountNumber, balance, owner, state, creationDate);
        if (riskProfile == null || riskProfile.isEmpty() || portfolioValue < 0) {
            throw new IllegalArgumentException("Invalid risk profile or portfolio value.");
        }
        this.riskProfile = riskProfile;
        this.portfolioValue = portfolioValue;
    }

    public String getRiskProfile() { return riskProfile; }
    public double getPortfolioValue() { return portfolioValue; }

    @Override
    public String toString() {
        return "InvestmentAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", owner=" + getOwner() +
                ", state=" + isState() +
                ", creationDate=" + getCreationDate() +
                ", riskProfile='" + riskProfile + '\'' +
                ", portfolioValue=" + portfolioValue +
                '}';
    }
}
