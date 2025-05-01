rootProject.name = "visualmusic-backend"

// Auth Service
include("auth-domain")
project(":auth-domain").apply {
    projectDir = file("auth/auth-domain")
}

include("auth-input-port")
project(":auth-input-port").apply {
    projectDir = file("auth/auth-input-port")
}

include("auth-shared")
project(":auth-shared").apply {
    projectDir = file("auth/auth-shared")
}

include("auth-output-port")
project(":auth-output-port").apply {
    projectDir = file("auth/auth-output-port")
}

include("auth-postgre-spring-data-jpa")
project(":auth-postgre-spring-data-jpa").apply {
    projectDir = file("auth/auth-postgre-spring-data-jpa")
}

include("auth-core")
project(":auth-core").apply {
    projectDir = file("auth/auth-core")
}

include("auth-input-port-adapter-spring-web")
project(":auth-input-port-adapter-spring-web").apply {
    projectDir = file("auth/auth-input-port-adapter-spring-web")
}

include("auth-output-port-adapter-spring-data-jpa")
project(":auth-output-port-adapter-spring-data-jpa").apply {
    projectDir = file("auth/auth-output-port-adapter-spring-data-jpa")
}

include("auth-security-spring")
project(":auth-security-spring").apply {
    projectDir = file("auth/auth-security-spring")
}

include(":auth-core-proxy-spring-transactional")
project(":auth-core-proxy-spring-transactional").apply {
    projectDir = file("auth/auth-core-proxy-spring-transactional")
}

// App
include("app-spring-boot")
project(":app-spring-boot").apply {
    projectDir = file("app/app-spring-boot")
}