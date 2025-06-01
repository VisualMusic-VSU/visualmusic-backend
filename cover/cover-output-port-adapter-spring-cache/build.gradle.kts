dependencies {
    implementation(project(":cover-domain"))
    implementation(project(":cover-output-port"))

    implementation("org.springframework.boot:spring-boot-starter-cache")

    implementation("io.minio:minio")
    implementation("com.github.ben-manes.caffeine:caffeine")
}