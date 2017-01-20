package io.github.jakaarl.iban;

import static io.github.jakaarl.iban.util.NullCheck.requireNonNull;

import java.util.List;

import io.github.jakaarl.iban.spi.IbanValidation;
import io.github.jakaarl.iban.spi.IbanValidationService;

/**
 * Validator for IBAN account numbers.
 * 
 * @see IbanValidationService
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">IBAN on Wikipedia</a>
 */
public class IbanValidator {

    private static final IbanValidationService VALIDATION_SERVICE = new IbanValidationService();
    private final boolean requireAdditionalValidations;

    /**
     * Constructs a validator which does not require additional validations.
     */
    public IbanValidator() {
        this(false);
    }

    /**
     * Constructs a validator.
     * 
     * @param requireAdditionalValidations   whether or not to require additional validations.
     */
    public IbanValidator(boolean requireAdditionalValidations) {
        this.requireAdditionalValidations = requireAdditionalValidations;
    }

    /**
     * Validates given IBAN string. If additional validations are required, returns <code>false</code> if no
     * {@link IbanValidation} can validate the IBAN. If several validations can, <i>all</i> of them must return
     * <code>true</code> in order to pass validation.
     * 
     * @param ibanString        IBAN to validate.
     * 
     * @return <code>true</code>, if valid.
     * 
     * @throws NullPointerException     if given IBAN string is <code>null</code>.
     * @throws IllegalArgumentException if given IBAN string does not match a generic IBAN pattern.
     */
    public boolean validate(String ibanString) {
        Iban iban = new Iban(ibanString);
        return validate(iban);
    }
    
    /**
     * Validates given <code>Iban</code>. If additional validations are required, returns <code>false</code> if no
     * {@link IbanValidation} can validate the IBAN. If several validations can, <i>all</i> of them must return
     * <code>true</code> in order to pass validation.
     * 
     * @param iban      IBAN to validate.
     * 
     * @return <code>true</code>, if valid.
     * 
     * @throws NullPointerException     if given IBAN is <code>null</code>.
     */
    public boolean validate(Iban iban) {
        requireNonNull(iban);
        if (requireAdditionalValidations) {
            List<IbanValidation> validations = VALIDATION_SERVICE.getValidations(iban);
            if (validations.isEmpty()) {
                return false;
            }
            for (IbanValidation validation : validations) {
                if (!validation.isValid(iban)) {
                    return false;
                }
            }
        }
        return true;
    }

}
