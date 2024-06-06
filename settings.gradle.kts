pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Android Engineer CodeCheck"
include(
    ":app",
    ":kmpShare",
    ":composeWasm",
)
