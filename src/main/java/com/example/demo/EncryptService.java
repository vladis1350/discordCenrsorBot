package com.example.demo;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    private StandardPBEStringEncryptor encryptor = null;

    public EncryptService() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("37da650Y");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    }

    public String getEncrypt(String text) {
        return encryptor.encrypt(text);
    }

    public String decryptPass(String shifr) {
        return encryptor.decrypt(shifr);
    }

}
