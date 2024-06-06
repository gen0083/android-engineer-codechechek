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
rootProject.name = "Android_Engineer_CodeCheck"
include(
    ":app",
    ":kmpShare",
    ":composeWasm",
)
