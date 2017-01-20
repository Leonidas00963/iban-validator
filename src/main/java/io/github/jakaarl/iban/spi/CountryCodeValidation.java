package io.github.jakaarl.iban.spi;

import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import io.github.jakaarl.iban.Iban;

/**
 * An {@link IbanValidation} which checks that the IBAN contains a valid country code. Here &quot;valid&quot; simply
 * means, that {@link Locale#getISOCountries()} includes the country code.
 */
public class CountryCodeValidation implements IbanValidation {

    private final SortedSet<String> isoCountries;

    public CountryCodeValidation() {
        this.isoCountries = new TreeSet<String>();
        for (String countryCode : Locale.getISOCountries()) {
            isoCountries.add(countryCode);
        }
    }

    @Override
    public boolean canValidate(Iban iban) {
        return true;
    }

    @Override
    public boolean isValid(Iban iban) {
        return isoCountries.contains(iban.countryCode);
    }

}
