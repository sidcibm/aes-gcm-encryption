package org.example.encryption.service;

import org.example.encryption.service.impl.EncryptionService;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class EncryptionServiceTest {
    IEncryptionService iEncryptionService = new EncryptionService();

    @Test
    public void decryptShouldBeTheInverseOfEncrypt() throws Exception {
        String plainString = "plainString";
        String key = "dRgUkXp2s5v8y/A?D(G+KbPeShVmYq3t";

        byte[] encryptedBytes = iEncryptionService.encrypt(plainString.getBytes(StandardCharsets.UTF_8), key);
        byte[] decryptedBytes = iEncryptionService.decrypt(encryptedBytes, key);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

        assertEquals(decryptedString, plainString);
    }
}
