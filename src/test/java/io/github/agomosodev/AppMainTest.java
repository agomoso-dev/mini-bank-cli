package io.github.agomosodev;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppMainTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testIbanArgumentValid() {
        App.main(new String[]{"GB82 WEST 1234 5698 7654 32"});
        String out = outContent.toString();
        assertTrue(out.contains("Valid IBAN: true"));
        assertTrue(out.contains("Account created: GB82WEST12345698765432"));
    }

    @Test
    public void testIbanArgumentInvalid() {
        App.main(new String[]{"INVALIDIBAN"});
        String out = outContent.toString();
        assertTrue(out.contains("Valid IBAN: false"));
        assertFalse(out.contains("Account created:"));
    }

    @Test
    public void testDemoSavingsDefault() {
        App.main(new String[]{"demo-savings"});
        String out = outContent.toString();
        assertTrue(out.contains("--- SavingsAccount demo ---"));
        assertTrue(out.contains("Before applyInterest: balance=1000.0"));
        assertTrue(out.contains("After applyInterest: balance=1020.0") || out.contains("After applyInterest: balance=1020"));
    }

    @Test
    public void testDemoSavingsInvalidIban() {
        App.main(new String[]{"demo-savings", "INVALIDIBAN"});
        String out = outContent.toString();
        assertTrue(out.contains("Failed to create SavingsAccount"));
    }

    @Test
    public void testDemoTransferDefault() {
        App.main(new String[]{"demo-transfer"});
        String out = outContent.toString();
        assertTrue(out.contains("--- Transfer demo ---"));
        assertTrue(out.contains("Before transfer: from=200.0, to=20.0"));
        assertTrue(out.contains("After transfer: from=150.0, to=70.0") || out.contains("After transfer: from=150.0, to=70"));
    }

    @Test
    public void testDemoTransferInsufficient() {
        App.main(new String[]{"demo-transfer", "500.0"});
        String out = outContent.toString();
        assertTrue(out.contains("--- Transfer demo ---"));
        assertTrue(out.contains("Transfer failed:"));
    }
}
