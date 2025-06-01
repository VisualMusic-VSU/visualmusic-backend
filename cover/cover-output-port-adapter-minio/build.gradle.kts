plugins {
    id("java")
}

dependencies {
    implementation(project(":cover-domain"))
    implementation(project(":cover-shared"))
    implementation(project(":cover-output-port"))

    implementation("io.minio:minio")
}