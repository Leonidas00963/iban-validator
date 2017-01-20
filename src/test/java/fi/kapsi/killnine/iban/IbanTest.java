package fi.kapsi.killnine.iban;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IbanTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionOnNullArgument() {
        new Iban(null);
    }
    
    @Test(expected = InvalidIbanException.class)
    public void throwsInvalidIbanExceptionOnInvalidArgument() {
        new Iban("1234");
    }
    
    @Test
    public void parsesIbanFields() {
        String countryCode = "XX";
        String checkDigits = "12";
        String accountNumber = "34";
        Iban iban = new Iban(countryCode + checkDigits + accountNumber);
        assertEquals(countryCode, iban.countryCode);
        assertEquals(checkDigits, iban.checkDigits);
        assertEquals(accountNumber, iban.accountNumber);
    }
    
    @Test
    public void normalizesIbanString() {
        String original = "xx12 3456 78 ";
        String normalized = "XX12345678";
        Iban iban = new Iban(original);
        assertEquals(normalized, iban.toString());
    }
    
}
