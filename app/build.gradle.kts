import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get().toInt())
    }
    kotlinOptions {
        jvmTarget = libs.versions.javaVersion.get()
    }
    buildFeatures {
        viewBinding = true
    }
    sourceSets.configureEach {
        kotlin.srcDir("${layout.buildDirectory}/generated/ksp/$name/kotlin/")
    }
}

tasks.withType(Test::class.java) {
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

kotlin {
    jvmToolchain(libs.versions.javaVersion.get().toInt())
    sourceSets.all {
        languageSettings.enableLanguageFeature("ExplicitBackingFields")
    }
}

dependencies {
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxAppcompat)
    implementation(libs.material)
    implementation(libs.androidxConstraintlayout)
    implementation(libs.androidxRecyclerview)

    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.androidxLifecycleLivedataKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)

    implementation(libs.androidxNavigationFragmentKtx)
    implementation(libs.androidxNavigationUiKtx)

    implementation(libs.kotlinxCoroutineAndroid)
    implementation(libs.ktorClientAndroid)
    implementation(libs.kotlinxSerializationJson)

    implementation(libs.coil)

    implementation(libs.koinCore)
    implementation(libs.koinAndroid)
    implementation(libs.koinKtor)
    implementation(libs.koinLoggerSlf4j)
    implementation(libs.koinAnnotation)
    ksp(libs.koinKspCompiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotestAssertion)
    testImplementation(libs.koinTest)
    androidTestImplementation(libs.androidxTextJunit)
    androidTestImplementation(libs.androidxTestEspressoCore)
    androidTestImplementation(libs.kotestAssertion)
    androidTestImplementation(libs.koinTest)
    androidTestImplementation(libs.koinAndroidTest)
}
