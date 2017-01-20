package fi.kapsi.killnine.iban;

/**
 * A runtime exception indicating an IBAN does not meet the minimal validity requirements.
 */
public class InvalidIbanException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;
    
    public InvalidIbanException(String message) {
        super(message);
    }

}
