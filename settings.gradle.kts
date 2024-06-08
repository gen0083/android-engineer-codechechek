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
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        mavenCentral()
    }
}
rootProject.name = "Android_Engineer_CodeCheck"
include(
    ":app",
    ":kmpShare",
    ":composeWasm",
)
