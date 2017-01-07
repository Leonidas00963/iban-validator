package fi.kapsi.killnine.iban.spi;

import java.util.ServiceLoader;

import fi.kapsi.killnine.iban.Iban;

/**
 * Interface for IBAN validations.
 * 
 * @see IbanValidationService
 * @see ServiceLoader
 */
public interface IbanValidation {

    /**
     * Checks if this validation can validate the given IBAN.
     * 
     * @param iban      IBAN to check.
     * 
     * @return  <code>true</code> if able to validate.
     */
    boolean canValidate(Iban iban);

    /**
     * Checks if given IBAN is valid.
     * 
     * @param iban      IBAN to check.
     * 
     * @return  <code>true</code> if valid.
     */
    boolean isValid(Iban iban);

}
