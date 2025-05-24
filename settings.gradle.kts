rootProject.name = "visualmusic-backend"

// Auth Service
val authProjects = arrayOf(
    "auth-domain",
    "auth-shared",
    "auth-input-port",
    "auth-output-port",
    "auth-postgre-spring-data-jpa",
    "auth-core",
    "auth-input-port-adapter-spring-web",
    "auth-output-port-adapter-spring-data-jpa",
    "auth-security-spring",
    "auth-core-proxy-spring-transactional"
)
includeProjects("auth", *authProjects)


// Cover Service
val coverProjects = arrayOf(
    "cover-domain",
    "cover-shared",
    "cover-input-port",
    "cover-core",
    "cover-core-proxy-spring-transactional",
    "cover-input-port-adapter-spring-web",
    "cover-output-port",
    "cover-output-port-adapter-spring-data-jpa",
    "cover-postgre-spring-data-jpa",
    "cover-output-port-adapter-direct"
)
includeProjects("cover", *coverProjects)


// App
includeProjects("app", "app-spring-boot")



// Utils
fun includeProjects(rootDir: String, vararg projectPaths: String) {
    projectPaths.forEach { projectPath ->
        include(projectPath)
        project(":$projectPath").apply {
            projectDir = file("$rootDir/$projectPath")
        }
    }
}