dependencies {
    implementation(project(":cover-domain"))
    implementation(project(":cover-shared"))
    implementation(project(":cover-output-port"))
    implementation(project(":cover-postgre-spring-data-jpa"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.mapstruct:mapstruct")
    implementation("org.projectlombok:lombok-mapstruct-binding")

    annotationProcessor("org.mapstruct:mapstruct-processor")
}