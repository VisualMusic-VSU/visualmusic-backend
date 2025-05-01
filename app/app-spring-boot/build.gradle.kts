plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":auth-input-port"))
    implementation(project(":auth-core"))
    implementation(project(":auth-core-proxy-spring-transactional"))
    implementation(project(":auth-input-port-adapter-spring-web"))
    implementation(project(":auth-output-port"))
    implementation(project(":auth-output-port-adapter-spring-data-jpa"))
    implementation(project(":auth-postgre-spring-data-jpa"))
    implementation(project(":auth-security-spring"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.liquibase:liquibase-core")
}
