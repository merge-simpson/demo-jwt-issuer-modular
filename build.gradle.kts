plugins {
    java
    `java-library`
}

group = "me.letsdev"
version = "1.0-SNAPSHOT"

allprojects {
    apply {
        plugin("java")
        plugin("java-library")
    }

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }
}

subprojects {
}

dependencies {
    implementation(project(":jwt-api"))
    implementation(project(":jwt-issuer"))
}