plugins {
    id("org.springframework.boot")
}
dependencies {
    implementation(project(":auth-domain"))
    implementation(project(":auth-output-port"))

    implementation(project(":auth-input-port-adapter-spring-web"))
    implementation(project(":cover-input-port-adapter-spring-web"))

    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("jakarta.servlet:jakarta.servlet-api")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api")

    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
}