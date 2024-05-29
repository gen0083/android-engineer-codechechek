plugins {
    id("com.android.application") version "8.0.2" apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}