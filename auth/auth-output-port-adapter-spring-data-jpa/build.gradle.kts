dependencies {
    implementation(project(":auth-domain"))
    implementation(project(":auth-output-port"))
    implementation(project(":auth-postgre-spring-data-jpa"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.mapstruct:mapstruct")
    implementation("org.projectlombok:lombok-mapstruct-binding")

    annotationProcessor("org.mapstruct:mapstruct-processor")
}