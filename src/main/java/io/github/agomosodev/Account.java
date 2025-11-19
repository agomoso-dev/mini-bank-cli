package io.github.agomosodev;

import java.time.LocalDate;

public class Account {
    private String accountNumber;
    private double balance;
    private Client owner;
    private String accountType;
    private boolean state;
    private LocalDate creationDate;

    public Account() {
    }
}
