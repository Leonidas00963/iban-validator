package fi.kapsi.killnine.iban.spi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.kapsi.killnine.iban.Iban;

public class CheckDigitValidationTest {

    private final CheckDigitValidation validation = new CheckDigitValidation();
    
    @Test
    public void failsOnInvalidCheckDigits() {
        Iban invalidIban = new Iban("XX12 3456");
        boolean valid = validation.isValid(invalidIban);
        assertFalse(valid);
    }
    
    @Test
    public void passesOnValidCheckDigits() {
        Iban validIban = new Iban("FI4250001510000023"); // valid example IBAN from Wikipedia article
        boolean valid = validation.isValid(validIban);
        assertTrue(valid);
    }
}
