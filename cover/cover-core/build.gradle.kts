dependencies {
    implementation(project(":cover-domain"))
    implementation(project(":cover-input-port"))
    implementation(project(":cover-output-port"))
    implementation(project(":cover-shared"))

    implementation("org.mapstruct:mapstruct")
    implementation("org.projectlombok:lombok-mapstruct-binding")

    annotationProcessor("org.mapstruct:mapstruct-processor")
}