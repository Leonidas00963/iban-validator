package io.github.jakaarl.iban.spi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.jakaarl.iban.Iban;
import io.github.jakaarl.iban.spi.CountryCodeValidation;

public class CountryCodeValidationTest {
    
    private final CountryCodeValidation validation = new CountryCodeValidation();
    
    @Test
    public void failsOnInvalidCountry() {
        Iban invalidCountryIban = new Iban("ZX12 3456");
        boolean valid = validation.isValid(invalidCountryIban);
        assertFalse(valid);
    }
    
    @Test
    public void passesOnValidCountry() {
        Iban validCountryIban = new Iban("FI12 3456");
        boolean valid = validation.isValid(validCountryIban);
        assertTrue(valid);
    }

}
