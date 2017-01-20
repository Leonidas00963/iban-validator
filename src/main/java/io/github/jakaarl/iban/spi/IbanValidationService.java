package io.github.jakaarl.iban.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import io.github.jakaarl.iban.Iban;

/**
 * A service for acquiring IBAN validations. Uses <code>ServiceLoader</code> as a simple SPI mechanism.
 */
public class IbanValidationService {

    private static final ServiceLoader<IbanValidation> VALIDATION_LOADER = ServiceLoader.load(IbanValidation.class);

    /**
     * Gets configured validations capable of validating the given IBAN.
     * 
     * @param iban      IBAN to validate.
     * 
     * @return validations for validating the IBAN.
     */
    public List<IbanValidation> getValidations(Iban iban) {
        List<IbanValidation> validations = new ArrayList<IbanValidation>();
        for (IbanValidation validation : VALIDATION_LOADER) {
            if (validation.canValidate(iban)) {
                validations.add(validation);
            }
        }
        return validations;
    }

}
