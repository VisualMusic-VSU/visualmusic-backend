plugins {
    java
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.7"
}

subprojects {
    group = "app.visualmusic"

    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.4")
        }

        dependencies {
            dependencySet("org.mapstruct:1.5.5.Final") {
                entry("mapstruct")
                entry("mapstruct-processor")
            }

            dependency("org.projectlombok:lombok-mapstruct-binding:0.2.0")

            dependencySet("io.jsonwebtoken:0.12.6") {
                entry("jjwt-api")
                entry("jjwt-impl")
                entry("jjwt-jackson")
            }
        }

    }

    dependencies {
        annotationProcessor("org.projectlombok:lombok")

        compileOnly("org.projectlombok:lombok")
    }
}
