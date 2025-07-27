package me.letsdev.jwt.api;

import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public enum AsymmetricJwtSignatureAlgorithm implements SupportedSignatureAlgorithm<PrivateKey, PublicKey> {
    RS256(SIG.RS256),
    RS384(SIG.RS384),
    RS512(SIG.RS512),
    PS256(SIG.PS256),
    PS384(SIG.PS384),
    PS512(SIG.PS512),
    ES256(SIG.ES256),
    ES384(SIG.ES384),
    ES512(SIG.ES512),
    EdDSA(SIG.EdDSA),
    ;

    private final SignatureAlgorithm algorithm;

    AsymmetricJwtSignatureAlgorithm(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public SecureDigestAlgorithm<PrivateKey, PublicKey> jjwtSignatureAlgorithm() {
        return algorithm;
    }
}