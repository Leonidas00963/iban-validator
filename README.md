# iban-validator

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6a47d943986f4a5b991cdf60351621be)](https://www.codacy.com/app/jakaarl/iban-validator?utm_source=github.com&utm_medium=referral&utm_content=jakaarl/iban-validator&utm_campaign=badger)
[![Build Status](https://travis-ci.org/jakaarl/iban-validator.svg?branch=master)](https://travis-ci.org/jakaarl/iban-validator)

A Java IBAN validator library. No runtime dependencies, requires only JDK 1.6.

## Basic usage

The central class is [IbanValidator](src/main/java/fi/kapsi/killnine/iban/IbanValidator.java). One can be instanced freely, or an instance shared safely, including across threads.

## Configuring validations

By default, the validator only performs a very basic IBAN format check. Further validations (see [IbanValidation](src/main/java/fi/kapsi/killnine/iban/spi/IbanValidation.java)) can be configured by providing an SPI configuration file in the class path. See an [example configuration](src/test/resources/META-INF/services/fi.kapsi.killnine.iban.spi.IbanValidation) and [ServiceLoader docs](http://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html). When the validator is configured to require additional validations, _all_ validations capable of validating an IBAN must pass for the IBAN to be considered valid.
