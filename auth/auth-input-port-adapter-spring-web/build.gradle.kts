plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":auth-input-port"))
    implementation(project(":auth-shared"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}