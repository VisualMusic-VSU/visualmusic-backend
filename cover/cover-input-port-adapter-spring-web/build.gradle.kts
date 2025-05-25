dependencies {
    implementation(project(":cover-input-port"))
    implementation(project(":cover-shared"))

    implementation("org.springframework:spring-web")
    implementation("org.springframework.security:spring-security-core")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
}