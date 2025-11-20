package io.github.agomosodev;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SavingsAccountTest {

    @Test
    public void constructorAndApplyInterest() {
        String iban = "GB82 WEST 1234 5698 7654 32";
        SavingsAccount sa = new SavingsAccount(iban, 1000.0, null, true, LocalDate.now(), 5.0);
        // IBAN should be normalized in parent
        assertEquals("GB82WEST12345698765432", sa.getAccountNumber());
        assertEquals(1000.0, sa.getBalance(), 0.0001);

        // apply 5% interest => 1000 * 0.05 = 50
        sa.applyInterest();
        assertEquals(1050.0, sa.getBalance(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeInterestInConstructorThrows() {
        new SavingsAccount("GB82 WEST 1234 5698 7654 32", 0.0, null, true, LocalDate.now(), -1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeInterestInSetterThrows() {
        SavingsAccount sa = new SavingsAccount("GB82 WEST 1234 5698 7654 32", 0.0, null, true, LocalDate.now(), 1.0);
        sa.setInterestRate(-2.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidIbanRejectedInSavingsAccount() {
        new SavingsAccount("INVALIDIBAN", 0.0, null, true, LocalDate.now(), 1.0);
    }
}
