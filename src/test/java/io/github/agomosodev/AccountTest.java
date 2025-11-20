package io.github.agomosodev;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AccountTest {

    @Test
    public void validIbanRecognized() {
        String iban = "GB82 WEST 1234 5698 7654 32";
        assertTrue("isValidIban should accept a known valid IBAN", Account.isValidIban(iban));

        Account acc = new Account(iban, 0.0, null, true, LocalDate.now());
        assertEquals("GB82WEST12345698765432", acc.getAccountNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorThrowsForInvalidIban() {
        new Account("INVALIDIBAN", 0.0, null, true, LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setterThrowsForInvalidIban() {
        Account acc = new Account();
        acc.setAccountNumber("XX00");
    }
}
