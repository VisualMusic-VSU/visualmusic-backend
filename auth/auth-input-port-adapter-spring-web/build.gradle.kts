plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":auth-input-port"))
    implementation(project(":auth-shared"))

    implementation("org.springframework:spring-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}