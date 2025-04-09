plugins {
    java
    id("org.springframework.boot") version libs.versions.spring apply false
    id("io.spring.dependency-management") version libs.versions.springDependencyManagement
}

subprojects {
    group = "app.visualmusic"

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
            mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.spring}")
        }

        dependencies {
            dependencySet("org.mapstruct:${libs.versions.mapstruct}") {
                entry("mapstruct")
                entry("mapstruct-processor")
            }

            dependency("org.projectlombok:${libs.versions.lombokMapstructBinding}")
        }

    }

    dependencies {
        annotationProcessor("org.projectlombok:lombok")

        compileOnly("org.projectlombok:lombok")
    }
}
