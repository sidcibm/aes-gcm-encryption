package org.example.encryption.service;

import java.security.GeneralSecurityException;

public interface IEncryptionService {
    byte[] encrypt(byte[] plainBytes, byte[] key) throws GeneralSecurityException;

    byte[] decrypt(byte[] cipherBytes, byte[] key) throws GeneralSecurityException;
}
