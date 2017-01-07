package fi.kapsi.killnine.iban;

import static fi.kapsi.killnine.iban.util.NullCheck.requireNonNull;

import java.util.List;

import fi.kapsi.killnine.iban.spi.IbanValidation;
import fi.kapsi.killnine.iban.spi.IbanValidationService;

/**
 * Validator for IBAN account numbers.
 * 
 * @see <a href="https://en.wikipedia.org/wiki/International_Bank_Account_Number">IBAN on Wikipedia</a>
 */
public class IbanValidator {

    private static final IbanValidationService VALIDATION_SERVICE = new IbanValidationService();
    private final boolean requireNumberValidation;

    /**
     * Constructs a validator which does not require account number validation.
     */
    public IbanValidator() {
        this(false);
    }

    /**
     * Constructs a validator.
     * 
     * @param requireNumberValidation
     *            whether or not to require account number validation.
     */
    public IbanValidator(boolean requireNumberValidation) {
        this.requireNumberValidation = requireNumberValidation;
    }

    /**
     * Validates given IBAN string. If bank account number validation is required, returns <code>false</code> if no
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
     * Validates given <code>Iban</code>. If bank account number validation is required, returns <code>false</code> if
     * no {@link IbanValidation} can validate the IBAN. If several validations can, <i>all</i> of them must return
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
        if (requireNumberValidation) {
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
