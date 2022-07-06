package org.example.encryption.service;

public interface IEncryptionService {
    byte[] encrypt(byte[] plainBytes, byte[] key) throws Exception;

    byte[] decrypt(byte[] cipherBytes, byte[] key) throws Exception;
}
