dependencies {
    api(project(":jwt-api"))

    // jwt
    api("io.jsonwebtoken:jjwt-impl:0.12.6")

    // jackson
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    runtimeOnly("com.fasterxml.jackson.core:jackson-databind:2.19.2")
}