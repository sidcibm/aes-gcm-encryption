package org.example.encryption.service;

public interface IEncryptionService {
    byte[] encrypt(byte[] plainBytes, String key) throws Exception;

    byte[] decrypt(byte[] cipherBytes, String key) throws Exception;
}
