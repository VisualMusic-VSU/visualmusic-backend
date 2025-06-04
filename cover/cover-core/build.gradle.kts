dependencies {
    implementation(project(":cover-domain"))
    implementation(project(":cover-input-port"))
    implementation(project(":cover-output-port"))
    implementation(project(":cover-shared"))

    implementation("org.mapstruct:mapstruct")
    implementation("org.projectlombok:lombok-mapstruct-binding")

    annotationProcessor("org.mapstruct:mapstruct-processor")


    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.assertj:assertj-core")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
tasks.test {
    useJUnitPlatform()
}