import me.letsdev.jwt.api.HmacJwtSignatureAlgorithm;
import me.letsdev.jwt.issuer.JwtIssuer;

import java.util.Base64;

public class MainApplication {
    public static void main(String[] args) {
        JwtIssuer hmacIssuer = new JwtIssuer(
                "uahwajqrxX5MU7BXl52ZEClgVXF4rFD/yf9SOdJhU+bWBDTOQjuSwj78e2yxfVuQMXmtMmRaM+YjvIuAb3ghcA",
                3_600L,
                HmacJwtSignatureAlgorithm.HS512
        );

        String jwt = hmacIssuer.issue("abc123");
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
