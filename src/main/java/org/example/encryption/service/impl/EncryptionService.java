package org.example.encryption.service.impl;

import org.example.encryption.service.IEncryptionService;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class EncryptionService implements IEncryptionService {
    private static final int GCM_IV_BYTES = 12;
    private static final int GCM_TAG_BITS = 128;

    private SecretKeySpec stringToSecretKeySpec(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }

    private Cipher getCipher(int operationMode, String key, GCMParameterSpec gcmParameterSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(operationMode, stringToSecretKeySpec(key), gcmParameterSpec);
        return cipher;
    }

    public byte[] encrypt(byte[] plainBytes, String key) throws Exception {
        byte[] iv = new byte[GCM_IV_BYTES];
        SecureRandom.getInstanceStrong().nextBytes(iv);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_BITS, iv);
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);
        byte[] cipherBytes = cipher.doFinal(plainBytes);
        return ByteBuffer.allocate(GCM_IV_BYTES + cipherBytes.length).put(iv).put(cipherBytes).array();
    }

    public byte[] decrypt(byte[] cipherBytes, String key) throws Exception {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_BITS, cipherBytes, 0, GCM_IV_BYTES);
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
        return cipher.doFinal(cipherBytes, GCM_IV_BYTES, cipherBytes.length - GCM_IV_BYTES);
    }
}
