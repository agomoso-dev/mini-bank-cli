package io.github.agomosodev;

import java.time.LocalDate;


public class PayrollAccount extends Account {
    private String employerName;
    private int payDay;

    public PayrollAccount(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate, String employerName, int payDay) {
        super(accountNumber, balance, owner, state, creationDate);
        if (employerName == null || employerName.isEmpty() || payDay < 1 || payDay > 31) {
            throw new IllegalArgumentException("Invalid employer or pay day.");
        }
        this.employerName = employerName;
        this.payDay = payDay;
    }

    public String getEmployerName() { return employerName; }
    public int getPayDay() { return payDay; }

    @Override
    public String toString() {
        return "PayrollAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", owner=" + getOwner() +
                ", state=" + isState() +
                ", creationDate=" + getCreationDate() +
                ", employerName='" + employerName + '\'' +
                ", payDay=" + payDay +
                '}';
    }
}
