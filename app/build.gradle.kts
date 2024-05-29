plugins {
    id("com.android.application")
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "jp.co.yumemi.android.codecheck"
    compileSdk = 31

    defaultConfig {
        applicationId = "jp.co.yumemi.android.codecheck"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

kotlin {
    jvmToolchain(11)
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

    implementation(libs.coil)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTextJunit)
    androidTestImplementation(libs.androidxTestEspressoCore)
}