import me.letsdev.jwt.api.AsymmetricJwtSignatureAlgorithm;
import me.letsdev.jwt.api.HmacJwtSignatureAlgorithm;
import me.letsdev.jwt.issuer.JwtIssuer;

import java.util.Base64;

public class MainApplication {
    public static void main(String[] args) {
        // HMAC 예시
        JwtIssuer hmacIssuer = new JwtIssuer(
                "uahwajqrxX5MU7BXl52ZEClgVXF4rFD/yf9SOdJhU+bWBDTOQjuSwj78e2yxfVuQMXmtMmRaM+YjvIuAb3ghcA",
                3_600L,
                HmacJwtSignatureAlgorithm.HS512
        );
        String hmacSignedJwt = hmacIssuer.issue("abc123");
        printJwt(hmacSignedJwt);

        // Ed25519 예시
        JwtIssuer edDsaIssuer = new JwtIssuer("""
                -----BEGIN PRIVATE KEY-----
                MC4CAQAwBQYDK2VwBCIEIExZ62Xae/u/s00tHgPTdwuhfAIXnvBLuijfRiKWc4xV
                -----END PRIVATE KEY-----""",
                3_600L,
                AsymmetricJwtSignatureAlgorithm.EdDSA
        );
        String asymmetricSignedJwt = edDsaIssuer.issue("abc123");
        printJwt(asymmetricSignedJwt);
    }

    private static void printJwt(String jwt) {
        String[] sections = jwt.split("\\.");

        System.out.printf("""
                
                Generated JWT
                : %s
                
                Header:
                  %s
                
                Payload:
                  %s
                
                Signature:
                  %s
                """,
                jwt,
                new String(Base64.getDecoder().decode(sections[0])),
                new String(Base64.getDecoder().decode(sections[1])),
                sections[2]
        );
    }
}
