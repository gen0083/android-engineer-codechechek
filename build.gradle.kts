plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("androidx.navigation.safeargs") version "2.7.7" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}