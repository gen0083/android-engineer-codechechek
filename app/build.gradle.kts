import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "jp.co.yumemi.android.codecheck"
    compileSdk = 34

    defaultConfig {
        applicationId = "jp.co.yumemi.android.codecheck"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    sourceSets.configureEach {
        kotlin.srcDir("${layout.buildDirectory}/generated/ksp/$name/kotlin/")
    }

    testOptions {
        managedDevices {
            localDevices {
                create("pixel4api33") {
                    device = "Pixel 4"
                    apiLevel = 33
                    systemImageSource = "aosp"
                }
            }
        }
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    composeCompiler {
        enableStrongSkippingMode = true
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
    }
}

tasks.withType(Test::class.java) {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugar)

    implementation(project(":kmpShare"))
    implementation(libs.androidxCoreKtx)
    implementation(libs.googleMaterial)

    implementation(libs.koinAndroid)

    implementation(libs.androidxActivityCompose)
    androidTestImplementation(libs.androidxComposeTest)
    debugImplementation(libs.androidxComposeManifest)

    testImplementation(libs.junit)
    testImplementation(libs.kotestAssertion)
    testImplementation(libs.koinTest)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidxTextJunit)
    androidTestImplementation(libs.androidxTestEspressoCore)
    androidTestImplementation(libs.kotestAssertion)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.koinAndroidTest)
}
