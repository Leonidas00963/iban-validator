package fi.kapsi.killnine.iban.spi;

import java.math.BigInteger;

import fi.kapsi.killnine.iban.Iban;

/**
 * An {@link IbanValidation} for validating IBAN check digits.
 */
public class CheckDigitValidation implements IbanValidation {

    private static final BigInteger BIG_INTEGER_97 = new BigInteger("97");
    private static final int EXPECTED_MOD_97 = 1;
    private static final int LETTER_OFFSET = -55;

    @Override
    public boolean canValidate(Iban iban) {
        return true;
    }

    @Override
    public boolean isValid(Iban iban) {
        BigInteger integerValue = toInteger(iban);
        return integerValue.mod(BIG_INTEGER_97).intValue() == EXPECTED_MOD_97;
    }

    private BigInteger toInteger(Iban iban) {
        String rearranged = iban.accountNumber + iban.countryCode + iban.checkDigits;
        return new BigInteger(replaceCharactersWithDigits(rearranged));
    }

    private String replaceCharactersWithDigits(String rearrangedIban) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rearrangedIban.length(); i++) {
            char character = rearrangedIban.charAt(i);
            if (Character.isDigit(character)) {
                sb.append(character);
            } else { // A-Z -> 10-35
                int intValue = (int) character + LETTER_OFFSET;
                sb.append(intValue);
            }
        }
        return sb.toString();
    }
}
