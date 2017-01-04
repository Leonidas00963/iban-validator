package fi.kapsi.killnine.iban;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * An IBAN.
 */
public class Iban {

    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int CHECK_DIGITS_LENGTH = 2;
    private static final int PREFIX_LENGTH = COUNTRY_CODE_LENGTH + CHECK_DIGITS_LENGTH;
    private static final Pattern IBAN_PATTERN = Pattern.compile("^[A-Za-z]{2}\\d{2}[A-Za-z0-9]{0,30}$");

    /** A two-letter country code (ISO 3166-1 alpha-2). */
    public final String countryCode;
    /** Two check digits. */
    public final String checkDigits;
    /** Basic bank account number. Format differs by country. */
    public final String accountNumber;

    private final String sourceString;

    /**
     * Constructs a new IBAN.
     * 
     * @param iban
     *            IBAN as a string.
     * 
     * @throws NullPointerException
     *             if <code>iban</code> is <code>null</code>.
     * @throws IllegalArgumentException
     *             if <code>iban</code> does not match a generic IBAN pattern.
     */
    public Iban(String iban) {
        Objects.requireNonNull(iban);
        this.sourceString = normalize(iban);
        validatePattern(sourceString);
        this.countryCode = sourceString.substring(0, CHECK_DIGITS_LENGTH);
        this.checkDigits = sourceString.substring(COUNTRY_CODE_LENGTH, PREFIX_LENGTH);
        this.accountNumber = sourceString.length() > PREFIX_LENGTH ? sourceString.substring(PREFIX_LENGTH) : "";
    }

    private static String normalize(String iban) {
        return iban.trim().replace(" ", "").toUpperCase();
    }

    private static void validatePattern(String iban) {
        if (!IBAN_PATTERN.matcher(iban).matches()) {
            throw new IllegalArgumentException("Argument does not match valid IBAN pattern");
        }
    }

    public String toString() {
        return sourceString;
    }

}
