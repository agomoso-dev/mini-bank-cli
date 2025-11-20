package io.github.agomosodev;

import java.math.BigInteger;
import java.time.LocalDate;

public class Account {
    // Attributes
    private String accountNumber;
    private double balance;
    private Client owner;
    private boolean state;
    private LocalDate creationDate;

    // Constructors
    public Account() {
    }

    public Account(String accountNumber, double balance, Client owner, boolean state, LocalDate creationDate) {
        this.accountNumber = normalizeAndValidateIban(accountNumber);
        this.balance = balance;
        this.owner = owner;
        this.state = state;
        this.creationDate = creationDate;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        String normalized = accountNumber.replaceAll("\\s+", "").toUpperCase();
        if (!isValidIban(normalized)) {
            throw new IllegalArgumentException("Invalid IBAN account number: " + accountNumber);
        }
        this.accountNumber = normalized;
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

    private static String normalizeAndValidateIban(String accountNumber) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Account number cannot be null");
        }
        String normalized = accountNumber.replaceAll("\\s+", "").toUpperCase();
        if (!isValidIban(normalized)) {
            throw new IllegalArgumentException("Invalid IBAN account number: " + accountNumber);
        }
        return normalized;
    }

    // Methods
    /**
     * Validate an IBAN using the standard checksum algorithm.
     * Returns true if the IBAN is syntactically valid and checksum passes.
     */
    public static boolean isValidIban(String iban) {
        if (iban == null) return false;
        String trimmed = iban.replaceAll("\\s+", "").toUpperCase();
        // Basic length check: IBAN lengths are between 15 and 34
        if (trimmed.length() < 15 || trimmed.length() > 34) return false;
        // Must start with two letters (country) and two digits (check digits)
        if (trimmed.length() < 4) return false;
        char c0 = trimmed.charAt(0);
        char c1 = trimmed.charAt(1);
        char c2 = trimmed.charAt(2);
        char c3 = trimmed.charAt(3);
        if (!Character.isLetter(c0) || !Character.isLetter(c1)) return false;
        if (!Character.isDigit(c2) || !Character.isDigit(c3)) return false;

        String rearranged = trimmed.substring(4) + trimmed.substring(0, 4);
        StringBuilder numeric = new StringBuilder(rearranged.length() * 2);
        for (char ch : rearranged.toCharArray()) {
            if (Character.isDigit(ch)) {
                numeric.append(ch);
            } else if (Character.isLetter(ch)) {
                int val = ch - 'A' + 10;
                numeric.append(val);
            } else {
                // invalid character
                return false;
            }
        }
        try {
            BigInteger num = new BigInteger(numeric.toString());
            return num.mod(BigInteger.valueOf(97)).intValue() == 1;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
