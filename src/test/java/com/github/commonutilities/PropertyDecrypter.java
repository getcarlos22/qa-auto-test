package com.github.commonutilities;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

class PropertyDecrypter {

    private final StandardPBEStringEncryptor decrypter;

    PropertyDecrypter(String passwordDecrypter) {
        this.decrypter = new StandardPBEStringEncryptor();
        String password = StringUtils.isBlank(passwordDecrypter)
                // properties are expected to encrypt with default password
                // if no password provided in configuration or properties
                ? Decrypt.PASSWORD.getValue()
                : passwordDecrypter;
        this.decrypter.setPassword(password);
    }

    String decrypt(String encryptedProp) {
        return decrypter.decrypt(encryptedProp);
    }

    /*
     * Default encryptor/decrypter password
     * */
    enum Decrypt {
        PASSWORD("password1");

        private final String passwd;

        Decrypt(String passwd) {
            this.passwd = passwd;
        }

        String getValue() {
            return this.passwd;
        }
    }
}



