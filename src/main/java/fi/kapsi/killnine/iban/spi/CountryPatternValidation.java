package fi.kapsi.killnine.iban.spi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Pattern;

import fi.kapsi.killnine.iban.Iban;

/**
 * Validation for checking IBANs against country-specific patterns.
 * 
 * By default, patterns are looked up from class path resource {@value #DEFAULT_PATTERN_FILE_LOCATION}.
 * The location can be overridden with system property {@value #PATTERN_FILE_LOCATION_KEY}. The resource
 * should include {@link Properties}, with country codes as keys and regex {@link Pattern}s as values.
 */
public class CountryPatternValidation implements IbanValidation {
    
    public static final String PATTERN_FILE_LOCATION_KEY = "CountryPatternValidation.patternFile";
    public static final String DEFAULT_PATTERN_FILE_LOCATION = "/CountryPatternValidation.properties";
    
    private final Map<String, Pattern> countriesToPatterns;
    
    public CountryPatternValidation() {
        this(createCountryPatternMap(readPatternFile(determinePatternFile())));
    }
    
    private static String determinePatternFile() {
        return System.getProperty(PATTERN_FILE_LOCATION_KEY, DEFAULT_PATTERN_FILE_LOCATION);
    }
    
    private static Properties readPatternFile(String patternFileLocation) {
        InputStream inputStream = CountryPatternValidation.class.getResourceAsStream(patternFileLocation);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + patternFileLocation);
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load pattern file", e);
        }
        return properties;
    }
    
    private static Map<String, Pattern> createCountryPatternMap(Properties properties) {
        Map<String, Pattern> countriesToPatterns = new TreeMap<String, Pattern>();
        for (String propertyName : properties.stringPropertyNames()) {
            String propertyValue = properties.getProperty(propertyName);
            Pattern pattern = Pattern.compile(propertyValue);
            countriesToPatterns.put(propertyName, pattern);
        }
        return countriesToPatterns;
    }
    
    protected CountryPatternValidation(Map<String, Pattern> countriesToPatterns) {
        this.countriesToPatterns = countriesToPatterns;
    }

    @Override
    public boolean canValidate(Iban iban) {
        return countriesToPatterns.containsKey(iban.countryCode);
    }

    @Override
    public boolean isValid(Iban iban) {
        Pattern pattern = countriesToPatterns.get(iban.countryCode);
        return pattern.matcher(iban.toString()).matches();
    }

}
