package me.letsdev.jwt.api;

import io.jsonwebtoken.security.SecureDigestAlgorithm;

import java.security.Key;

public interface SupportedSignatureAlgorithm<K1 extends Key, K2 extends Key> {
    SecureDigestAlgorithm<K1, K2> jjwtSignatureAlgorithm();
}