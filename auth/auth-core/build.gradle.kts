dependencies {
    implementation(project(":auth-domain"))
    implementation(project(":auth-input-port"))
    implementation(project(":auth-output-port"))
    implementation(project(":auth-shared"))

    implementation("org.mapstruct:mapstruct")
    implementation("org.projectlombok:lombok-mapstruct-binding")

    annotationProcessor("org.mapstruct:mapstruct-processor")
}
