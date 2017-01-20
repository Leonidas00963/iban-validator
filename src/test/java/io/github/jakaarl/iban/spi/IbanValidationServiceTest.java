package io.github.jakaarl.iban.spi;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import io.github.jakaarl.iban.Iban;
import io.github.jakaarl.iban.spi.IbanValidation;
import io.github.jakaarl.iban.spi.IbanValidationService;

public class IbanValidationServiceTest {
    
    private static final Iban TEST_IBAN = new Iban("FI12"); // meets minimal pattern requirements
    private static final int NUMBER_OF_CONFIGURED_VALIDATIONS = 3; // see configuration file in test resources
    
    @Test
    public void loadsConfiguredValidations() {
        IbanValidationService service = new IbanValidationService();
        List<IbanValidation> validations = service.getValidations(TEST_IBAN);
        assertEquals(NUMBER_OF_CONFIGURED_VALIDATIONS, validations.size());
    }

}
