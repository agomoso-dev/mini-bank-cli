package io.github.agomosodev;

import java.time.LocalDate;

public class Account {
    private String accountNumber;
    private double balance;
    private Client owner;
    private boolean state;
    private LocalDate creationDate;

    public Account() {
    }
    public Account(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.state = state;
        this.creationDate = creationDate;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Client getOwner() {
        return owner;
    }
    public void setOwner(Client owner) {
        this.owner = owner;
    }
    public boolean isState() {
        return state;
    }
    public void setState(boolean state) {
        this.state = state;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account{");
        sb.append("accountNumber=").append(accountNumber);
        sb.append(", balance=").append(balance);
        sb.append(", owner=").append(owner);
        sb.append(", state=").append(state);
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }
    
}
