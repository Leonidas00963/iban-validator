package io.github.jakaarl.iban.spi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.jakaarl.iban.Iban;
import io.github.jakaarl.iban.spi.CheckDigitValidation;

public class CheckDigitValidationTest {

    // valid example IBAN from Wikipedia article
    protected static final Iban VALID_FINNISH_IBAN = new Iban("FI4250001510000023");
    private final CheckDigitValidation validation = new CheckDigitValidation();
    
    @Test
    public void failsOnInvalidCheckDigits() {
        Iban invalidIban = new Iban("XX12 3456");
        boolean valid = validation.isValid(invalidIban);
        assertFalse(valid);
    }
    
    @Test
    public void passesOnValidCheckDigits() {
        Iban validIban = VALID_FINNISH_IBAN;
        boolean valid = validation.isValid(validIban);
        assertTrue(valid);
    }
}
