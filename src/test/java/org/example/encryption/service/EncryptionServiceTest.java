package org.example.encryption.service;

import org.example.encryption.service.impl.EncryptionService;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EncryptionServiceTest {
    IEncryptionService iEncryptionService = new EncryptionService();
    String plainString;
    byte[] key;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        byte[] plainBytes = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(plainBytes);
        plainString = new String(plainBytes, StandardCharsets.UTF_8);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        key = keyGenerator.generateKey().getEncoded();
    }

    @Test
    public void decryptShouldBeTheInverseOfEncrypt() throws GeneralSecurityException {
        byte[] encryptedBytes = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        byte[] decryptedBytes = iEncryptionService.decrypt(encryptedBytes, key);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        assertEquals(decryptedString, plainString);
    }

    @Test
    public void encryptingTwiceWithTheSameKeyShouldBeDifferent() throws GeneralSecurityException {
        byte[] encryptedBytes1 = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        byte[] encryptedBytes2 = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        assertNotEquals(encryptedBytes1, encryptedBytes2);
    }
}
