import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.roborazzi)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.javaVersion.get()
            }
        }
    }

    js(IR) {
        useCommonJs()
        browser {
            commonWebpackConfig {
            }
            binaries.executable()
        }
        nodejs {
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(compose.components.resources)

            // TODO: appモジュールで参照するため一時的にapiにする
            api(libs.kotlinxDatetime)
            implementation(libs.kotlinxCoroutineCore)
            implementation(libs.ktorCore)
            implementation(libs.ktorClientLogging)
            implementation(libs.kotlinxSerializationJson)

            implementation(libs.koinCore)

            implementation(libs.coil3)
            implementation(libs.coil3Compose)
            implementation(libs.coil3Core)
            implementation(libs.coil3ComposeCore)
            implementation(libs.coil3NetworkKtor)

            // voyager
            implementation(libs.voyagerNavigator)
            implementation(libs.voyagerScreenModel)
            implementation(libs.voyagerBottomSheetNavigator)
        }
        commonTest.dependencies {
            implementation(libs.kotlinTest)
            implementation(libs.kotestAssertion)

            implementation(libs.koinTest)
            implementation(libs.kotlinxCoroutineTest)
            implementation(libs.coil3Test)
        }
        androidMain.dependencies {
            implementation(libs.kotlinxCoroutineAndroid)
            implementation(libs.ktorCio)

            implementation(libs.koinAndroid)
            implementation(libs.voyagerKoin)
            implementation(libs.coil3ComposeAndroid)
        }
        jsMain.dependencies {
            implementation(libs.ktorCoreJs)
            implementation(libs.kotlinxCoroutineCoreJs)
        }
        wasmJsMain.dependencies {
            implementation(compose.material)
            implementation(libs.ktorClientJsWasmJs)
        }
    }
}

compose.resources {
    publicResClass = true
    generateResClass = always
    packageOfResClass = "jp.co.yumemi.codecheck.resources"
}

android {
    namespace = "jp.co.yumemi.codecheck"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    }
    buildFeatures {
        compose = true
    }
    testOptions.unitTests.isIncludeAndroidResources = true

    dependencies {
        implementation(libs.androidxComposeToolingPreview)
        debugImplementation(libs.androidxComposeTooling)

        testImplementation(libs.junit)
        testImplementation(libs.kotestAssertion)
        testImplementation(libs.koinTest)
        testImplementation(libs.robolectric)
        testImplementation(libs.androidxComposeTest)
        debugImplementation(libs.androidxComposeManifest)
        testImplementation(libs.roborazziCore)
        testImplementation(libs.roborazziCompose)
        testImplementation(libs.roborazziJunitRule)
    }
}
