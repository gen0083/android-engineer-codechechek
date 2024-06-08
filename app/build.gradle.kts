import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "jp.co.yumemi.android.codecheck"
    compileSdk = 34

    defaultConfig {
        applicationId = "jp.co.yumemi.android.codecheck"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "jp.co.yumemi.android.codecheck.InstrumentationTestRunner"
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
        viewBinding = true
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

    implementation(libs.androidxActivityCompose)
    implementation(libs.androidxFragmentCompose)
    implementation(libs.androidxComposeMaterial)
    implementation(libs.androidxComposeFoundation)
    implementation(libs.androidxComposeToolingPreview)
    debugImplementation(libs.androidxComposeTooling)
    androidTestImplementation(libs.androidxComposeTest)
    debugImplementation(libs.androidxComposeManifest)

    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.androidxLifecycleLivedataKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)

    implementation(libs.kotlinxCoroutineAndroid)
    implementation(libs.ktorClientAndroid)
    implementation(libs.kotlinxSerializationJson)

    implementation(libs.coilCompose)

    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koinKtor)
    implementation(libs.koinAnnotation)
    ksp(libs.koinKspCompiler)

    implementation(libs.voyagerNavigator)
    implementation(libs.voyagerScreenModel)
    implementation(libs.voyagerKoin)

    testImplementation(libs.junit)
    testImplementation(libs.kotestAssertion)
    testImplementation(libs.koinTest)
    androidTestImplementation(libs.androidxTextJunit)
    androidTestImplementation(libs.androidxTestEspressoCore)
    androidTestImplementation(libs.kotestAssertion)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.koinAndroidTest)
}
