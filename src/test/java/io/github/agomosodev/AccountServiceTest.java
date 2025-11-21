package io.github.agomosodev;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class AccountServiceTest {

    @Test
    public void depositIncreasesBalance() {
        Account a = new Account("GB82 WEST 1234 5698 7654 32", 100.0, null, true, LocalDate.now());
        AccountService.deposit(a, 50.0);
        assertEquals(150.0, a.getBalance(), 0.0001);
    }

    @Test
    public void withdrawReducesBalance() {
        Account a = new Account("GB82 WEST 1234 5698 7654 32", 100.0, null, true, LocalDate.now());
        AccountService.withdraw(a, 40.0);
        assertEquals(60.0, a.getBalance(), 0.0001);
    }

    @Test(expected = InsufficientFundsException.class)
    public void withdrawMoreThanBalanceThrows() {
        Account a = new Account("GB82 WEST 1234 5698 7654 32", 30.0, null, true, LocalDate.now());
        AccountService.withdraw(a, 50.0);
    }

    @Test
    public void checkingAllowsOverdraft() {
        CheckingAccount ca = new CheckingAccount("GB82 WEST 1234 5698 7654 32", 100.0, null, true, LocalDate.now(), 200.0, true);
        // withdraw more than balance but within overdraft
        AccountService.withdraw(ca, 250.0);
        // expected balance = 100 - 250 = -150
        assertEquals(-150.0, ca.getBalance(), 0.0001);
    }

    @Test(expected = InsufficientFundsException.class)
    public void checkingExceedingOverdraftThrows() {
        CheckingAccount ca = new CheckingAccount("GB82 WEST 1234 5698 7654 32", 0.0, null, true, LocalDate.now(), 100.0, true);
        AccountService.withdraw(ca, 250.0);
    }
}
