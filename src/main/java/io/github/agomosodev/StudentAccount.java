package io.github.agomosodev;

import java.time.LocalDate;

public class StudentAccount extends Account {
    private String schoolName;
    private boolean canOverdraft;

    public StudentAccount(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate, String schoolName, boolean canOverdraft) {
        super(accountNumber, balance, owner, state, creationDate);
        if (schoolName == null || schoolName.isEmpty()) {
            throw new IllegalArgumentException("School name cannot be empty.");
        }
        this.schoolName = schoolName;
        this.canOverdraft = canOverdraft;
    }

    public String getSchoolName() { return schoolName; }
    public boolean canOverdraft() { return canOverdraft; }

    @Override
    public String toString() {
        return "StudentAccount{" +
                "accountNumber=" + getAccountNumber() +
                ", balance=" + getBalance() +
                ", owner=" + getOwner() +
                ", state=" + isState() +
                ", creationDate=" + getCreationDate() +
                ", schoolName='" + schoolName + '\'' +
                ", canOverdraft=" + canOverdraft +
                '}';
    }
}
