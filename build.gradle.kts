plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.ksp) apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}