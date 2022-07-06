package org.example.encryption.service;

import org.example.encryption.service.impl.EncryptionService;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EncryptionServiceTest {
    IEncryptionService iEncryptionService = new EncryptionService();
    String plainString = "plainString";
    String key = "dRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t";

    @Test
    public void decryptShouldBeTheInverseOfEncrypt() throws Exception {
        byte[] encryptedBytes = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        byte[] decryptedBytes = iEncryptionService.decrypt(encryptedBytes, key);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        assertEquals(decryptedString, plainString);
    }

    @Test
    public void encryptingTwiceWithTheSameKeyShouldBeDifferent() throws Exception {
        byte[] encryptedBytes1 = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        byte[] encryptedBytes2 = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        assertNotEquals(encryptedBytes1, encryptedBytes2);
    }
}
