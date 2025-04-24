package org.com.spectorassignment.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {

    public static String encrypt(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(String cipherText) {
        return new String(Base64.getDecoder().decode(cipherText), StandardCharsets.UTF_8);
    }
}
