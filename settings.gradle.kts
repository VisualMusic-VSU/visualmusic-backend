rootProject.name = "visualmusic-backend"

include(":generation-domain")
project(":generation-domain").apply {
    projectDir = file("generation/generation-domain")
}