package me.letsdev.jwt.api;

import io.jsonwebtoken.security.SecureDigestAlgorithm;

import java.security.Key;

public sealed interface SupportedSignatureAlgorithm<K1 extends Key, K2 extends Key>
        permits HmacJwtSignatureAlgorithm, AsymmetricJwtSignatureAlgorithm {
    SecureDigestAlgorithm<K1, K2> jjwtSignatureAlgorithm();
}