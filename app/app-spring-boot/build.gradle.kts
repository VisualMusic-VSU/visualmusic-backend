import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
}

dependencies {
    // Auth Service
    implementation(project(":auth-input-port"))
    implementation(project(":auth-input-port-adapter-spring-web"))
    implementation(project(":auth-core"))
    implementation(project(":auth-core-proxy-spring-transactional"))
    implementation(project(":auth-output-port"))
    implementation(project(":auth-output-port-adapter-spring-data-jpa"))
    implementation(project(":auth-postgre-spring-data-jpa"))
    implementation(project(":auth-security-spring"))

    // Cover Service
    implementation(project(":cover-input-port"))
    implementation(project(":cover-input-port-adapter-spring-web"))
    implementation(project(":cover-core"))
    implementation(project(":cover-core-proxy-spring-transactional"))
    implementation(project(":cover-output-port"))
    implementation(project(":cover-output-port-adapter-spring-data-jpa"))
    implementation(project(":cover-output-port-adapter-direct"))
    implementation(project(":cover-postgre-spring-data-jpa"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
}

tasks {
    named<BootJar>("bootJar") {
        layered {
            enabled = true
        }
        archiveFileName.set("visualmusic.jar")
        mainClass.set("app.visualmusic.app.spring.boot.VisualMusicApplication")
    }

    named<Jar>("jar") {
        enabled = false
    }

    named("build") {
        dependsOn("bootJar")
    }
}