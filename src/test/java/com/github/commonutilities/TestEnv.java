package com.github.commonutilities;

import static com.github.commonutilities.functional.Memoize.memoizeSupplier;

import java.util.function.Supplier;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.apache.commons.lang3.StringUtils;

public class TestEnv {

    private static final Supplier<EnvironmentVariables> VARS = memoizeSupplier(TestEnv::allVars);
    private static final Supplier<PropertyDecrypter> DECRYPTER = memoizeSupplier(TestEnv::encryptor);

    public static String getProp(String prop) {
        EnvironmentSpecificConfiguration config = EnvironmentSpecificConfiguration.from(VARS.get());
        String propertyValue =  config.getPropertyValue(prop);

        // if property is Encrypted return decrypted value
        if(StringUtils.isNotBlank(propertyValue) && propertyValue.startsWith("ENC(")) {
            String encryptedProp = propertyValue.substring(4, propertyValue.length()-1);
            return DECRYPTER.get().decrypt(encryptedProp);
        }

        return propertyValue;
    }

    private static PropertyDecrypter encryptor() {
        String encryptorPassword = EnvironmentSpecificConfiguration.from(VARS.get())
                .getPropertyValue("passwordEncryptor");
        return new PropertyDecrypter(encryptorPassword);
    }

    /**
     *
     * Loading guice injector to read environment variables
     * jvm-properties (default and cli provided)
     * system environment variables (system level and cli provided)
     *
     * As this is a costly operation using a lazy loading memoize function
     * to read them at the first time when they are requested
     * and store them in a in-memory concurrent hashmap for faster access
     * and reduce the number of IO reads to just once for the entire test suite run
     *
     * */
    private static EnvironmentVariables allVars() {
        return Injectors.getInjector().getInstance(EnvironmentVariables.class);
    }

}
