# JwtIssuer 모듈

`JwtIssuer` 모듈은 JJWT 라이브러리를 기반으로 HMAC 및 비대칭 서명 방식의 JWT(JSON Web Token)를 간편하게 발급할 수 있도록 도와주는 유틸리티입니다.

## 주요 기능
- **HMAC 서명 지원**: HS256, HS384, HS512 알고리즘을 사용한 토큰 발급
- **비대칭 서명 지원**: RSA(RS256, RS384, RS512, PS256, PS384, PS512), EC(ES256, ES384, ES512), EdDSA(Ed25519) 알고리즘 지원
- **만료 시간 설정**: 발급 시 토큰의 최대 유효 기간(초 단위) 설정
- **간편한 API**: 단일 생성자, 단일 메서드 호출만으로 JWT 헤더·페이로드 서명 및 토큰 발급

## 모듈 구조

```
demo-jwt-issuer-modular
├── jwt-api              # 공용 API (인터페이스 및 알고리즘 enum)
│   ├── src/main/java
│   │   └── me/letsdev/jwt/api
│   │       ├── SupportedSignatureAlgorithm.java      # interface
│   │       ├── HmacJwtSignatureAlgorithm.java        # enum
│   │       └── AsymmetricJwtSignatureAlgorithm.java  # enum
│   └── build.gradle.kts
│
├── jwt-issuer           # 토큰 발급 구현체
│   ├── src/main/java
│   │   └── me/letsdev/jwt/issuer
│   │       └── JwtIssuer.java
│   └── build.gradle.kts
│
└── settings.gradle.kts  # 멀티모듈 프로젝트 설정
```

## 사용 방법

1. **HMAC 방식으로 JWT 발급**

   ```java
   import me.letsdev.jwt.api.HmacJwtSignatureAlgorithm;
   import me.letsdev.jwt.issuer.JwtIssuer;

   // 비밀 키(Base64)와 최대 유효 기간(초), 알고리즘 지정
   JwtIssuer hmacIssuer = new JwtIssuer(
       "your-base64-secret-key",
       3600L,
       HmacJwtSignatureAlgorithm.HS512
   );

   // 사용자 ID로 토큰 발급
   String token = hmacIssuer.issue("user123");
   System.out.println("Generated JWT: " + token);
   ```

2. **비대칭 방식으로 JWT 발급**

   ```java
   import me.letsdev.jwt.api.AsymmetricJwtSignatureAlgorithm;
   import me.letsdev.jwt.issuer.JwtIssuer;

   // 개인키(Base64)와 최대 유효 기간, 알고리즘 지정
   JwtIssuer rsaIssuer = new JwtIssuer(
       "your-base64-encoded-private-key",
       3600L,
       AsymmetricJwtSignatureAlgorithm.RS256
   );

   String token = rsaIssuer.issue("user123");
   System.out.println("Generated JWT: " + token);
   ```

## 구성 옵션

| 파라미터             | 설명                                       |
|-------------------|------------------------------------------|
| `base64Key`       | HMAC 서명용 비밀 키를 Base64 인코딩한 문자열            |
| `base64PrivateKey`| 비대칭 서명용 개인키를 Base64 인코딩한 문자열           |
| `maxAgeInSeconds` | 토큰의 최대 유효 기간(초 단위)                         |
| `algorithm`       | 서명 알고리즘 (`HmacJwtSignatureAlgorithm` 또는 `AsymmetricJwtSignatureAlgorithm`) |

## 예시 출력

JWT

```text
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmMxMjMiLCJpYXQiOjE3NTM1OTIzMzQsImV4cCI6MTc1MzU5NTkzNH0.57_hK8u9J6ETF3bW0xmWanRVuF00uITIb51o21SdGltx4xgCNLVrP4ioxkoy2HZiAI_1abd-ujs7Q3Y8DrtVOQ
```

Header (Decoded)

```JSON
{"alg":"HS512","typ":"JWT"}
```

Payload (Decoded)

```json
{"sub":"user123","exp":1623965320}
```