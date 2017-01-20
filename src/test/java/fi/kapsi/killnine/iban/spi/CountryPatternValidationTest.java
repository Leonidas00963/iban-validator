package fi.kapsi.killnine.iban.spi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fi.kapsi.killnine.iban.Iban;

public class CountryPatternValidationTest {
    
    private static final Iban UNCONFIGURED_COUNTRY_IBAN = new Iban("ZX12");
    private static final Iban CONFIGURED_COUNTRY_IBAN_INVALID = new Iban("FI12345A");
    private static final Iban CONFIGURED_COUNTRY_IBAN_VALID = CheckDigitValidationTest.VALID_FINNISH_IBAN;
    
    private final IbanValidation validation = new CountryPatternValidation();
    
    @Test
    public void doesNotValidateUnconfiguredCountry() {
        assertFalse(validation.canValidate(UNCONFIGURED_COUNTRY_IBAN));
    }
    
    @Test
    public void validatesConfiguredCountry() {
        assertTrue(validation.canValidate(CONFIGURED_COUNTRY_IBAN_INVALID));
    }
    
    @Test
    public void invalidIbanFails() {
        assertFalse(validation.isValid(CONFIGURED_COUNTRY_IBAN_INVALID));
    }
    
    @Test
    public void validIbanPasses() {
        assertTrue(validation.isValid(CONFIGURED_COUNTRY_IBAN_VALID));
    }

}
